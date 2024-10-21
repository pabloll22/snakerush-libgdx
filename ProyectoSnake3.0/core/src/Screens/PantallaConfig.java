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
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.game.MyGdxGame;

public class PantallaConfig implements Screen {
    Stage stage;

    Imagen fondo=new Imagen("SETTINGS_SCREEN.png");

    Table table;

    Texture bsal1=new Texture(Gdx.files.internal("menu-up2.png")),
            bsal2=new Texture(Gdx.files.internal("menu-down2.png")),
            bbor1=new Texture(Gdx.files.internal("delete-up2.png")),
            bbor2=new Texture(Gdx.files.internal("delete-down2.png"));
    Texture bmusica=new Texture(Gdx.files.internal("boton_volumen_sin_pulsar.png"));
    TextureRegionDrawable   bat1=new TextureRegionDrawable(bsal1),
                            bat2=new TextureRegionDrawable(bsal2),
                            bbt1=new TextureRegionDrawable(bbor1),
                            bbt2=new TextureRegionDrawable(bbor2);

    Skin skin;
    ImageButton musica=new ImageButton(new TextureRegionDrawable(new TextureRegion(bmusica)));
    ImageButtonStyle musicaoff = new ImageButtonStyle();
    ImageButtonStyle musicaon = new ImageButtonStyle();
    boolean sonido;
    
    Connection con;
	Statement stmt;
	ResultSet rset;


    public PantallaConfig(){
         skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        stage=new Stage(new FitViewport(Render.ANCHO,Render.ALTO));
        musicaoff.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_silencio.png")));
        musicaon.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_volumen_sin_pulsar.png")));
        if(MyGdxGame.musica.getVolume()!=0) {
        	sonido=true;
        	musica.setStyle(musicaon);
        	
        }else {
        	sonido=false;
        	musica.setStyle(musicaoff);
        }
        Button sal = new Button(bat1,bat2);
        Button borr=new Button(bbt1,bbt2);
        musica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sonido=!sonido;
                if(sonido){
                    musica.setStyle(musicaon);
                    MyGdxGame.musica.setVolume(0.05f);
                }else{
                    musica.setStyle(musicaoff);
                    MyGdxGame.musica.setVolume(0);

                }
            }
        });

        sal.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                 Render.game.setScreen(new MenuJuego());
            }
        });
        
        
        borr.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
            	String nombre = Jugador.getUsuario();
            	try {
                  	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
                  	 System.out.println("Estableciendo conexion...");
                  	 con = DriverManager.getConnection("jdbc:oracle:thin:@afrodita.lcc.uma.es:1521:Apolo", "ubd4230", "snakerush1234");
                  	 stmt = con.createStatement();
                  	 
                  	String consulta = "DELETE From Jugador WHERE nombre_usuario = '"+nombre+"'";
                  	String consulta2 = "DELETE From inventario WHERE usuario = '"+nombre+"'";
                  
                  	 stmt.executeUpdate(consulta);
                  	 stmt.executeUpdate(consulta2);
                    
              		stmt.close();
              		con.close();
              	} catch (SQLException e) {
              		// TODO Auto-generated catch block
              		e.printStackTrace();
              	}
            	
            	Render.game.setScreen(new pantallaSesion());
            }
        });
        
        
        stage.addActor(sal);
        musica.setPosition(Render.ANCHO-150,Render.ALTO-165);
        table=new Table(skin);
        table.padTop(250);
        stage.addActor(musica);
        table.add(sal).padBottom(30).row();
        table.add(borr).row();
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }




    @Override
    public void show() {
        fondo.setSize(1280,768);

        fondo.setTrans(1);


    }

    @Override
    public void render(float delta) {
        Render.limpiarPantalla(0,0,0);
        Render.batch.begin();
        fondo.dibujar();



        Render.batch.end();
        stage.draw();
        stage.act(delta);



    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
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
