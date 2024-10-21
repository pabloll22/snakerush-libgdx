package Juego;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.SnakeGame;

import static com.mygdx.game.SnakeGame.*;

public class Snake {
    public enum Direction {
        UP, RIGHT, DOWN, LEFT
    }

    public Array<Rectangle> parts;
    private Direction direction;

    public Snake(Array<Rectangle> parts,Direction d) {
        this.parts = parts;
        this.direction = d;
    }

    /*public void grow() {
        parts.add();
    }*/

    public void setDirection(Direction direction) {
        if (this.direction == Direction.UP && direction == Direction.DOWN) {
            return;
        }
        if (this.direction == Direction.RIGHT && direction == Direction.LEFT) {
            return;
        }
        if (this.direction == Direction.DOWN && direction == Direction.UP) {
            return;
        }
        if (this.direction == Direction.LEFT && direction == Direction.RIGHT) {
            return;
        }
        this.direction = direction;
    }
    public void move() {
        // Calcular la nueva posición de la cabeza de la serpiente
        Rectangle head = new Rectangle(parts.first());
        switch (direction) {
            case UP:
                head.y+=32;
                break;
            case RIGHT:
                head.x+=32;
                break;
            case DOWN:
                head.y-=32;
                break;
            case LEFT:
                head.x-=32;
                break;
        }
        // Añadir la nueva posición de la cabeza de la serpiente
        parts.insert(0, head);

        // Eliminar la última posición de la serpiente
        parts.removeIndex(parts.size - 1);
    }

    public void grow(){
    Rectangle newPart = new Rectangle(parts.peek().x,parts.peek().y,32,32);
    parts.add(newPart);
    }

    public boolean collidesWithWalls() {
        // Verificar si la cabeza de la serpiente ha colisionado con las paredes
        Rectangle head = head();
        return head.x < 224 || head.x >= (1056) || head.y < -1 || head.y >= (WORLD_HEIGHT);
    }

    public boolean collidesWithBody() {
        // Verificar si la cabeza de la serpiente ha colisionado con su propio cuerpo
        Rectangle head = head();
        for (int i = 1; i < parts.size; i++) {
            Rectangle part = parts.get(i);
            if (head.equals(part)) {
                return true;
            }
        }
        return false;
    }
    public Rectangle head() {
        // Devolver la posición de la cabeza de la serpiente
        return parts.get(0);
    }
    public Array<Rectangle> getParts() {
        // Devolver todas las posiciones de la serpiente
        return parts;
    }

}
