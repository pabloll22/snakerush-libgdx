package Screens;

import Objetos.Imagen;
import Utiles.Asset;
import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.sql.SQLException;

public class pantallaSesion implements Screen {

    /*

    Texture b1 = new Texture(Gdx.files.internal("boton1.png"));
    Texture b2 = new Texture(Gdx.files.internal("boton2.png"));

    TextureRegionDrawable bt1 = new TextureRegionDrawable(b1);
    TextureRegionDrawable bt2 = new TextureRegionDrawable(b2);

    Button b;*/

    /*Texture b1 = new Texture(Gdx.files.internal("boton_login_subido.png"));
    Texture b2 = new Texture(Gdx.files.internal("boton_login_bajado.png"));

    TextureRegionDrawable bt1 = new TextureRegionDrawable(b1);
    TextureRegionDrawable bt2 = new TextureRegionDrawable(b2);
    
    Texture b1r = new Texture(Gdx.files.internal("boton_signin_subido.png"));
    Texture b2r = new Texture(Gdx.files.internal("boton_signin_bajado.png"));

    TextureRegionDrawable bt1r = new TextureRegionDrawable(b1r);
    TextureRegionDrawable bt2r = new TextureRegionDrawable(b2r);
    
    Texture be1 = new Texture(Gdx.files.internal("boton_exit_subido.png"));
    Texture be2 = new Texture(Gdx.files.internal("boton_exit_bajado.png"));

    TextureRegionDrawable bte1 = new TextureRegionDrawable(be1);
    TextureRegionDrawable bte2 = new TextureRegionDrawable(be2);*/


    private Stage stage;
    private Skin skin;
    private Table table;
    Imagen fondo;

    public pantallaSesion(){
        stage = new Stage(new FitViewport(Render.ANCHO, Render.ALTO));
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        fondo = new Imagen("pantallaFondo.png");
        fondo.setSize(1280,768);

        Button login = new Button(Asset.btl1,Asset.btl2);
        Button signin = new Button(Asset.bt1r,Asset.bt2r);
        Button exit = new Button(Asset.bte1,Asset.bte2);

        //TextButton loginButton = new TextButton("Iniciar sesión",skin);
        login.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    Render.game.setScreen(new inicioSesion());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        TextButton registerButton = new TextButton("Registrate",skin);
        signin.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Render.game.setScreen(new registroSesion());
            }
        });

        //TextButton exitButton = new TextButton("Salir",skin);
        exit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        

        table = new Table(skin);
        table.setFillParent(true);
        table.padTop(300);
        table.add(login).padBottom(25).row();
        table.add(signin).padBottom(25).row();
        table.add(exit).padBottom(10).row();
        
  

        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show(){
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
        /*camera.update();

        stage.act(delta);
        stage.draw();*/
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
