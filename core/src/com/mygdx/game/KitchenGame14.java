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

import java.awt.*;
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

	Texture borderTex;

	private ArrayList<Texture> StationProcessingTextures = new ArrayList<>();
	private ArrayList<Rectangle> StationProcessingRectangles = new ArrayList<>();

	private ArrayList<Texture> Progresses = new ArrayList<>();
	private ArrayList<Point> StationsInProgress = new ArrayList<>();
	private ArrayList<Rectangle> StationProgressRectangles = new ArrayList<>();
	private ArrayList<Texture> InventoryTextures = new ArrayList<>();

	public Rectangle MenuItem1; //Menu Item Number 1
	public Rectangle Border; //Border Item

	private Texture InventoryPanel; //chef Inventory
	private ArrayList<Texture> chefImgs = new ArrayList<>(); //Icon for Chef Inventory
	static final int TILE_MAP_HEIGHT = 490; //Number of pixels tall the map is
	static final int TILE_MAP_WIDTH = 770; //Number of pixels wide the map is
	static final int TILE_SIZE = 70; //Multiply tile position by this to get the pixel position

	static final public int GRID_HEIGHT = 7; //Number of tiles tall the map is
	static final public int GRID_WIDTH = 11; //Number of tiles wide the map is

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

	public int[] cookingStations;

	Texture img;
	private TiledMap tiledMap;

	MyInputProcessor processor = new MyInputProcessor();

	private TiledMapRenderer tiledMapRenderer;
	public Tile[][] tileGrid = new Tile[GRID_WIDTH][GRID_HEIGHT];


	//Creates the timer;
	public static Timer clock = new Timer(true);
	BitmapFont font;
	int LastTime = -1;
	public ArrayList<Order> OrderList = new ArrayList<Order>();
	Point OrderPoint;
	int lastKeyPress = -1;
	int lastSpacePress = -1;
	//endregion Properties

	//region Main Methods
	@Override
	public void create() {
		Gdx.input.setInputProcessor(processor);
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		img = new Texture("PiazzaPanicTileSet.png");
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.translate(-150, -25);
		camera.update();
		tiledMap = new TmxMapLoader().load("PiazzaPanicLevel.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		//Gdx.input.setInputProcessor(processor);
		Gdx.input.setInputProcessor(this);

		batch = new SpriteBatch();

		//Create Chefs rectangle
		//ChefCount is the number of chefs created for the game
		int ChefCount = 2;
		for (int i = 0; i < ChefCount; i++) ChefList.add(new Chef());

		borderTex = new Texture("Border.png");
		InventoryPanel = new Texture ("InventoryPanel.png");
		chefImgs.add(0, new Texture("ChefADown.png"));
		chefImgs.add(1, new Texture("ChefBDown.png"));//add new chefs here to display a new one

		Progresses.add(new Texture("Progress.png"));
		Progresses.add(new Texture("Progress_20.png"));
		Progresses.add(new Texture("Progress_40.png"));
		Progresses.add(new Texture("Progress_60.png"));
		Progresses.add(new Texture("Progress_80.png"));
		Progresses.add(new Texture("Progress_Full.png"));

		InitialiseTileGrid();

		TopBorder = new Rectangle(0, TILE_MAP_HEIGHT, TILE_MAP_WIDTH, 25);
		BottomBorder = new Rectangle(0, -25, TILE_MAP_WIDTH, 25);
		RightBorder = new Rectangle(TILE_MAP_WIDTH, -25, 40, TILE_MAP_HEIGHT +50);
		LeftBorder = new Rectangle(-20, -25, 20, TILE_MAP_HEIGHT +50);

		//Create List of Orders rectangle
		MenuItem1 = new Rectangle(0, 540 - 100, 100, 50);
		Border = new Rectangle(0, 540 - 110, 100, 50);
		OrderPoint = new Point((int) (TopBorder.x - 150), 475);

	}

	//public void resize (int width, int height) {
	//	stage.getViewport().update(width, height, true);
	//}


	@Override
	public void render() {
		clock.tick();
		movement();
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		ScreenUtils.clear(1, 0, 0, 1);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();



		//batch.begin();
		//font = new BitmapFont();
		//CharSequence str = clock.getTimeElapsed();
		//font.draw(batch, str,-150 , 500);
		//batch.end();

		batch.begin();

		batch.draw(borderTex, TopBorder.x, TopBorder.y, TopBorder.getWidth(), TopBorder.getHeight());
		batch.draw(borderTex, BottomBorder.x, BottomBorder.y, BottomBorder.getWidth(), BottomBorder.getHeight());
		batch.draw(borderTex, RightBorder.x, RightBorder.y, RightBorder.getWidth(), RightBorder.getHeight());
		batch.draw(borderTex, LeftBorder.x, LeftBorder.y, LeftBorder.getWidth(), LeftBorder.getHeight());

		//remove this new texture to save memory.
		batch.draw(new Texture("Inventory.png"), RightBorder.x + 3, 258, TILE_SIZE/2, 200); //drawing chef Inventory panel
		batch.draw(chefImgs.get(SelectedChef), RightBorder.x + 2, 463, TILE_SIZE/2, TILE_SIZE/2); //drawing Inventory chef Icon

		for (int i = 0; i < ChefList.size(); i++) {
			batch.draw(ChefList.get(i).getTexture(), ChefList.get(i).getCameraX(), ChefList.get(i).getCameraY(), ChefList.get(i).getWidth(), ChefList.get(i).getHeight());
		}
		for (int i = 0; i < StationProcessingTextures.size(); i++) {
			batch.draw(StationProcessingTextures.get(i), StationProcessingRectangles.get(i).x, StationProcessingRectangles.get(i).y, StationProcessingRectangles.get(i).getWidth(), StationProcessingRectangles.get(i).getHeight());
			batch.draw(Progresses.get(
					tileGrid[StationsInProgress.get(i).x][StationsInProgress.get(i).y].getStation().getProgress()),
					StationProgressRectangles.get(i).x,
					StationProgressRectangles.get(i).y,
					StationProgressRectangles.get(i).getWidth(),
					StationProgressRectangles.get(i).getHeight());
		}
		for (int i = 0; i < InventoryTextures.size(); i++) {
			batch.draw(InventoryTextures.get(i), RightBorder.x + 3, (TILE_MAP_HEIGHT - 70) - (i * 40), TILE_SIZE / 2, TILE_SIZE / 2);
		}

		//DRAWING THE ORDER LIST BY ITEMS INDIVIDUALLY
		for (int i = 0; i < OrderList.size(); i++) {
			batch.draw(OrderList.get(i).getTexture(), OrderPoint.x, OrderPoint.y - ((i + 1) * 100), OrderList.get(i).getWidth(), OrderList.get(i).getHeight());
		}


		//batch.draw(ordersListTex, OrdersList.x, OrdersList.y);
		//batch.draw(menuItem1Tex, MenuItem1.x, MenuItem1.y);
		//batch.draw(borderTex, Border.x, Border.y);
		batch.end();
		//Rendering the timers
		batch.begin();
		//First we write the timer as text for the elapsed game time
		font = new BitmapFont();
		CharSequence str = clock.getTimeElapsed();
		font.draw(batch, str, -150, 500);
		//Then we render a timer bar for each order, larger the longer the order has been waiting
		//for (int i = 0; i < OrderList.size(); i++) {
		//	batch.draw(borderTex, OrderPoint.x, OrderPoint.y - ((i) * 100) - 10, 7 * clock.getTimeSince(OrderList.get(i).getTimeArrived()), 10);
		//}
		batch.end();
		//Then we check if the order has reached its "max waiting time", if so the customer leaves, so we remove it

		for(int i = 0; i < StationsInProgress.size(); i++) {
			if (clock.getMilliElapsed() % 60 == 0) {
				StationsInProgress.get(i);
				tileGrid[StationsInProgress.get(i).x][StationsInProgress.get(i).y].getStation().incrementProgress();
			}
		}

		for (int i = 0; i < OrderList.size(); i++) {
			if (clock.getMilliElapsed() % 60 == 0) {
				OrderList.get(i).incrementTimeWaited();
			}
			if (OrderList.get(i).getTimeWaited() == 20 || (OrderList.get(i).getIsCompleted() && OrderList.get(i).getTimeWaited() >= 3)) {
				OrderList.remove(i);
			}
		}
		if (clock.getTotalTime() % 15 == 0 && clock.getTotalTime() != LastTime && OrderList.size() < 5) {
			OrderList.add(new Order(true));
			LastTime = clock.getTotalTime();
		}




		camera.update();
		batch.setProjectionMatrix(camera.combined);
	}

	@Override
	public void dispose() {

		batch.dispose();
		//stage.dispose();

	}
	//endregion Main Methods

	//region Initialisation Methods


	private void InitialiseTileGrid() {

		for (int n = 0; n < WorkstationsGrid.length; n++) {
			for (int m = 0; m < WorkstationsGrid[0].length; m++) {
				if (WorkstationsGrid[n][m] > 0) {
					switch (WorkstationsGrid[n][m]) {
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
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.LETTUCE));
							break;
						case 801:// is a Tomato Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.TOMATO));
							break;
						case 802:// is an Onion Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.ONION));
							break;
						case 803:// is a Beef Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.BEEF_PATTY));
							break;
						case 804:// is a Cheese Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.CHEESE));
							break;
						case 805:// is a Buns Pantry
							tileGrid[n][m] = new Tile(n, m, new Pantry(Ingredient.IngredientType.BUNS));
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
		if (n > 0) {
			if (WorkstationsGrid[n][m] > 0) tileGrid[n - 1][m].setRightOfMe(true);
			if (WorkstationsGrid[n - 1][m] > 0) tileGrid[n][m].setLeftOfMe(true);

		}
		if (m > 0) {
			if (WorkstationsGrid[n][m] > 0) tileGrid[n][m - 1].setAboveMe(true);
			if (WorkstationsGrid[n][m - 1] > 0) tileGrid[n][m].setUnderMe(true);
		}
	}
	//endregion Initialisation Methods

	//region Other Methods
	public boolean SwitchChefs() {
		//Subroutine to switch between chef;
		SelectedChef++;
		if (SelectedChef > ChefList.size() - 1) SelectedChef = 0;
		return false;
	}

	private boolean collisionCheck(Chef PlayerChef) {
		return tileGrid[PlayerChef.getTileX()][PlayerChef.getTileY()].CanIMove(PlayerChef.getDirection());
	}

	private boolean VerifyInteract(Chef PlayerChef) {
		Tile currentTile = tileGrid[PlayerChef.getTileX()][PlayerChef.getTileY()];
		boolean result = currentTile.CanIInteract(PlayerChef.getDirection());
		return result;
	}

	private Inventory StationInteract(Chef Player) {
		int stationX = Player.getTileX();
		int stationY = Player.getTileY();

		switch(Player.getDirection()) {
			case UP:
				stationY += 1;
				break;
			case RIGHT:
				stationX += 1;
				break;
			case DOWN:
				stationY -= 1;
				break;
			case LEFT:
				stationX -= 1;
				break;
		}

		//change so that station is passing the chef inventory to assign back to the chef.
		Station currentStation = tileGrid[stationX][stationY].getStation();
		Inventory newInventory;
		if(currentStation == null) return Player.getInventory();
		//else if(currentStation.equals(StationType.DELIVERY_POINT)) newInventory = ((DeliveryPoint) currentStation).Deliver(Player.getInventory(), OrderList);
		else newInventory = currentStation.Interact(Player.getInventory());

		if(currentStation.equals(StationType.GRILL)) {
			if(((Grill) currentStation).getIsProcessing()) {
				switch(((Grill) currentStation).getContents().getIngredientType()) {
					case BEEF_PATTY:
						AddStationProcess("GrillProcess.png", stationX, stationY);
						AddStationProgress(stationX, stationY);
						break;
				}

			}
			else if(StationProcessingRectangles.contains(new Rectangle(stationX * TILE_SIZE, stationY * TILE_SIZE, TILE_SIZE, TILE_SIZE))){
				RemoveStationProcess(stationX, stationY);
				RemoveStationProgress(stationX, stationY);
			}
		}
		else if(currentStation.equals(StationType.OVEN)) {
			if(((Oven) currentStation).getIsProcessing()) {
				//if (Oven Interact Conditional) {
				//	AddStationProcess("OvenProcess.png", stationX, stationY);
				//}
			}
			else if(StationProcessingRectangles.contains(new Rectangle(stationX * TILE_SIZE, stationY * TILE_SIZE, TILE_SIZE, TILE_SIZE))){
				//RemoveStationProcess(stationX, stationY);
			}
		}
		else if(currentStation.equals(StationType.CUTTING_BOARD)) {
			if(((CuttingBoard) currentStation).getIsProcessing()) {
				switch(((CuttingBoard) currentStation).getContents().getIngredientType()) {
					case LETTUCE:
						AddStationProcess("CuttingBoard_Lettuce.png", stationX, stationY);
						AddStationProgress(stationX, stationY);
						break;
					case TOMATO:
						AddStationProcess("CuttingBoard_Tomato.png", stationX, stationY);
						AddStationProgress(stationX, stationY);
						break;
					case ONION:
						AddStationProcess("CuttingBoard_Onion.png", stationX, stationY);
						AddStationProgress(stationX, stationY);
						break;
					case CHEESE:
						AddStationProcess("CuttingBoard_Cheese.png", stationX, stationY);
						AddStationProgress(stationX, stationY);
						break;
				}
			}
			else if(StationProcessingRectangles.contains(new Rectangle(stationX * TILE_SIZE, stationY * TILE_SIZE, TILE_SIZE, TILE_SIZE))){
				RemoveStationProcess(stationX, stationY);
				RemoveStationProgress(stationX, stationY);
			}
		}
		else if(currentStation.equals(StationType.DELIVERY_POINT)) {
			if(((DeliveryPoint) currentStation).confirmDelivery()) {
				OrderList.get(0).displayRep(((DeliveryPoint) currentStation).getDeliveredMealType());
			}

		}

		tileGrid[stationX][stationY].setStation(currentStation); //Apply all changes to the station
		return newInventory;
	}



	//private boolean

	private void AddStationProcess(String imagePath, int x, int y) {
		StationProcessingTextures.add(new Texture(imagePath));
		StationProcessingRectangles.add(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
	}

	private void AddStationProgress(int x, int y) {
		StationsInProgress.add(new Point(x, y));
		StationProgressRectangles.add(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
	}

	private void AddStationProcess(String imagePath, int x, int y, int size) {
		StationProcessingTextures.add(new Texture(imagePath));
		StationProcessingRectangles.add(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, size, size));
	}

	private void RemoveStationProcess(int x, int y) {
		int index = StationProcessingRectangles.indexOf(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
		StationProcessingTextures.remove(index);
		StationProcessingRectangles.remove(index);
	}
	private void RemoveStationProgress(int x, int y) {
		int index = StationProgressRectangles.indexOf(new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE));
		StationsInProgress.remove(index);
		StationProgressRectangles.remove(index);
	}

	static public void InventoryFull() {

		int n = 1;

		//Success!
	}

	private void UpdateInventoryTextures() {
		Inventory items = ChefList.get(SelectedChef).getInventory();
		this.InventoryTextures = new ArrayList<>();
		for (int i = 0; i < items.size(); i++) {
			if(items.get(i).getClass().equals(new Ingredient().getClass())) {
				switch (((Ingredient) items.get(i)).getIngredientType()) {
					case LETTUCE: {
						if(((Ingredient) items.get(i)).isItChopped()){
							this.InventoryTextures.add(new Texture("Lettuce_Chopped.png"));
						}
						else {
							this.InventoryTextures.add(new Texture("Lettuce.png"));
						}
						break;
					}
					case TOMATO: {
						if(((Ingredient) items.get(i)).isItChopped()) {
							this.InventoryTextures.add(new Texture("Tomato_Chopped.png"));
						}
						else {
							this.InventoryTextures.add(new Texture("Tomato.png"));
						}
						break;
					}
					case CHEESE: {
						if(((Ingredient) items.get(i)).isItChopped()) {
							this.InventoryTextures.add(new Texture("Cheese_Chopped.png"));
						}
						else {
							this.InventoryTextures.add(new Texture("Cheese.png"));
						}
						break;
					}
					case ONION: {
						if(((Ingredient) items.get(i)).isItChopped()) {
							this.InventoryTextures.add(new Texture("Onion_Chopped.png"));
						}
						else {
							this.InventoryTextures.add(new Texture("Onion.png"));
						}
						break;
					}
					case BEEF_PATTY: {
						if(((Ingredient) items.get(i)).isItCooked()) {
							this.InventoryTextures.add(new Texture("Beef_Cooked.png"));
						}
						else {
							this.InventoryTextures.add(new Texture("Beef.png"));
						}
						break;
					}
					case BUNS: {
						this.InventoryTextures.add(new Texture("Buns.png"));
						break;
					}
				}
			}
			else {
				this.InventoryTextures.add(new Texture("Plate.png"));
			}

		}



	}

	private static Texture inventoryPanel(Texture background, Texture foreground){
		background.getTextureData().prepare();
		Pixmap pixmapA = background.getTextureData().consumePixmap();
		foreground.getTextureData().prepare();
		Pixmap pixmapB = foreground.getTextureData().consumePixmap();
		pixmapA.drawPixmap(pixmapB, 0, 0);
		//pixmapA.drawPixmap(pixmapB,(background.getWidth()/2 - foreground.getWidth()/2),(background.getHeight()/2 - foreground.getHeight()/2));
		Texture texOut = new Texture(pixmapA);
		return texOut;
	}

	public boolean movement(){
		if (lastKeyPress + 10 < clock.getMilliElapsed()) {
			if (processor.leftPressed) {
				ChefList.get(SelectedChef).setDirection(Facing.LEFT);
				if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
				lastKeyPress = clock.getMilliElapsed();
			}
			if (processor.rightPressed) {
				ChefList.get(SelectedChef).setDirection(Facing.RIGHT);
				if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
				lastKeyPress = clock.getMilliElapsed();
			}
			if (processor.upPressed) {
				ChefList.get(SelectedChef).setDirection(Facing.UP);
				if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
				lastKeyPress = clock.getMilliElapsed();
			}
			if (processor.downPressed) {
				ChefList.get(SelectedChef).setDirection(Facing.DOWN);
				if (collisionCheck(ChefList.get(SelectedChef))) ChefList.get(SelectedChef).MoveChef();
				lastKeyPress = clock.getMilliElapsed();
			}
		}
		if(processor.spacePressed && lastSpacePress + 8 < clock.getMilliElapsed())
		{
			SwitchChefs();
			lastSpacePress = clock.getMilliElapsed();
		}
		//if(processor.ePressed && VerifyInteract(ChefList.get(SelectedChef))){
		//		ChefList.get(SelectedChef).setInventory(StationInteract(ChefList.get(SelectedChef)));
		//	}
		//}
		return false;
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
		if(keycode == Keys.SPACE) {
			SwitchChefs();
			UpdateInventoryTextures();
		}
		if(keycode == Keys.E) if(VerifyInteract(ChefList.get(SelectedChef))) {
			ChefList.get(SelectedChef).setInventory(StationInteract(ChefList.get(SelectedChef)));
			UpdateInventoryTextures();
		}
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
