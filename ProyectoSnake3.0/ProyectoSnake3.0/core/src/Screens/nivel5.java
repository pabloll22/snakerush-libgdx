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

public class nivel5 implements Screen {
    private Array<Rectangle> obstaculo;
    public SnakeGame game;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score,b=32;

    public nivel5(){
        obstaculo = new Array<>();
        obstaculo.add(new Rectangle(288,64,320,32));
        obstaculo.add(new Rectangle(288,64,32,288));
        obstaculo.add(new Rectangle(288,32*6,120,32));

        obstaculo.add(new Rectangle(288,b*13,b,b*9));
        obstaculo.add(new Rectangle(288,768-96,b*10,b));

        obstaculo.add(new Rectangle(288+96,768-6*b,b*4,b));
        obstaculo.add(new Rectangle(288+96,768-7*b,b,b));

        obstaculo.add(new Rectangle(288+96,b*5,b,b*10));
        obstaculo.add(new Rectangle(288+96,b*5,b*11,b));
        obstaculo.add(new Rectangle(288+b*10,b*5,b,b*4));
        obstaculo.add(new Rectangle(288+b*9,b*8,b*7,b));
        obstaculo.add(new Rectangle(288+b*15,b*8,b,b*3));

        obstaculo.add(new Rectangle(288+b*6,b*8,b,b*3));

        obstaculo.add(new Rectangle(288+b*10,b*11,64,64));

        obstaculo.add(new Rectangle(288+b*10,b*15,b,b*4));
        obstaculo.add(new Rectangle(288+b*6,b*12,b,b*3));
        obstaculo.add(new Rectangle(288+b*6,b*15,b*6,b));
        obstaculo.add(new Rectangle(288+b*9,b*18,b*10,b));

        obstaculo.add(new Rectangle(288+b*12,768-b*3,b*10,b));
        obstaculo.add(new Rectangle(288+b*21,768-b*11,b,b*9));

        obstaculo.add(new Rectangle(288+b*21,b*2,b,b*9));
        obstaculo.add(new Rectangle(288+b*12,b*2,b*10,b));
        obstaculo.add(new Rectangle(288+b*18,b*9,b*4,b));
        obstaculo.add(new Rectangle(288+b*18,b*5,b,b*11));
        obstaculo.add(new Rectangle(288+b*16,b*5,b*3,b));

        obstaculo.add(new Rectangle(288+b*14,b*15,b*2,b));
        obstaculo.add(new Rectangle(288+b*15,b*13,b,b*3));
        game = new SnakeGame(5,10,obstaculo,40);
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
