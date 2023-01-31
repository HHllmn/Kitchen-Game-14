package com.mygdx.game;

public class Bin implements Station {

    static int BinID = 1; //Exists to identify the class for Debugging
    static {
        BinID += 1;
    }
    private final StationType stationType;

    public Bin() {
        this.stationType = StationType.BIN;

    }

    @Override
    public Inventory Interact(Inventory inventory) {
        inventory = new Inventory();
        //throw visual error, food isn't plated!
        return inventory;
    }





    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

    public int getProgress() {
        return 0;
    }
    public void incrementProgress() { }

}
