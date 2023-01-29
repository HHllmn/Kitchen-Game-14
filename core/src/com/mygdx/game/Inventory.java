package com.mygdx.game;

import java.util.ArrayList;

public class Inventory {


    private boolean inventoryFull;
    private boolean inventoryEmpty;
    private ArrayList<Item> items = new ArrayList<>();

    public Inventory() {

    }




    public void add(Item item) {
        if (!inventoryFull) {
            items.add(0, item);
            validateInventory();
        }
        else {
            //inventory was empty so nothing happened.
        }
    }

    public Item get(int index) {
        return items.get(index);
    }

    public Item getPreparedIngredient(Ingredient.IngredientType ingredient) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemType() == ItemType.INGREDIENT) { //make sure the item in the inventory being checked is an Ingredient
                if (((Ingredient) items.get(i)).isItPrepared() && ((Ingredient) items.get(i)).getIngredientType() == ingredient) { //check if its the correct Ingredient AND prepared.
                    return items.get(i); //returns ingredient if it is correct and prepared.
                }
            }
        }
        return null;
    }

    public Item getFirstItem() {
        return get(0);
    }

    public void removeFirstItem() {
        remove(0);
    }
    public void remove(int index) {
        if (!inventoryEmpty) {
            items.remove(index);
            validateInventory();
        }
        else {
            //inventory was empty so nothing happened.
        }
    }
    public void remove(Item item) {
        if (!inventoryEmpty) {
            items.remove(item);
            validateInventory();
        }
        else {
            //inventory was empty so nothing happened.
        }
    }

    public int size() {
        return items.size();
    }

    public boolean isNotEmpty(){
        return !inventoryEmpty;
    }

    public boolean isNotfull(){
        return !inventoryFull;
    }

    private void validateInventory() {
        inventoryFull = items.size() == 5;
        inventoryEmpty = items.size() == 0;
    }

    public boolean contains(ItemType type) {
        for (int i = 0; i < items.size(); i++) {
            return type == items.get(i).getItemType();
        }
        return false;
    }

    public boolean containsPreparedIngredient(Ingredient.IngredientType ingredient) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getItemType() == ItemType.INGREDIENT) { //make sure the item in the inventory being checked is an Ingredient
                if (((Ingredient) items.get(i)).isItPrepared() && ((Ingredient) items.get(i)).getIngredientType() == ingredient) { //check if its the correct Ingredient AND prepared.
                    return true; //returns true if ingredient is correct and prepared.
                }
            }
        }
        return false;
    }


}
