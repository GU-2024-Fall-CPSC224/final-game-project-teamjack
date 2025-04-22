/*
 * Final project main driver class
 * 
 * 
 * Project Description:
 * 
 * 
 * Contributors:
 * 
 * 
 * Copyright: 2023
 */
package edu.gonzaga;

import edu.gonzaga.Game.Round;
import edu.gonzaga.Users.Party;
import edu.gonzaga.Cards.Deck;


/** Main program class for launching your team's program. */
public class BlackJack 
{
    private Party party;
    private Deck deck;

    public static void main(String[] args) 
    {
        System.out.println("Hello Team Game");
        
        BlackJack game = new BlackJack();
        game.prepare();
        game.play();

        // Your code here. Good luck!

        // Attempting a commit and push

    }

    public void prepare()
    {

        party = new Party(1);
        party.displayAllScores();
    }

    public void play()
    {
        for (int index = 0 ; index < party.size() ; index++)
        {
            Round curRound = new Round(party, index, deck);
            curRound.play();
        }
    }

}
