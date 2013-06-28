package com.me.mygdxgame;


import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.me.mygdxgame.Car;
import com.me.mygdxgame.Car.State;
import com.me.mygdxgame.World;

public class WorldController {

	enum Keys {
		LEFT, RIGHT, UP, DOWN, ZOOMIN, ZOOMOUT
	}

	private World 	world;
	private WorldRenderer renderer;
	private Car 	car;

	static Map<Keys, Boolean> keys = new HashMap<WorldController.Keys, Boolean>();
	static {
		
		keys.put(Keys.ZOOMIN, false);
		keys.put(Keys.ZOOMOUT, false);
		
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
	
	public void zoomInPressed() {
		keys.get(keys.put(Keys.ZOOMIN, true));
	}

	public void zoomOutPressed() {
		keys.get(keys.put(Keys.ZOOMOUT, true));
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
	
	public void zoomInReleased() {
		keys.get(keys.put(Keys.ZOOMIN, false));
	}

	public void zoomOutReleased() {
		keys.get(keys.put(Keys.ZOOMOUT, false));
	}	

	/** The main update method **/
	public void update(float delta) {
		processInput();
		car.update(delta);
	}

	/** Change Bob's state and parameters based on input controls **/
	private void processInput() {
		
		if (keys.get(Keys.ZOOMIN)) {
			System.out.println("zoom in");
			//renderer.cam.position.set(1, 1, 3);
			//renderer.cam.update();
			
		}
		if (keys.get(Keys.ZOOMOUT)) {
			//renderer.getCam().translate(0, 0, -1);
		}
		
		if (keys.get(Keys.LEFT)) {
			car.setState(State.WALKING);
			car.getVelocity().x = -Car.SPEED;
		}
		if (keys.get(Keys.RIGHT)) {
			car.setState(State.WALKING);
			car.getVelocity().x = Car.SPEED;
		}
		if (keys.get(Keys.UP)) {
			car.setState(State.WALKING);
			car.getVelocity().y = Car.SPEED;
		}
		if (keys.get(Keys.DOWN)) {
			car.setState(State.WALKING);
			car.getVelocity().y = -Car.SPEED;
		}
		// need to check if both or none direction are pressed, then Bob is idle
		if ((!keys.get(Keys.LEFT) && !keys.get(Keys.RIGHT)  && !keys.get(Keys.UP)  && !keys.get(Keys.DOWN))) {
			car.setState(State.IDLE);
			// acceleration is 0 on the x
			car.getAcceleration().x = 0;
			// horizontal speed is 0
			car.getVelocity().x = 0;
			
			car.getAcceleration().y = 0;
			// horizontal speed is 0
			car.getVelocity().y = 0;
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
