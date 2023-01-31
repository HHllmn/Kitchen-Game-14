package com.mygdx.game;

//turn this into a class


public class Pantry implements Station {

    private final Ingredient.IngredientType pantryType;

    static int PantryID = 1; //Exists to identify the class for Debugging
    static {
        PantryID += 1;
    }
    private final StationType stationType;

    public Pantry(Ingredient.IngredientType pantryType) {
        this.stationType = StationType.PANTRY;
        this.pantryType = pantryType;
    }

    @Override
    public Inventory Interact(Inventory inventory) {
        //KitchenGame14.InventoryFull();

        //inventory needs to be initialized in the chef
        inventory.add(new Ingredient(pantryType));

        return inventory;
    }


    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

    public int getProgress() {
        return 0;
    }
    public void incrementProgress() { }

}
