package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.ApplicationAdapter;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.*;
import jdk.jpackage.internal.Log;
import sun.jvm.hotspot.gc.shared.Space;

import java.awt.*;
import java.util.ArrayList;

import static sun.rmi.transport.TransportConstants.Return;

public class KitchenGame14 extends ApplicationAdapter implements InputProcessor {

	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character

	//public ArrayList<Rectangle> RectangleList;
	public Chef ChefA; //Creates ChefA Rectangle/Hitbox
	public Chef ChefB;
	public int ChefNumber = 1;

	public Rectangle OrdersList; //Order list on the side of the screen
	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item

	public Rectangle Oven;

	public int TileSize; //Multiply tile position by this to get the pixel position

	public int[][] LogicGrid = new int[][]{
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
	};

	//private Stage stage;
	//private Table table;

	Texture img;
	TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;


		@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		img = new Texture("PiazzaPanicTileSet.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.translate(-170,-25);
		camera.update();
		tiledMap = new TmxMapLoader().load("PiazzaPanicLevel.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		batch = new SpriteBatch();
		//img = new Texture("badlogic.jpg");

		//Loads the images for Chef and Background (BG) in, 64x64 pixels each
		//ChefImage = new Texture(Gdx.files.internal("ChefImage.png"));
		//BGImage = new Texture(Gdx.files.internal("BGImage.png"));

		//Creates the camera and sprite batch
		//camera = new OrthographicCamera();
		//camera.setToOrtho(false, 960, 540); //Camera shows the whole screen (set to
		 // /540, desktop ratio)
		//batch = new SpriteBatch();

		//RectangleList = new ArrayList();
		//RectangleList.add(new Rectangle(0, 540 - 100, 100, 50));


		//Create Chef rectangle
		ChefA = new Chef();
		//ChefA.x = 800 / 2 - 64 / 2;
		//ChefA.y = 20;
		//ChefA.width = 80;
		//ChefA.height = 80;

		//Create ChefB rectangle
		ChefB = new Chef();



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
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);
		camera.update();


		ScreenUtils.clear(1, 0, 0, 1);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

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

		batch.draw(ChefA.getTex(), ChefA.x, ChefA.y, ChefA.getWidth(), ChefA.getHeight());
		batch.draw(ChefB.getTex(), ChefB.x, ChefB.y, ChefB.getWidth(), ChefB.getHeight());

		//batch.draw(ordersListTex, OrdersList.x, OrdersList.y);
		//batch.draw(menuItem1Tex, MenuItem1.x, MenuItem1.y);
		//batch.draw(borderTex, Border.x, Border.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		if(ChefA.x < 0) ChefA.x = 0;
		if(ChefA.x > 770 - ChefA.getWidth()) ChefA.x = 770 - ChefA.getWidth();
		if(ChefA.y < 0) ChefA.y = 0;
		if(ChefA.y > 490 - ChefA.getHeight()) ChefA.y = 490 - ChefA.getHeight();
	}

	public void SwitchChefs(){
		//Subroutine to switch between chef;
		if (ChefNumber == 1) ChefNumber++;
		if (ChefNumber == 2) ChefNumber--;
	}

	//This entire subroutine needs to be updated to make the chef moving from one space to another smooth, the movement animation should last
	// less than a second but incrementally move the chef over until its in the new space, like a sliding motion.
	private void translateChef(Chef Chef, int x, int y) {
		Chef.x = Chef.x + x;
		Chef.y = Chef.y + y;
	}

	//0 up, 1 right, 2 down, 3 left
	private boolean collisionCheck(Chef PlayerChef, int direction) {
		int gridX = 0;
		int gridY = 0;

		if(PlayerChef.x != 0) gridX = (PlayerChef.getX() / 70);
		if(PlayerChef.y != 0) gridY = (PlayerChef.getY() / 70);

		if(direction == 0 && (gridY != 0 || gridY != LogicGrid.length - 1)) if(LogicGrid[gridY + 1][gridX] == 0) return true;
		if(direction == 1 && (gridX != 0 || gridX != LogicGrid[0].length - 1)) if(LogicGrid[gridY][gridX + 1] == 0) return true;
		if(direction == 2 && (gridY != 0 || gridY != LogicGrid.length - 1)) if(LogicGrid[gridY - 1][gridX] == 0) return true;
		if(direction == 3 && (gridX != 0 || gridX != LogicGrid[0].length - 1)) if(LogicGrid[gridY][gridX - 1] == 0) return true;

		return false;

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		if(keycode == Input.Keys.LEFT)
			if(collisionCheck(ChefA, 3)) translateChef(ChefA,-70,0);
		if(keycode == Input.Keys.RIGHT)
			if(collisionCheck(ChefA, 1)) translateChef(ChefA,70,0);
		if(keycode == Input.Keys.UP)
			if(collisionCheck(ChefA, 0)) translateChef(ChefA,0,70);
		if(keycode == Input.Keys.DOWN)
			if(collisionCheck(ChefA, 2)) translateChef(ChefA,0,-70);
		if(keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		if(keycode == Input.Keys.E) SwitchChefs();
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amount, float amount2) {
		return false;
	}

	@Override
	public void dispose () {

		batch.dispose();
		//stage.dispose();

	}
}
