package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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


        private int value;
        private static Map map = new HashMap<>();

        private Facing(int value) {
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
    private Point tilePosition;
    private Point cameraPosition;
    private int width;
    private int height;


    String texturePath;

    private ArrayList<Texture> tex;
    private SpriteBatch batch;
    private Facing direction;
    private ArrayList<Item> Inventory = new ArrayList<>();

    //Creates a chef with ChefNumber/ID as 0, then increments (so next ChefNumber is 1)
    static int ChefNumber = 0;
    {
        ChefNumber += 1;
    }
    //0 is up, 1 is right, 2 is down, 3 is left
    public Chef(){
        this.tilePosition = new Point(8, 0);
        this.cameraPosition = new Point(tilePosition.x * KitchenGame14.TILE_SIZE, 0);
        this.width = 70;
        this.height = 70;
        this.direction = Facing.UP;
        //this.texturePath = "ChefUp.png";
        this.tex = new ArrayList<Texture>();
        this.tex.add(new Texture("ChefUp.png"));
        this.tex.add(new Texture("ChefRight.png"));
        this.tex.add(new Texture("ChefDown.png"));
        this.tex.add(new Texture("ChefLeft.png"));

    }
    public Chef(int xaxis, int yaxis, int w, int h){
        batch = new SpriteBatch();
        this.cameraPosition.x = xaxis;
        this.cameraPosition.y = yaxis;
        this.width = w;
        this.height = h;
        this.texturePath = "ChefUp.png";
    }
    public Chef(int xaxis, int yaxis, int w, int h, String tex){
            batch = new SpriteBatch();
            this.cameraPosition.x = xaxis;
            this.cameraPosition.y = yaxis;
            this.width = w;
            this.height = h;
            this.texturePath = tex;
    }

    //update this to be a switch statement.
    public void InteractWith() {

        if (direction == Facing.UP) {
            int WorkStationToInteract = KitchenGame14.WorkstationsGrid[tilePosition.y + 1][tilePosition.x];
        }
        else if (direction == Facing.RIGHT) {
            int WorkStationToInteract = KitchenGame14.WorkstationsGrid[tilePosition.y][tilePosition.x + 1];

        }
        else if (direction == Facing.DOWN) {
            int WorkStationToInteract = KitchenGame14.WorkstationsGrid[tilePosition.y - 1][tilePosition.x];

        }
        else if (direction == Facing.LEFT) {
            int WorkStationToInteract = KitchenGame14.WorkstationsGrid[tilePosition.y][tilePosition.x - 1];

        }
    }

    //public void translateChef(int x, int y){ //Description: move the chef using pixel distance relative to the camera
    //    this.cameraPosition.x += x;
    //    this.tilePosition.x += (x / 70);
    //    this.cameraPosition.y += y;
    //    this.tilePosition.y += (y / 70);
    //}


    //This entire subroutine needs to be updated to make the chef moving from one space to another smooth, the movement animation should last
    // less than a second but incrementally move the chef over until its in the new space, like a sliding motion.
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
    public void setCameraX(int value){
        cameraPosition.x = value;
    }
    public int getCameraY(){
        return cameraPosition.y;
    }
    public void setCameraY(int value){
        cameraPosition.y = value;
    }
    public Point getCameraPosition() { return this.cameraPosition; }
    public void setCameraPosition(int x, int y) { this.cameraPosition.setLocation(x, y); }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public String getTexturePath() {
        return texturePath;
    }
    public void setTexture(String tex){
        texturePath = tex;
    }
    public Texture getTexture(){
        return getTexture(this.direction);
    }
    public Texture getTexture(Facing direction){
        return tex.get(direction.getValue());
    }

    public Facing getDirection(){
        return direction;
    }
    public void setDirection(Facing direction){
        this.direction = direction;
    }

    public ArrayList<Item> getInventory() {
        return Inventory;
    }

    //endregion Get's and Set's

}
