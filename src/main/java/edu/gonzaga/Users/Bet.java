package edu.gonzaga.Users;

import edu.gonzaga.Cards.Hand;
import edu.gonzaga.Game.Interface;
import edu.gonzaga.Users.Dealer;

import java.util.Scanner;

/*
 * NOTE:
 * Feel free to change and adjust anything that would make it more efficient, clean or generally better!
 * Or make any comments that suggest changes as well!
 *
 */
public class Bet{
    
    
    //Want the current player's info.
    private Player currentPlayer;

    //Want current hand info
    private Hand currentHand;

    //Current Bet amount, default is set to 0
    private int betAmount;

    //Minimum a Player should be able to bet.
    private int minBet;

    //Variable declared to store the currentPlayer's current balance
    private int balanceAmount;

    //Default constructor
    public Bet(Player currentPlayer){

        this.currentPlayer = currentPlayer;
        this.currentHand = currentPlayer.getHand();

        balanceAmount = 0;
        betAmount = 0;
        minBet = 5;

    }


    //Method to that allows user to place a Bet, this will also check for any invalid inputs.
    public int placeBet(Player currentPlayer, int balanceAmount){

        //Getting the value of the current player's funds and
        //storing into the balanceAmount int variable.
        balanceAmount = currentPlayer.getCurrency();
       

        //if statement to check if the Player's balance is valid.
        if(balanceAmount>minBet){

            //Bet options that user can be able to set: 5, 10, 100, and 500.
            System.out.println("Place a bet!");
            System.out.println("A: 5");
            System.out.println("B: 10");
            System.out.println("C: 100");
            System.out.println("D: 500");
            
            String input = Interface.promptUser();

            //Bet options will be placed via a range of chars of A-D
            char betChoice = input.charAt(0);


            //this is the actual betting feature
            if(betChoice == 'A'){

                System.out.println("5 Works");

                if(balanceAmount>=minBet){

                    //operations to change the balance of the player when they choose Bet of choice.
                    int changedBalance = balanceAmount - minBet;
                    currentPlayer.setCurrency(changedBalance);
                    betAmount = 5;

                    return betAmount;
                }

            //similarity to above
            }else if(betChoice == 'B'){

                //for debugging testing if it works
                System.out.println("10 Works");

                // Making an if statement to check if the Player has enough for the valid Bet.
                //If not they will be given an error.
                if(balanceAmount>=10){
                    
        
                    int changedBalance = balanceAmount - 10;
                    currentPlayer.setCurrency(changedBalance);
                    betAmount = 10;

                    return betAmount;

                }else{
                    System.out.println("Not enough currency!");
            
                }

            }else if(betChoice == 'C'){
                
                System.out.println("100 Works");

                if(balanceAmount>=100){
    
                    int changedBalance = balanceAmount - 100;
                    currentPlayer.setCurrency(changedBalance);
                    betAmount = 100;

                    return betAmount;

                }else{
                    System.out.println("Not enough currency!");
           
                }
                      
            }else if(betChoice == 'D'){

                //debug
                System.out.println("500 Works");
                
                if(balanceAmount>=500){

                    int changedBalance = balanceAmount - 100;
                    currentPlayer.setCurrency(changedBalance);
                    betAmount = 500;

                    return betAmount;

                }else{
                    System.out.println("Not enough currency!");
                }
            }

        }else if(balanceAmount<minBet){
            System.out.println("You might have lost it all.");
        }
        return betAmount;
    }

}


/* NOTE: THIS WILL BE MOVED TO A DIFFERENT CLASS OR NEW ONE ENTIRELY. FEEL FREE TO ADJUST THIS OR REMAKE IT ENTIRELY.

    //method to check for win conditions
    //the Class Dealer does not exist atm.
    public void winCondition(Hand currentHand, Player currentPlayer, Dealer dealer){

        //Adjusting the Score if a player wins
        //if a player scores more than the dealer, they win the standard way. 
        //Earnings are doubled

        /* Since we dont have a dealer class yet.
        if(currentHand.getScore() > dealer.getScore())
        {
            System.out.println("Winning Works");
            if(betAmount == 5){

                int changedBalance = currentPlayer.getCurrency() + betAmount*2;
                currentPlayer.setCurrency(changedBalance);

            }else if(betAmount == 10){
                int changedBalance = currentPlayer.getCurrency() + betAmount*2;
                currentPlayer.setCurrency(changedBalance);

            }else if(betAmount == 100){
                int changedBalance = currentPlayer.getCurrency() + betAmount*2;
                currentPlayer.setCurrency(changedBalance);

            }else if(betAmount == 500){
                int changedBalance = currentPlayer.getCurrency() + betAmount*2;
                currentPlayer.setCurrency(changedBalance);

            }
        }
            */

        //BlackJack winnings has a different multiplier.
        /* 
        if(currentHand.hasBlackJack()){
            System.out.println("BlackJack Works");

            //want our balance to stay as int.
            int changedBalance = currentPlayer.getCurrency() + betAmount + (int)(betAmount*1.5);
            currentPlayer.setCurrency(changedBalance);

        }else if(currentHand.bust()){
            System.out.println("Losing Works");
            //Nothing changed here since we already adjusted the Player's Balance in the placeBet method.
        }

    }
        */
        
   
