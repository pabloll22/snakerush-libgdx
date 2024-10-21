package Objetos;

import Utiles.Render;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Imagen {
    private Texture t;
    private Sprite s;
    float x,y;

    public Imagen(String ruta){
        t = new Texture(ruta);
        s = new Sprite(t);
    }

    public void dibujar(){
        s.draw(Render.batch);
    }

    public void setTrans(float b){
        s.setAlpha(b);
    }

    public void setSize(float ancho, float alto){
        x=ancho;
        y=alto;
        s.setSize(ancho, alto);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
