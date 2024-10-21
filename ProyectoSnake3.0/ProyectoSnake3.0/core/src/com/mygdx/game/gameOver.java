package com.mygdx.game;

import ES.Entradas;
import Objetos.Imagen;
import Objetos.Texto;
import Screens.*;
import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class gameOver implements Screen {

    Imagen fondo;
    String titulos [] = {"Volver a jugar", "Menú", "Salir"};
    Texto opciones [] = new Texto[3];
    Entradas entradas = new Entradas(this);
    //LevelSelectionScreen level=new LevelSelectionScreen();
    int opc = 1;
    SpriteBatch s;
    public float cnt;
    
    private Skin skin;
    private Stage stage;
    private Table table;
    
    Texture b1 = new Texture(Gdx.files.internal("boton_RETRY_subido.png"));
    Texture b2 = new Texture(Gdx.files.internal("boton_RETRY_bajado.png"));

    TextureRegionDrawable bt1r = new TextureRegionDrawable(b1);
    TextureRegionDrawable bt2r = new TextureRegionDrawable(b2);
    
    Texture b1m = new Texture(Gdx.files.internal("boton_MENU_GAMEOVER_subido.png"));
    Texture b2m = new Texture(Gdx.files.internal("boton_MENU_GAMEOVER_bajado.png"));

    TextureRegionDrawable bt1m = new TextureRegionDrawable(b1m);
    TextureRegionDrawable bt2m = new TextureRegionDrawable(b2m);
    
    Texture b1e = new Texture(Gdx.files.internal("boton_EXIT_GAMEOVER_subido.png"));
    Texture b2e = new Texture(Gdx.files.internal("boton_EXIT_GAMEOVER_bajado.png"));

    TextureRegionDrawable bt1e = new TextureRegionDrawable(b1e);
    TextureRegionDrawable bt2e = new TextureRegionDrawable(b2e);
    
    Button br,bm,be;

    public gameOver(){
    	skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    	stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
    	
    	br = new Button(bt1r,bt2r);
    	bm = new Button(bt1m,bt2m);
    	be = new Button(bt1e,bt2e);
    	
    	fondo = new Imagen("game_over_fondo.png");
        fondo.setSize(1280, 768);
        fondo.setTrans(1);
    	
    	br.addListener(new ClickListener() {
   
        	public void clicked(InputEvent event, float x, float y) {
        		if (LevelSelectionScreen.getCurrentLevel() == 1) {
                    Render.game.setScreen(new nivel1());
                } else if (LevelSelectionScreen.getCurrentLevel() ==2) {
                    Render.game.setScreen(new nivel2());
                } else if(LevelSelectionScreen.getCurrentLevel() == 3){
                    Render.game.setScreen(new nivel3());
                } else if (LevelSelectionScreen.getCurrentLevel() == 4) {
                    Render.game.setScreen(new nivel4());
                } else if (LevelSelectionScreen.getCurrentLevel() == 5) {
                    Render.game.setScreen(new nivel5());
                } else if (LevelSelectionScreen.getCurrentLevel() == 6) {
                    Render.game.setScreen(new nivel6());
                }
            }
                
        });
    	
    	bm.addListener(new ClickListener() {
    		public void clicked(InputEvent event, float x, float y) {
    			Render.game.setScreen(new MenuJuego());
    		}
    	});
    	
    	be.addListener(new ClickListener() {
    		public void clicked(InputEvent event, float x, float y) {
    			Gdx.app.exit();
    		}
    	});
    	
    	table = new Table(skin);
        table.setFillParent(true);
        table.padTop(280);
        table.add(br).padBottom(25).row();
        table.add(bm).padBottom(25).row();
        table.add(be).colspan(10).padBottom(10);
       
        //table.add(backButton);

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
        
        
    	
        /*fondo = new Imagen("game_over_fondo.png");
        fondo.setSize(1280, 768);
        fondo.setTrans(1);

        int avance = 40;

        for(int i=0; i<opciones.length;i++){
            opciones[i] = new Texto("fuentes/pixel.ttf", 80, Color.WHITE, true);
            opciones[i].setStr(titulos[i]);
            opciones[i].setPosition((1280/2) - (opciones[i].getAncho()/2), (float) (((768/1.7)+(opciones[0].getAltura()/2))-(opciones[i].getAltura()+avance)*i));
        }

        Gdx.input.setInputProcessor(entradas);*/
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);
        Render.batch.begin();
        fondo.dibujar();
        Render.batch.end();
        
        stage.act(delta);
        stage.draw();
        /*for(int i=0; i<opciones.length;i++){
            opciones[i].dibujar();
        }
        Render.batch.end();

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
                if (LevelSelectionScreen.getCurrentLevel() == 1) {
                    Render.game.setScreen(new nivel1());
                } else if (LevelSelectionScreen.getCurrentLevel() ==2) {
                    Render.game.setScreen(new nivel2());
                } else if(LevelSelectionScreen.getCurrentLevel() == 3){
                    Render.game.setScreen(new nivel3());
                } else if (LevelSelectionScreen.getCurrentLevel() == 4) {
                    Render.game.setScreen(new nivel4());
                } else if (LevelSelectionScreen.getCurrentLevel() == 5) {
                    Render.game.setScreen(new nivel5());
                } else if (LevelSelectionScreen.getCurrentLevel() == 6) {
                    Render.game.setScreen(new nivel6());
                }
            }else if(opc == 2){
                Render.game.setScreen(new MenuJuego());
            }
            if(opc == 3){
                Gdx.app.exit();
            }
        }
        */
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
    	stage.dispose();
        skin.dispose();
    }
}
