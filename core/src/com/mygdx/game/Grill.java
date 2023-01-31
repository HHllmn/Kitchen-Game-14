package com.mygdx.game;

public class Grill implements Station {

    static int GrillID = 1; //Exists to identify the class for Debugging
    static {
        GrillID += 1;
    }

    private boolean isProcessing;
    private final StationType stationType;
    private Ingredient contents;
    private int progress;

    public Grill() {
        this.stationType = StationType.GRILL;
        this.isProcessing = false;
        this.progress = 0;
        this.contents = null;
    }

    private boolean isAllowedIngredient(Item item) {
        boolean result = false;
        if(item.getItemType() == ItemType.INGREDIENT){
            Ingredient ingredient = (Ingredient) item;
            if(!ingredient.isItCooked()){
                result = ingredient.getIngredientType() == Ingredient.IngredientType.BEEF_PATTY;
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
            if (this.isProcessing && progress == 5) {
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
        return this.stationType == type;
    }
    public int getProgress() {
        return progress;
    }
    public void incrementProgress() {
        if(progress < 5) progress++;
    }
}
