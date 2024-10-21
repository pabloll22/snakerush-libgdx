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

public class nivel3 implements Screen {
    public SnakeGame game ;
    private Array<Rectangle> obstaculo;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;

    public nivel3(){
        obstaculo = new Array<>();
        obstaculo.add(new Rectangle(256,32,128,64));
        obstaculo.add(new Rectangle(256,96,64,64));
        //arriba izq
        obstaculo.add(new Rectangle(256,634,64,64));
        obstaculo.add(new Rectangle(256,672,128,64));
        //
        obstaculo.add(new Rectangle(384,160,64,192));

        obstaculo.add(new Rectangle(384,416,64,192));
        obstaculo.add(new Rectangle(448,480,64,64));

        obstaculo.add(new Rectangle(544,224,192,64));
        obstaculo.add(new Rectangle(608,160,64,192));

        obstaculo.add(new Rectangle(608,416,64,192));

        obstaculo.add(new Rectangle(896,32,128,64));
        obstaculo.add(new Rectangle(960,96,64,64));

        obstaculo.add(new Rectangle(896,672,128,64));
        obstaculo.add(new Rectangle(960,634,64,64));

        obstaculo.add(new Rectangle(832,160,64,192));

        obstaculo.add(new Rectangle(832,416,64,192));
        obstaculo.add(new Rectangle(768,480,64,64));


        game = new SnakeGame(3,20, obstaculo,20);
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

