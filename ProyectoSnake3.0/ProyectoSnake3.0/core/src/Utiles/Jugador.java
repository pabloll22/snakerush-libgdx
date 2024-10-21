package Utiles;

public class Jugador {
	private static String usuario;
	private static String contrasena;
	private static int numMonedas;
	private static int nivel_actual;
	private static int skin_actual;
	private static int record;
	
	public Jugador(String usuario, String contrasena, int numMonedas, int nivel_actual, int skin_actual, int record) {
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.numMonedas = numMonedas;
		this.nivel_actual = nivel_actual;
		this.skin_actual = skin_actual;
		this.record = record;
	}

	public static String getUsuario() {
		return usuario;
	}

	public static String getContrasena() {
		return contrasena;
	}

	public static int getNumMonedas() {
		return numMonedas;
	}

	public static int getNivel_actual() {
		return nivel_actual;
	}
	
	public static void setSkin_actual(int skin) {
		skin_actual=skin;
	}

	public static int getSkin_actual() {
		return skin_actual;
	}

	public static int getRecord() {
		return record;
	}

	public static void setRecord(int record) {
		Jugador.record = record;
	}

	public static void setMonedas(int monedas) {
		numMonedas=monedas;
	}
	
	public static void setNivel_actual(int nivel_actual) {
		Jugador.nivel_actual = nivel_actual;
	}
		

}
