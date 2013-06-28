package com.me.mygdxgame;

import com.me.mygdxgame.GameScreen;

import com.badlogic.gdx.Game;

public class AmbulanceCreater extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
