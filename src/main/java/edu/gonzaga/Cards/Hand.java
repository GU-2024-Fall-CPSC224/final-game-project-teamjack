package edu.gonzaga.Cards;

public class Hand extends Collection{
    //ArrayList<Card> list = new ArrayList<Card>();

    // Need? Probably not
    public void addToHand(Card card){
        addCard(card);
    }

    public int getScore(){
        int score = 0;
        for(int i = 0; i < getSize(); ++i){
            score += getCard(i).getValue();
        }
        return score;
    }

    public boolean hasBlackJack(){
        if(getScore() == 21){
            return true;
        }
        return false;
    }

    public boolean bust(){
        if(getScore() > 21){
            return true;
        }
        return false;
    }
}
