package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Objetos.Imagen;
import Utiles.Render;

public class MenuJuego implements Screen{
	
	private Stage stage;
	private Table table;
	private Skin skin;
	private Imagen fondo;
	
	private ImageButton tienda = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("BOTON_TIENDA_MENU.png"))));
	private ImageButton configuracion = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("BOTON_config_MENU.png"))));
	
	Texture b1s = new Texture(Gdx.files.internal("boton_1player_subido.png"));
    Texture b1b = new Texture(Gdx.files.internal("boton_1player_bajado.png"));

    TextureRegionDrawable bt1s = new TextureRegionDrawable(b1s);
    TextureRegionDrawable bt1b = new TextureRegionDrawable(b1b);
    
    Texture b2s = new Texture(Gdx.files.internal("boton_2player_subido.png"));
    Texture b2b = new Texture(Gdx.files.internal("boton_2player_bajado.png"));

    TextureRegionDrawable bt2s = new TextureRegionDrawable(b2s);
    TextureRegionDrawable bt2b = new TextureRegionDrawable(b2b);
    
    Button b1,b2;
    
    Texture b1e = new Texture(Gdx.files.internal("boton_exit_subido.png"));
    Texture b2e = new Texture(Gdx.files.internal("boton_exit_bajado.png"));

    TextureRegionDrawable bt1e = new TextureRegionDrawable(b1e);
    TextureRegionDrawable bt2e = new TextureRegionDrawable(b2e);
    
    Button bExit;
    
   
	
	public MenuJuego(){
		stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		b1 = new Button(bt1s,bt1b);
		b2 = new Button(bt2s,bt2b);
		bExit = new Button(bt1e,bt2e);
		
		
		b1.addListener(new ClickListener () {
			public void clicked(InputEvent event, float x, float y) {
				Render.game.setScreen(new LevelSelectionScreen());
            }
		});
		
		b2.addListener(new ClickListener () {
			public void clicked(InputEvent event, float x, float y) {
				Render.game.setScreen(new dosJugadores());
            }
		});
		
		tienda.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Render.game.setScreen(new Tienda());
            }
		});
		
		configuracion.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y){
				Render.game.setScreen(new PantallaConfig());
            }
		});
		
		bExit.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
		});
		
		fondo = new Imagen("pantallaFondo.png");
        fondo.setSize(1280,768);
        
        table = new Table(skin);
        table.padTop(300);
        table.setFillParent(true);
        table.add(b1).padBottom(30).row();
        table.add(b2).row();
        
        stage.addActor(table);
        bExit.setPosition(75, Render.ALTO-140);
        tienda.setPosition(1000, Render.ALTO-140);
        configuracion.setPosition(1150, Render.ALTO-140);
        stage.addActor(configuracion);
        stage.addActor(tienda);
        stage.addActor(bExit);
        Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        Render.batch.begin();
        fondo.dibujar();
        Render.batch.end();
        

        stage.act(delta);
        stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stage.dispose();
        skin.dispose();
		
	}

}
