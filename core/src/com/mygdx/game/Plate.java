package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;

public class Plate implements Item {

    public enum MealType {
        HAMBURGER,
        SALAD,
        PIZZA,
        JACKET_POTATO;
    }

    private MealType Type;

    public Plate(MealType type){
        this.Type = type;
    }

    public Plate() {
        this.Type = null;
    }

    public MealType getMealType() {
        return this.Type;
    }
}
