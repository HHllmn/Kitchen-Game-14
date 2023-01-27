package com.mygdx.game;

import java.util.ArrayList;

//turn this into a class


public class Pantry implements Station {

    private Ingredient.IngredientType pantryType;

    static int PantryID = 1; //Exists to identify the class for Debugging
    {
        PantryID += 1;
    }

    public Pantry(Ingredient.IngredientType pantryType) {
        this.pantryType = pantryType;
    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        //KitchenGame14.InventoryFull();

        //inventory needs to be initialized in the chef
        inventory.add(0, new Ingredient(pantryType));

        return inventory;
    }
}
