package edu.gonzaga.Cards;

public class Hand extends Collection
{
    public Hand()
    {
        super();
    }

    // Need? Probably not
    public void popDeck(Deck deck)
    {
        addCard(deck.pop());
    }

    public int getScore()
    {
        int score = 0;
        for(int i = 0; i < getSize(); ++i)
            score += getCard(i).getValue();
        
        return score;
    }

    public boolean hasBlackJack()
    {
        return (getScore() == 21);
    }

    public boolean bust()
    {
        return (getScore() > 21);
    }

    public void clearHand(){
        for(int i = 0; i <= getSize(); i++){
            remCard(i);
        }
    }


    @Override
    public void printFancy()
    {
        for(int i = 0 ; i < getSize() ; ++i)
            System.out.println("| " + getCard(i).getString());

        System.out.println("Total:\t" + getScore());
    }
}
