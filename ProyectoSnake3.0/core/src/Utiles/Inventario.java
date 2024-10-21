package Utiles;

import java.util.ArrayList;

import java.util.List;

public class Inventario {
	
	private static String usuario;
	private static List<Integer> skinsI;
	
	public Inventario(String usuario, List<Integer> skins) {
		this.usuario = usuario;
		skinsI = new ArrayList<>();
		skinsI=skins;
	}
	public static List<Integer> getSkins() {
		return skinsI;
	}
	public static void anyadirSkins(List<Integer> skins) {
		skinsI = skins;
	}
	public static String getUsuario() {
		return usuario;
	}

}
