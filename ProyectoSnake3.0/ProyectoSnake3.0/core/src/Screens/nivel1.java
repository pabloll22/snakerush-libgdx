package Screens;

import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Render;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.SnakeGame;
import com.badlogic.gdx.*;
import java.util.*;
import com.badlogic.*;
import java.awt.*;


public class nivel1 implements Screen{
    private Array<Rectangle> obstaculo;
    public SnakeGame game;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;


    public nivel1(){
        obstaculo = new Array<>();
        game = new SnakeGame(1,	10, obstaculo,5);
        game.create();
        batch = Render.batch;
        fondo = new Imagen("test1.png");
        marcador = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        marcador.setStr("Marcador: ");
        marcador.setPosition(20, 40);
        valor = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        valor.setPosition(180, 40);
    }

    @Override
    public void render(float delta) {
        game.render();
        score = game.getScore();
        batch.begin();
        fondo.dibujar();
        marcador.dibujar();
        valor.setStr(String.valueOf(score));
        valor.dibujar();
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