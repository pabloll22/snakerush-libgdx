package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {
    private static final String DB_USERNAME = "tu_usuario";
    private static final String DB_PASSWORD = "tu_contraseña";
    private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";

    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Carga el controlador JDBC de Oracle
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Crea una conexión a la base de datos Oracle
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            // Hacer algo con la conexión...
            // Por ejemplo, imprimir el nombre del usuario actual
            System.out.println("Conectado como " + conn.getMetaData().getUserName());
        } catch (ClassNotFoundException | SQLException ex) {
            // Manejar excepciones
            ex.printStackTrace();
        } finally {
            try {
                // Cierra la conexión
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // Manejar excepciones
                ex.printStackTrace();
            }
        }
    }
}

