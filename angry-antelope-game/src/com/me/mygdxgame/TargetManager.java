package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TargetManager {

	ArrayList<Arrow> activeTargets;
	Stack<Arrow> inactiveTargets;
	
	
	
	public TargetManager(){
		activeTargets = new ArrayList<Arrow>();
		inactiveTargets = new Stack<Arrow>();
	}
	
	public List<Arrow> getTargets(){
		return activeTargets;
	}

	public int update(Car car){

		float car_x = car.getCenterPosition().x; 
		float car_y = car.getCenterPosition().y;
		int arrow_count = 0;
		for(Arrow arr : activeTargets){
			if (arr.update(car_x, car_y)){
				++arrow_count;
			}
		}
		return arrow_count;
	}
	
	public void addTarget(int x, int y){
		Arrow ar = null;
		if (!inactiveTargets.isEmpty()){
			ar = inactiveTargets.pop();
			ar.initTargetPosition(x, y);
			activeTargets.add(ar);
		}else {
			ar = new Arrow();
			ar.initTargetPosition(x, y);
			activeTargets.add(ar);
		}
	}
	
	public void drawTargets(SpriteBatch sprBatch) {
		for (Arrow arr : activeTargets){
			arr.draw(sprBatch);
		}
	}
}
