package com.mygdx.game;

import java.util.ArrayList;

public class Grill implements Station {

    static int GrillID = 1; //Exists to identify the class for Debugging
    {
        GrillID += 1;
    }

    private boolean isCooking;
    private Item contents;

    public Grill() {
        this.isCooking = true;
    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        if(this.contents == null) {
            if(inventory != null && inventory.size() > 0) {
                if(inventory.get(0).getIsIngredient()) {
                    this.contents = inventory.get(0);
                    inventory.remove(0);
                    isCooking = true;
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
}
