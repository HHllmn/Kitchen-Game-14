package com.mygdx.game;

import java.util.ArrayList;

public class CuttingBoard implements Station {


    static int CuttingBoardID = 1; //Exists to identify the class for Debugging
    {
        CuttingBoardID += 1;
    }

    public CuttingBoard() {

    }

    public ArrayList<Item> Interact(ArrayList<Item> inventory) {

        return inventory;
    }
}
