/*
 * Copyright 2017 Marc Liberatore.
 */

package list;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;


import list.RecursiveLinkedList;

public class LinkedListTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testEmpty() {
		RecursiveLinkedList<Integer> ll = new RecursiveLinkedList<>();
		assertEquals(0, ll.size());
	}

	@Test
	public void testNotEmpty() {
		RecursiveLinkedList<Integer> ll = new RecursiveLinkedList<>();
		ll.append(10);
		assertEquals(1, ll.size());
	}
	
	@Test
	public void testTwo() {
		RecursiveLinkedList<Integer> ll = new RecursiveLinkedList<>();
		ll.append(10);
		ll.append(10);
		assertEquals(2, ll.size());
	}
	
	@Test
	public void testContains() {
		RecursiveLinkedList<Integer> ll = new RecursiveLinkedList<>();
		ll.append(0);
		ll.append(10);
		ll.append(20);
		assertTrue(ll.contains(20));
		assertTrue(ll.contains(10));
	}
	
	@Test
	public void testNotContains() {
		RecursiveLinkedList<Integer> ll = new RecursiveLinkedList<>();
		ll.append(0);
		ll.append(10);
		ll.append(20);
		assertFalse(ll.contains(1));
	}
}
