package com.mygdx.game;
import java.awt.Point;

public class Tile {

    //region Properties
    private Station tileStation;
    private Point position;
    private boolean ContainsCounter;

    private boolean somethingAboveMe;
    private boolean somethingRightOfMe;
    private boolean somethingUnderMe;
    private boolean somethingLeftOfMe;

    //endregion

    //region Constructors
    //each tile when its created should know what is around it, so it knows if you can move to the next tile just by calling it
    public Tile(int x, int y) {
        this.position = new Point(x, y);
        this.tileStation = null;
        this.ContainsCounter = false;
    }
    public Tile(int x, int y, boolean containsCounter) {
        this.position = new Point(x, y);
        this.tileStation = null;
        this.ContainsCounter = containsCounter;
    }

    Tile(int x, int y, Station tileStation) {
        this.position = new Point(x, y);
        this.tileStation = tileStation;
    }

    Tile(int x, int y, Station tileStation, boolean up, boolean right, boolean down, boolean left) {
        this.position = new Point(x, y);
        this.tileStation = tileStation;
        this.somethingAboveMe = up;
        this.somethingRightOfMe = right;
        this.somethingUnderMe = down;
        this.somethingLeftOfMe = left;
    }
    //endregion Constructors


    public Boolean ContainsStation() {
        boolean doesContainStation = false;
        if (tileStation != null) doesContainStation = true;
        return doesContainStation;
    }

    public Station getTileStation(){
        return tileStation;
    }

    public void setTileStation(Station value) {
        tileStation = value;
    }

    public Point getGridPosition() {
        return position;
    }

    public void setGridPosition(int x, int y) {
        this.position.setLocation(x, y);
    }

    public int getGridX() {
        return position.x;
    }

    public void setGridX(int x) {
        this.position.x = x;
    }

    public int getGridY() {
        return position.y;
    }

    public void setGridY(int y) {
        this.position.y = y;
    }


    public boolean CanIMove(Chef.Facing direction) {
        boolean iCanMove = false;
        switch(direction) { // Chef is facing:
            case UP: //-1 because 0 based index
                if (position.y < KitchenGame14.GRID_HEIGHT - 1 && !AnythingThere(direction)) iCanMove = true;
                break;
            case RIGHT: //-1 because 0 based index
                if (position.x < KitchenGame14.GRID_WIDTH - 1 && !AnythingThere(direction)) iCanMove = true;
                break;
            case DOWN: //if current tile is at 0y next tile would be -1 which is out of bounds
                if (position.y > 0 && !AnythingThere(direction)) iCanMove = true;
                break;
            case LEFT: //if current tile is at 0x next tile would be -1 which is out of bounds
                if (position.x > 0 && !AnythingThere(direction)) iCanMove = true;
                break;
        }
        return iCanMove;
    }

    public boolean CanIInteract(Chef.Facing direction) {
        boolean iCanInteract = false;
        if (AnythingThere(direction)) iCanInteract = true;
        return iCanInteract;
    }

    //currently unused.
    private boolean DoesItExist(Point tilePos) {
        boolean itDoesExist = true;
        if(tilePos.y > KitchenGame14.GRID_HEIGHT - 1) itDoesExist = false; //-1 because 0 based index, easier to read and understand
        else if(tilePos.x > KitchenGame14.GRID_WIDTH - 1) itDoesExist = false;
        else if(tilePos.y < 0) itDoesExist = false;
        else if(tilePos.x < 0) itDoesExist = false;
        return itDoesExist;
    }

    private boolean AnythingThere(Chef.Facing direction) {
        switch(direction) {
            case UP:
                return somethingAboveMe;
            case RIGHT:
                return somethingRightOfMe;
            case DOWN:
                return somethingUnderMe;
            case LEFT:
                return somethingLeftOfMe;
        }
        return false;
    }


    public void setAboveMe(boolean value) {
        somethingAboveMe = value;
    }

    public void setRightOfMe(boolean value) {
        somethingRightOfMe = value;
    }

    public void setUnderMe(boolean value) {
        somethingUnderMe = value;
    }
    public void setLeftOfMe(boolean value) {
        somethingLeftOfMe = value;
    }

    public Station getStation() {
        return tileStation;
    }





}
