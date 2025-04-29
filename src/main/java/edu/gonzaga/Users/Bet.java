package edu.gonzaga.Users;

import edu.gonzaga.Cards.Hand;
import edu.gonzaga.Game.Interface;

/*
 * NOTE:
 * Feel free to change and adjust anything that would make it more efficient, clean or generally better!
 * Or make any comments that suggest changes as well!
 *
 */
public class Bet{
    
    
    //Want the current player's info.
    private Player player;

    //Want current hand info
    private Hand currentHand;

    //Current Bet amount, default is set to 0
    private int betAmount;

    private static final int NUM_BET_OPTIONS = 4;
    private static final int MIN_BET = 5;

    //Default constructor
    public Bet(Player player)
    {
        this.player = player;
        this.currentHand = player.getHand();

        this.betAmount = 0;
    }


    //Method to that allows user to place a Bet, this will also check for any invalid inputs.
    public void placeBet(Player player)
    {

        if (!canBet())
        {
            System.out.println("You do not have enough money for this bet!");
            betAmount = 0;
            return;
        }

        //Getting the value of the current player's funds and
        //storing into the balanceAmount int variable.
        int balanceAmount = player.getCurrency();
       
        promptBet();

        // Once bet amount is determined, updates player's currency
        int changedBalance = balanceAmount - betAmount;
        player.setCurrency(changedBalance);

        return;
    }


    private void promptBet()
    {
        int balanceAmount = player.getCurrency();

        // Loops for player input on bet amount until valid bet amount is given
        do
        {
            displayAnyErrors(betAmount);

            displayBetOptions();

            // Prompts for action
            System.out.println("");
            char input = Interface.promptUser().charAt(0);
            int betChoice = Character.toUpperCase(input) - 65;

            // Sets bet amount
            betAmount = getBetNum(betChoice);

            // Check if player can afford bet
            if (betAmount > balanceAmount)
                betAmount = -3;

        } while (betAmount <= 0);

    }

    private int getBetNum(char index)
        {return getBetNum((int)index - 65);}

    private int getBetNum(int index)
    {
        switch (index)
        {
            case 0:
                return 5;
            case 1:
                return 10;
            case 2:
                return 100;
            case 3:
                return 500;
        }
        // If bet option does not exist, returns -1
        return -1;
    }

    private void displayAnyErrors(int betAmount)
    {
        if (betAmount == -1)
                System.out.println("Error! Invalid input");

        if (betAmount == -2)
            System.out.println("Error! Try again");

        if (betAmount == -3)
            System.out.println("Error! You do not have enough money for this bet!");
    }

    private void displayBetOptions()
    {
        // Prompts player to input an amount
        System.out.println("");
        System.out.println(player.getName() + ", please place a bet");
        for (int index = 0 ; index < NUM_BET_OPTIONS ; index++)
            System.out.println( Character.toString((char)index + 65) + ": " + Integer.toString(getBetNum(index)));

        // Displays balance
        System.out.println("");
        System.out.println("Your balance: " + player.getCurrency());
    }



    public boolean canBet()
    {
        if (player.getCurrency() < MIN_BET)
            return false;

        return true;
    }
    

    public int getBetAmount()
        {return betAmount;}

}
