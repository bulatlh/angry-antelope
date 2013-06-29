package com.me.mygdxgame;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

public class World {

	/** Our player controlled hero **/
	Car car;
	ArrayList<Zombie> zombies;

	public Car getCar() {
		return car;
	}
	public ArrayList<Zombie> getZombies() {
		return zombies;
	}
	// --------------------

	public World() {
		createDemoWorld();
	}

	private void createDemoWorld() {
		car = new Car(new Vector2(70, 200));
		zombies = new ArrayList<Zombie>();
		for (int i=0;i<Constants.NUMBER_ZOMBIES;i++){
			Zombie z = new Zombie(new Vector2((float)Math.random()*1280,(float) (Math.random()*800)));
//			Zombie z = new Zombie(new Vector2(100,100));
			System.out.println((float)Math.random()*1280);
			zombies.add(z);
		}
	}
}
