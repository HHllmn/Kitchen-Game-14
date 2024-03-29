package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.*;

import static com.mygdx.game.Plate.MealType.randomMeal;

public class Order {
    ArrayList<Integer> Item = new ArrayList<>();

    int x = -20; //Where on the x-axis the orders appear
    private final Random rndNum = new Random();
    private final int MaxItems = 1; //MaxItems is the maximum possible number of items which can be in an order.
    // int ListLen = rd.nextInt(MaxItems); //Length of the order, number of items
    private static final int ListLen = 1;
    private static int TIMEARRIVED;
    private boolean isCompleted;
    private final boolean noTimer;
    private final int[] ItemList = new int[10]; //Array of all possible item IDs (0 is currently a placeholder)
    private int timeWaited = 0;
    String OrderBackgroundPath = "OrderImage2.png";
    private static final Texture successTexture = new Texture("Success.png");
    private static final Texture failTexture = new Texture("Fail.png");
    Texture texOrderBackground = new Texture(OrderBackgroundPath);
    private final Map<Plate.MealType, String> meals = new HashMap<>();
    private final Plate.MealType expectedMeal;


    String OrderForegroundPath;
    Texture texOrderForeground;


    Texture orderTexture;
    static int OrderScreenWidth = 130;
    static int OrderScreenHeight = 100 * ListLen;



    {
        //Creates a list of length ListLen (which is a random int), containing random numbers
        for (int i = 0; i < ListLen; i++) {
            Item.add(ItemList[rndNum.nextInt(ItemList.length)]);
        }
        if (ListLen < 1) OrderScreenHeight = 100;
        IntialiseMeals();
        expectedMeal = randomMeal();
        OrderForegroundPath = meals.get(expectedMeal);
        texOrderForeground = new Texture(OrderForegroundPath);
        orderTexture = combineTextures(texOrderBackground,texOrderForeground);
        isCompleted = false;

    }
    public Order(int timeArrived, boolean noTimer) {
        this.TIMEARRIVED = timeArrived;
        this.noTimer = noTimer;
    }

    public boolean compareTo(int ID){
        for (int i: Item) {
            if (ID == i) {
                Item.remove(i);
                return true;
            }
        }
        return false;
    }

    private void IntialiseMeals() { //new meals are added to the HashMap here to connect them to their pictures.
        meals.put(Plate.MealType.HAMBURGER, "Hamburger_Cheese.png");
        meals.put(Plate.MealType.SALAD, "Salad.png");
    }

    //compareTo searches through an array of item IDs from the ChefInventory.
    //if an item in ChefInventory matches and item in our order array (Item), then it is  removed from both
    //This then edits both arrays, meaning the search for the next items needs to be restarted with new lists
    //Complete is set to false - to go into the while loop - then set to true so if we make it through with no
    //matches then the while loop ends
    //if a match is found complete is set to false and the for loops broken, then the comparison starts again
    public boolean compareTo(ArrayList<Integer> ChefInventory) {
        boolean complete = false;
        boolean itemfound = false;
        while (!complete) {
            complete = true;
            for (int i = 0; i < ChefInventory.size(); i++) {
                for (int j = 0; j < Item.size(); j++) {
                    if (Objects.equals(ChefInventory.get(i), Item.get(j))) {
                        ChefInventory.remove(i);
                        Item.remove(j);
                        complete = false;
                        itemfound = true;
                        break;
                    }
                    if (!complete) break;
                }
            }
        }
        return itemfound;
    }

    public boolean getIsCompleted() {
        return this.isCompleted;
    }
    public void setIsCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public int getHeight(){return OrderScreenHeight;}
    public int getWidth(){return OrderScreenWidth;}

    private static Texture combineTextures(Texture background, Texture foreground){
        background.getTextureData().prepare();
        Pixmap pixmapA = background.getTextureData().consumePixmap();
        foreground.getTextureData().prepare();
        Pixmap pixmapB = foreground.getTextureData().consumePixmap();
        pixmapA.drawPixmap(pixmapB,(background.getWidth()/2 - foreground.getWidth()/2),(background.getHeight()/2 - foreground.getHeight()/2));
        return new Texture(pixmapA);
    }

    public void displayRep(Plate.MealType deliveredMeal) {

        if(deliveredMeal == expectedMeal) {
            texOrderForeground = successTexture;
        }
        else {
            texOrderForeground = failTexture;
        }
        orderTexture = combineTextures(texOrderBackground,texOrderForeground);
        isCompleted = true;
    }

    public Texture getTexture() {
        return orderTexture;
    }

    public int getTimeWaited() {
        return this.timeWaited;
    }

    public void incrementTimeWaited() {
        if(!noTimer || isCompleted) {
            timeWaited++;
        }
    }

    public int getTimeArrived() {
        return TIMEARRIVED;
    }
}
