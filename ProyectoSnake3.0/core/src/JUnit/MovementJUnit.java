package JUnit;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

import Juego.Snake;
import Juego.Snake.Direction;

import org.junit.Test;

import static org.junit.Assert.*;

public class MovementJUnit {

    @Test
    public void testMove() {
        // Inicializar la serpiente con una sola parte
        Array<Rectangle> snakeParts = new Array<>();
        snakeParts.add(new Rectangle(448,32,32,32));
        Snake snake = new Snake(snakeParts,Direction.RIGHT);

        // Guardar la posición inicial de la cabeza
        Rectangle initialHead =  snake.head();

        // Mover la serpiente hacia la derecha
        snake.move();

        // Verificar que la cabeza se haya movido hacia la derecha
        Rectangle expectedHead = new Rectangle(initialHead.x + 32, initialHead.y, 32, 32);
        assertEquals(expectedHead, snake.head());

        // Mover la serpiente hacia arriba
        snake.setDirection(Snake.Direction.UP);
        snake.move();

        // Verificar que la cabeza se haya movido hacia arriba
        expectedHead = new Rectangle(initialHead.x + 32, initialHead.y + 32, 32, 32);
        assertEquals(expectedHead, snake.head());

        // Mover la serpiente hacia la izquierda
        snake.setDirection(Snake.Direction.LEFT);
        snake.move();

        // Verificar que la cabeza se haya movido hacia la izquierda
        expectedHead = new Rectangle(initialHead.x, initialHead.y + 32, 32, 32);
        assertEquals(expectedHead, snake.head());

        // Mover la serpiente hacia abajo
        snake.setDirection(Snake.Direction.DOWN);
        snake.move();

        // Verificar que la cabeza se haya movido hacia abajo
        expectedHead = initialHead;
        assertEquals(expectedHead, snake.head());
    }
}