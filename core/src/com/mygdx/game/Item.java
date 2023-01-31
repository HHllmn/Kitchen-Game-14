package com.mygdx.game;


enum ItemType {
    INGREDIENT,
    PLATE
}
interface Item {


    ItemType type = null;
    ItemType getItemType();


}

