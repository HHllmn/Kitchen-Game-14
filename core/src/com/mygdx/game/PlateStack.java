package com.mygdx.game;

import java.util.ArrayList;

public class PlateStack implements Station{

    static int PlateStackID = 1; //Exists to identify the class for Debugging
    {
        PlateStackID += 1;
    }

    public PlateStack() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        if(inventory != null && inventory.size() > 0) {
            inventory = containsMeal(inventory);
        }
        //throw visual error, no recipe to plate!
        return inventory;
    }

    //this entire subroutine should be moved into a class called
    //Inventory which functions as a gateway to better access the ArrayList<Item>
    private ArrayList<Item> containsMeal(ArrayList<Item> inventory) {
        boolean containsPreparedLettuce = false,
                containsPreparedTomato = false,
                containsPreparedOnion = false,
                containsPreparedBeef_Patty = false,
                containsPreparedCheese = false,
                containsPreparedBuns = false;
        Ingredient lettuce = null,
                   tomato = null,
                   onion = null,
                   beef_patty = null,
                   cheese = null,
                   buns = null;
        for (int i = 0; i < inventory.size(); i++) {
            if(beef_patty == null) {
                if(containsIngredient((Ingredient) inventory.get(i), Ingredient.IngredientType.BEEF_PATTY)){
                    beef_patty = (Ingredient) inventory.get(i);
                }
            }
            if(buns == null) {
                if(containsIngredient((Ingredient) inventory.get(i), Ingredient.IngredientType.BUNS)){
                    buns = (Ingredient) inventory.get(i);
                }
            }
        }

        if(buns != null && beef_patty != null) { //hamburger
            inventory.remove(beef_patty);
            inventory.remove(buns);
            inventory.add(0, new Plate(Plate.MealType.HAMBURGER));
            return inventory;
        }
        //if it get here, throw visual error for no meal ready
        return inventory;
    }

    private boolean containsIngredient(Ingredient ingredient, Ingredient.IngredientType type) {
        return (ingredient.getIngredientType() == type && ingredient.getIsItPrepared());
    }

}
