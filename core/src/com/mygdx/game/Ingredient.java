package com.mygdx.game;

public class Ingredient implements Item {

    private boolean isCooked;
    private boolean isChopped;
    private boolean isPrepared;
    public enum IngredientType {
        LETTUCE,
        TOMATO,
        ONION,
        BEEF_PATTY,
        CHEESE,
        BUNS;
    }

    private IngredientType ingredientType;

    public Ingredient() {
        this.ingredientType = IngredientType.LETTUCE;
        this.isCooked = false;

    }

    public Ingredient(IngredientType type) {
        this.ingredientType = type;
        this.isCooked = false;
        this.isChopped = false;
        if(type != IngredientType.BUNS) { //buns do not need to be prepared.
            this.isPrepared = false;
        }
        else {
            this.isPrepared = true;
        }
    }

    public Ingredient(IngredientType type, boolean finished) {
        this.ingredientType = type;
        setPrepared(finished);

    }

    public void setCooked(boolean cooked) {
        this.isCooked = cooked;
        isItPrepared();
    }
    public void setChopped(boolean chopped) {
        this.isChopped = chopped;
        isItPrepared();
    }

    public IngredientType getIngredientType() {
        return this.ingredientType;
    }

    public boolean getIsItPrepared() {
        return this.isPrepared;
    }

    private void setPrepared(boolean finished) {
        switch (ingredientType) {
            case LETTUCE:
            case TOMATO:
            case CHEESE:
            case ONION: {
                isChopped = finished;
                isPrepared = finished;
                break;
            }
            case BEEF_PATTY:
                isCooked = finished;
                isPrepared = finished;
                break;
            case BUNS:
                isPrepared = finished;
                break;
        }
    }

    private void isItPrepared() {
        switch (ingredientType) {
            case LETTUCE:
            case TOMATO:
            case CHEESE:
            case ONION: {
                isPrepared = isChopped;
                break;
            }
            case BEEF_PATTY:
                isPrepared = isCooked;
                break;
            case BUNS:
                isPrepared = true;
                break;
        }
    }

    public boolean isItChopped() {
        return this.isChopped;
    }
    public boolean isItCooked() {
        return this.isCooked;
    }

    static boolean isCorrectIngredient(Item ingredient, IngredientType type) {
        return ((Ingredient) ingredient).getIngredientType() == type;
    }

    static boolean isItChopped(Item ingredient) {
        return ((Ingredient) ingredient).isItChopped();
    }
    static boolean isItCooked(Item ingredient) {
        return ((Ingredient) ingredient).isItCooked();
    }




}
