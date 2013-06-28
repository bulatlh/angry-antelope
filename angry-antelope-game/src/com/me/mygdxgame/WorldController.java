package com.me.mygdxgame;


import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.Car;
import com.me.mygdxgame.Car.State;
import com.me.mygdxgame.World;

public class WorldController {

	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}

	private World 	world;
	private WorldRenderer renderer;
	private Car 	car;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	};

	public WorldController(World world, WorldRenderer renderer) {
		this.world = world;
		this.renderer = renderer;
		this.car = world.getCar();
	}

	// ** Key presses and touches **************** //

	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
	}

	public void upPressed() {
		keys.get(keys.put(Keys.UP, true));
	}

	public void downPressed() {
		keys.get(keys.put(Keys.DOWN, true));
	}
	
	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
	}

	public void upReleased() {
		keys.get(keys.put(Keys.UP, false));
	}

	public void downReleased() {
		keys.get(keys.put(Keys.DOWN, false));
	}

	/** The main update method **/
	public void update(float delta) {
		processInput();
		car.update(delta);
	}

	/** Change Bob's state and parameters based on input controls **/
	private void processInput() {
		
		if (keys.get(Keys.LEFT)) {
			car.rotateCW();
			renderer.rotateCW();
		}
		if (keys.get(Keys.RIGHT)) {
			car.rotateCCW();
			renderer.rotateCCW();
		}
		
		if (keys.get(Keys.UP)) {
			car.setState(State.IDLE);
			car.acceleration(1);
		}
		if (keys.get(Keys.DOWN)) {
			car.setState(State.IDLE);
			car.acceleration(-1);		
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((!keys.get(Keys.UP)  && !keys.get(Keys.DOWN))) {
			
		}
		
	}

	public void accelerometerControlX() {
		// TODO Auto-generated method stub
		car.position.y-= Gdx.input.getAccelerometerX()*2 * Gdx.graphics.getDeltaTime();
		
	}
	public void accelerometerControlY() {
		// TODO Auto-generated method stub
		car.position.x+= Gdx.input.getAccelerometerY()*2 * Gdx.graphics.getDeltaTime();
		
	}
}
