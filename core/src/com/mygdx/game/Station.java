package com.mygdx.game;

enum StationType {
    BIN,
    CUTTING_BOARD,
    GRILL,
    DELIVERY_POINT,
    OVEN,
    PLATE_STACK,
    PANTRY;
}
public interface Station {

    public Inventory Interact(Inventory inventory); //interface method to handle
    public int getProgress();
    public void incrementProgress();
    StationType stationType = null;
    public boolean equals(StationType type);

}


