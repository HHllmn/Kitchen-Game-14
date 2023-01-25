package com.mygdx.game;

import com.sun.org.apache.xalan.internal.xsltc.dom.KeyIndex;
import sun.jvm.hotspot.types.CIntegerField;

import java.time.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class Order {
    ArrayList<Integer> Item = new ArrayList<>();

    int x = -20; //Where on the x-axis the orders appear
    Random rd = new Random();
    int MaxItems = 1; //MaxItems is the maximum possible number of items which can be in an order.
    int ListLen = rd.nextInt(MaxItems); //Length of the order, number of items
    int[] ItemList = new int[0]; //Array of all possible item IDs (0 is currently a placeholder)

    String TimeMade; //NEEDS TO HAVE LOCAL TIME ON PC WHEN MADE
    {
        //Creates a list of length ListLen (which is a random int), containing random numbers
        for (int i = 0; i < ListLen; i++) {
            Item.add(rd.nextInt(1,ItemList.length));
        }

    }
    public Order(){

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
}
