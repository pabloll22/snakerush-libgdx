package Objetos;

import Utiles.Render;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Texto {
    BitmapFont font;
    private float x = 0;
    private float y = 0;
    private String str="";
    GlyphLayout layout;

    public Texto(String fuente, int dimension, Color color, boolean sombra){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(fuente));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = dimension;
        parameter.color = color;
        if(sombra){
            parameter.shadowColor = Color.BLACK;
            parameter.shadowOffsetX = 1;
            parameter.shadowOffsetY = 1;
        }
        font = generator.generateFont(parameter);
        layout = new GlyphLayout();
    }

    public void dibujar(){
        font.draw(Render.batch, str, x,y);
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public void setColor(Color c){
        font.setColor(c);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
        layout.setText(font, str);
    }

    public float getAncho(){
        return layout.width;
    }

    public float getAltura(){
        return layout.height;
    }

    public Vector2 getDimension(){
        return new Vector2(layout.width, layout.height);
    }

    public Vector2 getPosicion(){
        return new Vector2(x,y);
    }
}
