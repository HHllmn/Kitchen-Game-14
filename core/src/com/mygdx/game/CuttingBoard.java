package com.mygdx.game;

import java.util.ArrayList;
import com.mygdx.game.Ingredient.IngredientType;

public class CuttingBoard implements Station {


    static int CuttingBoardID = 1; //Exists to identify the class for Debugging
    {
        CuttingBoardID += 1;
    }


    private boolean isChopping;
    private Ingredient contents;

    public CuttingBoard() {
        this.isChopping = false;
        this.contents = null;
    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        if(this.contents == null) {
            if(inventory != null && inventory.size() > 0) {
                if(inventory.get(0).getClass().equals(new Ingredient().getClass())) {
                    if(Ingredient.isCorrectIngredient(inventory.get(0), IngredientType.CHEESE) && !Ingredient.isItChopped(inventory.get(0))) {
                        this.contents = (Ingredient) inventory.get(0);
                        inventory.remove(0);
                        isChopping = true;
                    }
                }
            }

        }
        else if(inventory.size() < 5 && this.contents != null) {
            if(isChopping) {
                contents.setChopped(true);
                inventory.add(0, contents);
                this.contents = null;
                isChopping = false;
            }
        }
        else {
            //throw ui statement about full inventory
        }
        return inventory;
    }

    public boolean getIsProcessing() {
        return this.isChopping;
    }



    public Ingredient getContents() {
        return this.contents;
    }
}