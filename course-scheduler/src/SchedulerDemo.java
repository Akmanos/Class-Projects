/*
 * Copyright 2018 David Wemhoener.
 */

package scheduler;

import java.util.Arrays;

public class SchedulerDemo {

    public static void main(String[] args) {
        Course a = new Course("ANTHRO100", 2);
        Course b = new Course("BIO100", 2);
        Course c = new Course("COMM100", 1);
        System.out.println("Courses: ");
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println();
        
        
        Student s = new Student("Alex", 1, 3, Arrays.asList(new Course[] {a, b, c}));
        Student t = new Student("Susan", 2, 4, Arrays.asList(new Course[] {c, a, b}));
        Student u = new Student("Charles", 3, 5, Arrays.asList(new Course[] {c, b, a}));
        System.out.println("Students: ");
        System.out.println(s);
        System.out.println(t);
        System.out.println(u);
        System.out.println();
        
        Scheduler scheduler = new Scheduler();
        
        scheduler.addStudent(s);
        scheduler.addStudent(t);
        scheduler.addStudent(u);
        scheduler.addCourse(a);
        scheduler.addCourse(b);
        scheduler.addCourse(c);
        scheduler.assignAll();
        
        System.out.println("Students Enrolled in " + a.toString());
        for(Student x: a.getRoster())
        System.out.println(x);
        System.out.println();
        
        System.out.println("Students Enrolled in " + b.toString());
        for(Student x: b.getRoster())
        System.out.println(x);
        System.out.println();
        
        System.out.println("Students Enrolled in " + c.toString());
        for(Student x: c.getRoster())
        System.out.println(x);
        System.out.println();
        
        
        
    }
    
}
