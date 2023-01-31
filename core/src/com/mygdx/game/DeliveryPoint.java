package com.mygdx.game;

public class DeliveryPoint implements Station {

    static int DeliveryPointID = 1; //Exists to identify the class for Debugging
    static {
        DeliveryPointID += 1;
    }
    private final StationType stationType;
    private Plate meal;

    public DeliveryPoint() {
        this.stationType = StationType.DELIVERY_POINT;
    }

    @Override
    public Inventory Interact(Inventory inventory) {
        if(inventory != null && inventory.size() > 0) {
            if (inventory.get(0).getItemType() == ItemType.PLATE) {
                //Success!! Item Delivered.
                this.meal = (Plate) inventory.getFirstItem();
                inventory.remove(0);
                return inventory;
            }
        }
        //throw visual error, food isn't plated!
        return inventory;
    }

    @Override
    public boolean equals(StationType type) {
        return this.stationType == type;
    }

    public boolean confirmDelivery() {
        return this.meal != null;
    }

    public Plate.MealType getDeliveredMealType() {
        return this.meal.getMealType();
    }

    public int getProgress() {
        return 0;
    }
    public void incrementProgress() { }

}