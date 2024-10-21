package JUnit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Juego.Snake;
import Juego.Snake.Direction;

public class SnakeTest {
    private Snake snake;

    @Before
    public void setUp() throws Exception {
        
        Array<Rectangle> snakeParts = new Array<Rectangle>();
        snakeParts.add(new Rectangle(448,32,32,32));
        snakeParts.add(new Rectangle(416,32,32,32));

        snake = new Snake(snakeParts,Direction.LEFT);
    }

    @Test
    public void testCollidesWithWalls() {
       
        assertFalse(snake.collidesWithWalls());

       
        snake.head().setPosition(0, 0);
        assertTrue(snake.collidesWithWalls());

       
        snake.setDirection(Snake.Direction.LEFT);
        snake.move();
        assertTrue(snake.collidesWithWalls());

        
        snake.head().setPosition(448, 32);
        snake.setDirection(Snake.Direction.UP);
        snake.move();
        assertFalse(snake.collidesWithWalls());
    }

    @Test
    public void testCollidesWithBody() {
        
        assertFalse(snake.collidesWithBody());

       
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.grow();
        assertFalse(snake.collidesWithBody());

        
        snake.setDirection(Snake.Direction.UP);
        snake.move();
        snake.setDirection(Snake.Direction.RIGHT);
        snake.move();
        snake.setDirection(Snake.Direction.DOWN);
        snake.move();
        assertTrue(snake.collidesWithBody());

        
    }

    
}
