package com.mygdx.game;

import java.util.ArrayList;

public class DeliveryPoint implements Station {

    static int DeliveryPointID = 1; //Exists to identify the class for Debugging
    {
        DeliveryPointID += 1;
    }

    public DeliveryPoint() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        return inventory;
    }


}
