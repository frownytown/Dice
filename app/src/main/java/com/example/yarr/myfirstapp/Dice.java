package com.example.yarr.myfirstapp;


import java.util.Random;

/**
 Created by Roger McClain on 12/6/2017.
 */

public class Dice
{

    private int diceRollValue;

    public static final int DEFAULT_SIZE = 6;
 
     // constructor
    public Dice(int sides)
    {
        if (!setSides(sides))
            sides = DEFAULT_SIZE;
    }

    // default constructor
    public Dice()
    {
        int sides = DEFAULT_SIZE;
    }

    // actual dice roll math
    public static int diceRoll(int sidesDie)
    {
        Random rand = new Random();
        return rand.nextInt(sidesDie) + 1;
    }

    // modifier math
    public static int addModify(int dieRoll, int modifier)
    {
        return dieRoll + modifier;
    }

    // getter
    public int getDiceRollValue() {return diceRollValue;}

    // setter
    public boolean setSides(int sides)
    {
        // validate input function
        diceRollValue = sides;
        return true;
    }

    // verification that a number has been entered
    static boolean tryParseInt(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }



}
