package com.mygdx.game;

public class Ingredient implements Item {

    public enum IngredientType {
        LETTUCE,
        TOMATO,
        ONION,
        BEEF,
        CHEESE,
        BUNS;
    }

    private IngredientType ingredientType;

    public Ingredient(IngredientType type) {

        this.ingredientType = type;

    }



}
