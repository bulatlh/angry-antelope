package com.me.mygdxgame.controller;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.me.mygdxgame.GameScreen;

public class ScreenCreater extends Game implements ApplicationListener{

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
