package Screens;

import ES.Entradas;
import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Render;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.SnakeGame;


public class PantallaMenu implements Screen{

    Imagen fondo;
    SpriteBatch s;
    Texto opciones [] = new Texto[3];
    String titulos [] = {"Un jugador", "Dos jugadores", "Salir"};
    Entradas entradas = new Entradas(this);
    public float cnt;
    int opc = 1;
    SnakeGame game;

    @Override
    public void show() {
        fondo = new Imagen("fondo.jpg");
        s = Render.batch;

        fondo.setSize(1280, 768);
        fondo.setTrans(1);

        int avance = 40;

        for(int i=0; i<opciones.length;i++){
            opciones[i] = new Texto("fuentes/JANSINA.ttf", 80, Color.WHITE, true);
            opciones[i].setStr(titulos[i]);
            opciones[i].setPosition((1280/2) - (opciones[i].getAncho()/2), (float) (((768/1.8)+(opciones[0].getAltura()/2))-(opciones[i].getAltura()+avance)*i));
        }

        Gdx.input.setInputProcessor(entradas);
    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);
        s.begin();
            fondo.dibujar();
            for(int i=0; i<opciones.length;i++){
                opciones[i].dibujar();
            }
        s.end();

        cnt+=delta;
        if(entradas.isAbajo()){
            if(cnt>0.1f){
                cnt = 0;
                opc++;
                if(opc>3){
                    opc = 1;
                }
            }
        }

        if(entradas.isArriba()){
            if(cnt>0.1f){
                cnt = 0;
                opc--;
                if(opc<1){
                    opc = 3;
                }
            }
        }

        for(int i=0;i<opciones.length;i++){
            if((entradas.getMouseX()>=opciones[i].getX()) && (entradas.getMouseX()<=opciones[i].getX()+opciones[i].getAncho())){
                if((entradas.getMouseY()>=opciones[i].getY()-opciones[i].getAltura()) && (entradas.getMouseY()<=opciones[i].getY())){
                    opc = i+1;
                }
            }
        }

        for(int i=0; i<opciones.length; i++){
            if(i == (opc-1)){
                opciones[i].setColor(Color.ORANGE);
            }else{
                opciones[i].setColor(Color.WHITE);
            }
        }

        if(entradas.isEnter() || entradas.isClick()){
            if(opc == 1){
                Render.game.setScreen(new LevelSelectionScreen());
            }
            if (opc==2){
                Render.game.setScreen(new dosJugadores());
            }
            if(opc == 3){
                Gdx.app.exit();
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
