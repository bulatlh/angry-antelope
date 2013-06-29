package com.me.mygdxgame;

import com.me.mygdxgame.controller.GameState;
import com.me.mygdxgame.controller.TitleCreater;
import com.me.mygdxgame.controller.GameState.State;
import com.me.mygdxgame.controller.StateController;

public class Constants {

	public static State game_state = GameState.State.MAIN;
	public static TitleCreater theGreatCreater;
	
	
	public static final float SPEED = 50f;	// unit per second

	public static final float ROTATE_SPEED = 1.5f; // half a unit
	
	public static final float CAMERA_WIDTH = 1280;
	public static final float CAMERA_HEIGHT = 800;

	
	public static final int CAR_SCALAR = 2;
	
	public static final int CAR_HEIGHT = 48*CAR_SCALAR;
	public static final int CAR_WIDTH = 24*CAR_SCALAR;
	public static final int ZOMBIE_HEIGHT = 14*CAR_SCALAR;
	public static final int ZOMBIE_WIDTH = 15*CAR_SCALAR;
	
	public static final int ARROW_WIDTH = 10;
	public static final int ARROW_HEIGHT = 10;
	
	public static final int HALF_CAR_WIDTH = CAR_WIDTH/2;
	public static final int HALF_CAR_HEIGHT = CAR_HEIGHT/2;
	
	public static final int MAX_VELOCITY = 20;
	
	public static final float CAR_BACK_ACCELERATION_SCALE = .25f;
	public static final float CAR_ACCELERATION = 8;
	public static final float CAR_MAX_ACCELERATION = 320;
	public static final float ZOMBIE_BACK_ACCELERATION_SCALE = .25f;
	public static final float ZOMBIE_ACCELERATION = 4;
	public static final float ZOMBIE_MAX_ACCELERATION = 160;

	public static final float ZOMBIE_CAR_THRESHOLD_HEIGHT = HALF_CAR_HEIGHT+ZOMBIE_HEIGHT+1;
	public static final float ZOMBIE_CAR_THRESHOLD_WIDTH = HALF_CAR_WIDTH+ZOMBIE_WIDTH+1;
	
	public static final float ACCELEROMETER_SCALAR = 20;
	
	public static final float ACCELERATION_LOSS = .97f;
	public static final float ACCELERATION_MIN = 15;

	public static final float ROTATION_SCALAR = .1f;
	public static final int DEGREE_OFFSET = 90;
	
	public static final int PICKUP_RADIUS = 100;
	public static final int ZOMBIE_TRACKING_RADIUS = 200;
	public static final int ZOMBIE_TRACKING_SIREN_RADIUS = 400;
	
	public static final int ZOMBIE_ATTACKING_RADIUS = 50;

	public static final float TURN_THRESHOLD = 1.3f;

	public static float X_OFFSET = 0; // set this value when start game is pressed.

	public static final int DURATION = 5; 
	
	public static final int NUMBER_ZOMBIES = 100;
	public static final int PLAYER_HEALTH = 100;
	public static final float MIN_ZOMBIE_ACCELERATION = 2;
	public static final float CAR_BOX_SHIFT_X = -10;
	public static final float CAR_BOX_SHIFT_Y = -4;

}
