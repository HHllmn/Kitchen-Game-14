package com.mygdx.game;

import java.util.ArrayList;

public class Grill implements Station {

    static int GrillID = 1; //Exists to identify the class for Debugging
    {
        GrillID += 1;
    }

    public Grill() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {
        if(TopItemValid(inventory.get(0))) inventory = null;





        return inventory;
    }

    private boolean TopItemValid(Item topItem) {
        return true;
    }





}
