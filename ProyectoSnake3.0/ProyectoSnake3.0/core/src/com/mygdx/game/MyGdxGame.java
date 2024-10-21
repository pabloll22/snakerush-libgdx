package com.mygdx.game;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import Screens.MenuJuego;
import Screens.PantallaMenu;
import Screens.PantallaPrincipal;
import Screens.pantallaSesion;
import Utiles.Asset;
import Utiles.Render;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game{

	@Override
	public void create (){
		Asset.load();
		Render.game = this;
		Render.batch = new SpriteBatch();
		this.setScreen(new PantallaPrincipal());
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
	}
}

