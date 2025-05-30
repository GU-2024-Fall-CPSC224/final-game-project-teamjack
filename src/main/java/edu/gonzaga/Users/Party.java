/*
 * Zag Farkle - Gonzaga Farkle game
 * CPSC 224 Homework 3

 * Author: Kyle Choate
 * Date: 04/06/2025

 */

package edu.gonzaga.Users;

import java.util.ArrayList;

public class Party
{

    private ArrayList<Player> list = new ArrayList<Player>();
    Dealer dealer;

    /** Primary constructor
     * 
     * @param size
     * @param maxScore
     */
    public Party(int size)
    {
        for (int index = 1 ; index <= size ; index++)
        {
            Player user = new Player(index);
            user.promptName();
            add(user);
        }

        dealer = new Dealer();

    }

    /** Prints all the scores for players */
    public void displayAllScores()
    {
        for (int index = 0 ; index < size() ; index++)
            System.out.println(getPlayerName(index) + "'s balance':  " + getPlayerCurrency(index) + " ");
    }

    // getters for private attributes
    public Player getPlayer(int index)
        {return list.get(index);}
    public int getPlayerCurrency(int index)
        {return list.get(index).getCurrency();}
    public String getPlayerName(int index)
        {return list.get(index).getName();}
    public Dealer getDealer()
        {return dealer;}

    // Managing the privately stored ArrayList<Player>
    public void add(Player user)
        {list.add(user);}
    public Player get(int index)
        {return list.get(index);}
    public int size()
        {return list.size();}
}
