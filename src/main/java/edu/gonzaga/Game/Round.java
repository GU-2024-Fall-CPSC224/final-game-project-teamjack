/*
 * Zag Farkle - Gonzaga Farkle game
 * CPSC 224 Homework 3

 * Author: Kyle Choate
 * Date: 04/06/2025

 */


package edu.gonzaga.Game;

import edu.gonzaga.Users.Party;
import edu.gonzaga.Cards.Deck;

/** Manages a round in the game of Farkle
 */
public class Round 
{
    private Party party; // User performing their turn in round
    private int numRound;
    private Deck deck; // Deck created to use for the round


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
        for (int index = 0 ; index < party.size() ; index++)
        {
            Turn curTurn = new Turn(party.getPlayer(index), deck);
            curTurn.play();
            curTurn.end();
            party.displayAllScores();
        }
    }

}
