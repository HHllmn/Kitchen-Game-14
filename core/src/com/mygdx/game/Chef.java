package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Chef {
    //Initial Variables
    private int x;
    private int y;
    private int width;
    private int height;
    String texturePath = "chef.png";
    Rectangle Sprite;
    int ChefNumber;
    private SpriteBatch batch;

    //0 is up, 1 is right, 2 is down, 3 is left
    int direction = 0;

    public Chef(Chef self){
            batch = new SpriteBatch();
         Sprite = new Rectangle();
         self.x = 800 / 2 - 64 / 2;
         self.y = 20;


        Texture selfTex = new Texture(texturePath);
        self.width = selfTex.getWidth();
        self.height = selfTex.getHeight();

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


}
