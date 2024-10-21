package JUnit;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import org.junit.Assert;
import org.junit.Test;

public class PuertaJUnit {

    @Test
    public void testAppleSpawn() {
        // Arrange
        int worldWidth = 832;
        int worldHeight = 768;
        
        
        Array<Rectangle> obstaculo = new Array<>();
        Rectangle playArea = new Rectangle(224,0,worldWidth , worldHeight);
        Rectangle puerta;
        //Copio la funcion spawnPuerta para cambiar las coordenadas de puerta
        for (int i = 0; i < 500; i++) {
        	int x = 32 * (MathUtils.random(7, 31));
            int y = 32 * (MathUtils.random(0, 22));
            puerta = new Rectangle();
            puerta.set(x, y, 64, 64);
            
            for(Rectangle s : obstaculo){
                if(puerta.overlaps(s)){
                    int p = 32 * (MathUtils.random(7, 31));
                    int l = 32 * (MathUtils.random(0, 22));
                    puerta = new Rectangle();
                    puerta.set(p, l, 64, 64);
                }
            }
            // Assert
            Assert.assertTrue(playArea.overlaps(puerta));
        }
            
           
           
            
        }
    }

