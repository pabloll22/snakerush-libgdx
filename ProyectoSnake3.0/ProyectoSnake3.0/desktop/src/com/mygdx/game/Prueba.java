package com.mygdx.game;
import java.sql.*;

public class Prueba {
	
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/SnakeRush";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

	public static void main(String[] args) {
		// Inicia el bloque try-catch para manejar excepciones
		try {
            // Establecer la conexi�n a la base de datos
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexi�n exitosa a la base de datos");

            if (connection != null) {
                // Inicia un segundo bloque try para realizar la consulta
                try {
                    // Crear una consulta SQL
                    String sql = "SELECT * FROM Jugador";

                    // Crear un Statement para ejecutar la consulta
                    Statement statement = connection.createStatement();

                    // Ejecutar la consulta y obtener los resultados
                    ResultSet resultSet = statement.executeQuery(sql);

                    // Procesar los resultados
                    while (resultSet.next()) {
                        String nombre = resultSet.getString("nombre_usuario");
                        String contrasena = resultSet.getString("contrasena");

                        // Imprimir los datos obtenidos
                        System.out.println("Nombre: " + nombre + ", Contrase�a: " + contrasena);
                    }

                    // Cerrar el ResultSet y Statement
                    resultSet.close();
                    statement.close();
                } catch (SQLException e) {
                    System.err.println("Error al ejecutar la consulta: " + e.getMessage());
                } finally {
                    // Cerrar la conexi�n en el bloque finally para asegurar el cierre
                    try {
                        if (connection != null && !connection.isClosed()) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        System.err.println("Error al cerrar la conexi�n: " + e.getMessage());
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error de conexi�n: " + e.getMessage());
        }
    }
}
