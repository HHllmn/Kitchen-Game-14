package com.mygdx.game;

import java.util.ArrayList;

public class Bin implements Station {

    static int BinID = 1; //Exists to identify the class for Debugging
    {
        BinID += 1;
    }

    public Bin() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        return inventory;
    }

}
