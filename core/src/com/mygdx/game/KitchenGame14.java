package com.mygdx.game;

//region Imports
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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input.*;
import com.mygdx.game.Chef.Facing;

import java.util.ArrayList;
//endregion Imports

public class KitchenGame14 extends ApplicationAdapter implements InputProcessor {

	//region Properties
	private OrthographicCamera camera; //Camera which will be used to view the game
	private SpriteBatch batch; //Sprite batch which will use the ChefImage to display the character

	//public ArrayList<Rectangle> RectangleList;

	ArrayList<Chef> ChefList = new ArrayList<>();
	//ArrayList<Station> StationsList = new ArrayList<>();
	public int SelectedChef = 0;

	public Rectangle TopBorder;
	public Rectangle RightBorder;
	public Rectangle BottomBorder;
	public Rectangle LeftBorder;

	Texture borderTex = new Texture("Border.png");

	public Rectangle OrdersList; //Order list on the side of the screen
	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item

	static final int MAP_HEIGHT = 490; //Number of pixels tall the map is
	static final int TILE_MAP_WIDTH = 770; //Number of pixels wide the map is
	static final int TILE_SIZE = 70; //Multiply tile position by this to get the pixel position

	static final public int GRID_HEIGHT = 7; //Number of tiles tall the map is
	static final public int GRID_WIDTH = 11; //Number of tiles wide the map is

	//static final public int[][] CollisionGrid = new int[][]{
	//		{ 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1 },
	//		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	//		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	//		{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
	//		{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1 },
	//		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
	//		{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }
	//};

	//Kitchen Counter = 1
	//Bin = 2, Plates = 3, DeliveryPoint = 4, Cutting board = 5, Grill = 6, Oven = 7, Pantry = 800+
	//800 = lettuce, 801 = tomato, 802 = onion, 803 = beef, 804 = cheese, 805 = buns
	static final int[][] WorkstationsGrid = new int[][]{
			{ 1, 1, 1, 1, 3, 4, 4},
			{ 1, 0, 0, 0, 0, 0, 0},
			{ 1, 0, 0, 0, 0, 0, 0},
			{ 1, 0, 0, 1, 1, 0, 0},
			{ 1, 0, 0, 7, 6, 0, 0},
		  { 802, 0, 0, 7, 6, 0, 0},
		  { 805, 0, 0, 1, 1, 0, 0},
			{ 1, 0, 0, 5, 5, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0},
			{ 0, 0, 0, 0, 0, 0, 0},
			{ 2, 1, 1, 800, 801, 804, 803}
	};

	Texture img;
	private TiledMap tiledMap;
	private TiledMapRenderer tiledMapRenderer;
	public Tile[][] tileGrid = new Tile[GRID_WIDTH][GRID_HEIGHT];


	//Creates the timer;
	public Timer clock = new Timer(true);
	BitmapFont font;

	public ArrayList<Order> OrderList = new ArrayList<Order>();
	//endregion Properties

	//region Main Methods
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
		int ChefCount = 2;
		for (int i = 0; i < ChefCount; i++) ChefList.add(new Chef());

		InitialiseTileGrid();

		TopBorder = new Rectangle(0, MAP_HEIGHT, TILE_MAP_WIDTH, 25);
		BottomBorder = new Rectangle(0, -25, TILE_MAP_WIDTH, 25);
		RightBorder = new Rectangle(TILE_MAP_WIDTH, -25, 40, MAP_HEIGHT +50);
		LeftBorder = new Rectangle(-20, -25, 20, MAP_HEIGHT +50);

