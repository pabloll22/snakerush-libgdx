package com.mygdx.game;

import Juego.Snake;
import Objetos.Imagen;
import Objetos.Texto;
import Screens.MenuJuego;
import Screens.PantallaMenu;
import Utiles.Render;
import box2dLight.Light;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
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
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class SnakeGame2 extends Game {

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
    private Texture snakeBodyTexture,backgroundTexture,snakeBodyTexture2;
    private Texture foodTexture,foodTexture2,speedTexture,sizeTexture,DoubleTexture;
    private SpriteBatch batch;
    private Snake snake,snake2;
    private Rectangle food;
    private static int score1;
	private static int score2;
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
    Imagen fondo;
    public static int loser=0;
    private boolean sonido=true;
    ImageButtonStyle musicaoff = new ImageButtonStyle();
    ImageButtonStyle musicaon = new ImageButtonStyle();
    private Rectangle food2, Double, speed, size;

    private float timeSinceLastMove1 = 0, timeSinceLastMove2 = 0;
    private float moveDelay1 = 0.1F, moveDelay2 = 0.1F; // La serpiente se moverá cada 0.1 segundos

    private boolean DoubleActive = false, speedActive = false, sizeActive = false;
    private boolean spawn = false;
    private int n = 0;
    private float timer=0;
    private int currentFruit=0;
   // Texto marcador1,marcador2,valor1,valor2;

    @Override
    public void create() {
        camara= new OrthographicCamera();
        camara.setToOrtho(false,1280,768);
        backgroundTexture = new Texture(Gdx.files.internal("nivel1.png"));
        snakeBodyTexture = new Texture(Gdx.files.internal("cuerpo.png"));
        snakeBodyTexture2 = new Texture(Gdx.files.internal("azul.png"));
        foodTexture = new Texture(Gdx.files.internal("manzana.png"));
        foodTexture2 = new Texture(Gdx.files.internal("manzana.png"));
        DoubleTexture = new Texture(Gdx.files.internal("cereza.png"));
        speedTexture = new Texture(Gdx.files.internal("pera.png"));
        sizeTexture = new Texture(Gdx.files.internal("melon.png"));
        skin = new Skin(Gdx.files.internal("default/skin/uiskin.json"));
        /*marcador1 = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        marcador1.setStr("Marcador1: ");
        marcador1.setPosition(256, 40);

        marcador2 = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        marcador2.setStr("Marcador2: ");
        marcador2.setPosition(256+3226, 40);

        valor1 = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        valor1.setPosition(186, 40);

        valor2 = new Texto("fuentes/JANSINA.ttf", 40, Color.WHITE, true);
        valor2.setPosition(256+3226, 40);*/

        // Crear los botones
        botonReanudar = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("resume-up.png"))),new TextureRegionDrawable(new Texture(Gdx.files.internal("resume-down.png"))));
        botonMusica=new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("boton_volumen_sin_pulsar.png")))));
        musicaoff.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_silencio.png")));
        musicaon.up = new TextureRegionDrawable(new TextureRegion(new Texture("boton_volumen_sin_pulsar.png")));
        botonMenuPrincipal = new Button(new TextureRegionDrawable(new Texture(Gdx.files.internal("menu-up.png"))),new TextureRegionDrawable(new Texture(Gdx.files.internal("menu-down.png"))));
        
        // Añadir los listeners de los botones
        if(MyGdxGame.musica.getVolume()!=0) {
        	sonido=true;
        	botonMusica.setStyle(musicaon);
        	
        }else {
        	sonido=false;
        	botonMusica.setStyle(musicaoff);
        }
        // Añadir los listeners de los botones
        botonMusica.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sonido=!sonido;
                if(sonido){
                    botonMusica.setStyle(musicaon);
                    MyGdxGame.musica.setVolume(0.05f);
                }else{
                    botonMusica.setStyle(musicaoff);
                    MyGdxGame.musica.setVolume(0);

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
        //HASTA AQUI


        // Inicializar la serpiente
        Array<Rectangle> snakeParts = new Array<Rectangle>();
        Array<Rectangle> snakeParts2 = new Array<Rectangle>();

        snakeParts.add(new Rectangle(256,96,32,32));
        snakeParts.add(new Rectangle(286,96,32,32));
        snakeParts2.add(new Rectangle(288 + (32 * 20), 32 * 20, 32, 32));
        snakeParts2.add(new Rectangle(256 + (32 * 20), 32 * 20, 32, 32));


        snake = new Snake(snakeParts, Snake.Direction.RIGHT);
        snake2 = new Snake(snakeParts2,Snake.Direction.LEFT);

        // Inicializar la comida
        spawnFood();
        spawnFood2();
        spawnDouble();
        spawnSpeed();
        spawnSize();

        // Inicializar la puntuación del jugador
        score1 = 0;
        score2 = 0;

        //Inicializar el obheto SpriteBatch
        batch = new SpriteBatch();

        // Carga la imagen desde el archivo en la carpeta assets
        float randomValue = MathUtils.random();
        if (randomValue < 0.6f) {
            spawnFood();
        } else if (randomValue < 0.75f) {
            spawnDouble();
        } else if (randomValue < 0.90f) {
            spawnSize();
        } else {
            spawnSpeed();
        }

    }

    @Override
    public void render() {


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camara.update();
        Gdx.input.setInputProcessor(escenarioPausa);

        // Dibujar el fondo
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);
        batch.end();
        /*batch.begin();
        
        marcador1.dibujar();
        marcador2.dibujar();
        valor1.setStr(String.valueOf(getScore1()));
        valor1.dibujar();
        valor2.setStr(String.valueOf(getScore2()));
        valor2.dibujar();
        batch.end();*/

        // Dibujar la serpiente
        batch.begin();
        for(Rectangle rect: snake.getParts()){
            batch.draw(snakeBodyTexture,rect.x,rect.y,rect.width,rect.height);
        }
        batch.end();
        batch.begin();
        for(Rectangle rect: snake2.getParts()){
            batch.draw(snakeBodyTexture2,rect.x,rect.y,rect.width,rect.height);
        }
        batch.end();

        // Dibujar la comida
        batch.begin();
        batch.draw(foodTexture, food.x, food.y);
        batch.end();
        /*if (DoubleActive) {
            batch.begin();
            batch.draw(foodTexture2, food2.x, food2.y);
            batch.end();
        }*/

        if (currentFruit==2 &&!DoubleActive && getScore1() + getScore2() > 2 && (!sizeActive) && (!speedActive)) {
            batch.begin();
            batch.draw(DoubleTexture, Double.x, Double.y);
            batch.end();
        }

        if (currentFruit==4 &&!speedActive && getScore2() + getScore1() > 2 && (!DoubleActive) && (!sizeActive)) {
            batch.begin();
            batch.draw(speedTexture, speed.x, speed.y);
            batch.end();
        }
        if (currentFruit==3 &&!sizeActive && getScore1() + getScore2() > 2 && (!DoubleActive) && (!speedActive)) {
            batch.begin();
            batch.draw(sizeTexture, size.x, size.y);
            batch.end();
        }

        timeSinceLastMove1+=Gdx.graphics.getDeltaTime();
        timeSinceLastMove2+=Gdx.graphics.getDeltaTime();
        timer+=Gdx.graphics.getDeltaTime();

        if (timer>=5){
            if (!pausa) {
                spawn();
            }
            timer=0;
        }

        if (timeSinceLastMove1 >= moveDelay1) {
            if (!pausa) {
                snake.move();
            }
            timeSinceLastMove1 = 0; // Reinicia el temporizador

         }

        if (timeSinceLastMove2 >= moveDelay2) {
            if (!pausa /*&& Gdx.input.isKeyPressed(Input.Keys.F)*/) {
                snake2.move();
            }
            timeSinceLastMove2=0;
        }

        // Verificar si la serpiente ha colisionado con la comida
        checkCollideWithApple(snake, food);
        checkCollideWithApple(snake, food2);
        checkCollideWithApple(snake2, food);
        checkCollideWithApple(snake2, food2);
        checkCollideWithSpeed(snake, speed);
        checkCollideWithSpeed(snake2, speed);
        if (snake.head().overlaps(Double) || snake2.head().overlaps(Double)) {
            if (getScore2() + getScore1() > 2 &&currentFruit==2 &&(!speedActive) && (!sizeActive)) {
                DoubleActive = true;
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        DoubleActive = false;
                    }
                }, 5);
            }
        }
        if (currentFruit==3 &&snake.head().overlaps(size)) {
            if (getScore2() + getScore1() > 2 && (!DoubleActive) && (!speedActive)) {
                sizeActive = true;
                for (int i = 0; i < 2; i++) {
                    snake.grow();
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        sizeActive = false;
                    }
                }, 5);
            }
        }
        if (snake2.head().overlaps(size) && getScore2() + getScore1() > 2 &&currentFruit==3) {
            if (getScore2() + getScore1() > 2 && (!DoubleActive) && (!speedActive)) {
                sizeActive = true;
                for (int i = 0; i < 2; i++) {
                    snake2.grow();
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        sizeActive = false;
                    }
                }, 5);
            }
        }
        if (checkCollideWithHeads()) {
        	Render.game.setScreen(new gameOver2());
        }

        // Verificar si la serpiente ha colisionado con las paredes o su propio cuerpo
        if (snake.collidesWithWalls() || snake.collidesWithBody()){
        	loser=1;
            Render.game.setScreen(new gameOver2());
        }
        if (snake2.collidesWithWalls() || snake2.collidesWithBody()){
        	loser=2;
            Render.game.setScreen(new gameOver2());
        }
        if (checkCollideWithSnake1() || checkCollisionWithSnake2()){
        	
            Render.game.setScreen(new gameOver2());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)||Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            pausa=true;
        }
        if (pausa) {
            escenarioPausa.act(Gdx.graphics.getDeltaTime());
            escenarioPausa.draw();
        }

        // Verificar las entradas del usuario
        if ( Gdx.input.isKeyPressed(Input.Keys.A)) {
            snake.setDirection(Snake.Direction.LEFT);
        } else if ( Gdx.input.isKeyPressed(Input.Keys.D)) {
            snake.setDirection(Snake.Direction.RIGHT);
        } else if ( Gdx.input.isKeyPressed(Input.Keys.W)) {
            snake.setDirection(Snake.Direction.UP);
        } else if ( Gdx.input.isKeyPressed(Input.Keys.S)) {
            snake.setDirection(Snake.Direction.DOWN);
        }


        if ( Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            snake2.setDirection(Snake.Direction.LEFT);
        }else if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        snake2.setDirection(Snake.Direction.RIGHT);
        }else if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        snake2.setDirection(Snake.Direction.UP);
        }else if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        snake2.setDirection(Snake.Direction.DOWN);
        }
    }


    double offset = 3;

    public static int getScore1() {
        return score1;
    }
    public static int getScore2() {
        return score2;
    }
    public void setScore() {
        score1 = 0;
        score2 = 0;
    }

    public void add1() {
        score1 += 1;
    }

    private void checkCollideWithApple(Snake sn, Rectangle r) {

        if (sn.head().overlaps(r)) {
            if (sn == snake) {
            	score1++;
                if (DoubleActive) {
                    //spawnFood2();
                	score1++;
                 }
            }
            if (sn == snake2) {
                score2++;
                if (DoubleActive) {
                    //spawnFood2();
                	score2++;
                 }
            }
            sn.grow();

            spawnFood();
            if (DoubleActive) {
               //spawnFood2();
            	sn.grow();
            }
        }

    }
    private void checkCollideWithSpeed(Snake sn,Rectangle r){
        if(getScore2()+getScore1()>2&&currentFruit==4) {
            if (sn.head().overlaps(r)) {
                speedActive= true;
                if (sn == snake) {
                    moveDelay2 = 0.05F;
                } else if (sn == snake2) {
                    moveDelay1 = 0.05F;
                }
                Timer.schedule(new Timer.Task() {
                    @Override
                    public void run() {
                        speedActive=false;
                        moveDelay2 = 0.1F;
                        moveDelay1 = 0.1F;

                    }
                }, 5);
            }
        }
    }

    private boolean sincY(float y, float y1) {
        boolean ok=false;
        if(y1>=y-offset && y1<=y+offset){
            ok = true;
        }
        if(y1>=y+offset && y1-offset<=y){
            ok = true;
        }
        return ok;
    }

    private boolean sincX(float x, float x1) {
        boolean ok=false;
        if(x1>=x-offset && x1<=x+offset){
            ok = true;
        }
        if(x1>=x+offset && x1-offset<=x){
            ok = true;
        }
        return ok;
    }

    private void spawnFood() {
        int x = 32*(MathUtils.random(7, 32));
        int y = 32*(MathUtils.random(0, 23));
        food = new Rectangle();
        food.set(x,y,32,32);
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
             for(Rectangle r2 : snake2.getParts()){
                    if(food.overlaps(r2)){
                        int c = 32 * (MathUtils.random(7, 32));
                        int v = 32 * (MathUtils.random(0, 23));
                        food.set(c, v, 32, 32);
                        overlap = true;
                        break;
                 }
             }
            }
        }
        //spawn();
    }
    private void spawnFood2() {
        int x = 32*(MathUtils.random(7, 32));
        int y = 32*(MathUtils.random(0, 23));
        food2 = new Rectangle();
        food2.set(x,y,32,32);
        boolean overlap = true;
        while (overlap) {
            overlap = false;
            for(Rectangle r : snake.getParts()){
                if(food2.overlaps(r)){
                    int c = 32 * (MathUtils.random(7, 32));
                    int v = 32 * (MathUtils.random(0, 23));
                    food2.set(c, v, 32, 32);
                    overlap = true;
                    break;
                }
             for(Rectangle r2 : snake2.getParts()){
                    if(food2.overlaps(r2)){
                        int c = 32 * (MathUtils.random(7, 32));
                        int v = 32 * (MathUtils.random(0, 23));
                        food2.set(c, v, 32, 32);
                        overlap = true;
                        break;
                 }
             }
            }
        }
    }
    private void spawnDouble() {
        int x = 32*(MathUtils.random(7, 32));
        int y = 32*(MathUtils.random(0, 23));
        Double = new Rectangle();
        Double.set(x,y,32,32);
        boolean overlap = true;
        while (overlap) {
            overlap = false;
            for(Rectangle r : snake.getParts()){
                if(Double.overlaps(r)){
                    int c = 32 * (MathUtils.random(7, 32));
                    int v = 32 * (MathUtils.random(0, 23));
                    Double.set(c, v, 32, 32);
                    overlap = true;
                    break;
                }
             for(Rectangle r2 : snake2.getParts()){
                    if(Double.overlaps(r2)){
                        int c = 32 * (MathUtils.random(7, 32));
                        int v = 32 * (MathUtils.random(0, 23));
                        Double.set(c, v, 32, 32);
                        overlap = true;
                        break;
                 }
             }
            }
        }
    }
    private void spawnSpeed() {

        int x = 32 * (MathUtils.random(7, 32));
        int y = 32 * (MathUtils.random(0, 23));
        speed = new Rectangle();
        speed.set(x, y, 32, 32);
        boolean overlap = true;
        while (overlap) {
            overlap = false;
            for(Rectangle r : snake.getParts()){
                if(speed.overlaps(r)){
                    int c = 32 * (MathUtils.random(7, 32));
                    int v = 32 * (MathUtils.random(0, 23));
                    speed.set(c, v, 32, 32);
                    overlap = true;
                    break;
                }
             for(Rectangle r2 : snake2.getParts()){
                    if(speed.overlaps(r2)){
                        int c = 32 * (MathUtils.random(7, 32));
                        int v = 32 * (MathUtils.random(0, 23));
                        speed.set(c, v, 32, 32);
                        overlap = true;
                        break;
                 }
             }
            }
        }
    }
    private void spawnSize(){
        int x = 32 * (MathUtils.random(7, 32));
        int y = 32 * (MathUtils.random(0, 23));
        size = new Rectangle();
        size.set(x, y, 32, 32);
        boolean overlap = true;
        while (overlap) {
            overlap = false;
            for(Rectangle r : snake.getParts()){
                if(size.overlaps(r)){
                    int c = 32 * (MathUtils.random(7, 32));
                    int v = 32 * (MathUtils.random(0, 23));
                    size.set(c, v, 32, 32);
                    overlap = true;
                    break;
                }
             for(Rectangle r2 : snake2.getParts()){
                    if(size.overlaps(r2)){
                        int c = 32 * (MathUtils.random(7, 32));
                        int v = 32 * (MathUtils.random(0, 23));
                        size.set(c, v, 32, 32);
                        overlap = true;
                        break;
                 }
             }
            }
        }
    }
    private void spawn() {
        float randomValue = MathUtils.random();
        /*if (randomValue < 0.6f) {
            spawnFood();
            currentFruit=1;

        } else*/ if (randomValue < 0.35f) {
            currentFruit=2;
            spawnDouble();/*

            }if (!DoubleActive && getScore1() + getScore2() > 2 && (!sizeActive) && (!speedActive)) {
                batch.begin();
                batch.draw(DoubleTexture, Double.x, Double.y);
                batch.end();
            */
        } else if (randomValue < 0.65f) {
            spawnSize();
            currentFruit=3;
            /*if (!sizeActive && getScore1() + getScore2() > 2 && (!DoubleActive) && (!speedActive)) {
                batch.begin();
                batch.draw(sizeTexture, size.x, size.y);
                batch.end();
            }

             */
        } else {
            /*if (!speedActive && getScore2() + getScore1() > 2 && (!DoubleActive) && (!sizeActive)) {
                batch.begin();
                batch.draw(speedTexture, speed.x, speed.y);
                batch.end();
            }

             */
            currentFruit=4;
            spawnSpeed();
        }
    }
    private boolean checkCollideWithHeads() {
    	if (snake.head().equals(snake2.head())) {
    		loser=0;
    		return true;
    	}
    	return false;
    }
    private boolean checkCollideWithSnake1() {
        for (int i = 1; i < snake2.parts.size; i++) {
            Rectangle part2 = snake2.parts.get(i);
            if (snake.head().equals(part2)) {
                loser=1;
                return true;
            }
        }
        return false;
    }
    private boolean checkCollisionWithSnake2(){
        for (int i=1;i<snake.parts.size;i++){
            Rectangle part= snake.parts.get(i);
            if (snake2.head().equals(part)){
                loser=2;
                return true;
            }
        }

        return false;
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
        foodTexture2.dispose();
        DoubleTexture.dispose();
        sizeTexture.dispose();
        speedTexture.dispose();
        batch.dispose();

    }
}