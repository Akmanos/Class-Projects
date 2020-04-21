/*
 * Copyright 2017 Marc Liberatore.
 */

package simulator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class CarTest {
	@Rule
    public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds 
	
	@Test
	public void testConstructor() {
		Car b = new Car(1, new RoadMap(0, 0), 2, 3);
		assertEquals(1, b.number);
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
	}

	@Test
	public void testMoveNorthFromStop() {
		Car b = new Car(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveNorthTwice() {
		Car b = new Car(0, RoadMap.fromString("X.X X.X X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());		
	}

	@Test
	public void testMoveNorthThenEastOneWay() {
		Car b = new Car(0, RoadMap.fromString("XXX X.. X.X"), 1, 2);
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());		
	}
	
	@Test
	public void testMoveSouthThenWestAtTee() {
		Car b = new Car(0, RoadMap.fromString("X.X ... XXX"), 1, 0);
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());		
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());		
	}

	@Test
	public void testSmallWorldStuck() {
		Car b = new Car(0, RoadMap.fromString("."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
	}

	@Test
	public void testLessSmallWorldStuck() {
		Car b = new Car(0, RoadMap.fromString("XXX X.X XXX"), 1, 1);
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
	}
	
	@Test
	public void testLessSmallWorldLoopCW() {
		Car b = new Car(0, RoadMap.fromString("... .X. ..."), 1, 0);
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
	}	
	
	@Test
	public void testLessSmallWorldLoopCCW() {
		Car b = new Car(0, RoadMap.fromString("... .X. ..."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
	}
	
	@Test
	public void testZigZag() {
		Car b = new Car(0, RoadMap.fromString("..X X.. XX."), 0, 0);
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(0, b.getY());
	}

	@Test
	public void testFigureEight() {
		Car b = new Car(0, RoadMap.fromString("XX... XX.X. ..... .X.XX ...XX"), 4, 0);
		assertEquals(4, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(3, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(3, b.getY());
		b.move();
		assertEquals(0, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(1, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(4, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(3, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(2, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(1, b.getY());
		b.move();
		assertEquals(2, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(3, b.getX());
		assertEquals(0, b.getY());
		b.move();
		assertEquals(4, b.getX());
		assertEquals(0, b.getY());
	}
}
