package com.mygdx.game;

import java.util.ArrayList;

public class Oven implements Station {

    static int OvenID = 1; //Exists to identify the class for Debugging
    {
        OvenID += 1;
    }

    private boolean isCooking;
    private Ingredient contents;

    public Oven() {
        this.isCooking = false;
    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        if(this.contents == null) {
            //if(inventory != null && inventory.size() > 0) {
                //if(inventory.get(0).getClass().equals(new Ingredient().getClass())) {
                    //if(((Ingredient) inventory.get(0)).getIngredientType() == Ingredient.IngredientType.[PUTNAMEHERE]) {
                    //    this.contents = (Ingredient) inventory.get(0);
                    //    inventory.remove(0);
                        contents = new Ingredient();
                        isCooking = true;
                    //}
                //}
            //}
        }
        else {
            contents = null;
            isCooking = false;
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
