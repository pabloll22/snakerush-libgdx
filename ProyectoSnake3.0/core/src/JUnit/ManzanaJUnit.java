package JUnit;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import org.junit.Assert;
import org.junit.Test;

public class ManzanaJUnit {

    @Test
    public void testAppleSpawn() {
        // Arrange
        int worldWidth = 832;
        int worldHeight = 768;
       
        
        Array<Rectangle> obstaculo = new Array<>();
        Rectangle playArea = new Rectangle(224,0,worldWidth , worldHeight);
        Rectangle manzana;
        //Copio la funcion spawnFood para cambiar las coordenadas de manzana
        for (int i = 0; i < 500; i++) {
        	int x = 32 * (MathUtils.random(7, 32));
            int y = 32 * (MathUtils.random(0, 23));
            manzana = new Rectangle();
            manzana.set(x, y, 32, 32);

            boolean overlap = true;
            while (overlap) {
                overlap = false;
                
                if (!overlap) {
                    for(Rectangle s : obstaculo){
                        if(manzana.contains(s) || manzana.overlaps(s)){
                            int p = 32 * (MathUtils.random(7, 32));
                            int l = 32 * (MathUtils.random(0, 23));
                            manzana.set(p, l, 32, 32);
                            overlap = true;
                            break;
                        }
                    }
                }
            }
            // Assert
            Assert.assertTrue(playArea.overlaps(manzana));
            
        }
    }
}
