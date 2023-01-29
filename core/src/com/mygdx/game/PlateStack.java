package com.mygdx.game;

public class PlateStack implements Station{

    static int PlateStackID = 1; //Exists to identify the class for Debugging
    {
        PlateStackID += 1;
    }
    private StationType stationType;

    public PlateStack() {

    }

    @Override
    public Inventory Interact(Inventory items) {
        if(items != null && items.size() > 0) {
            items = containsMeal(items);
        }
        //throw visual error, no recipe to plate!
        return items;
    }

    //this entire subroutine should be moved into a class called
    //Inventory which functions as a gateway to better access the ArrayList<Item>
    private Inventory containsMeal(Inventory items) {

        Ingredient preparedLettuce = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.LETTUCE);
        Ingredient preparedTomato = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.TOMATO);
        Ingredient preparedOnion = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.ONION);
        Ingredient preparedBeef_Patty = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.BEEF_PATTY);
        Ingredient preparedCheese = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.CHEESE);
        Ingredient preparedBuns = (Ingredient) items.getPreparedIngredient(Ingredient.IngredientType.BUNS);

        if(preparedBuns != null && preparedBeef_Patty != null && preparedCheese != null) { //hamburger
            items.remove(preparedBeef_Patty);
            items.remove(preparedBuns);
            items.remove(preparedCheese);
            items.add(new Plate(Plate.MealType.HAMBURGER));
            return items;
        }

        if(preparedLettuce != null && preparedTomato != null&& preparedOnion != null) { //salad
            items.remove(preparedLettuce);
            items.remove(preparedTomato);
            items.remove(preparedOnion);
            items.add(new Plate(Plate.MealType.SALAD));
            return items;
        }

        //if it get here, throw visual error for no meal ready
        return items;
    }

    private boolean containsIngredient(Ingredient ingredient, Ingredient.IngredientType type) {
        return (ingredient.getIngredientType() == type && ingredient.isItPrepared());
    }


    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

}
