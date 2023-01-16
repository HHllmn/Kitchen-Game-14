package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.*;

public class KitchenGame14 extends ApplicationAdapter {
	Texture img;

	private Texture ChefImage; //Image of the chef variable
	private Texture BGImage; //Image for the background
	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character
	public Rectangle ChefA; //Creates ChefA Rectangle/Hitbox
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

		//Loads the images for Chef and Background (BG) in, 64x64 pixels each
		//ChefImage = new Texture(Gdx.files.internal("ChefImage.png"));
		//BGImage = new Texture(Gdx.files.internal("BGImage.png"));

		//Creates the camera and sprite batch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 960, 540); //Camera shows the whole screen (set to 960/540, desktop ratio)
		batch = new SpriteBatch();

		//Create Chef rectangle
		ChefA = new Rectangle();
		ChefA.x = 800 / 2 - 64 / 2;
		ChefA.y = 20;
		ChefA.width = 100;
		ChefA.height = 50;
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		Texture chefAtex = new Texture("chef.png");
		ChefA.width = chefAtex.getWidth();
		ChefA.height = chefAtex.getHeight();
		batch.begin();
		batch.draw(chefAtex, ChefA.x, ChefA.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if(Gdx.input.isKeyPressed(Keys.A)) ChefA.x -= 275 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.D)) ChefA.x += 275 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.S)) ChefA.y -= 200 * Gdx.graphics.getDeltaTime();
		if(Gdx.input.isKeyPressed(Keys.W)) ChefA.y += 200 * Gdx.graphics.getDeltaTime();

		if(ChefA.x < 0) ChefA.x = 0;
		if(ChefA.x > 960 - ChefA.width) ChefA.x = 960 - ChefA.width;
		if(ChefA.y < 0) ChefA.y = 0;
		if(ChefA.y > 540 - ChefA.height) ChefA.y = 540 - ChefA.height;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
