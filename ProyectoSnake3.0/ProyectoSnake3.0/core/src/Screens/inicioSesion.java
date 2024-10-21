package Screens;

import Utiles.Asset;
import Utiles.Inventario;
import Utiles.Jugador;
import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Objetos.Imagen;

import com.badlogic.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


public class inicioSesion implements Screen, Input.TextInputListener {

    private Stage stage;
    private Skin skin,skin2;
    private Table table;
    private TextField usernameField;
    private TextField passwordField;
    private String usuario,contrasena;
    private List<Integer> inventario = new ArrayList<>();
    
    String consultaUsu,consultaPass;
    String consultaMonedas,consultaNivel,consultaSkin_act,consultaRecord;
    
    int monedas,nivel,skin_act,record;
    
    public Jugador j;
    public Inventario i;
    public String consulta2;
    
    private String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private String USER = "root";
    private String PASSWORD = "1234";
    
    Connection con;
	Statement stmt;
	ResultSet rset;

    TextButton backButton;
    Imagen fondo;
    
    /*Texture b1 = new Texture(Gdx.files.internal("boton_atras_niveles_subido.png"));
    Texture b2 = new Texture(Gdx.files.internal("boton_atras_niveles_bajado.png"));

    TextureRegionDrawable bt1 = new TextureRegionDrawable(b1);
    TextureRegionDrawable bt2 = new TextureRegionDrawable(b2);
    
    Texture b1t = new Texture(Gdx.files.internal("boton_confirmar_subido.png"));
    Texture b2t = new Texture(Gdx.files.internal("boton_confirmar_bajado.png"));

    TextureRegionDrawable bt1t = new TextureRegionDrawable(b1t);
    TextureRegionDrawable bt2t = new TextureRegionDrawable(b2t);*/
    
    Button b,bt;

    public inicioSesion() throws SQLException {
        stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
        skin = new Skin(Gdx.files.internal("uipixel/craftacular-ui.json"));
        skin2 = new Skin(Gdx.files.internal("ui/uiskin.json"));
        
        b = new Button(Asset.bt1a,Asset.bt2a);
        bt = new Button(Asset.bt1tc,Asset.bt2tc);
        b.addListener(new ClickListener() {
   
        	public void clicked(InputEvent event, float x, float y) {
                Render.game.setScreen(new pantallaSesion());
            }
                
        });
        
        fondo = new Imagen("pantallaFondo.png");
        fondo.setSize(1280,768);

        System.out.println("Controlador registrado");

      
        usernameField = new TextField("",skin);
        passwordField = new TextField("",skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        
        backButton = new TextButton("Volver",skin2);
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Render.game.setScreen(new pantallaSesion());
            }
        });
        
        backButton.setPosition(500, 400);

        TextButton loginButton = new TextButton("Confirmar", skin2);
        bt.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                usuario=usernameField.getText();
                contrasena=passwordField.getText();
                
                try {
               	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
               	 System.out.println("Estableciendo conexion...");
               	 con = DriverManager.getConnection(URL, USER, PASSWORD);
               	 stmt = con.createStatement();
               	 
               	 String consulta = "SELECT * FROM Jugador where nombre_usuario= '"+usuario+"'";
               
               	 rset = stmt.executeQuery(consulta);
           
               	 System.out.println("Finalizando transacci�n...");
               	 System.out.println("Resultado de la consulta:");
               	 
               	 while(rset.next()) {
           
               		consultaUsu = rset.getString(1);
                    consultaPass = rset.getString(2);
                    consultaMonedas = rset.getString(3);
                    consultaNivel = rset.getString(4);
                    consultaSkin_act = rset.getString(5);
                    consultaRecord = rset.getString(6);
                    
               	 }
               	 
               	consulta2 = "SELECT cod_skin FROM Inventario where usuario= '"+usuario+"'";
        		rset = stmt.executeQuery(consulta2);
        		while(rset.next()) {
               		int ncol = rset.getMetaData().getColumnCount();
               		for (int i = 1; i <= ncol; i++) { // for: Itera sobre las columnas de la fila
               			int n = Integer.parseInt(rset.getString(i));
                		inventario.add(n);
                	}
               	}      
                 
           		stmt.close();
           		con.close();
           	} catch (SQLException e) {
           		// TODO Auto-generated catch block
           		e.printStackTrace();
           	}
                
 
                
                //System.out.println(usuario + " -> " +consultaUsu);
            	//System.out.println(contrase�a + " -> " +consultaPass);
                
                if(usuario.equals(consultaUsu) && contrasena.equals(consultaPass)){ 
                	monedas = Integer.parseInt(consultaMonedas);
                    nivel = Integer.parseInt(consultaNivel);
                    skin_act = Integer.parseInt(consultaSkin_act);
                    record = Integer.parseInt(consultaRecord);
                    j = new Jugador(consultaUsu,consultaPass,monedas,nivel,skin_act,record);
                    i = new Inventario(consultaUsu,inventario);
                    Render.game.setScreen(new MenuJuego());
                }else{
                    Dialog errorDialog = new Dialog("Error", skin2);
                    errorDialog.text("Incorrect username or password");
                    errorDialog.button("OK", true);
                    errorDialog.show(stage);
                }
            }
        });

        table = new Table(skin2);
        table.setFillParent(true);
        table.padTop(250);
        table.add(new Label("Username:", skin)).padRight(10).padBottom(50);
        table.add(usernameField).padBottom(50).row();
        table.add(new Label("Password:", skin)).padRight(10).padBottom(50);
        table.add(passwordField).padBottom(50).row();
        table.add(bt).colspan(10).padBottom(10);
       
        //table.add(backButton);

        stage.addActor(table);
        b.setPosition(75, Render.ALTO-140);
        stage.addActor(b);
        Gdx.input.setInputProcessor(stage);
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

    @Override
    public void input(String text) {

    }

    @Override
    public void canceled() {

    }
}
