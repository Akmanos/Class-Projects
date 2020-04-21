/*
 * Copyright 2017 Marc Liberatore.
 */

package scheduler;

import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a Course.
 * 
 * @author liberato
 *
 */
public class Course {
	/**
	 * Instantiates a new Course object. The course number must be non-empty, and the 
	 * capacity must be greater than zero.
	 * @param courseNumber a course number, like "COMPSCI190D"
	 * @param capacity     the maximum number of students that can be in the class
	 * @throws IllegalArgumentException thrown if the courseNumber or capacity are invalid
	 */
	private String courseNumber;
	private int capacity;
	int openSlots;
	List<Student> roster;
	
	public Course(String courseNumber, int capacity) throws IllegalArgumentException {
		this.courseNumber=courseNumber;
		this.capacity=capacity;
		openSlots=capacity;
		roster= new ArrayList<Student>();
		
		if(courseNumber.length()==0 || capacity<1 || courseNumber.equals("")) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 
	 * @return the capacity of the course
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/**
	 * 
	 * @return the course number
	 */
	public String getCourseNumber() {
		return courseNumber;
	}
	
	public boolean addToSlot() {
		if(openSlots != 0) {
			openSlots-=1;
			return true;
		}
		return false;
	}
	
	public boolean dropStudent() {
		openSlots+=1;
		return true;
	}
	
	public int openSeat() {
		return openSlots;
	}
	
	public void addToRoster(Student s) {
		roster.add(s);
	}
	
	public void removeFromRoster(Student s) {
		roster.remove(s);
	}

	/**
	 * Returns the list of students enrolled in the course. 
	 * 
	 * This returned object does not share state with the internal state of the Course.
	 * 
	 * @return the list of students currently in the course
	 */
	public List<Student> getRoster() {		
		List<Student> roster=this.roster;
		
		return roster;
	}
}
