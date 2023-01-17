package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.*;

import java.awt.*;
import java.util.ArrayList;

public class KitchenGame14 extends ApplicationAdapter {
	Texture img;

	private Texture ChefImage; //Image of the chef variable
	private Texture BGImage; //Image for the background
	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character

	//public ArrayList<Rectangle> RectangleList;
	public Rectangle ChefA; //Creates ChefA Rectangle/Hitbox

	public Rectangle OrdersList; //Order list on the side of the screen
	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item

	public Rectangle Oven;

	//private Stage stage;
	//private Table table;


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

		//RectangleList = new ArrayList();
		//RectangleList.add(new Rectangle(0, 540 - 100, 100, 50));


		//Create Chef rectangle
		ChefA = new Rectangle(800 / 2 - 64 / 2, 20, 70, 70);
		//ChefA.x = 800 / 2 - 64 / 2;
		//ChefA.y = 20;
		//ChefA.width = 80;
		//ChefA.height = 80;

		//Create List of Orders rectangle
		OrdersList = new Rectangle(0, 0, 100, 50);


		MenuItem1 = new Rectangle(0, 540 - 100, 100, 50);
		Border = new Rectangle(0, 540 - 110, 100, 50);
		Oven = new Rectangle(170 + (70 * 4), 25 + (4*70), 70, 70);


		//stage = new Stage();
		//Gdx.input.setInputProcessor(stage);

		//table = new Table();
		//table.setFillParent(true);
		//table.add(ChefA);
		//stage.addActor(table);

		//table.setDebug(true);

	}

	//public void resize (int width, int height) {
	//	stage.getViewport().update(width, height, true);
	//}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//stage.act(Gdx.graphics.getDeltaTime());
		//stage.draw();

		Texture chefAtex = new Texture("chef.png");
		//ChefA.width = chefAtex.getWidth();
		//ChefA.height = chefAtex.getHeight();

		Texture ordersListTex = new Texture("MenuTesting.png");
		OrdersList.width = ordersListTex.getWidth();
		OrdersList.height = ordersListTex.getHeight();

		Texture ovenTex = new Texture("Oven.png");

		Texture menuItem1Tex = new Texture("BurgerMenuItem.png");
		MenuItem1.width = menuItem1Tex.getWidth();
		MenuItem1.height = menuItem1Tex.getHeight();

		Texture borderTex = new Texture("BorderLine.png");
		Border.width = borderTex.getWidth();
		Border.height = borderTex.getHeight();

		Texture floorTex = new Texture("FloorPattern.png");

		floorTex.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);

		batch.begin();
		batch.draw(floorTex, 170, 25, 0, 0, 770, 490);
		batch.draw(ovenTex, Oven.x, Oven.y, Oven.height, Oven.width);
		batch.draw(ovenTex, Oven.x, Oven.y - 70, Oven.height, Oven.width);

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

		if(ChefA.x < 170) ChefA.x = 170;
		if(ChefA.x > 960 - 20 - ChefA.width) ChefA.x = 960 - 20 - ChefA.width;
		if(ChefA.y < 25) ChefA.y = 25;
		if(ChefA.y > 540 - 25 - ChefA.height) ChefA.y = 540 - 25 - ChefA.height;

		//counter collision
		CounterCollision(ChefA);


	}

	private void CounterCollision (Rectangle playerChef) {

		if(playerChef.x > (170 + (2 * 70)) && playerChef.y < (25 + (5 * 70))) {
			playerChef.x = (170 + (2 * 70));
			playerChef.y = (25 + (5 * 70));
		}

	}
	
	@Override
	public void dispose () {

		batch.dispose();
		//stage.dispose();

	}
}
