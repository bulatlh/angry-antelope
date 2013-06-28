package com.me.mygdxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		new LwjglApplication(new AmbulanceCreater(), "Ambulance Stuff", 1280, 800, true);
	}
}
