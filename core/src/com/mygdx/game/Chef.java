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

    //Creates a chef with ChefNumber/ID as 0, then increments (so next ChefNumber is 1)
    static int ChefNumber = 0;
    {
        ChefNumber += 1;
    }
    //0 is up, 1 is right, 2 is down, 3 is left
    public Chef(){
        this.tilePosition = new Point(8, 0);
        this.cameraPosition = new Point(tilePosition.x * KitchenGame14.TileSize, 0);
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

    public void InteractWith() {


        if (direction == Facing.UP) {
            int WorkStationToInteract = KitchenGame14.WorkStations[tilePosition.y + 1][tilePosition.x];

        }
    }




    public int getTileX(){
        return tilePosition.x;
    }
    public void setTileX(int value){
        tilePosition.x = value;
    }
    public int getTileY(){ return tilePosition.y; }
    public void setTileY(int value){ tilePosition.y = value; }
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










    public void setTex(String tex){
        texturePath = tex;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public String getTexturePath() {
        return texturePath;
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

    //move the chef using pixel distance relative to the camera
    public void translateChef(int xmove, int ymove){
        this.cameraPosition.x += xmove;
        this.tilePosition.x += (xmove / 70);
        this.cameraPosition.y += ymove;
        this.tilePosition.y += (ymove / 70);
    }

    //move the chef using number of tiles relative to his current position
    public void MoveChef(int x, int y){
        this.cameraPosition.x += x;
        this.tilePosition.x += (x / 70);
        this.cameraPosition.y += y;
        this.tilePosition.y += (y / 70);
    }

}
