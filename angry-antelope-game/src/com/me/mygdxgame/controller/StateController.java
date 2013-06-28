package com.me.mygdxgame.controller;

import java.util.HashMap;

import com.me.mygdxgame.controller.GameState.State;

public class StateController {

	private State currentState = null;  
	private HashMap<State, GameState> stateMap = null;
	
	public StateController(State initStateKey, GameState initStateVal) {
		stateMap = new HashMap<GameState.State, GameState>();
		stateMap.put(initStateKey, initStateVal);
		currentState = initStateKey;
	}
	
	public void addState(State stateKey, GameState stateVal) {
		stateMap.put(stateKey, stateVal);
	}
	
	public GameState getState() {
		//current State must be initialized
		return stateMap.get(currentState);
	}
	
	public void setState(State stateKey) {
		currentState = stateKey;
	}

	public void updateState(){
		GameState toUpdate = stateMap.get(currentState);
		if (toUpdate != null) {
			toUpdate.update();
		}else {
			System.err.println("Current state is not defined");
		}
	}
}
