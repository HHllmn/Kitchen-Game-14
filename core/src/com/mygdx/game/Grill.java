package com.mygdx.game;

public class Grill implements Station {

    static int GrillID = 1; //Exists to identify the class for Debugging
    {
        GrillID += 1;
    }

    private boolean isProcessing;
    private StationType stationType;
    private Ingredient contents;

    public Grill() {
        this.stationType = StationType.GRILL;
        this.isProcessing = false;
        this.contents = null;
    }

    private boolean isAllowedIngredient(Item item) {
        boolean result = false;
        if(item.getItemType() == ItemType.INGREDIENT){
            Ingredient ingredient = (Ingredient) item;
            if(ingredient.isItCooked() == false){
                result = ingredient.getIngredientType() == Ingredient.IngredientType.BEEF_PATTY;
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
            if (this.isProcessing) {
                items = setResults(items);
            }
        }
        else {
            //throw ui statement about full inventory
        }
        return items;
    }

    private Inventory setResults(Inventory items) {
        contents.setCooked(true);
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
