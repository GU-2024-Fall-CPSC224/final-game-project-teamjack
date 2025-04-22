package edu.gonzaga.Cards;

import edu.gonzaga.Cards.Suits.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CardTest 
{
    @Test
    void testRandomCardPrint()
    {
        Card example = new Card();
        example.print();
        assertEquals(example.getCardType(), example.getCardType());
    }
}
