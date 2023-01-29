package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {
    public boolean leftPressed;
    public boolean rightPressed;
    public boolean downPressed;
    public boolean upPressed;
    public boolean spacePressed;
    public boolean ePressed;

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            leftPressed = true;
        }
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            rightPressed = true;
        }
        if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
            downPressed = true;
        }
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP){
            upPressed = true;
        }
        if (keycode == Input.Keys.SPACE){
            spacePressed = true;
        }
        if (keycode == Input.Keys.E){
            ePressed = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
            leftPressed = false;
        }
        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT){
            rightPressed = false;
        }
        if(keycode == Input.Keys.S || keycode == Input.Keys.DOWN){
            downPressed = false;
        }
        if (keycode == Input.Keys.W || keycode == Input.Keys.UP){
            upPressed = false;
        }
        if (keycode == Input.Keys.SPACE){
            spacePressed = false;
        }
        if (keycode == Input.Keys.E){
            ePressed = false;
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
}
