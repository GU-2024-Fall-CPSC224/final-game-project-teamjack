package edu.gonzaga.Game;

import edu.gonzaga.Cards.Hand;

public class Scoresheet 
{
    public static String compare(Hand hand1, Hand hand2)
    {

        int score1 = hand1.getScore();
        int score2 = hand2.getScore();

        if (score1 > 21)
            return "Bust1";

        if (score2 > 21)
            return "Bust2";

        if (score1 == score2)
            return "Push";

        if (score2 == 21)
            return "Blackjack2";

        if (score1 == 21)
            return "Blackjack1";
        
        if (score1 < score2)
            return "Win2";

        if (score1 > score2)
            return "Win1";
    
        return "Error";
    }
}
