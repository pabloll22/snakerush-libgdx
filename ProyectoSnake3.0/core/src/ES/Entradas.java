package ES;

import Screens.PantallaMenu;
import Screens.Tienda;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gameOver;


public class Entradas implements InputProcessor {

    boolean abajo = false;
    boolean arriba = false;
    boolean enter = false;
    private int mouseX = 0;
    private int mouseY = 0;
    private boolean click = false;
    PantallaMenu app;
    gameOver game;
    Tienda tienda;

    public Entradas(PantallaMenu app){
        this.app = app;
    }

    public Entradas(gameOver game){
        this.game = game;
    }
    
    public Entradas(Tienda t) {
    	tienda = t;
    }

    @Override
    public boolean keyDown(int keycode){
        //app.cnt += 0.8f;
        if(keycode == Input.Keys.DOWN){
            abajo = true;
        }

        if(keycode == Input.Keys.UP){
            arriba = true;
        }

        if(keycode == Input.Keys.ENTER){
            enter = true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode){
        if(keycode == Input.Keys.UP){
            arriba = false;
        }
        if(keycode == Input.Keys.DOWN){
            abajo = false;
        }
        if(keycode == Input.Keys.ENTER){
            enter = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        click = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        click = false;
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseX = screenX;
        mouseY = 768 - screenY;
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isAbajo() {
        return abajo;
    }

    public boolean isClick() {
        return click;
    }

    public boolean isArriba() {
        return arriba;
    }

    public boolean isEnter() {
        return enter;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }
}
