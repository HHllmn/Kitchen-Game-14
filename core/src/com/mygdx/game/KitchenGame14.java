package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Pixmap;
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

import java.awt.*;

public class KitchenGame14 extends ApplicationAdapter {
	Texture img;

	private Texture ChefImage; //Image of the chef variable
	private Texture BGImage; //Image for the background
	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character
	public Rectangle ChefA; //Creates ChefA Rectangle/Hitbox

	public Rectangle OrdersList; //Order list on the side of the screen
	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item


	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

		//Loads the images for Chef and Background (BG) in, 64x64 pixels each
		//ChefImage = new Texture(Gdx.files.internal("ChefImage.png"));
		//BGImage = new Texture(Gdx.files.internal("BGImage.png"));

		//Creates the camera and sprite batch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 960, 540); //Camera shows the whole screen (set to
		 // /540, desktop ratio)
		batch = new SpriteBatch();

		//Create Chef rectangle
		ChefA = new Rectangle(800 / 2 - 64 / 2, 20, 80, 80);
		//ChefA.x = 800 / 2 - 64 / 2;
		//ChefA.y = 20;
		//ChefA.width = 80;
		//ChefA.height = 80;

		//Create List of Orders rectangle
		OrdersList = new Rectangle(0, 0, 100, 50);


		MenuItem1 = new Rectangle(0, 0, 100, 50);
		Border = new Rectangle(0, 0, 100, 50);



	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		//Pixmap pixmap200 = new Pixmap(Gdx.files.internal("chef.png"));
		//Pixmap pixmap100 = new Pixmap(100, 100, pixmap200.getFormat());
		//pixmap100.drawPixmap(pixmap200,
		//		0, 0, pixmap200.getWidth(), pixmap200.getHeight(),
		//		0, 0, pixmap100.getWidth(), pixmap100.getHeight()
		//);
		//Texture texture = new Texture(pixmap100);
		//pixmap200.dispose();
		//pixmap100.dispose();

		Texture chefAtex = new Texture("chef.png");
		//ChefA.width = chefAtex.getWidth();
		//ChefA.height = chefAtex.getHeight();

		Texture ordersListTex = new Texture("MenuTesting.png");
		OrdersList.width = ordersListTex.getWidth();
		OrdersList.height = ordersListTex.getHeight();

		Texture menuItem1Tex = new Texture("BurgerMenuItem.png");
		MenuItem1.width = menuItem1Tex.getWidth();
		MenuItem1.height = menuItem1Tex.getHeight();

		Texture borderTex = new Texture("BorderLine.png");
		Border.width = borderTex.getWidth();
		Border.height = borderTex.getHeight();

		batch.begin();
		batch.draw(chefAtex, ChefA.x, ChefA.y, ChefA.height, ChefA.width);
		batch.draw(ordersListTex, OrdersList.x, OrdersList.y);
		batch.draw(menuItem1Tex, MenuItem1.x, MenuItem1.y);
		batch.draw(borderTex, Border.x, Border.y);
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
