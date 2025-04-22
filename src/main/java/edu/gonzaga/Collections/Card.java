package edu.gonzaga.Collections;


public class Card{
    private Suit suit;      
    private int value;      // Hold the value of the card (ALL FACES 10 ACE 1 or 10) (score/win handling)
    private String cardType; // What the actual card is even if it does not matter for the game (output handling)

    // Card constructor (Creates a random card)
    Card(){
        // Generate random number 1 thorugh 4 for a random suit
        int suitt = (int)(Math.random() * 4) + 1;
        switch(suitt){
            case 1:
                suit = new Heart();
                break;
            case 2:
                suit = new Diamond();
                break;
            case 3:
                suit = new Club();
                break;
            case 4:
                suit = new Spade();
                break;
            default:
                suit = null;
                //System.out.println("Invalid number for identifying suit");
        }
        // Generate randome number 1 thorugh 13 for random card Ace to King
        Integer cardType = (int)(Math.random() * 13) + 1;
        if(cardType >= 2 && cardType <= 10){
            this.cardType = cardType.toString();
            value = cardType;
        }else{
            switch(cardType){
                case 1:
                    // Ace so value can be either 1 or 10
                    this.cardType = "Ace";
                    break;
                case 11:
                    this.cardType = "Jack";
                    value = 10;
                    break;
                case 12:
                    this.cardType = "Queen";
                    value = 10;
                    break;
                case 13:
                    this.cardType = "King";
                    value = 10;
                    break;
            }
        }
    }
    public int getValue(){
        return value;
    }
    public Suit getSuit(){
        return suit;
    }
    public String getCardType(){
        return cardType;
    }
    private void setCardType(String cardType){
        this.cardType = cardType;
    }
    private void setSuit(Suit suit){
        this.suit = suit;
    }
    private void setValue(int value){
        this.value = value;
    }
    public void print(){
        System.out.println(cardType + " " + suit.getName());
    }
}

