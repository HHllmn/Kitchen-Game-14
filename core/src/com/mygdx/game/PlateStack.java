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

        return inventory;
    }


}
