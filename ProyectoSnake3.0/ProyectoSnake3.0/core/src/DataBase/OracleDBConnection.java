package DataBase;

import java.sql.*;

public class OracleDBConnection {
	private static String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private static String USER = "root";
    private static String PASSWORD = "1234";
    public static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    }
    
    public Connection connection() {
    	try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar a la base de datos", e);
        }
    	return conn;
    }

    public static String getUrl() {
		return URL;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static void main(String[] args) {
        Connection conn = null;
        try {
            // Carga el controlador JDBC de Oracle

            // Crea una conexión a la base de datos Oracle
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Hacer algo con la conexión...
            // Por ejemplo, imprimir el nombre del usuario actual
            System.out.println("Conectado como " + conn.getMetaData().getUserName());
        } catch (SQLException ex) {
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

