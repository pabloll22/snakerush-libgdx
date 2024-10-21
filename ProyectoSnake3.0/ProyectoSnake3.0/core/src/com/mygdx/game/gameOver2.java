package com.mygdx.game;

import ES.Entradas;
import Objetos.Imagen;
import Objetos.Texto;
import Screens.MenuJuego;
import Screens.PantallaMenu;
import Screens.dosJugadores;
//import Screens.unJugador;
import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class gameOver2 extends gameOver {

    Imagen fondo;
    String titulos [] = {"Volver a jugar", "Menú", "Salir"};
    String win[]= {"Ha ganado el jugador 1","Ha ganado el jugador 2",};
    Texto winner[]=new Texto[1];
    Texto opciones [] = new Texto[3];
    Entradas entradas = new Entradas(this);
    int opc = 3;
    SpriteBatch s;
    public float cnt;
    private SnakeGame2 snakeGame2;

    public gameOver2(){
        fondo = new Imagen("gOver.jpg");
        fondo.setSize(1280, 768);
        fondo.setTrans(1);

        int avance = 40;

        for(int i=0; i<opciones.length;i++){
            opciones[i] = new Texto("fuentes/JANSINA.ttf", 80, Color.WHITE, true);
            opciones[i].setStr(titulos[i]);
            opciones[i].setPosition((1280/2) - (opciones[i].getAncho()/2), (float) (((768/1.7)+(opciones[0].getAltura()/2))-(opciones[i].getAltura()+avance)*i));
        }
        /*if(snakeGame2.loser==1) {
            winner[i].setStr(win[0]);
        }else{
            winner[i].setStr(win[1]);
        }
        for(int i=0; i<winner.length;i++){
            winner[i] = new Texto("fuentes/JANSINA.ttf", 80, Color.WHITE, true);

            winner[i].setPosition((1280/2) - (winner[i].getAncho()/2), (float) (((768/1.7)+(winner[0].getAltura()/2))-(winner[i].getAltura()+avance)*i));
        }

         */

        Gdx.input.setInputProcessor(entradas);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);
        Render.batch.begin();
        fondo.dibujar();
        for(int i=0; i<opciones.length;i++){
            opciones[i].dibujar();
        }
        Render.batch.end();

        /*Render.batch.begin();
        fondo.dibujar();
        for(int i=0; i<winner.length;i++){
            winner[i].dibujar();
        }
        Render.batch.end();

         */

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
                Render.game.setScreen(new dosJugadores());
            }else if(opc == 2){
                Render.game.setScreen(new MenuJuego());
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
