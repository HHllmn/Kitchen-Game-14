package com.mygdx.game;

public class Plate implements Item {

    public enum MealType {
        HAMBURGER,
        SALAD,
        PIZZA,
        JACKET_POTATO;
    }

    private MealType mealType;
    private ItemType itemType;


    public Plate(MealType mealType){
        this.mealType = mealType;
        this.itemType = ItemType.PLATE;
    }

    public Plate() {
        this.mealType = null;
        this.itemType = ItemType.PLATE;
    }

    public MealType getMealType() {
        return this.mealType;
    }

    public ItemType getItemType() {
        return this.itemType;
    };
}
