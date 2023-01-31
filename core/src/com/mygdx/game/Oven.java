package com.mygdx.game;

public class Oven implements Station {

    static int OvenID = 1; //Exists to identify the class for Debugging
    static {
        OvenID += 1;
    }
    private final StationType stationType;

    private boolean isProcessing;
    private Ingredient contents;

    public Oven() {
        this.stationType = StationType.OVEN;
        this.isProcessing = false;
    }

    @Override
    public Inventory Interact(Inventory inventory) {

        if(this.contents == null) {
            //if(inventory != null && inventory.size() > 0) {
                //if(inventory.get(0).getClass().equals(new Ingredient().getClass())) {
                    //if(((Ingredient) inventory.get(0)).getIngredientType() == Ingredient.IngredientType.[PUTNAMEHERE]) {
                    //    this.contents = (Ingredient) inventory.get(0);
                    //    inventory.remove(0);
                        contents = new Ingredient();
                        isProcessing = true;
                    //}
                //}
            //}
        }
        else {
            contents = null;
            isProcessing = false;
        }
        return inventory;
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
        return 0;
    }
    public void incrementProgress() { }

}
