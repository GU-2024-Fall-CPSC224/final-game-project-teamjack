package edu.gonzaga.Users;

import edu.gonzaga.Cards.Hand;

import java.util.Scanner;
/*
 * NOTE:
 * Feel free to change and adjust anything that would make it more efficient, clean or generally better!
 * Or make any comments that suggest changes as well!
 * Red scribbles due to every other class still a work in progress.
 */
public class Bet{
    
    //For Player's to Bet.
    
    
    //Setting the current Balance from a player for a turn.
    private Player currentPlayer = new Player();

    //Current Bet amount, default is set to 0
    private double betAmount;

    //Minimum a Player should be able to bet.
    private double minBet;

    //Default constructor
    public Bet(){
        betAmount = 0;
        minBet = 5;

    }



    //Method to that allows user to place a Bet, this will also check for any invalid inputs.
    public double placeBet(Player CurrentPlayer){

        //if statement to check if the Player's balance is valid.

        //assuming we're passing by an object, the Player Object has a getBalance method.
        if(currentPlayer.getBalance()>minBet){

            //Bet options that user can be able to set: 5, 10, 100, and 500.
            System.out.println("Place a bet!");
            System.out.println("5");
            System.out.println("10");
            System.out.println("100");
            System.out.println("500");
            Scanner input = new Scanner(System.in);

            //Bet options will be placed via a range of chars of A-D
            double betChoice = input.nextDouble();

            //this is the actual betting feature
            if(betChoice == 5){

                System.out.println("5 Works");

                //Assuming we're working with an object.
                if(currentPlayer.getBalance()>=minBet){

                    //these operations WILL BE CHANGED to actually retrieve the values.
                    double changedBalance = currentPlayer.getBalance() - minBet;
                    currentPlayer.setBalance(changedBalance);
                    betAmount = 5;
                    return betAmount;


                }

            //similarity to above
            }else if(betChoice == 10){
                //for debugging testing if it works
                System.out.println("10 Works");

                //Assuming we're working with an object. Making an if statement to check if the Player has enough for the valid Bet.
                //If not they will be given an error.
                if(currentPlayer.getBalance()>=10){
                    
                    //these operations WILL BE CHANGED to actually retrieve the values.
                    double changedBalance = currentPlayer.getBalance() - 10;
                    currentPlayer.setBalance(changedBalance);
                    betAmount = 10;
                    return betAmount;

  

                }else{
                    System.out.println("Not enough!");
                
                    break;
                }

            }else if(betChoice == 100){
                
                System.out.println("100 Works");

                if(currentPlayer.getBalance()>=100){
    
                    //these operations WILL BE CHANGED to actually retrieve the values.
                    double changedBalance = currentPlayer.getBalance() - 100;
                    currentPlayer.setBalance(changedBalance);
                    betAmount = 100;
                    return betAmount;



                }else{
                    System.out.println("Not enough!");
           
                    break;
                }
                      
            }else if(betChoice == 500){
                System.out.println("500 Works")
                
                //these operations WILL BE CHANGED to actually retrieve the values.
                if(currentPlayer.getBalance()>=500){
                    double changedBalance = currentPlayer.getBalance() - 100;
                    currentPlayer.setBalance(changedBalance);
                    betAmount = 500;
                    return betAmount;


                }
            }
        }
    }

    //method to check for win conditions
    //the Class Dealer does not exist atm.
    public void winCondition(Hand currentHand, Player currentPlayer, Dealer theDealer){

        //Adjusting the Score if a player wins
        //if a player scores more than the dealer, they win the standard way. 
        //Earnings are doubled
        if(currentHand.getScore > theDealer.getScore)
        {
            System.out.println("Winning Works");
            if(betAmount==5){

                double changedBalance = currentPlayer.getBalance() + betAmount*2;
                currentPlayer.setBalance(changedBalance)

            }else if(betAmount == 10){
                double changedBalance = currentPlayer.getBalance() + betAmount*2;
                currentPlayer.setBalance(changedBalance)

            }else if(betAmount == 100){
                double changedBalance = currentPlayer.getBalance() + betAmount*2;
                currentPlayer.setBalance(changedBalance)

            }else if(betAmount==500){
                double changedBalance = currentPlayer.getBalance() + betAmount*2;
                currentPlayer.setBalance(changedBalance)

            }
        }

        //BlackJack winnings has a different multiplier.
        if(currentHand.hasBlackJack()){
            System.out.println("BlackJack Works");
            double am = betAmount*1.5;

            //want our balance to stay as int.
            double changedBalance = currentPlayer.getBalance() + betAmount + am;
            currentPlayer.setBalance(changedBalance)

        }else if(currentHand.bust()){
            System.out.println("Losing Works");
            //None changed here since we already adjusted the Player's Balance in the placeBet method.
        }

    }
}