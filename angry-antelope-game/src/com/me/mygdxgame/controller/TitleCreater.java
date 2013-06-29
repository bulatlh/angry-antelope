package com.me.mygdxgame.controller;

import com.me.mygdxgame.screens.TitleScreen;

import com.badlogic.gdx.Game;

public class TitleCreater extends Game {

	@Override
	public void create() {
		setScreen(new TitleScreen());
	}
}
