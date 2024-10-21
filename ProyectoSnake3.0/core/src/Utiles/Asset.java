package Utiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Asset {
	public static Texture b1l;
	public static Texture b2l;

	public static TextureRegionDrawable btl1;
	public static TextureRegionDrawable btl2;
    
	public static Texture b1r;
	public static Texture b2r;

	public static TextureRegionDrawable bt1r;
	public static TextureRegionDrawable bt2r;
    
	public static Texture be1;
	public static Texture be2;

	public static TextureRegionDrawable bte1;
	public static TextureRegionDrawable bte2;
	
	public static Texture b1a;
	public static Texture b2a;

	public static TextureRegionDrawable bt1a;
	public static TextureRegionDrawable bt2a;
    
	public static Texture b1tc;
	public static Texture b2tc;

	public static TextureRegionDrawable bt1tc;
	public static TextureRegionDrawable bt2tc;
	
	public static Texture prevButtonImage;
	public static ImageButton previousButton1;
	
	public static Texture nextButtonImage;
	public static ImageButton nextButton1; 
    
    public static void load() {
    	b1l = new Texture(Gdx.files.internal("boton_login_subido.png"));
    	b2l = new Texture(Gdx.files.internal("boton_login_bajado.png"));
    	
    	btl1 = new TextureRegionDrawable(b1l);
    	btl2 = new TextureRegionDrawable(b2l);
    	
    	b1r = new Texture(Gdx.files.internal("boton_signin_subido.png"));
    	b2r = new Texture(Gdx.files.internal("boton_signin_bajado.png"));
    	
    	bt1r = new TextureRegionDrawable(b1r);
    	bt2r = new TextureRegionDrawable(b2r);
    	
    	be1 = new Texture(Gdx.files.internal("boton_exit_subido.png"));
    	be2 = new Texture(Gdx.files.internal("boton_exit_bajado.png"));
    	
    	bte1 = new TextureRegionDrawable(be1);
    	bte2 = new TextureRegionDrawable(be2);
    	
    	b1a = new Texture(Gdx.files.internal("boton_atras_niveles_subido.png"));
    	b2a = new Texture(Gdx.files.internal("boton_atras_niveles_bajado.png"));
    	
    	bt1a = new TextureRegionDrawable(b1a);
    	bt2a = new TextureRegionDrawable(b2a);
    	
    	b1tc = new Texture(Gdx.files.internal("boton_confirmar_subido.png"));
    	b2tc = new Texture(Gdx.files.internal("boton_confirmar_bajado.png"));
    	
    	bt1tc = new TextureRegionDrawable(b1tc);
    	bt2tc = new TextureRegionDrawable(b2tc);
    	
    	prevButtonImage = new Texture(Gdx.files.internal("flecha_ant.png"));
    	previousButton1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(prevButtonImage)));
    	
    	nextButtonImage = new Texture(Gdx.files.internal("flecha_sig.png"));
    	nextButton1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(nextButtonImage)));
    }
}
