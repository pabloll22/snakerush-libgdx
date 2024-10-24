package com.mygdx.game;

import Juego.Snake;
import Juego.Snake.Direction;
import Objetos.Imagen;
import Screens.*;
import Utiles.Inventario;
import Utiles.Jugador;
import Utiles.Render;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SnakeGame extends Game {
    public static final int WORLD_WIDTH = 1280;
    public static final int WORLD_HEIGHT = 768;
    public static final int CONST = 8;

    // Objetos del juego
    private Texture snakeHeadTexture;
    private Texture level1;
    private Texture level2;
    private Texture level3;
    private Texture level4,level5;
    private OrthographicCamera camara;
    private Texture snakeBodyTexture;
    private Texture foodTexture;
    private SpriteBatch batch;
    private Snake snake;
    private Rectangle food;
    private Rectangle puerta;
    private int score;
    private int SnakeIn=3;
    private float timeSinceLastMove = 0;
    private float moveDelay = 0.1F; // La serpiente se moverá cada 0.1 segundos
    private int level;
    private int puntuacion_maxima;

    private Array<Rectangle>obstaculo;
    private boolean pausa=false;

    private Stage escenarioPausa;
    private Skin skin;
    private Button botonReanudar;
    private ImageButton  botonMusica;
    private Button botonMenuPrincipal;

    private Texture puertaTexture;
    private Sprite puertaSprite;
    private boolean desaparece=false;
    private boolean sonido=true;
    ImageButtonStyle musicaoff = new ImageButtonStyle();
    ImageButtonStyle musicaon = new ImageButtonStyle();
    
    private String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private String USER = "root";
    private String PASSWORD = "1234";
    
    Connection con;
	Statement stmt;
	
	private int monedas;

    public SnakeGame(int nivel,int max, Array<Rectangle> obs,int monedas){
        level=nivel;
        puntuacion_maxima=max;
        obstaculo=obs;
        this.monedas = monedas;
    }

    @Override
    public void create() {
        // Cargar el skin de los botones
    	skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));

        // Crear los botones
        botonReanudar = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("resume-up.png"))),new TextureRegionDrawable(new Texture(Gdx.files.internal("resume-down.png"))));
        botonMusica=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("boton_volumen_sin_pulsar.png")))));
        musicaoff.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_silencio.png")));
        musicaon.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_volumen_sin_pulsar.png")));
        botonMenuPrincipal = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("menu-up.png"))),new TextureRegionDrawable(new Texture(Gdx.files.internal("menu-down.png"))));
        
        // Añadir los listeners de los botones
        botonMusica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sonido=!sonido;
                if(sonido){
                    botonMusica.setStyle(musicaon);
                }else{
                    botonMusica.setStyle(musicaoff);

                }
            }
        });
        botonReanudar.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pausa = false;
            }
        });

        

        botonMenuPrincipal.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Aquí iría el código para volver al menú principal
                Render.game.setScreen(new MenuJuego());
            }
        });

        // Crear el escenario de pausa y añadir los botones
        escenarioPausa = new Stage(new FitViewport(1280,768));
        Table tabla = new Table(skin);
       
        Texture texture = new Texture("menu_pausa.png");
        TextureRegionDrawable textureDrawable = new TextureRegionDrawable(new TextureRegion(texture));

        tabla.setWidth(645);
        tabla.setHeight(680);
        
        //tabla.setFillParent(true);

        tabla.setBackground(textureDrawable);

        tabla.padTop(230);
        tabla.add(botonReanudar).padBottom(30).row();
       
        //escenarioPausa.addActor(botonMusica);
        
        tabla.add(botonMenuPrincipal).padBottom(30).row();
        tabla.add(botonMusica).padBottom(30).padLeft(400).row();
        escenarioPausa.addActor(tabla);
        float tableX = (escenarioPausa.getWidth() - tabla.getWidth()) / 2;
        float tableY = (escenarioPausa.getHeight() - tabla.getHeight()) / 2;
        tabla.setPosition(tableX, tableY);
        Gdx.input.setInputProcessor(escenarioPausa);



        camara= new OrthographicCamera();
        camara.setToOrtho(false,1280,768);
        level1 = new Texture(Gdx.files.internal("nivel1.png"));
        level2 = new Texture(Gdx.files.internal("nivel2.png"));
        level3 = new Texture(Gdx.files.internal("nivel3.png"));
        level4 = new Texture(Gdx.files.internal("nivel4.png"));
        level5 = new Texture(Gdx.files.internal("nivel5.png"));

        //Trabajar en el cambio de skin con la base de datos
        snakeBodyTexture = new Texture(Gdx.files.internal("bodysnake-"+Jugador.getSkin_actual()+".png"));
        foodTexture = new Texture(Gdx.files.internal("manzana.png"));


        // Inicializar la serpiente
        Array<Rectangle> snakeParts = new Array<Rectangle>();

        snakeParts.add(new Rectangle(448,32,32,32));
        snakeParts.add(new Rectangle(416,32,32,32));


        snake = new Snake(snakeParts,Direction.RIGHT);

        // Inicializar la comida
        spawnFood();

        //Crear Puerta
        puertaTexture = new Texture(Gdx.files.internal("puerta_final.png"));
        puertaSprite = new Sprite(puertaTexture);
        puertaSprite.setAlpha(0);
        puerta = null;

        // Inicializar la puntuación del jugador
        score = 0;

        //Inicializar el obheto SpriteBatch
        batch = new SpriteBatch();

        // Carga la imagen desde el archivo en la carpeta assets

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camara.update();
        Gdx.input.setInputProcessor(escenarioPausa);

        // Dibujar el fondo
        batch.begin();
        switch (level){
            case 1: batch.draw(level1, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
                break;
            case 2: batch.draw(level2, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
                break;
            case 3: batch.draw(level3, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
                break;
            case 4: batch.draw(level4, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
                break;
            case 5: batch.draw(level5, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
                break;
            case 6: batch.draw(level1, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        }

        batch.end();

        // Dibujar la serpiente
        batch.begin();
        for(Rectangle rect: snake.getParts()){
            batch.draw(snakeBodyTexture,rect.x,rect.y,rect.width,rect.height);
        }
        batch.end();

        // Dibujar la comida
        if(desaparece==false){
            batch.begin();
            batch.draw(foodTexture, food.x, food.y);
            batch.end();
        }

        // Dibujar la puerta
        if(puerta!=null){
            batch.begin();
            batch.draw(puertaSprite,puerta.x,puerta.y);
            batch.end();
        }

        timeSinceLastMove += Gdx.graphics.getDeltaTime();

        if (timeSinceLastMove >= moveDelay) {
            // Actualiza la posición de la serpiente
            if (!pausa){
                snake.move();
            }
            timeSinceLastMove = 0; // Reinicia el temporizador
        }

        // Verificar si la serpiente ha colisionado con la comida
        if (snake.head().overlaps(food)) {
            score++;
            //verificamos si sobrepasa la puntuacion_maxima
            boolean ok = false;
            if (score == puntuacion_maxima) {
                puertaSprite.setAlpha(1);
                spawnPuerta();


            }
            snake.grow();
            if (puerta != null && ok == false) {
                ok = true;
            }
            if (ok != true) {
                spawnFood();
            } else {
                food = new Rectangle();
                desaparece = true;
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)||Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            pausa=true;
        }
        if (pausa) {
            escenarioPausa.act(Gdx.graphics.getDeltaTime());
            escenarioPausa.draw();
        }

        // Verificar si la serpiente ha colisionado con las paredes o su propio cuerpo o con un obstaculo
        if (snake.collidesWithWalls() || snake.collidesWithBody() || colisionaObstaculo()){
        	if(level == 6 && score>Jugador.getRecord()) {
        		Jugador.setRecord(score);
        		try {
            		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            		con = DriverManager.getConnection(URL, USER, PASSWORD);
                  	stmt = con.createStatement();
                  	
                  	String consulta = "UPDATE Jugador\n" + 
            			       "SET record = "+Jugador.getRecord()+"\n" + 
            		       "where nombre_usuario= '"+Jugador.getUsuario()+"'";

                  	stmt.executeUpdate(consulta);
            	   	 	stmt.close();
            	   	 	con.close();
            		}catch (SQLException e) {
            			// TODO Auto-generated catch block
            			e.printStackTrace();
            		}
        	}
            Render.game.setScreen(new gameOver());
        }

        // Verificar las entradas del usuario
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
            snake.setDirection(Snake.Direction.LEFT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
            snake.setDirection(Snake.Direction.RIGHT);
        } else if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {
            snake.setDirection(Snake.Direction.UP);
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {
            snake.setDirection(Snake.Direction.DOWN);
        }

        //verificar si la puerta choca
        if(puerta!=null && puerta.overlaps(snake.head())){
        	if(!(Jugador.getNivel_actual() > level)) {
        		Jugador.setNivel_actual(Jugador.getNivel_actual()+1);
        		
        	}
        	Jugador.setMonedas(Jugador.getNumMonedas()+monedas);
        	try {
        		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        		con = DriverManager.getConnection(URL, USER, PASSWORD);
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
            Render.game.setScreen(new LevelSelectionScreen());
            //Meter que en la base de datos el valor del nivel haga ++
        }
    }

    double offset = 3;

    public int getScore() {
        return score;
    }



    private void spawnFood() {
        int x = 32 * (MathUtils.random(7, 32));
        int y = 32 * (MathUtils.random(0, 23));
        food = new Rectangle();
        food.set(x, y, 32, 32);

        boolean overlap = true;
        while (overlap) {
            overlap = false;
            for(Rectangle r : snake.getParts()){
                if(food.overlaps(r)){
                    int c = 32 * (MathUtils.random(7, 32));
                    int v = 32 * (MathUtils.random(0, 23));
                    food.set(c, v, 32, 32);
                    overlap = true;
                    break;
                }
            }
            if (!overlap) {
                for(Rectangle s : obstaculo){
                    if(food.contains(s) || food.overlaps(s)){
                        int p = 32 * (MathUtils.random(7, 32));
                        int l = 32 * (MathUtils.random(0, 23));
                        food.set(p, l, 32, 32);
                        overlap = true;
                        break;
                    }
                }
            }
        }
    }

    private void spawnPuerta(){
        int x = 32 * (MathUtils.random(7, 31));
        int y = 32 * (MathUtils.random(0, 22));
        puerta = new Rectangle();
        puerta.set(x, y, 64, 64);
        for(Rectangle r : snake.getParts()){
            if(puerta.overlaps(r)){
                int c = 32 * (MathUtils.random(7, 31));
                int v = 32 * (MathUtils.random(0, 22));
                puerta = new Rectangle();
                puerta.set(c, v, 64, 64);
            }
        }
        for(Rectangle s : obstaculo){
            if(puerta.overlaps(s)){
                int p = 32 * (MathUtils.random(7, 31));
                int l = 32 * (MathUtils.random(0, 22));
                puerta = new Rectangle();
                puerta.set(p, l, 64, 64);
            }
        }
    }


    private boolean colisionaObstaculo() {
        boolean ok = false;
        for (Rectangle r : obstaculo) {
            if (snake.head().overlaps(r)) {
                ok= true;
            }
        }
        return ok;
    }


    @Override
    public void dispose() {
        /*batch.dispose();
        // Libera la memoria de la imagen al finalizar
        image.dispose();*/

        // Liberar la memoria de las texturas y el objeto SpriteBatch
        snakeHeadTexture.dispose();
        snakeBodyTexture.dispose();
        foodTexture.dispose();
        batch.dispose();
        level1.dispose();
        level2.dispose();
        level3.dispose();
        level4.dispose();
        level5.dispose();
        skin.dispose();
        escenarioPausa.dispose();
        puertaTexture.dispose();

    }
}
