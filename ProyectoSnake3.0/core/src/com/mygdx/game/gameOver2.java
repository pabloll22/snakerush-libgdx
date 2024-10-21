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

public class gameOver2 implements Screen {

    Imagen fondo;
    String titulos [] = {"Volver a jugar", "Menú", "Salir"};
    Texto opciones [] = new Texto[3];
    //Entradas entradas = new Entradas(this);
    //LevelSelectionScreen level=new LevelSelectionScreen();
    int opc = 1;
    SpriteBatch s;
    public float cnt;
    
    private Skin skin;
    private Stage stage;
    private Table table;
    private int loser=SnakeGame2.loser;
    
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
    Texto t1,t2,t3,t4;
    Button br,bm,be;

    public gameOver2(){
    	skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
    	stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
    	
    	t1=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
    	t1.setStr("Player 1");
    	t1.setPosition(80, 700);
    	if(loser==2) {
    		t3=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
        	t3.setStr("Winner");
        	t3.setPosition(90, 650);
        	t4=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
        	t4.setStr("Loser");
        	t4.setPosition(1000, 650);
    	}else {
    		t3=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
        	t3.setStr("Loser");
        	t3.setPosition(110, 650);
        	t4=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
        	t4.setStr("Winner");
        	t4.setPosition(990, 650);
    	}
    	
    	
    	t2=new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
    	t2.setStr("Player 2");
    	t2.setPosition(980, 700);
    	
    	
    	
    	br = new Button(bt1r,bt2r);
    	bm = new Button(bt1m,bt2m);
    	be = new Button(bt1e,bt2e);
    	
    	fondo = new Imagen("game_over_fondo.png");
        fondo.setSize(1280, 768);
        fondo.setTrans(1);
    	
    	br.addListener(new ClickListener() {
   
        	public void clicked(InputEvent event, float x, float y) {
        		Render.game.setScreen(new dosJugadores());
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
        
        
    	
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);
        Render.batch.begin();
        fondo.dibujar();
        t1.dibujar();
        t2.dibujar();
        t3.dibujar();
        t4.dibujar();
        Render.batch.end();
        
        stage.act(delta);
        stage.draw();
        
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
