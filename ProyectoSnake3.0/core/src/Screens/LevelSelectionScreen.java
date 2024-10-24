package Screens;

import Objetos.Imagen;
import Utiles.Jugador;
import Utiles.Render;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelSelectionScreen implements Screen {

    private Stage stage;
    private Screen level1Screen = new nivel1();
    private Screen level2Screen = new nivel2();

    private Screen level3Screen= new nivel3();
    private Screen level4Screen= new nivel4();
    private Screen level5Screen= new nivel5();

    private Screen level6Screen = new nivel6();


    private SpriteBatch batch;
    private ImageButton previousButton, nextButton;
    private ImageButton centralButton;
    private TextButton backButton;
    private Image levelImage;
    public static int currentLevel = 1;
    private final int MAX_LEVEL = 6;
    private Imagen fondo;
    private Texture centralButtonImage;
    
    private static String usuario = Jugador.getUsuario();
	private static String contrasena = Jugador.getContrasena();
	private static int numMonedas = Jugador.getNumMonedas();
	private static int nivel_actual = Jugador.getNivel_actual();
	private static int skin_actual = Jugador.getSkin_actual();
	private static int record = Jugador.getRecord();
    
    Connection con;
	Statement stmt;
	ResultSet rset;
	
	private Skin skin;
	

    public LevelSelectionScreen() {
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        
        try {
        	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        	 System.out.println("Estableciendo conexion...");
        	 con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "ubd4230", "snakerush1234");
        	 stmt = con.createStatement();
        	 
        	 String consulta = "UPDATE Jugador\n" + 
				       "SET nivel_actual = "+Jugador.getNivel_actual()+"\n" + 
				       "where nombre_usuario= '"+Jugador.getUsuario()+"'";
        	 
        	 stmt.executeUpdate(consulta);
          
    		stmt.close();
    		con.close();
    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
    
        // Configura los botones de siguiente y anterior nivel
        Texture prevButtonImage = new Texture(Gdx.files.internal("flecha_ant.png"));
        Texture nextButtonImage = new Texture(Gdx.files.internal("flecha_sig.png"));

        previousButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(prevButtonImage)));
        previousButton.setPosition(32, Gdx.graphics.getHeight() / 2 - previousButton.getHeight() / 2);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentLevel > 1) {
                    currentLevel--;
                    updateLevelImage();
                } else if (currentLevel == 1) {
                    currentLevel=MAX_LEVEL;
                    updateLevelImage();
                }
            }
        });
        stage.addActor(previousButton);

        nextButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextButtonImage)));
        nextButton.setPosition(Gdx.graphics.getWidth() - nextButton.getWidth() - 32, Gdx.graphics.getHeight() / 2 - nextButton.getHeight() / 2);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentLevel < MAX_LEVEL) {
                    currentLevel++;
                    updateLevelImage();
                } else if (currentLevel == MAX_LEVEL) {
                    currentLevel=1;
                    updateLevelImage();
                }
            }
        });
        stage.addActor(nextButton);

        // Configura el botón de volver
        Texture backButtonUp = new Texture(Gdx.files.internal("button-up.png"));
        Texture buttonDown = new Texture(Gdx.files.internal("button-down.png"));
        backButton = new TextButton(" ", new TextButton.TextButtonStyle(
                new TextureRegionDrawable(new TextureRegion(backButtonUp)), // Imagen del botón "arriba"
                new TextureRegionDrawable(new TextureRegion(buttonDown)), // Imagen del botón "abajo"
                null,
                new BitmapFont()));
        backButton.setPosition(0, Gdx.graphics.getHeight() - backButton.getHeight());
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Aquí deberías cambiar la pantalla actual a la pantalla anterior
                currentLevel=1;
                Render.game.setScreen(new MenuJuego());

            }
        });
        stage.addActor(backButton);

        // Configura el botón central
        nivel_actual = Jugador.getNivel_actual();
        System.out.println(nivel_actual);
        centralButtonImage = new Texture(Gdx.files.internal("level-"+currentLevel+".png"));
        centralButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(centralButtonImage)));
        centralButton.setPosition(Gdx.graphics.getWidth() / 2 - centralButton.getWidth() / 2, Gdx.graphics.getHeight() / 2 - centralButton.getHeight() / 2);
        centralButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Aquí deberías cambiar la pantalla actual a la pantalla correspondiente al nivel en el que se encuentra el usuario
                if (currentLevel == 1){
                	Render.game.setScreen(level1Screen);
                } else if (currentLevel ==2) {
                	if(Jugador.getNivel_actual() < currentLevel) {
                		Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Desbloquea el nivel anterior");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}else {
                		Render.game.setScreen(level2Screen);
                	}
                } else if(currentLevel == 3){
                	if(Jugador.getNivel_actual() < currentLevel) {
                		Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Desbloquea el nivel anterior");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}else {
                		Render.game.setScreen(level3Screen);
                	}
                } else if (currentLevel == 4) {
                	if(Jugador.getNivel_actual() < currentLevel) {
                		Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Desbloquea el nivel anterior");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}else {
                		Render.game.setScreen(level4Screen);
                	}
                } else if (currentLevel == 5) {
                	if(Jugador.getNivel_actual() < currentLevel) {
                		Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Desbloquea el nivel anterior");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}else {
                		Render.game.setScreen(level5Screen);
                	}
                } else if(currentLevel == 6){
                	if(Jugador.getNivel_actual() < currentLevel) {
                		Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Desbloquea el nivel anterior");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}else {
                		Render.game.setScreen(level6Screen);
                	}
                }
                //currentLevel=1;
            }
        });
        stage.addActor(centralButton);

    }

    private void updateLevelImage() {
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("level-" + currentLevel + ".png"))));
        centralButton.getStyle().imageUp = buttonDrawable;
        centralButton.getStyle().imageDown = buttonDrawable;
    }
    public static int getCurrentLevel(){
        return currentLevel;
    }

    @Override
    public void show() {
        fondo = new Imagen("fondo_selniv.png");

        fondo.setSize(1280, 768);
        fondo.setTrans(1);
        Gdx.input.setInputProcessor(stage);
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
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();


    }
}

