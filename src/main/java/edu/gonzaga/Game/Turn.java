
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

        hand = new Hand();

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
            // Actions to loop through in each turn until turn is done
            System.out.println(user.getName() + " played his Turn");
            // Takes a card from deck
            hand.popDeck(deck);
            // Displays new card
            System.out.println(user.getName() + " now has a card: " + hand.getCard(0).getString());
            going = false;

        } while (going == true);
        
    }


    /** going variable getter
     * @return If the turn is still going
     */
    public Boolean isGoing()
        {return going;}

}
 