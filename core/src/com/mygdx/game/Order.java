package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Order {
    ArrayList<Integer> Item = new ArrayList<>();

    int x = -20; //Where on the x-axis the orders appear
    private Random rndNum = new Random();
    private int MaxItems = 1; //MaxItems is the maximum possible number of items which can be in an order.
    // int ListLen = rd.nextInt(MaxItems); //Length of the order, number of items
    private int ListLen = 1;
    private int[] ItemList = new int[10]; //Array of all possible item IDs (0 is currently a placeholder)
    public int TimeArrived = KitchenGame14.clock.getTotalTime();
    String OrderBackgroundPath = "OrderImage2.png";
    Texture texOrderBackground = new Texture(OrderBackgroundPath);
    private Map<Plate.MealType, String> map = new HashMap<Plate.MealType, String>();


    String OrderForegroundPath = "Hamburger.png";
    Texture texOrderForeground = new Texture(OrderForegroundPath);


    Texture tex;
    int OrderScreenWidth = 130;
    int OrderScreenHeight = 100 * ListLen;

    {
        //Creates a list of length ListLen (which is a random int), containing random numbers
        for (int i = 0; i < ListLen; i++) {
            Item.add(ItemList[rndNum.nextInt(ItemList.length)]);
        }
        if (ListLen < 1) OrderScreenHeight = 100;
        tex = combineTextures(texOrderBackground,texOrderForeground);

    }
    public Order(){
        InitialiseOrder();
    }

    private void InitialiseOrder() {
        map.put(Plate.MealType.HAMBURGER, "Hamburger.png");
        map.put(Plate.MealType.SALAD, "Salad.png");
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
                    if (ChefInventory.get(i) == Item.get(j)) {
                        ChefInventory.remove(i);
                        Item.remove(j);
                        complete = false;
                        itemfound = true;
                        break;
                    }
                    if (complete == false) break;
                }
            }
        }
        return itemfound;
    }

    public int getHeight(){return OrderScreenHeight;}
    public int getWidth(){return OrderScreenWidth;}

    private static Texture combineTextures(Texture background, Texture foreground){
        background.getTextureData().prepare();
        Pixmap pixmapA = background.getTextureData().consumePixmap();
        foreground.getTextureData().prepare();
        Pixmap pixmapB = foreground.getTextureData().consumePixmap();
        pixmapA.drawPixmap(pixmapB,(background.getWidth()/2 - foreground.getWidth()/2),(background.getHeight()/2 - foreground.getHeight()/2));
        Texture texOut = new Texture(pixmapA);
        return texOut;
    }
}
