/*
 * Copyright 2018 David Wemhoener.
 */

package simulator;

import java.util.Random;
import java.util.Scanner;

/**
 * This class, when run, lets you interactively test your Car and RoadMap code.
 * 
 * @author wem
 *
 */
public class CarVisualizer{
	static RoadMap roadMap;
	static Car[] cars;
	static int carCount;

	public static void main(String[] args) {
		//roadMap = new RoadMap(10, 10); // you could replace this with a call to
		                               // RoadMap.fromString(); see the tests for an example.
		RoadMap roadMapSmall= RoadMap.fromString("..... " +
				   ".X.X. " +
                ".....");
		RoadMap roadMapBig= RoadMap.fromString("...... " +
				   ".X.XX. " +
             ".X.X.. " +
		     "......");
		roadMap = roadMapSmall;
		String rms = "sm";
		
		cars = new Car[5];
		carCount = 0;
		
		//Commands:
		//q = quit
		//c = add car
		//r = remove car
		//m = move cars
		//s = change map size
		
		Scanner scan = new Scanner(System.in);
		String s = "z";
		while(!s.equals("q")) {			
			draw();
			System.out.print("ENTER COMMAND:");
			s = scan.next();
			if (s.equals("c")){
				Random rand = new Random();
				boolean addedCar = false;
				while(!addedCar) {
					int x = rand.nextInt(roadMap.xSize);
					int y = rand.nextInt(roadMap.ySize);
					if ((carCount < cars.length) && (roadMap.isRoad(x, y))) {
						// and, is it clear (no bus)?
						boolean isClear = true;
						for (int i = 0; i < carCount; i++) {
							Car b = cars[i];
							if (b.getX() == x && b.getY() == y) {
								isClear = false;
								break;
							}
						}
						if (isClear) {
							cars[carCount] = new Car(carCount, roadMap, x, y);
							carCount++;
							addedCar = true;
						}
					}
				}
			}
			else if (s.equals("s")) {
				if (rms.equals("sm")) {
					cars = new Car[5];
					carCount = 0;
					roadMap = roadMapBig;
					rms = "big";
				}
				else if (rms.equals("big")) {
					cars = new Car[5];
					carCount = 0;
					roadMap = roadMapSmall;
					rms = "sm";
				}
			}
			else keyPressed(s);
		}
		
		scan.close();
	}

	public static void draw() {
		int[][] carmap = new int[10][10];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < carCount; i++) {
			Car b = cars[i];
			carmap[b.getX()][b.getY()] = 1; 					
		}
		// first draw the road/non-road
		for (int y = 0; y < roadMap.ySize; y++) {
			for (int x = 0; x < roadMap.xSize; x++) {
				if (carmap[x][y] == 1) {
					sb.append("@");
				}
				else if (roadMap.isRoad(x, y)) {
					sb.append("-");
				}
				else {
					sb.append("^");
				} 
			}
			System.out.println(sb.toString());
			sb = new StringBuilder();
		}
		System.out.println("");
	}
	
	public static void keyPressed(String key) {
		if (key.equals("m")) {
			for (int i = 0; i < carCount; i++) {
				cars[i].move();
			}
		} 
		else if (key.equals("d")) {
			if (carCount > 0) {
				carCount--;
				cars[carCount] = null;
			}
		}
	}
}
