package com.mygdx.game;

import com.mygdx.game.Ingredient.IngredientType;

public class CuttingBoard implements Station {

    static int CuttingBoardID = 1; //Exists to identify the class for Debugging
    {
        CuttingBoardID += 1;
    }
    static private StationType stationType;
    private boolean isProcessing;
    private Ingredient contents;

    public CuttingBoard() {
        this.stationType = StationType.CUTTING_BOARD;
        this.isProcessing = false;
        this.contents = null;
    }

    private boolean isAllowedIngredient(Item item) {
        boolean result = false;
        if(item.getItemType() == ItemType.INGREDIENT){
            Ingredient ingredient = (Ingredient) item;
            if(ingredient.isItChopped() == false){
                result = ingredient.getIngredientType() == IngredientType.LETTUCE ||
                        ingredient.getIngredientType() == IngredientType.TOMATO ||
                        ingredient.getIngredientType() == IngredientType.ONION ||
                        ingredient.getIngredientType() == IngredientType.CHEESE;
            }
        }
        return result;
    }

    @Override
    public Inventory Interact(Inventory items) {
        if(this.contents == null && this.isProcessing == false) {
            if(items.isNotEmpty()) {
                if(items.contains(ItemType.INGREDIENT)) {
                    if(isAllowedIngredient(items.getFirstItem())) {
                        this.contents = (Ingredient) items.getFirstItem();
                        items.removeFirstItem();
                        isProcessing = true;
                    }
                }
            }
        }
        else if(items.isNotfull()) {
            if(this.isProcessing) {
                items = setResults(items);
            }
        }
        else {
            //throw ui statement about full inventory
        }
        return items;
    }

    private Inventory setResults(Inventory items) {
        contents.setChopped(true);
        items.add(contents);
        this.contents = null;
        isProcessing = false;
        return items;
    }

    public boolean getIsProcessing() {
        return this.isProcessing;
    }
    public Ingredient getContents() {
        return this.contents;
    }
    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }


}