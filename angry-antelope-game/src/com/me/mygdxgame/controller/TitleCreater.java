package com.me.mygdxgame.controller;

import com.me.mygdxgame.GameScreen;
import com.me.mygdxgame.screens.TitleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class TitleCreater extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
