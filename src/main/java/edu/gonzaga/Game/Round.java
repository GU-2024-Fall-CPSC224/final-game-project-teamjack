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
        giveDealerCards();
        displayDealerCards(1);

        // Start of player's turn after bet
        for (int index = 0 ; index < party.size() ; index++)
        {
            // Initialize a turn for a particular player
            Turn curTurn = new Turn
            (
                party.getPlayer(index), 
                party.getDealer(),
                deck
            );
            // Perform and then end the turn
            curTurn.play();
            curTurn.end();
            party.displayAllScores();
        }

        // Dealer reveals second card
        updateDealerCards();

        comparePartyBets();

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

    private void giveDealerCards()
    {
        Hand dealerHand = party.getDealer().getHand();
        dealerHand.popDeck(deck);
        dealerHand.popDeck(deck);
    }

    private void updateDealerCards()
    {

        System.out.println("");
        System.out.println("The dealer's full hull hand shall now be shown");
        displayDealerCards();

        if (dealerMustDraw())
            dealerDraw();

        System.out.println("");
        System.out.println("Dealer's Score: " + party.getDealer().getHand().getScore());
    }



    private void displayDealerCards()
        {displayDealerCards(party.getDealer().getHand().getSize());}

    private void displayDealerCards(int num)
    {
        Hand dealerHand = party.getDealer().getHand();
        
        System.out.println("Dealer's Hand:");
        dealerHand.printFancy();
    }



    private boolean dealerMustDraw()
        {return (party.getDealer().getHand().getScore() < 17) ;}


    
    private void dealerDraw()
    {
        Hand dealerHand = party.getDealer().getHand();

        while (dealerHand.getScore() < 17)
        {
            System.out.println("The dealer is drawing a card...");
            dealerHand.popDeck(deck);
            System.out.println("The card is a " + dealerHand.peekTail().getString());
        }

        System.out.println("");
        System.out.println("The dealer's hand has been updated!");
        displayDealerCards();
        System.out.println("");
    }



    //win condition including aces here:
    private void comparePartyBets()
    {
        System.out.println("Now we will see who won their bets...");
        for(int index = 0; index < party.size() ; index++)
            comparePlayerBets(index);
    }

    private void comparePlayerBets(int index)
    {
        // Grabs the hand of the dealer
        Hand dealerHand = party.getDealer().getHand();
        
        // Grabs player's hand and their bet amount
        Hand playerHand = party.getPlayer(index).getHand();
        int betAmount = betList.get(index).getBetAmount();

        System.out.println("");
        // Below checks if the player wins

        if(dealerHand.hasBlackJack() && !playerHand.hasBlackJack())
        {
            System.out.println("Dealer has blackjack, you lose");
            dealerHand.clearHand();
            playerHand.clearHand();
            return;
        }

        if(dealerHand.getScore() > 21 && playerHand.getScore() <= 21){
            System.out.println("Dealer busts, you win!");
            party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + 2 * betAmount);
            dealerHand.clearHand();
            playerHand.clearHand();
        }

        if(playerHand.hasBlackJack())
        {
            System.out.println("Blackjack!");
            if(dealerHand.hasBlackJack())
            {
                System.out.println("Unlucky! It's a push.");
                // give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
                dealerHand.clearHand();
                playerHand.clearHand();
            } 
        }
        
        else
        {
            if(playerHand.getScore() > dealerHand.getScore() && playerHand.getScore() <= 21)
            {
                //win, give user bet amount x 2
                System.out.println("Winner Winner!");
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + 2 * betAmount);
                dealerHand.clearHand();
                playerHand.clearHand();
            } 
            
            else if(playerHand.getScore() == dealerHand.getScore() && playerHand.getScore() <= 21)
            {
                //push, give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
                System.out.println("Push!");
                dealerHand.clearHand();
                playerHand.clearHand();
            } 
            else 
            {
                //loss
                System.out.println("You lost... :(");
                dealerHand.clearHand();
                playerHand.clearHand();
                return;
            }
        }
    }

}
