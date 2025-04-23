package edu.gonzaga.Game;

import edu.gonzaga.Users.*;
import java.util.Scanner;

/* Betting class for amount user bets, loses, and wins */

public class Bet{

    // Amount of available currency user has
    private int amountAvailable;

    // user constructor
    private Player user;

    /*bet function prompts user for bet amount, checking if the bet is valid and if the user has sufficient funds*/
    public int bet(int amountAvailable, Player user){
        boolean betValid = true;
        amountAvailable = user.getCurrency();

        //prompting user for bet amount
        System.out.println("Enter integer amount to bet: ");
        System.out.println("Funds available: " + amountAvailable);
        betAmountString = Interface.promptUser();

        //checking if bet valid (only integers)
        for(int i = 0; i < betAmountString.length(); i++){
            if(!Character.isDigit(betAmountString.charAt(i))){
                betValid = false;
                System.out.println("Bet invalid, please enter only integers.")
                bet(user);
            }
        }

        //converting string to integer, checking if user has enough currency
        betAmount = Integer.valueOf(Interface.promptUser());
        if(betAmount > amountAvailable){
            betValid = false;
            System.out.println("Insufficient funds!")
            bet(user);
        }

        //letting user know bet was valid, confirming amount they wish to bet
        if(betValid == true){
            System.out.println("Your bet: " + betAmount);
        }
        return betAmount;
    }

    // adds winnings to user's currency
    public void win(int betAmount, Player user){
        System.out.println("You won!");
        user.setCurrency(user.getCurrency() + betAmount);
        System.out.println("Remaining funds: " + user.getCurrency());
    }

    //subtracts winnings from user's currency
    public void lose(int betAmount, Player user){
        System.out.println("You lost...");
        user.setCurrency(user.getCurrency() - betAmount);
        System.out.println("Remaining funds: " + user.getCurrency());
    }
}
