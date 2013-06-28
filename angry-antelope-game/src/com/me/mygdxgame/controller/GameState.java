package com.me.mygdxgame.controller;

public interface GameState {

	public static enum State {
		MAIN,
		GAME,
		PAUSE,
		GAME_CENTER,
		CREDITS,
		OPTIONS,
		GAME_OVER
	}
	
	public void update();
	
}
