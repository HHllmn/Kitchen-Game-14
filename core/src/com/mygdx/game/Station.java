package com.mygdx.game;

enum StationType {
    BIN,
    CUTTING_BOARD,
    GRILL,
    DELIVERY_POINT,
    OVEN,
    PLATE_STACK,
    PANTRY
}
public interface Station {

    Inventory Interact(Inventory inventory); //interface method to handle
    int getProgress();
    void incrementProgress();
    boolean equals(StationType type);

}


