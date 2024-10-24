package Screens;

import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Render;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SnakeGame;
import com.badlogic.gdx.math.Rectangle;

public class nivel2 implements Screen {
    public SnakeGame game;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;
    private Array<Rectangle> obstaculo;



    public nivel2(){
        obstaculo= new Array<Rectangle>();
        //creo el mapa
        obstaculo.add(new Rectangle(320,640,192,32));
        obstaculo.add(new Rectangle(320,480,32,160));
        obstaculo.add(new Rectangle(320,96,32,192));
        obstaculo.add(new Rectangle(320,96,192,32));
        obstaculo.add(new Rectangle(448,448,96,32));
        obstaculo.add(new Rectangle(512,448,32,96));
        obstaculo.add(new Rectangle(448,288,96,32));
        obstaculo.add(new Rectangle(512,224,32,96));

        obstaculo.add(new Rectangle(736,288,96,32));
        obstaculo.add(new Rectangle(736,224,32,96));
        obstaculo.add(new Rectangle(768,96,192,32));
        obstaculo.add(new Rectangle(928,96,32,192));

        obstaculo.add(new Rectangle(736,448,32,96));
        obstaculo.add(new Rectangle(736,448,96,32));
        obstaculo.add(new Rectangle(768,640,192,32));
        obstaculo.add(new Rectangle(928,480,32,192));


        game = new SnakeGame(2,15,obstaculo,10);
        game.create();
        batch = Render.batch;
        fondo = new Imagen("test.png");
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

