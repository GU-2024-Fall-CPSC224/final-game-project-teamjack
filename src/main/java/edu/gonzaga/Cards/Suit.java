package edu.gonzaga.Cards;

public abstract class Suit 
{
    public String name;

    /** Constructor */
    public Suit(String name)
    {
        setName(name);
    }

    /** Name getter */
    public String getName()
    {
        return this.name;
    }

    /** Name setter */
    private void setName(String name)
    {
        this.name = name;
    }

}
