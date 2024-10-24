package Screens;

import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Inventario;
import Utiles.Jugador;
import Utiles.Render;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import ES.Entradas;

public class Tienda implements Screen {

    private Stage stage;
    private Screen level1Screen = new nivel1();
    private Screen level2Screen = new nivel2();

    private Screen level3Screen= new nivel3();
    private Screen level4Screen= new nivel4();
    private Screen level5Screen= new nivel5();

    private SpriteBatch batch;
    private ImageButton previousButton, nextButton;
    private ImageButton centralButton;
    private TextButton backButton;
    public static int currentSkin = 1;
    private final int MAX_SKIN = 4;
    private Imagen fondo;
    private Texture centralButtonImage;
    private Texture botonComprarImage;
    private ImageButton botonComprar;
    boolean[] estaGotIt = new boolean[4];
    	
    private List<Integer> inven = new ArrayList<>();
    
    private Image moneda;
    
    int precio_2=10;
    int precio_3=20;
    int precio_4=100;

    private Skin skin;
    private Skin skin2 = new Skin(Gdx.files.internal("ui/uiskin.json"));
    private Label l;

    Connection con;
	Statement stmt;
	ResultSet rset;

    public Tienda() {
    	skin = new Skin(Gdx.files.internal("uipixel/craftacular-ui.json"));
    	moneda = new Image(new Texture(Gdx.files.internal("MONEDA.png")));
    	moneda.setPosition(1150, Render.ALTO-140);
    	
    	l = new Label(Integer.toString(Jugador.getNumMonedas()),skin);
    	l.setPosition(1050, Render.ALTO-100);
    	l.setFontScale(1.7f);
    	l.setColor(Color.WHITE);
    	
        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
        inven = Inventario.getSkins();
        
        for(int i=0;i<4;i++) {
        	estaGotIt[i] = false;
        }
        
        for(int i=0;i<inven.size();i++) {
        	estaGotIt[inven.get(i)-1] = true;
        	
        }
        
        for(int i=0;i<4;i++) {
        	System.out.println(estaGotIt[i]);
        }

        // Configura los botones de siguiente y anterior nivel
        Texture prevButtonImage = new Texture(Gdx.files.internal("flecha_ant.png"));
        Texture nextButtonImage = new Texture(Gdx.files.internal("flecha_sig.png"));

        previousButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(prevButtonImage)));
        previousButton.setPosition(224, (Gdx.graphics.getHeight() / 2 - previousButton.getHeight() / 2)-100);
        previousButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentSkin > 1) {
                    currentSkin--;
                    updateLevelImage();
                    updateBotonImage();

                } else if (currentSkin ==1) {
                    currentSkin=MAX_SKIN;
                    updateLevelImage();
                    updateBotonImage();

                }
            }
        });
        stage.addActor(previousButton);

        nextButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextButtonImage)));
        nextButton.setPosition(Gdx.graphics.getWidth() - nextButton.getWidth() - 224, (Gdx.graphics.getHeight() / 2 - nextButton.getHeight() / 2)-100);
        nextButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (currentSkin < MAX_SKIN) {
                    currentSkin++;
                    updateLevelImage();
                    updateBotonImage();
                } else if (currentSkin == MAX_SKIN) {
                    currentSkin=1;
                    updateLevelImage();
                    updateBotonImage();

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
                currentSkin=1;
                Render.game.setScreen(new MenuJuego());

            }
        });
        stage.addActor(backButton);

        // Configura el botón central
        centralButtonImage = new Texture(Gdx.files.internal("skin-"+currentSkin+".png"));
        centralButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(centralButtonImage)));
        updateLevelImage();
        centralButton.setPosition(Gdx.graphics.getWidth() / 2 - centralButton.getWidth() / 2, (Gdx.graphics.getHeight() / 2 - centralButton.getHeight() / 2)-100);
        centralButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Aquí deberías cambiar la pantalla actual a la pantalla correspondiente al nivel en el que se encuentra el usuario
                if (currentSkin == 1) {
                	if(estaGotIt[0]) {
                		Jugador.setSkin_actual(currentSkin);
                		System.out.println(Jugador.getSkin_actual());
                		//Est� seleccionada, hay que a�adirle un tick
                		updateLevelImage();
                		
                	}
                } else if (currentSkin ==2) {
                	if(estaGotIt[1]) {
                		Jugador.setSkin_actual(currentSkin);
                		System.out.println(Jugador.getSkin_actual());
                		//Est� seleccionada, hay que a�adirle un tick
                		updateLevelImage();
                		updateBbdd(currentSkin);
                	}else{
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("No adquirida");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                	
                } else if(currentSkin == 3){
                	if(estaGotIt[2]) {
                		Jugador.setSkin_actual(currentSkin);
                		System.out.println(Jugador.getSkin_actual());
                		updateLevelImage();
                		updateBbdd(currentSkin);
                	}else {
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("No adquirida");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                } else if (currentSkin == 4) {
                	if(estaGotIt[3]) {
                		Jugador.setSkin_actual(currentSkin);
                		System.out.println(Jugador.getSkin_actual());
                		updateLevelImage();
                		updateBbdd(currentSkin);
                	}else {
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("No adquirida");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                }

            }

        });
        stage.addActor(centralButton);
        
        //Configura el boton de compra
        botonComprarImage = new Texture(Gdx.files.internal("boton-"+currentSkin+"-subido.png"));
        botonComprar = new ImageButton(new TextureRegionDrawable(new TextureRegion(botonComprarImage)));
        botonComprar.setPosition(Gdx.graphics.getWidth() / 2 - botonComprar.getWidth() / 2, (Gdx.graphics.getHeight() / 2 - botonComprar.getHeight() / 2)-310);
        botonComprar.addListener(new ClickListener() {
        	@Override
        	public void clicked(InputEvent event, float x,float y) {
        		// Aquí deberías cambiar la pantalla actual a la pantalla correspondiente al nivel en el que se encuentra el usuario
                if (currentSkin == 1) {

                } else if (currentSkin ==2) {
                	if(Jugador.getNumMonedas()>= precio_2) {
                		//Primero se te restan las monedas
                		Jugador.setMonedas(Jugador.getNumMonedas()-precio_2);
                		l.setText(Jugador.getNumMonedas());
                		//cambio la skin del bot�n a la de got-it
                		ponerGotIt();
                		//Pongo el bool a true para poder seleccionarla
                		estaGotIt[1] = true;
                		restarMonedas();
                		
                	}else {
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("Monedas insuficientes");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                } else if(currentSkin == 3){
                	if(Jugador.getNumMonedas()>= precio_3) {
                		//Primero se te restan las monedas
                		Jugador.setMonedas(Jugador.getNumMonedas()-precio_3);
                		l.setText(Jugador.getNumMonedas());
                		//cambio la skin del bot�n a la de got-it
                		ponerGotIt();
                		//Pongo el bool a true para poder seleccionarla
                		estaGotIt[2] = true;
                		restarMonedas();
                	}else {
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("Monedas insuficientes");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                } else if (currentSkin == 4) {
                	if(Jugador.getNumMonedas()>= precio_4) {
                		//Primero se te restan las monedas
                		Jugador.setMonedas(Jugador.getNumMonedas()-precio_4);
                		l.setText(Jugador.getNumMonedas());
                		//cambio la skin del bot�n a la de got-it
                		ponerGotIt();
                		//Pongo el bool a true para poder seleccionarla
                		estaGotIt[3] = true;
                		restarMonedas();
                	}else {
                		Dialog errorDialog = new Dialog("Error", skin2);
                        errorDialog.text("Monedas insuficientes");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                	}
                }
                
            }

        });
        stage.addActor(botonComprar);
        stage.addActor(moneda);
        stage.addActor(l);
    }
    
    private void restarMonedas() {
    	try {
    		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          	con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "ubd4230", "snakerush1234");
          	stmt = con.createStatement();
          	
          	String consulta = "UPDATE Jugador\n" + 
    			       "SET num_monedas = "+Jugador.getNumMonedas()+"\n" + 
    		       "where nombre_usuario= '"+Jugador.getUsuario()+"'";

          	stmt.executeUpdate(consulta);
    	   	 	stmt.close();
    	   	 	con.close();
    		}catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		
	}
    
    private void updateBbdd(int currentSkin) {
    	try {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      	con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "ubd4230", "snakerush1234");
      	stmt = con.createStatement();
      	
      	String consulta = "UPDATE Jugador\n" + 
			       "SET skin_actual = "+currentSkin+"\n" + 
		       "where nombre_usuario= '"+Jugador.getUsuario()+"'";
      	inven.add(currentSkin);
      	
      	Inventario.anyadirSkins(inven);
      	stmt.executeUpdate(consulta);
	   	 	stmt.close();
	   	 	con.close();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    private void updateLevelImage() {
    	if(Jugador.getSkin_actual() == currentSkin) {
    		Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin-" + currentSkin + "-selected.png"))));
            centralButton.getStyle().imageUp = buttonDrawable;
            centralButton.getStyle().imageDown = buttonDrawable;
    	}else {
    		Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("skin-" + currentSkin + ".png"))));
            centralButton.getStyle().imageUp = buttonDrawable;
            centralButton.getStyle().imageDown = buttonDrawable;
    	}
      
    }
    
    private void updateBotonImage() {
    	if(!estaGotIt[currentSkin-1]) {
    		Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("boton-" + currentSkin + "-subido.png"))));
        	botonComprar.getStyle().imageUp = buttonDrawable;
        	botonComprar.getStyle().imageDown = buttonDrawable;
    	}else {
    		Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("boton-" + 1 + "-subido.png"))));
        	botonComprar.getStyle().imageUp = buttonDrawable;
        	botonComprar.getStyle().imageDown = buttonDrawable;
    	}
    	
    }
    
    private void ponerGotIt() {
        Drawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("boton-" + 1 + "-subido.png"))));   
        botonComprar.getStyle().imageUp = buttonDrawable;
        botonComprar.getStyle().imageDown = buttonDrawable;
        
        //Acceso bbdd
        
        try {
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          	con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "ubd4230", "snakerush1234");
          	stmt = con.createStatement();
          	
          	String consulta = "INSERT INTO Inventario VALUES('"+Inventario.getUsuario()+"','"+currentSkin+"')";
          	//inven.set(currentSkin-1, currentSkin);
          	inven.add(currentSkin);
          	
          	Inventario.anyadirSkins(inven);
          	stmt.executeUpdate(consulta);
   	   	 	stmt.close();
   	   	 	con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
    public static int getCurrentLevel(){
        return currentSkin;
    }
    

    @Override
    public void show() {
        fondo = new Imagen("tienda.png");

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
        skin.dispose();

    }
}
