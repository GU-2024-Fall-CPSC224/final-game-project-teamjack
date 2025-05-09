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

import edu.gonzaga.Game.Interface;
import edu.gonzaga.Game.Round;
import edu.gonzaga.Users.Party;


/** Main program class for launching your team's program. */
public class BlackJack 
{
    private Party party;

    public static void main(String[] args) 
    {
        System.out.println("Hello Team Game");
        
        BlackJack game = new BlackJack();
        game.prepare();
        game.play();

        // Your code here. Good luck!

        // Attempting a commit and push

    }

    public BlackJack()
    {
    }

    public void prepare()
    {
        // Get size of party
        System.out.println("Welcome to Blackjack!");
        System.out.println();
        int size = promptPartySize(); 
        party = new Party(size);
    }

    public void play()
    {
        for (int index = 1 ; index < 5 ; index++)
        {
            Round curRound = new Round(party, index);
            curRound.play();
            curRound.end();
        }
    }

    private int promptPartySize()
    {
        int input = 0;
        while (input <= 0)
        {
            System.out.println("How many players would you like to play with?");
            input = Interface.promptUserInt();
            if (input > 8)
            {
                System.out.println("Error! You can only have up to 8 players");
                input = 0;
            }
            System.out.println("");
        }
        return input;
    }

}
