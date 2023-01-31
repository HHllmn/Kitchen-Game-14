package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chef {
    public enum Facing {
        UP(0),
        RIGHT(1),
        DOWN(2),
        LEFT(3);
        private final int value;
        private static final Map map = new HashMap<>();
        Facing(int value) {
            this.value = value;
        }
        static {
            for (Facing facing : Facing.values()) {
                map.put(facing.value, facing);
            }
        }
        public static Facing valueOf(int facing) {
            return (Facing) map.get(facing);
        }
        public int getValue() {
            return value;
        }
    }

    //Initial Variables
    private final Point tilePosition;
    private final Point cameraPosition;
    private final int width;
    private final int height;


    private final ArrayList<Texture> chefTextures;
    private Facing direction;
    private Inventory inventory = new Inventory();

    //Creates a chef with ChefNumber/ID as 0, then increments (so next ChefNumber is 1)
    static int ChefNumber = 0;
    {
        ChefNumber += 1;
    }
    //0 is up, 1 is right, 2 is down, 3 is left
    public Chef() {
        this.tilePosition = new Point(8, 0);
        this.cameraPosition = new Point(tilePosition.x * KitchenGame14.TILE_SIZE, 0);
        this.width = 70;
        this.height = 70;
        this.direction = Facing.UP;
        //this.texturePath = "ChefUp.png";
        this.chefTextures = new ArrayList<>();
        if(ChefNumber == 1) {
            this.chefTextures.add(new Texture("ChefAUp.png"));
            this.chefTextures.add(new Texture("ChefARight.png"));
            this.chefTextures.add(new Texture("ChefADown.png"));
            this.chefTextures.add(new Texture("ChefALeft.png"));
        }
        else {
            this.chefTextures.add(new Texture("ChefBUp.png"));
            this.chefTextures.add(new Texture("ChefBRight.png"));
            this.chefTextures.add(new Texture("ChefBDown.png"));
            this.chefTextures.add(new Texture("ChefBLeft.png"));
        }
    }


    //This entire subroutine needs to be updated to make the chef moving from one space to another smooth, the movement animation should last
    // less than a second but incrementally move the chef over until it's in the new space, like a sliding motion.
    public void MoveChef(){ //Description: move the chef using number of tiles relative to his current position
        switch(direction) {
            case UP:
                setTileY(tilePosition.y + 1);
                break;
            case RIGHT:
                setTileX(tilePosition.x + 1);
                break;
            case DOWN:
                setTileY(tilePosition.y - 1);
                break;
            case LEFT:
                setTileX(tilePosition.x - 1);
                break;
        }
    }


    //region Get's and Set's
    public int getTileX(){
        return tilePosition.x;
    }
    public void setTileX(int value){
        tilePosition.x = value;
        cameraPosition.x = value * KitchenGame14.TILE_SIZE;
    }
    public int getTileY(){ return tilePosition.y; }
    public void setTileY(int value){
        tilePosition.y = value;
        cameraPosition.y = value * KitchenGame14.TILE_SIZE;
    }
    public Point getTilePos() { return this.tilePosition; }
    public void setTilePos(int x, int y) { this.tilePosition.setLocation(x, y); }

    public int getCameraX(){
        return cameraPosition.x;
    }

    public int getCameraY(){
        return cameraPosition.y;
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public Texture getTexture(){
        return getTexture(this.direction);
    }
    public Texture getTexture(Facing direction){
        return chefTextures.get(direction.getValue());
    }

    public Facing getDirection(){
        return direction;
    }
    public void setDirection(Facing direction){
        this.direction = direction;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    //endregion Get's and Set's

}
