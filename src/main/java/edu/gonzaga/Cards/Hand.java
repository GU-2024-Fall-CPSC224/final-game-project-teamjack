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
        return getScore(getSize());
    }

    /** A way to show the score based on only the first n cards revealed in a hand
     *  Used for the dealer
     * 
     * @param num
     * @return score of the first n cards found
     */
    public int getScore(int num)
    {
        int score = 0;
        for(int i = 0; i < num; ++i)
            score += getCard(i).getValue();
        
        return score;
    }

    /** Determines if hand has a blackjack
     * 
     * @return if hand is a blackjack
     */
    public boolean hasBlackJack()
    {
        return (getScore() == 21);
    }

    /** Determines if hand is a bust
     * 
     * @return If hand is a bust
     */
    public boolean bust()
    {
        return (getScore() > 21);
    }

    /** Clears the hand */
    public void clearHand()
    {
        while(getSize() > 0)
        {
            remCard(0);
        }
    }


    /** Fancy print function for listing what cards are available */
    @Override
    public void printFancy()
    {
        for(int i = 0 ; i < getSize() ; ++i)
            System.out.println("| " + getCard(i).getString());

        System.out.println("Total:\t" + getScore());
    }

    /** Prints the first n cards of the hand */
    public void printFancy(int num)
    {
        for(int i = 0 ; (i < num) && (i < getSize()) ; ++i )
            System.out.println("| " + getCard(i).getString());

        System.out.println("Total:\t" + getScore(num));
    }
}
