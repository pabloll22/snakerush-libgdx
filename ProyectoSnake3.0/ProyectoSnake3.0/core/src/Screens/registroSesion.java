package Screens;

import Utiles.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Objetos.Imagen;

import DataBase.*;

public class registroSesion implements Screen {

    private Stage stage;
    private Skin skin,skin2;
    private Table table;
    private TextField usernameField;
    private TextField passwordField;
    private TextField passwordField2;
    private String usuario,contrasena,confirmacionC;
    
    Connection con;
	Statement stmt;
	ResultSet rset;
	
	int monedas = 0;
    int nvl_act = 1;
    int skin_actual = 1;
    int record = 0;
    Imagen fondo;
    
    private List<String> usuarios = new ArrayList<>();
    
    private String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private String USER = "root";
    private String PASSWORD = "1234";
    
    public OracleDBConnection bd;
    
    /*Texture b1 = new Texture(Gdx.files.internal("boton_atras_niveles_subido.png"));
    Texture b2 = new Texture(Gdx.files.internal("boton_atras_niveles_bajado.png"));

    TextureRegionDrawable bt1 = new TextureRegionDrawable(b1);
    TextureRegionDrawable bt2 = new TextureRegionDrawable(b2);
    
    Texture b1t = new Texture(Gdx.files.internal("boton_confirmar_subido.png"));
    Texture b2t = new Texture(Gdx.files.internal("boton_confirmar_bajado.png"));

    TextureRegionDrawable bt1t = new TextureRegionDrawable(b1t);
    TextureRegionDrawable bt2t = new TextureRegionDrawable(b2t);*/
    
    Button b,bt;

    public registroSesion(){
        stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        skin2 = new Skin(Gdx.files.internal("uipixel/craftacular-ui.json"));
        
        fondo = new Imagen("pantallaFondo.png");
        fondo.setSize(1280,768);
        
        b = new Button(Asset.bt1a,Asset.bt2a);
        bt = new Button(Asset.bt1tc,Asset.bt2tc);
        b.addListener(new ClickListener() {
   
        	public void clicked(InputEvent event, float x, float y) {
                Render.game.setScreen(new pantallaSesion());
            }
                
        });

        usernameField = new TextField("",skin2);
        passwordField = new TextField("",skin2);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        passwordField2 = new TextField("",skin2);
        passwordField2.setPasswordMode(true);
        passwordField2.setPasswordCharacter('*');
        
        try {
          	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          	 System.out.println("Estableciendo conexion...");
          	 con = DriverManager.getConnection(URL, USER, PASSWORD);
          	 stmt = con.createStatement();
          	 
          	 String consulta = "SELECT nombre_usuario FROM Jugador";
          
          	 rset = stmt.executeQuery(consulta);
          	 
          	 while(rset.next()){
          		usuarios.add(rset.getString(1));         
          	 }
          	 
          	 System.out.println(usuarios);
            
      		stmt.close();
      		con.close();
      	} catch (SQLException e) {
      		// TODO Auto-generated catch block
      		e.printStackTrace();
      	}

        TextButton signin = new TextButton("Registrar", skin);
        bt.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                usuario=usernameField.getText();
                contrasena=passwordField.getText();
                confirmacionC = passwordField2.getText();
                
                if(usuario.isEmpty()) {
                	Dialog errorDialog = new Dialog("Error", skin);
                    errorDialog.text("Select an username");
                    errorDialog.button("OK", true);
                    errorDialog.show(stage);
                }else if(yaExiste(usuarios,usuario)) {
                	Dialog errorDialog = new Dialog("Error", skin);
                    errorDialog.text("Username already taken");
                    errorDialog.button("OK", true);
                    errorDialog.show(stage);
                }else {
                	if(contrasena.equals(confirmacionC)) {
                    	try {
        					DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        	              	stmt = DataBase.OracleDBConnection.conn.createStatement();
        	              	
        	              	String consulta = "INSERT INTO Jugador VALUES('"+usuario+"','"+contrasena+"','"+monedas+"','"+nvl_act+"','"+skin_actual+"','"+record+"')";
        	              	String consulta2 = "INSERT INTO Inventario VALUES('"+usuario+"','"+skin_actual+"')";
        	              	stmt.executeUpdate(consulta);
        	              	stmt.executeUpdate(consulta2);
        	       	   	 	stmt.close();
        	       	   	 	con.close();
        				} catch (SQLException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    	Render.game.setScreen(new pantallaSesion());
                    }else {
                    	Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Different passwords");
                        errorDialog.button("OK", true);
                        errorDialog.show(stage);
                    }
                }
               
                
                
            }
        });
        
        

        TextButton backButton = new TextButton("Volver",skin);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Render.game.setScreen(new pantallaSesion());
            }
        });

        table = new Table(skin);
        table.setFillParent(true);
        table.padTop(300);
        table.add(new Label("Username:", skin2)).padRight(10).padBottom(50);
        table.add(usernameField).padBottom(50).row();
        table.add(new Label("Password:", skin2)).padRight(10).padBottom(50);
        table.add(passwordField).padBottom(50).row();
        table.add(new Label("Confirm password:", skin2)).padRight(10).padBottom(50);
        table.add(passwordField2).padBottom(50).row();
        table.add(bt).colspan(10).padBottom(10);
        //table.add(backButton).padBottom(10);

        stage.addActor(table);
        b.setPosition(75, Render.ALTO-140);
        stage.addActor(b);
        Gdx.input.setInputProcessor(stage);
    }

    protected boolean yaExiste(List<String> usuarios2, String usuario2) {
		boolean ok = false;
		for(int i=0;i<usuarios2.size();i++) {
			if(usuarios2.get(i).equals(usuario2)) {
				ok = true;
			}
		}
		return ok;
	}

	@Override
    public void show() {

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
