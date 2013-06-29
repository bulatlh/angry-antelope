package com.me.mygdxgame.controller;

import com.badlogic.gdx.Game;
import com.me.mygdxgame.screens.TitleScreen;

public class TitleCreater extends Game {

	@Override
	public void create() {
		setScreen(new TitleScreen());
	}
}
