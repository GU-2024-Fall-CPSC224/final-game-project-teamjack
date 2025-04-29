
package edu.gonzaga.Game;

import edu.gonzaga.Users.Player;
import edu.gonzaga.Users.Dealer;
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
    private Dealer dealer;

    // Stores card stuff
    Hand hand; // Player's individual collection of cards during their turn
    Deck deck; // Overall deck used in the game

    /** Constructor function creates new turn for
     *  inputted user
     * 
     *  @param  inUser Player set to be playing this turn
     */
    public Turn(Player user, Dealer dealer, Deck deck) 
    {
        this.user = user;
        this.dealer = dealer;
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
        System.out.println("");
        System.out.println(user.getName() + " ended their turn");
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
            displayStats();

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
        
    }

    private void displayStats()
    {
        System.out.println("");
        System.out.println(user.getName() + "'s Hand: ");
        hand.printFancy();

        System.out.println("Dealer:\t" + dealer.getHand().peekHead().getValue() + " (based on the only seen card)");
        System.out.println("");
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
            String userInput = Interface.promptUser();
            actionValue = validateAction(userInput);
    
        } while (actionValue == 0);
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
            going = false;
        }
    }

    private void bustProtocol()
    {
        // **Improve bust message
        System.out.println("Bust!");
        turnScore = 0;
        going = false;
    }

    // checks if user input is one digit between 1 and 3 inclusive
    private int validateAction(String userInput)
    {
        int action = 0;
        try {
            action = Integer.parseInt(userInput);
        }
        
        catch (NumberFormatException e) {
            System.out.println("Action invalid");
            return 0;
        }

        if (hand.getSize() > 2 && action == 3)
            return 0;
        
        return action;

    }

    /** going variable getter
     * @return If the turn is still going
     */
    public Boolean isGoing()
        {return going;}

}
 