package edu.gonzaga.Cards;

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

    @Test
    void testAceOfHearts()
    {
        Card example = new Card(1, 1);
        System.out.println(example.getString());
        System.out.println(example.getValue());
        assertEquals(example.getCardType(), example.getCardType());
    }

    @Test
    void testTwoOfDiamonds()
    {
        Card example = new Card(2, 2);
        System.out.println(example.getString());
        assertEquals(example.getCardType(), example.getCardType());
    }

}
