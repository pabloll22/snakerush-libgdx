package Screens;

import Objetos.Imagen;
import Utiles.Render;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PantallaPrincipal implements Screen{

    Imagen fondo;
    SpriteBatch b;
    boolean terminadoTrans = false;
    float c=0;
    float cont = 0;
    float espera = 5;
    boolean terminado = false;
    float contTermina=0;



    @Override
    public void show() {
        fondo = new Imagen("logoIIS.png");
        b=Render.batch;
        fondo.setSize(1280, 768);
        fondo.setTrans(c);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);

        procesarTrans();

        b.begin();
            fondo.dibujar();
        b.end();
    }

    private void procesarTrans() {
        if(!terminadoTrans){
            c+=0.01f;
            if(c>1){
                terminadoTrans = true;
                c=1;
            }
        }else{
            cont+=0.05f;
            if(cont>espera){
                c-=0.01f;
                if(c < 0){
                    terminado = true;
                    c=0;
                }
            }
        }
        fondo.setTrans(c);

        if(terminado){
            contTermina+=0.04f;
            if(contTermina>5){
                Render.game.setScreen(new pantallaSesion());
            }
        }


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

    }
}
