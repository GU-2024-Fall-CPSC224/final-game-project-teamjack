package edu.gonzaga.Game;

// Scanner library enables the ability to read inputs from the user terminal.
import java.util.Scanner;

/** Class to manage generic terminal actions from
 *  user inputs to start/end screens
 */
public class Interface 
{
    private static Scanner in = new Scanner(System.in);
    

    /** Displayed at beginning of Farkle game
     *  Credit for generating big bubble text: https://www.fancytextpro.com/BigTextGenerator/Big
     */
    public static void startScreen()
    {
        System.out.println("Welcome to BlackJack!");

    }


    /** Displayed at end. Primary function is 
     * to have a way to close the Scanner at end
     */
    public static void endScreen()
    {
        // Closes Scanner
        in.close();
        
    }


    /** Use terminal to get user to input
     *  values for other class methods
     * 
     * @return String of what user typed
     */
    public static String promptUser()
    {
        System.out.print(" > ");
        return in.nextLine();
    }


}