/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package scheduler;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


public class SchedulerTest {

	// uncomment the following if you're trying to diagnose an infinite loop in a
	// test
	// @Rule
	// public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	private Scheduler scheduler;
	
	private Scheduler singleScheduler;
	private Course courseOne;
	private Student studentOne;

	private Course a;
	private Course b;
	private Course c;
	private List<Course> listA;
	private List<Course> listB;
	private List<Course> listAB;
	private List<Course> listBA;	
	private List<Course> listAC;
	private List<Course> listCA;	

	// This method is run before each test, performing initialization
	// on objects for the test.
	@Before
	public void setup() {
		scheduler = new Scheduler();
		courseOne = new Course("ONE1", 1);
		List<Course> l = new ArrayList<>();
		l.add(courseOne);
		studentOne = new Student("one", 1, 1, l);
		singleScheduler = new Scheduler();
		singleScheduler.addStudent(studentOne);
		singleScheduler.addCourse(courseOne);

		a = new Course("ANTHRO100", 2);
		b = new Course("BIO100", 2);
		c = new Course("COMM100", 1);
		
		listA = Arrays.asList(new Course[] {a});
		listB = Arrays.asList(new Course[] {b});
		listAB = Arrays.asList(new Course[] {a, b});
		listBA = Arrays.asList(new Course[] {b, a});
		listAC = Arrays.asList(new Course[] {a, c});
		listCA = Arrays.asList(new Course[] {c, a});
	}

	private static <E> void checkList(List<E> expected, List<E> actual) {
		assertEquals(expected.size(), actual.size());
		for (E e: expected) {
			assertTrue(actual.contains(e));
		}
	}
	
	@Test
	public void testGetCoursesEmpty() {
		assertTrue(scheduler.getCourses().isEmpty());
	}

	@Test
	public void testGetStudentsEmpty() {
		assertTrue(scheduler.getStudents().isEmpty());
	}

	@Test
	public void testAddOneCourse() {
		assertEquals(1, singleScheduler.getCourses().size());
		assertTrue(singleScheduler.getCourses().contains(courseOne));
	}

	@Test
	public void testAddOneStudent() {
		assertEquals(1, singleScheduler.getStudents().size());
		assertTrue(singleScheduler.getStudents().contains(studentOne));
	}

	@Test
	public void testGetCoursesNotShared() {
		List<Course> l = singleScheduler.getCourses();
		l.clear();
		assertTrue(singleScheduler.getCourses().contains(courseOne));
	}

	@Test
	public void testGetStudentsNotShared() {
		List<Student> l = singleScheduler.getStudents();
		l.clear();
		assertTrue(singleScheduler.getStudents().contains(studentOne));
	}
	
