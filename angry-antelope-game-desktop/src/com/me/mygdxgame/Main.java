package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.me.mygdxgame.controller.TitleCreater;

public class Main {
	public static void main(String[] args) {
		Constants.theGreatCreater = new TitleCreater();
		new LwjglApplication(Constants.theGreatCreater, "Ambulance Stuff", 1280, 800, true);
	}
}
