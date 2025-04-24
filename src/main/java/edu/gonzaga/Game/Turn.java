
package edu.gonzaga.Game;

import edu.gonzaga.Users.Player;
import edu.gonzaga.Cards.*;

/** Manages a single turn for an individual player 
 *  such as rolling, melding/banking, and hot hands
 */
public class Turn
{
    // Indicates if turn is still active
    private Boolean going; 

    // Represents user's total score during turn 
    // Not uploaded to user's private score until turn ends without farkle
    private int turnScore; 

    // User performing their turn in turn
    private Player user;

    // Stores card stuff
    Hand hand; // Player's individual collection of cards during their turn
    Deck deck; // Overall deck used in the game


    /** Constructor function creates new turn for
     *  inputted user
     * 
     *  @param  inUser Player set to be playing this turn
     */
    public Turn(Player user, Deck deck) 
    {
        this.user = user;
        this.deck = deck;
        going = true;
        hand = user.getHand();

        System.out.println("");
        System.out.println("---------------------------");
        System.out.println("");
        System.out.println("It is now " + user.getName() + "'s turn");
    }


    /** Updates user's saved score with score
     *  termined from ending turn
    */
    public void end()
    {
        // Updates user's score
        user.setScore(turnScore);

        System.out.println("");
        System.out.println(user.getName() + " ended their turn with " + user.getScore() + " points");
        System.out.println("");
    }


    /** Performs a single turn for user as
     *  they roll dice and given meld menu
    */
    public void play()
    {
        do
        {
            System.out.println(user.getName() + "'s Hand: ");
            // Get two cards and display them
            for(int i = 0; i < 2; i++){
                hand.popDeck(deck);
                System.out.println(hand.getCard(i).getString());
            }

            // Decide if to take another cards
            // If not take card, then stand -> Wait for everyone else to go -> Store score of top scores
            // If bust, end turn with lost money
            // If not bust, repeat step #2

            if(action(user, hand) == false){
                System.out.println("Bust!");
                turnScore = 0;
            } else {
                turnScore = hand.getScore();
            }
            going = false;

        } while (going == true);
        System.out.println(user.getName() + " played his Turn");
        end();
        
    }

    /* action function lets user choose what they want to do during their turn */
    public boolean action(Player user, Hand hand){
        
        int actionValue = 0;

        System.out.println(user.getName() + ", action is to you!");

        //since player can only double their first action, make sure hand only has two cards
        if(hand.getSize() == 2){
            System.out.println("Type '1' to hit\n Type '2' to stand\n Type '3' to double");
        } else {
            System.out.println("Type '1' to hit\n Type '2' to stand");
        }

        //validate input, if invalid call action() again
        String userInput = Interface.promptUser();
        actionValue = validateAction(userInput);
        if(actionValue == -1){
            return action(user, hand);
        }

        // 'hit' adds card to user's hand
        if(actionValue == 1){
            System.out.println("Hit!");
            hand.popDeck(deck);
            System.out.println(hand.getCard(hand.getSize() - 1).getString());
            if(hand.bust() == true){
                return false;
            } else {
            return action(user, hand);
            }
        }
        // 'stand' ends aaction()
        if(actionValue == 2){
            System.out.println("Stand! Ending action");
            return true;
        }
        // 'double' doubles the bet and 'hits' only once, ending action
        if(actionValue == 3){
            System.out.println("Double!!");
            hand.popDeck(deck);
            System.out.println(hand.getCard(hand.getSize() - 1).getString());
            if(hand.bust() == true){
                return false;
            }
            return true;
        }
        return true;
    }

    // checks if user input is one digit between 1 and 3 inclusive
    private int validateAction(String userInput){
        int userVal = 0;
        
        if(userInput.length() != 1){
            System.out.println("Action invalid");
            return -1;
        } else {
            for(int i = 1; i < 4; i++){
                if(userInput.contains(String.valueOf(i))){
                    userVal = Integer.valueOf(userInput);
                }
            }
            if(userVal >= 1){
                return userVal;
            } else {
                System.out.println("Action invalid");
                return -1;
            }
        }
    }


    //win condition including aces here:

    /** going variable getter
     * @return If the turn is still going
     */
    public Boolean isGoing()
        {return going;}

}
 