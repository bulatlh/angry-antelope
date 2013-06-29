package com.me.mygdxgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TargetManager {

	private ArrayList<Arrow> activeTargets;
	private Stack<Arrow> inactiveTargets;
	
	
	
	public TargetManager(){
		activeTargets = new ArrayList<Arrow>();
		inactiveTargets = new Stack<Arrow>();
	}
	
	public void update(Car car){
		float car_x = car.getCenterPosition().x; 
		float car_y = car.getCenterPosition().y;
		for(Arrow arr : activeTargets){
			arr.update(car_x, car_y);
		}
		
//		Iterator<Arrow> iter = activeTargets.iterator();
//		
//		while(iter.hasNext()){
//			if (iter.next().update(car_x, car_y))
//				iter.remove();
//			
//		}
//		for (Arrow arrow : activeTargets){
//			if ( arrow.update(car_x, car_y) ){
//				activeTargets.remove(arrow);
//			}
//			// add logic here. 
//			// remove or disable targets when needs to be removed
//		}
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
