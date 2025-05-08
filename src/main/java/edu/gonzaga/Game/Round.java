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
    public Round(Party party, int numRound)
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

        party.displayAllScores();

        System.out.println("Press enter to continue...");
        Interface.promptUser();
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
            if (betList.get(index).getBetAmount() > 0)
                performTurn(index);

            // For the people who have
            else
                System.out.println(party.getPlayerName(index) + ", you did not bet! Your turn has been skipped");
        }

        // Dealer reveals second card
        updateDealerCards();

        comparePartyBets();

    }

    private void performTurn(int index)
    {
        // Initialize a turn for a particular player
        Turn curTurn = new Turn
        (
            party.getPlayer(index), 
            party.getDealer(),
            deck,
            betList.get(index)
        );
        // Perform and then end the turn
        curTurn.play();
        curTurn.end();
        party.displayAllScores();
    }

    private void createPartyBets()
    {
        // Adds bets for each player to bestList
        for (int index = 0 ; index < party.size() ; index++)
            addPlayerBet(party.get(index));
    }

    /** Creates a bet for the inputted user
     * 
     * @param user
     */
    private void addPlayerBet(Player user)
    {
        Bet bet = new Bet(user);
        bet.placeBet(user);
        betList.add(bet);
    }

    /** Gives dealer cards. Intended for start of the round */
    private void giveDealerCards()
    {
        Hand dealerHand = party.getDealer().getHand();
        dealerHand.popDeck(deck);
        dealerHand.popDeck(deck);
    }

    /** Reveals dealer's hand and then adds cards if the dealer needs to after everyone does their turn */
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


    /** Prints dealer's full hand */
    private void displayDealerCards()
    {
        System.out.println("Dealer's Hand:");
        party.getDealer().getHand().printFancy();
    }

    /** Prints first n cards in dealer's hand */
    private void displayDealerCards(int num)
    {        
        System.out.println("Dealer's Hand:");
        party.getDealer().getHand().printFancy(num);
        System.out.println("(Based on the first " + Integer.toString(num) + " cards revealed)");
    }


    /** Determines if dealer needs to draw */
    private boolean dealerMustDraw()
        {return (party.getDealer().getHand().getScore() < 17) ;}


    /** Performs action to add cards to dealer's hand */
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

        party.getDealer().getHand().clearHand();
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
        }


        else if(playerHand.hasBlackJack())
        {
            System.out.println(party.getPlayerName(index) + ", you got a blackjack!");
            if(dealerHand.hasBlackJack())
            {
                System.out.println(party.getPlayerName(index) + ", you are unlucky! It's a push.");
                // give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
            } 
            else
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount + (betAmount * 3 / 2) );
        }
        
        else
        {
            if(playerHand.getScore() > dealerHand.getScore() && playerHand.getScore() <= 21)
            {
                //win, give user bet amount x 2
                System.out.println(party.getPlayerName(index) + " is a winner winner chicken dinner!");
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + 2 * betAmount);
            } 
            
            else if(playerHand.getScore() == dealerHand.getScore() && playerHand.getScore() <= 21)
            {
                //push, give bet amount back to user
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + betAmount);
                System.out.println(party.getPlayerName(index) + ", you pushed!");
            }
            else if(dealerHand.getScore() > 21 && playerHand.getScore() <= 21){
                System.out.println("Dealer busts, " + party.getPlayerName(index) + " won in this round!");
                party.getPlayer(index).setCurrency(party.getPlayer(index).getCurrency() + 2 * betAmount);
            }
            else{
                //loss
                System.out.println(party.getPlayerName(index) + " lost this round... :(");
            }
        }
        playerHand.clearHand();
    }

}
