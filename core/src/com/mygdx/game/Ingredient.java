package com.mygdx.game;

public class Ingredient implements Item {


    private boolean iAmIngredient;
    private boolean isCooked;
    public enum IngredientType {
        LETTUCE,
        TOMATO,
        ONION,
        BEEF,
        CHEESE,
        BUNS;
    }

    public IngredientType ingredientType;

    public Ingredient(IngredientType type) {
        this.ingredientType = type;
        this.iAmIngredient = true;
        if(type != IngredientType.BUNS) {
            this.isCooked = false;
        }
        else {
            this.isCooked = true;
        }

    }


    public boolean getIsIngredient() {
        return iAmIngredient;
    }

    public void setCooked(boolean cooked) {
        this.isCooked = cooked;
    }



}
