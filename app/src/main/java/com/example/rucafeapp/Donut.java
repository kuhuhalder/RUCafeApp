package com.example.rucafeapp;

/**
 * This is the Donut class to create donut objects for orders and store their corresponding information.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class Donut extends MenuItem
{
    /**
     * instance variable to store flavor of donuts
     */
    private String flavor;
    /**
     * final constant to store price
     */
    private static final double PRICE = 1.39;

    /**
     * This is the parametrized constructor to create a donut object
     *
     * @param flavor   string for the flavor of donut
     * @param quantity int for the quantity of donut
     */
    public Donut(String flavor, int quantity)
    {
        super(quantity);
        this.flavor = flavor;
    }

    /**
     * Calculates the price of donut
     */
    @Override
    public void itemPrice()
    {
        price = PRICE * quantity;
    }

    /**
     * Prints all of the Donut's information
     *
     * @return a string that has the donut flavor and quantity
     */
    @Override
    public String toString()
    {
        return flavor + "(" + quantity + ")";
    }

    /**
     * Getter method for getting the flavor of donut
     *
     * @return the flavor of the dount
     */
    public String getFlavor()
    {
        return flavor;
    }

    /**
     * This function is to check is two donuts are equal or not (by checking their flavor)
     *
     * @param c Object c instance of donut
     * @return boolean true if equal, false otherwise
     */
    @Override
    public boolean equals(Object c)
    {
        if (c instanceof Donut)
        {
            Donut c1 = (Donut) c;
            if (this.flavor.equals(c1.getFlavor()))
            {
                return true;
            }
            return false;
        }
        else return false;
    }


}
