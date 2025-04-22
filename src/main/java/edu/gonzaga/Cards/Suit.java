package edu.gonzaga.Cards;

public abstract class Suit 
{
    public String name;

    public Suit(String name)
    {
        setName(name);
    }

    public String getName()
    {
        return this.name;
    }

    private void setName(String name)
    {
        this.name = name;
    }

}
