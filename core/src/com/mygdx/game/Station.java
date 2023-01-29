package com.mygdx.game;

import java.util.ArrayList;

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
    StationType stationType = null;
    public boolean equals(StationType type);

}