	@Test
	public void testSingleCourse() {		
		Student s = new Student("s", 1, 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(listA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listA, s.getSchedule());
	}
	
	@Test
	public void testDropSimple() {		
		Student s = new Student("s", 1, 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.assignAll();
		scheduler.drop(s, a);
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(listA, scheduler.getCourses());
		assertTrue(s.getSchedule().isEmpty());
		assertTrue(a.getRoster().isEmpty());
	}

	@Test
	public void testSingleUnavailableCourse() {		
		Student s = new Student("s",1, 1, listA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		assertEquals(Arrays.asList(new Course[] {b}), scheduler.getCourses());
		assertTrue(a.getRoster().isEmpty());
		assertTrue(b.getRoster().isEmpty());
	}
	
	@Test
	public void testPreferFirst() {		
		Student s = new Student("s", 1, 1, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listA, s.getSchedule());
		assertTrue(b.getRoster().isEmpty());
		assertEquals(1,scheduler.interestLevel(a));
        assertEquals(1,scheduler.interestLevel(b));
	}

	@Test
	public void testPreferSecond() {		
		Student s = new Student("s", 1, 1, listBA);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, b.getRoster());
		assertEquals(listB, s.getSchedule());
		assertTrue(a.getRoster().isEmpty());
        assertEquals(1,scheduler.interestLevel(a));
        assertEquals(1,scheduler.interestLevel(b));
	}
	
	@Test
	public void testAddTwo() {		
		Student s = new Student("s", 2, 2, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertEquals(listS, b.getRoster());
		
		assertEquals(2, s.getSchedule().size());
		checkList(listAB, s.getSchedule());
	}
	
	@Test
	public void testAddOnlyOne() {		
		Student s = new Student("s", 1, 1, listAB);
		List<Student> listS = Arrays.asList(new Student[] {s});
		
		scheduler.addStudent(s);
		scheduler.addCourse(b);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		assertEquals(listS, scheduler.getStudents());
		checkList(listBA, scheduler.getCourses());
		assertEquals(listS, a.getRoster());
		assertTrue(b.getRoster().isEmpty());
		assertEquals(listA, s.getSchedule());
	}
	
	@Test
	public void testTwoInTwo() {		
		Student s = new Student("s", 1, 2, listAB);
		Student t = new Student("t", 2, 2, listBA);
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		
		checkList(listST, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		checkList(listST, a.getRoster());
		checkList(listTS, b.getRoster());
		checkList(listAB, s.getSchedule());
		checkList(listBA, t.getSchedule());
        assertEquals(2,scheduler.interestLevel(a));
        assertEquals(2,scheduler.interestLevel(b));
	}
	
	@Test
	public void testDropFromOne() {		
		Student s = new Student("s", 1, 2, listAB);
		Student t = new Student("t", 2, 2, listBA);
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.assignAll();
		scheduler.drop(t, a);
		
		checkList(listST, scheduler.getStudents());
		checkList(listAB, scheduler.getCourses());
		assertEquals(Arrays.asList(new Student[] {s}), a.getRoster());
		checkList(listTS, b.getRoster());
		checkList(listAB, s.getSchedule());
		assertEquals(listB, t.getSchedule());
        assertEquals(2,scheduler.interestLevel(a));
        assertEquals(2,scheduler.interestLevel(b));
	}
	
	@Test
	public void testOneFull() {		
		Student s = new Student("s", 1, 2, listAC);
		Student t = new Student("t", 2, 2, listCA);
		List<Student> listT = Arrays.asList(new Student[] {t});
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(t);
		scheduler.addStudent(s);
		scheduler.addCourse(c);
		scheduler.addCourse(a);
		scheduler.assignAll();
		
		checkList(listTS, scheduler.getStudents());
		checkList(listCA, scheduler.getCourses());
		checkList(listST, a.getRoster());
		assertEquals(listT, c.getRoster());
		assertEquals(listA, s.getSchedule());
		checkList(listCA, t.getSchedule());
        assertEquals(2,scheduler.interestLevel(a));
        assertEquals(2,scheduler.interestLevel(c));
	}
	
	@Test
	public void testOtherFull() {		
		Student s = new Student("s", 1, 2, listAC);
		Student t = new Student("t", 2, 2, listCA);
		List<Student> listT = Arrays.asList(new Student[] {t});
		List<Student> listST = Arrays.asList(new Student[] {s, t});
		List<Student> listTS = Arrays.asList(new Student[] {t, s});
		
		scheduler.addStudent(t);
		scheduler.addStudent(s);
		scheduler.addCourse(a);
		scheduler.addCourse(c);
		scheduler.assignAll();
		
		checkList(listTS, scheduler.getStudents());
		checkList(listAC, scheduler.getCourses());
		checkList(listST, a.getRoster());
		assertEquals(listT, c.getRoster());
		assertEquals(listA, s.getSchedule());
		checkList(listCA, t.getSchedule());
        assertEquals(2,scheduler.interestLevel(a));
        assertEquals(2,scheduler.interestLevel(c));
	}
	
	@Test
	public void testThree() {
		Course d = new Course("DUTCH100", 2);
		Course e = new Course("ECON100", 3);
		
		Student s = new Student("s", 1, 3, Arrays.asList(new Course[] {a, b, c, d, e}));
		Student t = new Student("t", 2, 4, Arrays.asList(new Course[] {c, a, d, e, b}));
		Student u = new Student("u", 3, 5, Arrays.asList(new Course[] {b, a, d, c, e}));
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addStudent(u);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.addCourse(c);
		scheduler.addCourse(d);
		scheduler.addCourse(e);
		scheduler.assignAll();
		
		checkList(Arrays.asList(new Student[] {s, t, u}), scheduler.getStudents());		
		checkList(Arrays.asList(new Course[] {a, b, c, d, e}), scheduler.getCourses());
		checkList(Arrays.asList(new Student[] {s, t}), a.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), b.getRoster());
		assertEquals(Arrays.asList(new Student[] {t}), c.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), d.getRoster());
		checkList(Arrays.asList(new Student[] {t, u}), e.getRoster());
		checkList(Arrays.asList(new Course[] {a, b, d}), s.getSchedule());
		checkList(Arrays.asList(new Course[] {c, a, e}), t.getSchedule());
		checkList(Arrays.asList(new Course[] {b, d, e}), u.getSchedule());
        assertEquals(3,scheduler.interestLevel(a));
        assertEquals(3,scheduler.interestLevel(b));
        assertEquals(3,scheduler.interestLevel(c));
        assertEquals(3,scheduler.interestLevel(d));
        assertEquals(3,scheduler.interestLevel(e));
	}

	@Test
	public void testFour() {
		Course d = new Course("DUTCH100", 2);
		Course e = new Course("ECON100", 3);
		
		Student s = new Student("s", 1, 3, Arrays.asList(new Course[] {a, b, c, d, e}));
		Student t = new Student("t", 2, 4, Arrays.asList(new Course[] {c, a, d, e, b}));
		Student u = new Student("u", 3, 5, Arrays.asList(new Course[] {b, a, d, c, e}));
		Student v = new Student("v", 4, 4, Arrays.asList(new Course[] {a, b, c, d, e}));
		Student w = new Student("w", 5, 1, Arrays.asList(new Course[] {c, a, d, e, b}));
		
		scheduler.addStudent(s);
		scheduler.addStudent(t);
		scheduler.addStudent(u);
		scheduler.addStudent(v);
		scheduler.addStudent(w);
		scheduler.addCourse(a);
		scheduler.addCourse(b);
		scheduler.addCourse(c);
		scheduler.addCourse(d);
		scheduler.addCourse(e);
		scheduler.assignAll();
		
		checkList(Arrays.asList(new Student[] {s, t, u, v, w}), scheduler.getStudents());		
		checkList(Arrays.asList(new Course[] {a, b, c, d, e}), scheduler.getCourses());
		checkList(Arrays.asList(new Student[] {s, v}), a.getRoster());
		checkList(Arrays.asList(new Student[] {u, s}), b.getRoster());
		assertEquals(Arrays.asList(new Student[] {t}), c.getRoster());
		checkList(Arrays.asList(new Student[] {w, t}), d.getRoster());
		checkList(Arrays.asList(new Student[] {u, v, s}), e.getRoster());
		checkList(Arrays.asList(new Course[] {a, b, e}), s.getSchedule());
		checkList(Arrays.asList(new Course[] {c, d}), t.getSchedule());
		checkList(Arrays.asList(new Course[] {b, e}), u.getSchedule());
		checkList(Arrays.asList(new Course[] {a, e}), v.getSchedule());
		assertEquals(Arrays.asList(new Course[] {d}), w.getSchedule());
        assertEquals(5,scheduler.interestLevel(a));
        assertEquals(5,scheduler.interestLevel(b));
        assertEquals(5,scheduler.interestLevel(c));
        assertEquals(5,scheduler.interestLevel(d));
        assertEquals(5,scheduler.interestLevel(e));
	}
	
}
