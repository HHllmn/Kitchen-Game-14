package com.mygdx.game;

import java.util.ArrayList;

//turn this into a class
enum Ingredient {
    LETTUCE,
    TOMATO,
    ONION,
    BEEF,
    CHEESE,
    BUNS;
}

public class Pantry implements Station {

    private Ingredient pantryType;

    static int PantryID = 1; //Exists to identify the class for Debugging
    {
        PantryID += 1;
    }

    public Pantry(Ingredient pantryType) {
        this.pantryType = pantryType;


    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        return inventory;
    }
}
