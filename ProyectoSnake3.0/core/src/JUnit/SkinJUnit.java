package JUnit;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Utiles.Jugador;

public class SkinJUnit {

    private Jugador jugador;

    @Before
    public void setUp() {
        // Crear un nuevo jugador con skin 1
        jugador = new Jugador("usuario", "contrasena", 0, 1, 1, 0);
    }

    @Test
    public void testSetSkin_actual() {
        // Comprobar que la skin actual es la misma que la que se pone al empezar la partida (skin 1)
        assertEquals(1, jugador.getSkin_actual());

        // Cambiar la skin actual a 2
        jugador.setSkin_actual(2);

        // Comprobar que la skin actual es 2
        assertEquals(2, jugador.getSkin_actual());
    }

}