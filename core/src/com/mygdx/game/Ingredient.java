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
        BUNS
    }

    private final ItemType itemType;
    private final IngredientType ingredientType;

    public Ingredient() {
        this.ingredientType = IngredientType.LETTUCE;
        this.isCooked = false;
        this.itemType = ItemType.INGREDIENT;

    }

    public Ingredient(IngredientType type) {
        this.ingredientType = type;
        this.isCooked = false;
        this.isChopped = false;
        //buns do not need to be prepared.
        this.isPrepared = type == IngredientType.BUNS;
        this.itemType = ItemType.INGREDIENT;
    }

    public void setCooked(boolean cooked) {
        this.isCooked = cooked;
        checkIfPrepared();
    }
    public void setChopped(boolean chopped) {
        this.isChopped = chopped;
        checkIfPrepared();
    }

    public boolean isItPrepared() {
        return this.isPrepared;
    }

    private void checkIfPrepared() {
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


    public IngredientType getIngredientType() {
        return this.ingredientType;
    }

    public ItemType getItemType() {
        return this.itemType;
    }

}
