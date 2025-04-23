/*
 * Final project main driver class
 * 
 * 
 * Project Description: This is the card game blackjack. The goal of this game is to get as close to 21 as you can 
 *                      without going over. The user will play against a "dealer", if the dealer gets closer to
 *                      21 than the player, or if the player goes over 21 the dealer wins. If the player 
 *                      gets 21 using only two cards (ace and a ten), the dealer goes over 21, or the player's hand is 
 *                      higher than the dealer's hand, the player wins.
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

        party = new Party(2);
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
