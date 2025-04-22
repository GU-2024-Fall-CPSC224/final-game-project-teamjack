package edu.gonzaga.Cards;

import java.util.Collections;

public class Deck extends Collection
{

    public Deck()
    {
        super();

        for (int suit = 1 ; suit < 5 ; suit++)
        {
            for (int val = 1 ; val < 14 ; val++)
            {
                Card tmp = new Card(val, suit);
                getList().add(tmp);
            }
        }

        shuffle();

    }

    public void shuffle()
    {
        Collections.shuffle(getList());
    }
    
}
