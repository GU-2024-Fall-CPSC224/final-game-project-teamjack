package edu.gonzaga.Cards;
// Class will essentialy just be a collection of cards for using
// When creating a deck of cards or a hand for the player
import java.util.ArrayList;


public class Collection 
{
    private ArrayList<Card> cardCollection;

    public void addCard(Card card){
        cardCollection.add(card);
    }
    public void addCard(int index){
        
    }
    public void remCard(Card card){
        cardCollection.remove(card);
    }
    public void remCard(int index){
        cardCollection.remove(index);
    }
    public Card getCard(int index){
        return cardCollection.get(index);
    }
    public void print(){
        Card newCard = new Card();

        for(int i = 0; i < cardCollection.size(); ++i){
            newCard = getCard(i);
            newCard.print();
        }
        // System.out.println();
    }
}