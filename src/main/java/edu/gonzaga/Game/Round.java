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
    private Hand dealerHand;
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

        // Dealer reveals second card
        // If below 17, then dealer hits
        // Determine whether list of players who stood win

        // Update currency

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
        dealerHand.popDeck(deck);
        dealerHand.popDeck(deck);
    }

    private void displayDealerCards(int num)
    {
        System.out.println("Dealer's Hand:");
        for (int index = 0 ; (index < num) && (index < dealerHand.getSize()) ; index++)
            // Gets the string representation of the dealer's indexed card
            System.out.println(dealerHand.getCard(index).getString());
    }

}
