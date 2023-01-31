package com.mygdx.game;

import java.util.*;

public class Plate implements Item {

    public enum MealType {
        HAMBURGER(0),
        SALAD(1);
        //PIZZA(2),
        //JACKET_POTATO(3);
        private int value;
        private static Map map = new HashMap<>();
        private MealType(int value) {
            this.value = value;
        }
        static {
            for (Plate.MealType mealType : Plate.MealType.values()) {
                map.put(mealType.value, mealType);
            }
        }
        public static Plate.MealType valueOf(int mealType) {
            return (Plate.MealType) map.get(mealType);
        }
        public int getValue() {
            return value;
        }
        public static int size() {
            return map.size();
        }

        private static final List<MealType> VALUES =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();
        private static final Random RANDOM = new Random();

        public static MealType randomMeal()  {
            return VALUES.get(RANDOM.nextInt(SIZE));
        }

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
