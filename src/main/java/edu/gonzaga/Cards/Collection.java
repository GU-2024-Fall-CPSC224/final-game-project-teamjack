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
    {
        Card tmp = getCard(0);
        remCard(0);
        return tmp;
    }

    public Card peekHead()
        {return getCard(0);}

    public Card peekTail()
        {return getCard(list.size() - 1);}

    public int getSize(){
        return list.size();
    }

    public void print()
    {
        for(int i = 0 ; i < getSize() ; ++i)
            System.out.println(getCard(i).getString());
    }

    public void printFancy()
    {
        for(int i = 0 ; i < getSize() ; ++i)
            System.out.println("| " + getCard(i).getString());
    }
}