package com.example.yarr.myfirstapp;


import java.util.Random;

/**
 Created by Roger McClain on 12/6/2017.
 */

public class Dice
{

    private int diceSides;

    public static final int DEFAULT_SIZE = 6;

    // actual dice roll math
    public static int diceRoll(int sidesDie)
    {
        Random rand = new Random();
        int dieOne;
        dieOne = rand.nextInt(sidesDie) + 1;
        return dieOne;
    }

    // modifier math
    public static int addModify(int dieRoll, int modifier)
    {
        return dieRoll + modifier;
    }

    // constructor
    public Dice(int sides)
    {
        if (!setSides(sides))
            sides = DEFAULT_SIZE;
    }

    // getter
    public int getDiceSides() {return diceSides;}

    // setter
    public boolean setSides(int sides)
    {
        // validate input function
        diceSides = sides;
        return true;
    }



}
