package com.mygdx.game;

import java.util.ArrayList;

public class Inventory {


    private boolean inventoryFull;
    private boolean inventoryEmpty;
    private final ArrayList<Item> items = new ArrayList<>();

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
        for (Item item : items) {
            if (item.getItemType() == ItemType.INGREDIENT) { //make sure the item in the inventory being checked is an Ingredient
                if (((Ingredient) item).isItPrepared() && ((Ingredient) item).getIngredientType() == ingredient) { //check if its the correct Ingredient AND prepared.
                    return item; //returns ingredient if it is correct and prepared.
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

    public boolean isNotFull(){
        return !inventoryFull;
    }

    private void validateInventory() {
        inventoryFull = items.size() == 5;
        inventoryEmpty = items.size() == 0;
    }

    public boolean contains(ItemType type) {
        for (Item item : items) {
            return type == item.getItemType();
        }
        return false;
    }


}
