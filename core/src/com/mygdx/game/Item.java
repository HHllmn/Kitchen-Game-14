package com.mygdx.game;


enum ItemType {
    INGREDIENT,
    PLATE;
}
interface Item {


    ItemType type = null;
    public abstract ItemType getItemType();


}

