package com.mygdx.game;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Screens.MenuJuego;
import Screens.PantallaMenu;
import Screens.PantallaPrincipal;
import Screens.dosJugadores;
import Screens.nivel5;
import Screens.pantallaSesion;
import Utiles.Asset;
import Utiles.Render;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game{
	 public static Music musica;

	@Override
	public void create (){
		Asset.load();
		Render.game = this;
		
		musica = Gdx.audio.newMusic(Gdx.files.internal("MenuMusic.mp3"));
		musica.setLooping(true);
		musica.setVolume(0.05f);
		musica.play();
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaPrincipal());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		musica.dispose();
	}
}

