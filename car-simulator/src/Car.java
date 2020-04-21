/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

public class Car {
	public final int number;
	private final RoadMap roadMap;
	private int x;
	private int y;
	private int count;
    private int n;
    
    
	public Car(int number, RoadMap roadMap, int x, int y) {
		this.number = number;
		this.roadMap = roadMap;
		this.x = x;
		this.y = y;
		count=0;
		n=0;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	/**
	 * Move the car. Cars only move along the cardinal directions
	 * (north/south/east/west), not diagonally.
	 * 
	 * If the car is stopped (that is, if it was just placed, or if it didn't
	 * move last time move() was called), then it should attempt to move north.
	 * If it cannot (no road, or off the map), then it should attempt east,
	 * then south, then west. If no move is available, it should stay in its
	 * current position.
	 * 
	 * If the car is moving (that is, if it successfully moved the last time
	 * move() was called), then it should attempt to continue moving in the same
	 * direction.
	 * 
	 * If it cannot (no road, or off the map), then it should attempt to turn
	 * right. For example, if the car was moving north, but there is no more
	 * road to the north, it should move east if possible.
	 * 
	 * If it cannot turn right, it should turn left. If it cannot turn left, it
	 * should reverse direction (that is, move backward, if possible). 
	 * If it cannot do any of these things, it should stay in its current position.
	 */
	public void move() {
		
		if(count==0) {
			if(y>0 && roadMap.isRoad(x, y-1)) {
					y-=1; //move north
					n=1;
				}
			
			else if(x<roadMap.xSize && roadMap.isRoad(x+1, y)) {
						x+=1; //move east
						n=2;
					}
				
			else if(y<roadMap.ySize && roadMap.isRoad(x, y+1)) {
						y+=1; //move south
						n=3;
				}
				
			else if(x>0 && roadMap.isRoad(x-1, y)) {
						x-=1; //move west
						n=4;
				}
			
			else {
				
			}
		}
		
		else if(count>0) {
			if(n==1) {
				if(y>0 && roadMap.isRoad(x, y-1)) {
					y-=1; //if HEADING NORTH keep heading north
					n=1;
				}
				
				else if(x<roadMap.xSize && roadMap.isRoad(x+1, y)) {
					x+=1; //turn right, east
					n=2;
				}
				
				else if(x>0 && roadMap.isRoad(x-1, y)) {
					x-=1; //otherwise turn left, west
					n=4;
				}
				
				else if(y<roadMap.ySize && roadMap.isRoad(x, y+1)) {
					y+=1; //turn south
					n=3;
				}
				
				else {
					
				}
			}
			
			else if(n==2) {
				if(x<roadMap.xSize && roadMap.isRoad(x+1, y)) {
					x+=1; //if HEADING EAST keep heading east (right)
					n=2;
				}
				
				else if(y<roadMap.ySize && roadMap.isRoad(x, y+1)) {
					y+=1; //turn south
					n=3;
				}
				
				else if(y>0 && roadMap.isRoad(x, y-1)) {
					y-=1; //turn north
					n=1;
				}
				
				else if(x>0 && roadMap.isRoad(x-1, y)) {
					x-=1; //otherwise turn left, west
					n=4;
				}
				
				else {
					
				}
			}
			
			else if(n==3) {
				if(y<roadMap.ySize && roadMap.isRoad(x, y+1)) {
					y+=1; //if HEADING SOUTH keep heading south
					n=3;
				}
				
				else if(x>0 && roadMap.isRoad(x-1, y)) {
					x-=1; //otherwise turn left, west
					n=4;
				}
				
				else if(x<roadMap.xSize && roadMap.isRoad(x+1, y)) {
					x+=1; //turn right, east
					n=2;
				}
				
				else if(y>0 && roadMap.isRoad(x, y-1)) {
					y-=1; //turn north
					n=1;
				}
				else {
					
				}
			}
			
			else if(n==4) {
				if(x>0 && roadMap.isRoad(x-1, y)) {
					x-=1; //if HEADING WEST keep heading west
					n=4;
				}
				
				else if(y>0 && roadMap.isRoad(x, y-1)) {
					y-=1; //turn north
					n=1;
				}
				
				else if(y<roadMap.ySize && roadMap.isRoad(x, y+1)) {
					y+=1; //turn south
					n=3;
				}
				
				else if(x<roadMap.xSize && roadMap.isRoad(x+1, y)) {
					x+=1; //turn right, east
					n=2;
				}
				else {
					
				}
			}
			count=1;
		}
	}
}
