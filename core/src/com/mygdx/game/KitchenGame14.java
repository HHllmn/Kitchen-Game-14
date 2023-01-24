package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
import sun.jvm.hotspot.gc.shared.Space;
import com.mygdx.game.Chef.Facing;

import java.awt.*;
import java.util.ArrayList;

public class KitchenGame14 extends ApplicationAdapter implements InputProcessor {

	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character

	//public ArrayList<Rectangle> RectangleList;

	ArrayList<Chef> ChefList = new ArrayList<>();
	public int SelectedChef = 0;

	public Rectangle TopBorder;
	public Rectangle RightBorder;
	public Rectangle BottomBorder;
	public Rectangle LeftBorder;

	public Rectangle OrdersList; //Order list on the side of the screen
	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item

	public Rectangle Oven;

	static final int TileSize = 70; //Multiply tile position by this to get the pixel position
	static final int TileMapHeight = 490;
	static final int TileMapWidth = 770;

	static final public int[][] CollisionGrid = new int[][]{
			{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
	};


	//Bin = 1, Plates = 2, DeliveryPoint = 6, Cutting board = 20-29, FryPan = 30-39, Oven = 40-49, Pantry = 100-199
	//100 = lettuce, 101 = tomato, 102 = onion, 103 = beef, 104 = cheese, 105 = buns
	public int[][] WorkStations = new int[][]{
			{ 0, 0, 0, 0, 0, 102, 105, 0, 0, 0, 1 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 40, 49, 0, 20, 0, 0, 100 },
			{ 2, 0, 0, 0, 30, 31, 0, 21, 0, 0, 101 },
			{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 104 },
			{ 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 103 }
	};



	Texture img;
	TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;

	//Creates the timer;
	public Timer clock = new Timer(true);
	BitmapFont font;


		@Override
	public void create () {

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		img = new Texture("PiazzaPanicTileSet.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false,w,h);
		camera.translate(-150,-25);
		camera.update();
		tiledMap = new TmxMapLoader().load("PiazzaPanicLevel.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		batch = new SpriteBatch();


		//Create Chefs rectangle
		//ChefCount is the number of chefs created for the game
		int ChefCount = 5;
		for (int i = 0; i < ChefCount; i++) ChefList.add(new Chef());

		TopBorder = new Rectangle(0, TileMapHeight, TileMapWidth, 25);
		BottomBorder = new Rectangle(0, -25, TileMapWidth, 25);
		RightBorder = new Rectangle(TileMapWidth, -25, 40, TileMapHeight+50);
		LeftBorder = new Rectangle(-20, -25, 20, TileMapHeight+50);


			//Create List of Orders rectangle

		OrdersList = new Rectangle(0, 0, 100, 50);
		MenuItem1 = new Rectangle(0, 540 - 100, 100, 50);
		Border = new Rectangle(0, 540 - 110, 100, 50);
		Oven = new Rectangle(170 + (70 * 4), 25 + (4*70), 70, 70);


	}

	//public void resize (int width, int height) {
	//	stage.getViewport().update(width, height, true);
	//}

    //writing this to make another commit to merge

	@Override
	public void render () {
			clock.tick();
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

		Texture borderTex = new Texture("Border.png");

		batch.begin();
		font = new BitmapFont();
		CharSequence str = clock.getTimeElapsed();
		font.draw(batch, str,-150 , 500);
		batch.end();

		batch.begin();

		for (int i = 0; i < ChefList.size(); i++) {
			batch.draw(ChefList.get(i).getTexture(), ChefList.get(i).x, ChefList.get(i).y, ChefList.get(i).getWidth(), ChefList.get(i).getHeight());
		}

		batch.draw(borderTex, TopBorder.x, TopBorder.y, TopBorder.getWidth(), TopBorder.getHeight());
		batch.draw(borderTex, BottomBorder.x, BottomBorder.y, BottomBorder.getWidth(), BottomBorder.getHeight());
		batch.draw(borderTex, RightBorder.x, RightBorder.y, RightBorder.getWidth(), RightBorder.getHeight());
		batch.draw(borderTex, LeftBorder.x, LeftBorder.y, LeftBorder.getWidth(), LeftBorder.getHeight());




		//batch.draw(ordersListTex, OrdersList.x, OrdersList.y);
		//batch.draw(menuItem1Tex, MenuItem1.x, MenuItem1.y);
		//batch.draw(borderTex, Border.x, Border.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		}
	public void SwitchChefs(){
		//Subroutine to switch between chef;
		SelectedChef++;
		if (SelectedChef > ChefList.size()-1) SelectedChef = 0;
	}

	//This entire subroutine needs to be updated to make the chef moving from one space to another smooth, the movement animation should last
	// less than a second but incrementally move the chef over until its in the new space, like a sliding motion.
	private void translateChef(Chef Chef, int x, int y) {
		Chef.x = Chef.x + x;
		Chef.y = Chef.y + y;
	}

	//0 up, 1 right, 2 down, 3 left
	private boolean collisionCheck(Chef PlayerChef, Facing direction) {
		int gridX = 0;
		int gridY = 0;

		if(PlayerChef.getX() != 0) gridX = (PlayerChef.getX() / TileSize);
		if(PlayerChef.getY() != 0) gridY = (PlayerChef.getY() / TileSize);

		if(direction == Facing.UP && (gridY != CollisionGrid.length - 1)) if(CollisionGrid[gridY + 1][gridX] == 0) return true; //Check if the currently selected chef can move up.
		if(direction == Facing.RIGHT && (gridX != CollisionGrid[0].length - 1)) if(CollisionGrid[gridY][gridX + 1] == 0) return true; //move right.
		if(direction == Facing.DOWN && (gridY != 0)) if(CollisionGrid[gridY - 1][gridX] == 0) return true; //move down.
		if(direction == Facing.LEFT && (gridX != 0)) if(CollisionGrid[gridY][gridX - 1] == 0) return true; //move left.

		return false;

	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		if(keycode == Input.Keys.LEFT || keycode == Keys.A) {
			ChefList.get(SelectedChef).setDirection(Facing.LEFT);
			if (collisionCheck(ChefList.get(SelectedChef), Facing.LEFT)) ChefList.get(SelectedChef).translateChef(-70, 0);
		}
		if(keycode == Input.Keys.RIGHT || keycode == Keys.D) {
			ChefList.get(SelectedChef).setDirection(Facing.RIGHT);
			if (collisionCheck(ChefList.get(SelectedChef), Facing.RIGHT)) ChefList.get(SelectedChef).translateChef(70,0);
		}
		if(keycode == Input.Keys.UP || keycode == Keys.W) {
			ChefList.get(SelectedChef).setDirection(Facing.UP);
			if (collisionCheck(ChefList.get(SelectedChef), Facing.UP)) ChefList.get(SelectedChef).translateChef(0,70);
		}
		if(keycode == Input.Keys.DOWN || keycode == Keys.S) {
			ChefList.get(SelectedChef).setDirection(Facing.DOWN);
			if (collisionCheck(ChefList.get(SelectedChef), Facing.DOWN)) ChefList.get(SelectedChef).translateChef(0,-70);
		}
		if(keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if(keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		if(keycode == Keys.SPACE) SwitchChefs();
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
