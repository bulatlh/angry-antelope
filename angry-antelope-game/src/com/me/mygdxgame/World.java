package com.me.mygdxgame;

import com.badlogic.gdx.math.Vector2;

public class World {

	/** Our player controlled hero **/
	Car car;
	TargetManager targets;

	public Car getCar() {
		return car;
	}
	
	public TargetManager getTargets(){
		return targets;
	}
	// --------------------

	public World() {
		createDemoWorld();
	}

	private void createDemoWorld() {
		car = new Car(new Vector2(70, 200));
		targets = new TargetManager();
		
		targets.addTarget(400, 400);
		targets.addTarget(800, 400);
		
	}
	
	
}
