/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package scheduler;

import java.util.List;
import java.util.ArrayList;

public class Scheduler {
	/**
	 * Instantiates a new, empty scheduler.
	 */
	List<Scheduler> scheduler;
	List<Course> course;
	List<Student> student;
	
	public Scheduler() {
		scheduler= new ArrayList<Scheduler>();
		course = new ArrayList<Course>();
		student = new ArrayList<Student>();
	}
	
	/**
	 * Adds a course to the scheduler.
	 * 
	 * @param course  the course to be added
	 */
	public void addCourse(Course course) {
		this.course.add(course);
	}
	
	/** 
	 * Returns the list of courses that this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 *
	 * @return the list of courses
	 */
	public List<Course> getCourses() {
		List<Course> course=this.course;
		
		return course;
	}
	
	/**
	 * Adds a student to the scheduler.
	 * 
	 * @param student the student to add
	 */
	public void addStudent(Student student) {
		this.student.add(student);
	}
	
	/**
	 * Returns a list of the students this scheduler knows about.
	 * 
	 * This returned object does not share state with the internal state of the Scheduler.
	 * @return
	 */
	public List<Student> getStudents() {	
		List<Student> student=this.student;
		
		return student;
	}
	
	/**
	 * Assigns all students to courses in the following manner:
	 * 
	 * For a given student, check their list of preferred courses. Add them to the course that:
	 * 	 - exists in the scheduler's list of courses
	 *   - the student most prefers (that is, comes first in their preference list)
	 *   - the student is not not already enrolled in
	 *   - and is not full (in other words, at capacity)
	 * Adds courses to the *end* of the student's current list of classes. Adds students to 
	 * the *end* of the course's roster.
	 *   
	 * Repeat this process for each student, one-by-one; each student will now have one course,
	 * usually (but not always) their most preferred course.
	 * 
	 * Then repeat this whole process (adding one course per student, when possible, proceeding
	 * round-robin among students), until there is nothing left to do: Students might 
	 * all be at their maximum number of courses, or there may be no available seats in courses 
	 * that students want.
	 */
	public void assignAll() {
		
		for(int i=0; i<student.size(); i++) {
			int courseIndex=0;
			for(int n=0; n<student.get(i).preferences.size(); n++) {
				
				for(int j=0; j<course.size(); j++) {
					
					if(student.get(i).preferences.get(n).getCourseNumber().equals(course.get(j).getCourseNumber())){
						courseIndex=j;
						continue;
					}
					continue;
				}
				if(course.get(courseIndex).openSeat()>0 && student.get(i).getN()>0) {
					course.get(courseIndex).addToSlot();
					course.get(courseIndex).addToRoster(student.get(i));
					student.get(i).fillMax();
					continue;
				}
				continue;
			}
		}
	}

	/**
	 * Drops a student from a course.
	 * 
	 * @param student
	 * @param course 
	 * @throws IllegalArgumentException  if either the student or the course are not known to this scheduler
	 */
	public void drop(Student student, Course course) throws IllegalArgumentException {
		int studentIndex=0;
		int courseIndex=0;
		if(!this.course.contains(course) || !this.student.contains(student)) {
			throw new IllegalArgumentException();
		}
		
		for(int i=0; i<this.student.size(); i++) {
			if(this.student.get(i) == student) {
				studentIndex=i;
			}
			
			for(int j=0; j<this.course.size(); j++) {
				if(this.course.get(j) == course) {
					courseIndex=j;
				}
			}
		}
		
		this.course.get(courseIndex).dropStudent();
		this.course.get(courseIndex).removeFromRoster(this.student.get(studentIndex));
	}

	
    /**
     * Calculates the number of students interested in a course
     * 
     * @param course
     * @throws IllegalArgumentException  if the course is not known to this scheduler
     * @return
     */
    public int interestLevel(Course course) throws IllegalArgumentException{
    	int n=0;
    	if(!this.course.contains(course)) {
    		throw new IllegalArgumentException();
    	}
    	
    	for(int i=0; i<student.size(); i++) {
    		for(int j=0; j<student.get(i).preferences.size(); j++) {
    			if(student.get(i).preferences.get(j).getCourseNumber().equals(course.getCourseNumber())){
    				n++;
    			}
    		}
    	}
    		
        return n;
    }
}
