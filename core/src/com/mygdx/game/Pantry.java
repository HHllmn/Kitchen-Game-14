package com.mygdx.game;

import java.util.ArrayList;

//turn this into a class


public class Pantry implements Station {

    private Ingredient.IngredientType pantryType;

    static int PantryID = 1; //Exists to identify the class for Debugging
    {
        PantryID += 1;
    }
    private StationType stationType;

    public Pantry(Ingredient.IngredientType pantryType) {
        this.pantryType = pantryType;
    }

    @Override
    public Inventory Interact(Inventory inventory) {
        //KitchenGame14.InventoryFull();

        //inventory needs to be initialized in the chef
        inventory.add(new Ingredient(pantryType));

        return inventory;
    }


    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

    public int getProgress() {
        return 0;
    }
    public void incrementProgress() { }

}
