/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package scheduler;

import java.util.List;
import java.util.ArrayList;

/**
 * A class representing a student.
 * 
 * @author liberato
 *
 */
public class Student {
	/**
	 * 
	 * Instantiates a new Student object. The student's maximum course load must be greater
	 * than zero, and the preferences list must contain at least one course.
	 * 
	 * The preference list is copied into this Student object.
	 * 
	 * @param name        the student's name
	 * @param int         the student's id
	 * @param maxCourses  the maximum number of courses that can be on this student's schedule
	 * @param preferences the student's ordered list of preferred courses
	 * @throws IllegalArgumentException thrown if the maxCourses or preferences are invalid
	 */
	private String name;
	private int id;
	private int maxCourses;
	List<Course> preferences;
	List<Course> schedule;
	int n;
	
	public Student(String name, int id, int maxCourses, List<Course> preferences) throws IllegalArgumentException {
		this.name=name;
		this.id=id;
		this.maxCourses=maxCourses;
		this.preferences=preferences;
		schedule= new ArrayList<Course>();
		n=this.maxCourses;
		
		if(maxCourses<1 || preferences.size()<1) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * 
	 * @return the student's name
	 */
	public String getName() {
		return name;
	}

    /**
     * 
     * @return the student's id
     */
    public int getID() {
        return id;
    }
	
	/**
	 * 
	 * @return the student's max course load
	 */
	public int getMaxCourses() {
		return maxCourses;
	}
	
	public void fillMax() {
		if(n>0) {
			n--;
		}
	}
	
	public int getN() {
		return n;
	}
	/**
	 * Returns the student's list of course preferences, ordered from most- to least-desired.
	 * 
	 * This returned object does not share state with the internal state of the Student.
	 * 
	 * @return the student's preference list
	 */
	public List<Course> getPreferences() {
		return preferences;
	}
	
	/**
	 * Returns the student's current schedule.
	 * 
	 * This returned object does not share state with the internal state of the Student.
     *
	 * @return the student's schedule
	 */
	public List<Course> getSchedule() {
		if(!this.preferences.isEmpty()) {
			for(int i=0; i<this.preferences.size(); i++) {
				schedule.add(this.preferences.get(i));
			}
		}
		return schedule;
	}
}
