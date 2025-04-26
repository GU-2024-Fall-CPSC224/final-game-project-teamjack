/*
* Zag Farkle - Gonzaga Farkle game
* CPSC 224 Homework 3

* Author: Kyle Choate
* Date: 04/06/2025

*/


package edu.gonzaga.Game;

import edu.gonzaga.Users.*;
import edu.gonzaga.Cards.Deck;
import edu.gonzaga.Cards.Hand;
import java.util.ArrayList;

/** Manages a round in the game of Farkle
 */
public class Round 
{
    private Party party; // User performing their turn in round
    private int numRound;
    private Deck deck; // Deck created to use for the round
    private ArrayList<Bet> betList;


    /** Constructor function creates new round for
     *  inputted Party to play
    * 
    *  @param  inUser Player set to be playing this round
    */
    public Round(Party party, int numRound, Deck deck)
    {
        this.party = party;
        this.numRound = numRound;
        this.deck = new Deck();
        this.betList = new ArrayList<Bet>();

        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("We have entered round #" + this.numRound + "!");
    }


    /** Message displayed at end of round
     */
    public void end()
    {
        System.out.println("");
        System.out.println("The round has ended!");
        System.out.println("");

    }


    /** Performs a turn for each player
     */
    public void play()
    {

        // Players place a bet
        createPartyBets();

        // Gives dealer cards and displays one
        getDealerCards();
        displayDealerCards(1);

        // Start of player's turn after bet
        for (int index = 0 ; index < party.size() ; index++)
        {
            Turn curTurn = new Turn(party.getPlayer(index), deck);
            curTurn.play();
            curTurn.end();
            party.displayAllScores();
        }

        // BELOW NEEDS TO BE DONE
        // Dealer reveals second card
        // If below 17, then dealer hits
        // Determine whether list of players who stood win

        // Update currency

        comparePartyCards();

    }

    private void createPartyBets()
    {
        // Adds bets for each player to bestList
        for (int index = 0 ; index < party.size() ; index++)
            addPlayerBet(party.get(index));
    }

    private void addPlayerBet(Player user)
    {
        Bet bet = new Bet(user);
        bet.placeBet(user);
        betList.add(bet);
    }

    private void getDealerCards()
    {
        Hand dealerHand = party.getDealer().getHand();
        dealerHand.popDeck(deck);
        dealerHand.popDeck(deck);
    }

    private void displayDealerCards(int num)
    {
        Hand dealerHand = party.getDealer().getHand();
        
        System.out.println("Dealer's Hand:");
        for (int index = 0 ; (index < num) && (index < dealerHand.getSize()) ; index++)
            // Gets the string representation of the dealer's indexed card
            System.out.println(dealerHand.getCard(index).getString());
    }

    //win condition including aces here:
    private void comparePartyCards()
    {
        for(int index = 0; index < party.size() - 1 ; index++)
            comparePlayerCards(index);
    }

    private void comparePlayerCards(int index)
    {
        // Grabs the hand of the dealer
        Hand dealerHand = party.getDealer().getHand();
        
        // Grabs player's hand and their bet amount
        Hand playerHand = party.getPlayer(index).getHand();
        int betAmount = betList.get(index).getBetAmount();

        // Below checks if the player wins

        if(dealerHand.hasBlackJack() && !playerHand.hasBlackJack())
        {
            System.out.println("Dealer has blackjack, you lose");
            return;
        }
    
        if(playerHand.hasBlackJack())
        {
            System.out.println("Blackjack!");
            if(dealerHand.hasBlackJack())
            {
                System.out.println("Unlucky! It's a push.");
                // give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
            } 
        }
        
        else
        {
            if(playerHand.getScore() > dealerHand.getScore() && playerHand.getScore() <= 21)
            {
                //win, give user bet amount x 2
                System.out.println("Winner Winner!");
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + 2 * betAmount);
            } 
            
            else if(playerHand.getScore() == dealerHand.getScore())
            {
                //push, give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
                System.out.println("Push!");
            } 
            else 
            {
                //loss
                System.out.println("You lost...");
                return;
            }
        }
    }

}
