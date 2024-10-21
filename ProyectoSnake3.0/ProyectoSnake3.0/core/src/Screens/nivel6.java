package Screens;

import Objetos.Imagen;
import Objetos.Texto;
import Utiles.Render;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.*;
import com.mygdx.game.SnakeGame;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.*;
import java.util.*;
import java.util.List;

import com.badlogic.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class nivel6 implements Screen {
    private Array<Rectangle> obstaculo;
    public SnakeGame game;
    Texto marcador;
    Texto valor;
    private Imagen fondo;
    private SpriteBatch batch;

    int score;
    private Texto t;

    Connection con;
	Statement stmt;
	ResultSet rset;
	
	private List<String> top3;
	private Skin skin;
	
	private String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private String USER = "root";
    private String PASSWORD = "1234";
	
	String s;

    public nivel6() {
    	skin = new Skin(Gdx.files.internal("uipixel/craftacular-ui.json"));
    	top3 = new ArrayList<>();
        obstaculo = new Array<>();
        game = new SnakeGame(6, 622, obstaculo,1000);
        game.create();
        batch = Render.batch;
        fondo = new Imagen("test1.png");
        
        marcador = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        marcador.setStr("Marcador: ");
        marcador.setPosition(20, 40);
        
        try {
          	 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
          	 System.out.println("Estableciendo conexion...");
          	 con = DriverManager.getConnection(URL, USER, PASSWORD);
          	 stmt = con.createStatement();
          	 
          	 String consulta = "SELECT nombre_usuario,record FROM Jugador Order by record DESC limit 3";
          
          	 rset = stmt.executeQuery(consulta);
          	 
          	 while(rset.next()) {
          		top3.add(rset.getString(1)+" "+rset.getString(2));
          	}
          	 
          	stmt.close();
       		con.close();
       
	    }catch (SQLException e) {
	   		// TODO Auto-generated catch block
	   		e.printStackTrace();
	   	}
        
        s = "Record:\n" + top3.get(0)+"\n"+ top3.get(1)+"\n"+ top3.get(2);
        
        t = new Texto("fuentes/pixel.ttf",40,Color.WHITE,true);
        t.setStr(s);
        t.setPosition(1070, Render.ALTO-40);
        
        
      valor = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
      valor.setPosition(180, 40);
    }

    @Override
    public void render(float delta) {
        game.render();
        score = game.getScore();
        batch.begin();
        fondo.dibujar();
        marcador.dibujar();
        t.dibujar();
        valor.setStr(String.valueOf(score));
        
        valor.dibujar();
       
        batch.end();
    }

    @Override
    public void show() {
        fondo.setSize(1280, 768);
        fondo.setTrans(0);
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
        game.dispose();
    }
}
