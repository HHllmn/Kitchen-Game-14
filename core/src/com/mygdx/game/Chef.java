package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

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
    int x = 800 / 2 - 64 / 2;
    int y = 20;
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
        this.x = 8*70;
        this.y = 0;
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
        this.x = xaxis;
        this.y = yaxis;
        this.width = w;
        this.height = h;
        this.texturePath = "ChefUp.png";
    }
    public Chef(int xaxis, int yaxis, int w, int h, String tex){
        batch = new SpriteBatch();
        this.x = xaxis;
        this.y = yaxis;
        this.width = w;
        this.height = h;
        this.texturePath = tex;
    }

    public int getX(){
        return x;
    }
    public void setX(int value){
        x = value;
    }
    public int getY(){
        return y;
    }
    public void setY(int value){
        y = value;
    }

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

    public void translateChef(int xmove, int ymove){
        x += xmove;
        y += ymove;
    }
}