package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Null;
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
	public Rectangle ChefB;
	public int ChefNumber = 1;
	
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

		//Create ChefB rectangle
		ChefB = new Rectangle();
		ChefB.x = 800 / 2 - 64 / 2;
		ChefB.y = 20;
		ChefB.width = 100;
		ChefB.height = 50;


	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		if(Gdx.input.isKeyPressed(Keys.SPACE)) SwitchChefs();
		chefA();
		chefB();

	}
	public void SwitchChefs(){
		//Subroutine to switch between chef;
		if (ChefNumber == 1) ChefNumber++;
		if (ChefNumber == 2) ChefNumber--;
	}

	public void chefA(){
		Texture chefAtex = new Texture("chef.png");
		ChefA.width = chefAtex.getWidth();
		ChefA.height = chefAtex.getHeight();
		batch.begin();
		batch.draw(chefAtex, ChefA.x, ChefA.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (ChefNumber == 1) {
			if (Gdx.input.isKeyPressed(Keys.A)) ChefA.x -= 275 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.D)) ChefA.x += 275 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.S)) ChefA.y -= 200 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.W)) ChefA.y += 200 * Gdx.graphics.getDeltaTime();
		}

		if(ChefA.x < 0) ChefA.x = 0;
		if(ChefA.x > 960 - ChefA.width) ChefA.x = 960 - ChefA.width;
		if(ChefA.y < 0) ChefA.y = 0;
		if(ChefA.y > 540 - ChefA.height) ChefA.y = 540 - ChefA.height;
	}

	public void chefB(){
		Texture chefBtex = new Texture("chef.png");
		ChefB.width = chefBtex.getWidth();
		ChefB.height = chefBtex.getHeight();
		batch.begin();
		batch.draw(chefBtex, ChefB.x, ChefB.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if (ChefNumber == 2) {
			if (Gdx.input.isKeyPressed(Keys.A)) ChefB.x -= 275 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.D)) ChefB.x += 275 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.S)) ChefB.y -= 200 * Gdx.graphics.getDeltaTime();
			if (Gdx.input.isKeyPressed(Keys.W)) ChefB.y += 200 * Gdx.graphics.getDeltaTime();
		}

		if(ChefB.x < 0) ChefB.x = 0;
		if(ChefB.x > 960 - ChefB.width) ChefB.x = 960 - ChefB.width;
		if(ChefB.y < 0) ChefB.y = 0;
		if(ChefB.y > 540 - ChefB.height) ChefB.y = 540 - ChefB.height;
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
