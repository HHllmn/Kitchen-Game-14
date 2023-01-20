package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

import java.awt.*;
import java.util.ArrayList;

public class Chef {
    //Initial Variables
    int x = 800 / 2 - 64 / 2;
    int y = 20;
    private int width;
    private int height;

    String texturePath;

    private ArrayList<Texture> tex;
    private SpriteBatch batch;
    private int direction;

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
        this.direction = 0;
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

    public Texture getTexture(int direction){
        return tex.get(direction);
    }

    public int getDirection(){
        return direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    public void translateChef(int xmove, int ymove){
        x += xmove;
        y += ymove;
    }
}