		//Create List of Orders rectangle
		MenuItem1 = new Rectangle(0, 540 - 100, 100, 50);
		Border = new Rectangle(0, 540 - 110, 100, 50);

	}

	//public void resize (int width, int height) {
	//	stage.getViewport().update(width, height, true);
	//}



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



		batch.begin();
		font = new BitmapFont();
		CharSequence str = clock.getTimeElapsed();
		font.draw(batch, str,-150 , 500);
		batch.end();

		batch.begin();

		for (int i = 0; i < ChefList.size(); i++) {
			batch.draw(ChefList.get(i).getTexture(), ChefList.get(i).getCameraX(), ChefList.get(i).getCameraY(), ChefList.get(i).getWidth(), ChefList.get(i).getHeight());
		}

		batch.draw(borderTex, TopBorder.x, TopBorder.y, TopBorder.getWidth(), TopBorder.getHeight());
		batch.draw(borderTex, BottomBorder.x, BottomBorder.y, BottomBorder.getWidth(), BottomBorder.getHeight());
		batch.draw(borderTex, RightBorder.x, RightBorder.y, RightBorder.getWidth(), RightBorder.getHeight());
		batch.draw(borderTex, LeftBorder.x, LeftBorder.y, LeftBorder.getWidth(), LeftBorder.getHeight());

		//CREATING THE ORDER LIST BY ITEMS INDIVIDUALLY
		//for (int i = 0; i < OrderList.size(); i++) {
		//	batch.draw(orderlistTex, TopBorder.x - orderlistTex.getWidth(), TopBorder.y*i);
		//}


		//batch.draw(ordersListTex, OrdersList.x, OrdersList.y);
		//batch.draw(menuItem1Tex, MenuItem1.x, MenuItem1.y);
		//batch.draw(borderTex, Border.x, Border.y);
		batch.end();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}
	@Override
	public void dispose () {

		batch.dispose();
		//stage.dispose();

	}
	//endregion Main Methods

	//region Initialisation Methods


	private void InitialiseTileGrid() {

		for (int n = 0; n < WorkstationsGrid.length; n++) {
			for (int m = 0; m < WorkstationsGrid[0].length; m++) {
				if (WorkstationsGrid[n][m] > 0) {
					switch(WorkstationsGrid[n][m]) {
						case 1:// is an empty counter
							tileGrid[n][m] = new Tile(n, m, true);
							break;
						case 2:// is Bin
							tileGrid[n][m] = new Tile(n, m, new Bin());
							break;
						case 3:// is PlateStack
							tileGrid[n][m] = new Tile(n, m, new PlateStack());
							break;
						case 4:// is a DeliveryPoint
							tileGrid[n][m] = new Tile(n, m, new DeliveryPoint());
							break;
						case 5:// is a CuttingBoard
							tileGrid[n][m] = new Tile(n, m, new CuttingBoard());
							break;
						case 6:// is a Grill
							tileGrid[n][m] = new Tile(n, m, new Grill());
							break;
						case 7:// is a Oven
							tileGrid[n][m] = new Tile(n, m, new Oven());
							break;
						case 800:// is a Lettuce Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.LETTUCE));
							break;
						case 801:// is a Tomato Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.TOMATO));
							break;
						case 802:// is an Onion Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.ONION));
							break;
						case 803:// is a Beef Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.BEEF));
							break;
						case 804:// is a Cheese Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.CHEESE));
							break;
						case 805:// is a Buns Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.BUNS));
							break;
					}
				}
				else {
					tileGrid[n][m] = new Tile(n, m);
				}
				SetTileAdjacency(n, m);
			}
		}

	}

	private void SetTileAdjacency(int n, int m) {
		if(n > 0) {
			if(WorkstationsGrid[n][m] > 0) tileGrid[n-1][m].setRightOfMe(true);
			if(WorkstationsGrid[n-1][m] > 0) tileGrid[n][m].setLeftOfMe(true);

		}
		if(m > 0) {
			if(WorkstationsGrid[n][m] > 0) tileGrid[n][m-1].setAboveMe(true);
			if(WorkstationsGrid[n][m-1] > 0) tileGrid[n][m].setUnderMe(true);
		}
	}
	//endregion Initialisation Methods

	//region Other Methods
	public void SwitchChefs(){
		//Subroutine to switch between chef;
		SelectedChef++;
		if (SelectedChef > ChefList.size()-1) SelectedChef = 0;
	}

	private boolean collisionCheck(Chef PlayerChef) {
		return tileGrid[PlayerChef.getTileX()][PlayerChef.getTileY()].CanIMove(PlayerChef.getDirection());
	}

	private boolean VerifyInteract(Chef PlayerChef) {
		Tile currentTile = tileGrid[PlayerChef.getTileX()][PlayerChef.getTileY()];
		boolean result = currentTile.CanIInteract(PlayerChef.getDirection());
		return result;
	}

	private Station GetStation(Chef Player) {

		switch(Player.getDirection()) {
			case UP:
				tileGrid[Player.getTileX()][Player.getTileY() + 1].getStation();
				break;
			case RIGHT:
				tileGrid[Player.getTileX() + 1][Player.getTileY()].getStation();
				break;
			case DOWN:
				tileGrid[Player.getTileX()][Player.getTileY() - 1].getStation();
				break;
			case LEFT:
				tileGrid[Player.getTileX() - 1][Player.getTileY()].getStation();
				break;
		}
		return null;
	}



	//endregion Methods

	//region Events
	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		int random;

		if(keycode == Input.Keys.LEFT) {
			ChefList.get(SelectedChef).setDirection(Facing.LEFT);
			if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
		}
		if(keycode == Input.Keys.RIGHT) {
			ChefList.get(SelectedChef).setDirection(Facing.RIGHT);
			if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
		}
		if(keycode == Input.Keys.UP) {
			ChefList.get(SelectedChef).setDirection(Facing.UP);
			if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
		}
		if(keycode == Input.Keys.DOWN) {
			ChefList.get(SelectedChef).setDirection(Facing.DOWN);
			if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
		}
		//if(keycode == Input.Keys.NUM_1) tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		//if(keycode == Input.Keys.NUM_2) tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		if(keycode == Keys.SPACE) SwitchChefs();
		if(keycode == Keys.E) if(VerifyInteract(ChefList.get(SelectedChef))) GetStation(ChefList.get(SelectedChef)).Interact(ChefList.get(SelectedChef).getInventory());
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

	//endregion Events

}
