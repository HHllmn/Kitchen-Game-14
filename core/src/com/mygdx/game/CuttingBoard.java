package com.mygdx.game;

import com.mygdx.game.Ingredient.IngredientType;

public class CuttingBoard implements Station {

    static int CuttingBoardID = 1; //Exists to identify the class for Debugging
    static {
        CuttingBoardID += 1;
    }
    static private StationType stationType;
    private boolean isProcessing;
    private Ingredient contents;
    private int progress;

    public CuttingBoard() {
        stationType = StationType.CUTTING_BOARD;
        this.isProcessing = false;
        this.contents = null;
    }

    private boolean isAllowedIngredient(Item item) {
        boolean result = false;
        if(item.getItemType() == ItemType.INGREDIENT){
            Ingredient ingredient = (Ingredient) item;
            if(!ingredient.isItChopped()){
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
        if(this.contents == null && !this.isProcessing) {
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
        else if(items.isNotFull()) {
            if(this.isProcessing && progress == 5) {
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
        progress = 0;
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
        return stationType == type;
    }
    public int getProgress() {
        return progress;
    }
    public void incrementProgress() {
        if(progress < 5) progress++;
    }
}