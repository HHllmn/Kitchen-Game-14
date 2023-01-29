package com.mygdx.game;

import java.util.ArrayList;

public class DeliveryPoint implements Station {

    static int DeliveryPointID = 1; //Exists to identify the class for Debugging
    {
        DeliveryPointID += 1;
    }
    private StationType stationType;

    public DeliveryPoint() {

    }

    @Override
    public Inventory Interact(Inventory inventory) {
        if(inventory != null && inventory.size() > 0) {
            if (inventory.get(0).getClass().equals(new Plate().getClass())) {
                //Success!! Item Delivered.
                inventory.remove(0);
                return inventory;
            }
        }
        //throw visual error, food isn't plated!
        return inventory;
    }

    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

}
