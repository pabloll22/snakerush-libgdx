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

public class nivel4 implements Screen {
    private Array<Rectangle> obstaculo;

    public SnakeGame game;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;

    public nivel4(){
        obstaculo = new Array<>();
        obstaculo.add(new Rectangle(256,160,384,32));
        obstaculo.add(new Rectangle(256,192,384,32));
        obstaculo.add(new Rectangle(256,224,384,32));
        obstaculo.add(new Rectangle(512,128,96,32));
        obstaculo.add(new Rectangle(544,96,32,32));

        obstaculo.add(new Rectangle(384,352,96,384));
        obstaculo.add(new Rectangle(416,352,64,384));
        obstaculo.add(new Rectangle(448,352,32,384));
        obstaculo.add(new Rectangle(352,416,32,96));
        obstaculo.add(new Rectangle(320,448,32,32));
        obstaculo.add(new Rectangle(576,352,96,96));

        obstaculo.add(new Rectangle(800,32,32,384));
        obstaculo.add(new Rectangle(832,32,32,384));
        obstaculo.add(new Rectangle(864,32,32,384));
        obstaculo.add(new Rectangle(768,288,32,96));
        obstaculo.add(new Rectangle(736,320,32,32));

        obstaculo.add(new Rectangle(640,512,384,32));
        obstaculo.add(new Rectangle(640,544,384,32));
        obstaculo.add(new Rectangle(640,576,384,32));
        obstaculo.add(new Rectangle(672,608,96,32));
        obstaculo.add(new Rectangle(704,640,32,32));
        obstaculo.add(new Rectangle(928,480,96,32));
        obstaculo.add(new Rectangle(960,448,32,32));



        game = new SnakeGame(4,15, obstaculo,30);
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
