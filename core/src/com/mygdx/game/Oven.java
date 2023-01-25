package com.mygdx.game;

import java.util.ArrayList;

public class Oven implements Station {

    static int OvenID = 1; //Exists to identify the class for Debugging
    {
        OvenID += 1;
    }

    public Oven() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        return inventory;
    }
}
