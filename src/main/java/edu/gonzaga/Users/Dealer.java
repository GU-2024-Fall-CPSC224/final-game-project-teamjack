package edu.gonzaga.Users;

import edu.gonzaga.Cards.Deck;
import edu.gonzaga.Cards.Hand;

public class Dealer {
    private String name;
    private Hand dealerhand;

    Dealer(){
        name = "Dealer";
        dealerhand = new Hand();
    }
    
    public String getName(){
        return name;
    }
    
    public void initializeDeal(Deck deck){
        dealerhand.popDeck(deck);
        dealerhand.popDeck(deck);

    }


    // Should call hit at the end of rounds, dealer will keep hitting
    // until their hand has a value greater than or equal to 17
    public void hit(Deck deck){
        while(dealerhand.getScore() < 17 || !dealerhand.bust()){
            dealerhand.popDeck(deck);
        }
    }
    // Or get hand, will only show what is in the hand at the current time
    // We will use the second card in the hand list to mimic the game but never
    // Ouput / reveal it
    public void printHand()
    {
        for(int i = 0; i < dealerhand.getSize(); ++i){
            if(i != 1){
                System.out.println(dealerhand.getCard(i));
            }
        }
    }

    public Hand getHand()
        {return dealerhand;}

    public int getScore(){
        return dealerhand.getScore();
    }

}
