package com.me.mygdxgame;

import com.badlogic.gdx.math.Vector2;

public class World {

	/** Our player controlled hero **/
	Car car;

	public Car getCar() {
		return car;
	}
	// --------------------

	public World() {
		createDemoWorld();
	}

	private void createDemoWorld() {
		car = new Car(new Vector2(70, 200));
	}
}
