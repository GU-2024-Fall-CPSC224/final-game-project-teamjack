package edu.gonzaga.Users;

import edu.gonzaga.Cards.Hand;
import edu.gonzaga.Game.*;

/** A class for personal player data, including score and name
*/
public class Player 
{

    // Used to identify Player in terminal by something other than index
    private String name;

    // Score of Player. Updated after each round performed
    private int score;

    // Index of Player
    private int index; 

    // How much money they have
    public int currency;

    // Player's individual collection of cards at a given time
    Hand hand; 

    /** Sets score to zero and prompts
     *  user to set a name for Player
     */
    public Player(int num)
    {
        currency = 1000;
        score = 0;
        index = num;
        name = "Unknown Player";
        hand = new Hand();
    }


    /** Prompts user for a name
     */
    public void promptName()
    {
        System.out.println("");
        System.out.println("Player #" + index + ", what is your name?");
        name = Interface.promptUser();

        if (name.length() == 0)
            setNameToDefault();
    }

    /** Provides default names to users who do not input their own name. Goes up to 8 then starts providing indexed names */
    private void setNameToDefault()
    {
        name = "Player #" + Integer.toString(index);
        // Overrides indexed name if user is #1-8
        switch (index)
        {
            case 1:
                name = "Orion";
                break;
            case 2:
                name = "Phoenix";
                break;
            case 3:
                name = "Gemini";
                break;
            case 4:
                name = "Cygnus";
                break;
            case 5:
                name = "Hydra";
                break;
            case 6:
                name = "Carina";
                break;
            case 7:
                name = "Andromeda";
                break;
            case 8:
                name = "Aquila";
                break;
        }
        System.out.println("Your name is now: " + name);
    }


    /** name variable setter */
    public void setName(String inName) 
        {name = inName;}

    /** score variable setter */
    public void setScore(int inScore) 
        {score = inScore;}

    // currency variable setter 
    public void setCurrency(int inCurrency){
        currency = inCurrency;
    }

        
    // returns currency
    public int getCurrency(){
        return currency;
    }

    /** name variable getter 
     * @return Name of Player
    */
    public String getName() 
        {return name;}

    /** score variable getter
     * @return Score of Player
    */
    public int getScore() 
        {return score;}

    public Hand getHand() 
        {return hand;}

}
