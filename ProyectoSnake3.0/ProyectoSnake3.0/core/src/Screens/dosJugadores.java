package Screens;

import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SnakeGame;
import com.mygdx.game.SnakeGame2;


public class dosJugadores implements Screen {
    public SnakeGame2 game = new SnakeGame2();
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;


    public dosJugadores(){
        game = new SnakeGame2();
        game.create();
        batch = Render.batch;
        fondo = new Imagen("test1.png");
        /*marcador = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        marcador.setPosition(20, 40);
        valor = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        valor.setPosition(180, 40);
        Gdx.input.isKeyPressed(Input.Keys.LEFT)
        Gdx.input.isKeyPressed(Input.Keys.RIGHT)
        Gdx.input.isKeyPressed(Input.Keys.UP)
        Gdx.input.isKeyPressed(Input.Keys.DOWN)
         */
    }

    @Override
    public void render(float delta) {
        game.render();
        //score = game.getScore1();
        batch.begin();
        fondo.dibujar();
        /*marcador.dibujar();
        valor.setStr(String.valueOf(score));
        valor.dibujar();
        */
        batch.end();

    }

    @Override
        public void show() {
        fondo.setSize(1280,768);
        fondo.setTrans(0);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        game.dispose();
    }
}