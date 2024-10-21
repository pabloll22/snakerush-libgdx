package Utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;

public class Render {
    public static SpriteBatch batch;
    public static MyGdxGame game;
    public static int ANCHO = 1280;
    public static int ALTO = 768;
    public static void limpiarPantalla(float r, float g, float b){
        Gdx.gl.glClearColor(r,g,b,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

}
