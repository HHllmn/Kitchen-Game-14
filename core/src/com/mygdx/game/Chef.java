package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

import java.awt.*;

public class Chef {
    //Initial Variables
    int x = 800 / 2 - 64 / 2;
    int y = 20;
    private int width;
    private int height;

    String texturePath;
    Texture tex;
    private SpriteBatch batch;

    //Creates a chef with ChefNumber/ID as 0, then increments (so next ChefNumber is 1)
    static int ChefNumber = 0;
    {
        ChefNumber += 1;
    }
    //0 is up, 1 is right, 2 is down, 3 is left
    int direction = 0;
    public Chef(){
        x = 8*70;
        y = 0;
        width = 70;
        height = 70;
        texturePath = "chef.png";
        tex = new Texture(texturePath);
    }
    public Chef(int xaxis, int yaxis, int w, int h){
        batch = new SpriteBatch();
        x = xaxis;
        y = yaxis;
        width = w;
        height = h;
        texturePath = "chef.png";
    }
    public Chef(int xaxis, int yaxis, int w, int h, String tex){
            batch = new SpriteBatch();
            x = xaxis;
            y = yaxis;
            width = w;
            height = h;
            texturePath = tex;
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

    public Texture getTex(){
        return new Texture(texturePath);
    }

    public void translateChef(int xmove, int ymove){
        x += xmove;
        y += ymove;
    }
}
