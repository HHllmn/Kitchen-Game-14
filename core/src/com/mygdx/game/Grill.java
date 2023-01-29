package com.mygdx.game;

import java.util.ArrayList;

public class Grill implements Station {

    static int GrillID = 1; //Exists to identify the class for Debugging
    {
        GrillID += 1;
    }

    private boolean isCooking;
    private Ingredient contents;

    public Grill() {
        this.isCooking = false;
        this.contents = null;
    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        if(this.contents == null) {
            if(inventory != null && inventory.size() > 0) {
                if(inventory.get(0).getClass().equals(new Ingredient().getClass())) {
                    if(Ingredient.isCorrectIngredient(inventory.get(0), Ingredient.IngredientType.BEEF_PATTY) && !Ingredient.isItCooked(inventory.get(0))) {
                        this.contents = (Ingredient) inventory.get(0);
                        inventory.remove(0);
                        isCooking = true;
                    }
                }
            }

        }
        else if(inventory.size() < 5 && this.contents != null) {
            if(isCooking) {
                contents.setCooked(true);
                inventory.add(0, contents);
                this.contents = null;
                isCooking = false;
            }
        }
        else {
            //throw ui statement about full inventory
        }


        return inventory;
    }

    public boolean getIsProcessing() {
        return this.isCooking;
    }


    public Ingredient getContents() {
        return this.contents;
    }

}
