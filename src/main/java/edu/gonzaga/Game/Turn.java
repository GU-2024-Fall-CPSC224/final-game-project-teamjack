
package edu.gonzaga.Game;

import edu.gonzaga.Users.Player;
import edu.gonzaga.Users.Bet;
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
        this.going = true;
        this.hand = user.getHand();
        this.turnScore = 0;

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
        
        hand.popDeck(deck);
        hand.popDeck(deck);

        do
        {
            displayHand();

            // Decide if to take another cards
            // If not take card, then stand -> Wait for everyone else to go -> Store score of top scores
            // If bust, end turn with lost money
            // If not bust, repeat step #2

            int action = promptAction();
            performAction(action);

            if ( hand.bust() == true)
                bustProtocol();
            
        } while (going == true);
        
        if ( hand.bust() == false)
            turnScore = hand.getScore();

        System.out.println(user.getName() + " played his Turn");
        end();
        
    }

    private void displayHand()
    {
        System.out.println(user.getName() + "'s Hand: ");
        // Loops through each card to print
        for (int index = 0 ; index < hand.getSize() ; index++)
            System.out.println("| " + hand.getCard(index).getString());

        System.out.println("Total: " + hand.getScore());
    }

    private int promptAction()
    {
        int actionValue = 0;
        do
        {
            System.out.println(user.getName() + ", action is to you!");
    
            //since player can only double their first action, make sure hand only has two cards
            if(hand.getSize() == 2)
                System.out.println("Type '1' to hit\n Type '2' to stand\n Type '3' to double");
            else
                System.out.println("Type '1' to hit\n Type '2' to stand");
    
            //validate input, if invalid, then the loop will repeat
            actionValue = Interface.promptUserInt();

            if (hand.getSize() > 2 && actionValue == 3)
                actionValue = -1;
    
        } while (actionValue <= 0);
        return actionValue;
    }

    private void performAction(int actionValue)
    {
        // 'hit' adds card to user's hand
        if(actionValue == 1)
        {
            System.out.println("Hit!");
            hand.popDeck(deck);
            System.out.println(hand.getCard(hand.getSize() - 1).getString());
        }

        // 'stand' ends aaction()
        if(actionValue == 2)
        {
            System.out.println("Stand! Ending action");
            going = false;
        }

        // 'double' doubles the bet and 'hits' only once, ending action
        if(actionValue == 3)
        {
            System.out.println("Double!!");
            hand.popDeck(deck);
            System.out.println(hand.getCard(hand.getSize() - 1).getString());
        }
    }

    private void bustProtocol()
    {
        System.out.println("Bust!");
        turnScore = 0;
        going = false;
    }

    //win condition including aces here:
    public void winCondition(Player user, Hand hand, int turnScore, Dealer dealer, Bet bet){
        if(dealer.getHand().hasBlackJack() && !hand.hasBlackJack()){
            System.out.println("Dealer has blackjack, you lose");
            return;
        }
        
        if(hand.hasBlackJack()){
            System.out.println("Blackjack!");
            if(dealer.getHand().hasBlackJack()){
                System.out.println("Unlucky! It's a push.");
                // give bet amount back to user
            }
        } else {
            if(hand.getScore() > dealer.getHand().getScore()){
                //win, give user bet amount x 2
                System.out.println("Winner Winner!")
            } else if(hand.getScore() == dealer.getHand().getScore()){
                //push, give bet amount back to user
                System.out.println("Push!")
            } else {
                //loss
                System.out.println("You lost...");
                return;
            }
        }
    }

    /** going variable getter
     * @return If the turn is still going
     */
    public Boolean isGoing()
        {return going;}

}
 