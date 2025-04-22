package edu.gonzaga.Cards;
// Class will essentialy just be a collection of cards for using
// When creating a deck of cards or a hand for the player
import java.util.ArrayList;


public class Collection 
{
    private ArrayList<Card> list;

    public Collection()
    {
        list = new ArrayList<Card>();
    }

    ArrayList<Card> getList()
        {return list;}

    public void addCard(Card card){
        list.add(card);
    }
    public void addCard(int index){
        
    }
    public void remCard(Card card){
        list.remove(card);
    }
    public void remCard(int index){
        list.remove(index);
    }
    public Card getCard(int index)
        {return list.get(index);}

    public Card pop()
        {return getCard(0);}

    public void print(){
        Card newCard = new Card();

        for(int i = 0; i < list.size(); ++i){
            newCard = getCard(i);
            newCard.print();
        }
        // System.out.println();
    }
}