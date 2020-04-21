/*
 * Copyright 2017 Marc Liberatore.
 */

package list;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import list.RecursiveNode;

public class CharacterNodeTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(10); // 10 seconds

	@Test
	public void testInfo() {
		RecursiveNode<Character> a = new RecursiveNode<>('a');
		assertEquals("Expected 'a' but found " + a.getValue(),
					 new Character('a'), a.getValue());
	}

	@Test
	public void testLink() {
		RecursiveNode<Character> a = new RecursiveNode<>('a');
		assertEquals("Expected 'a' but found " + a.getValue(),
					new Character('a'), a.getValue());
		RecursiveNode<Character> b = new RecursiveNode<>('b');
		a.setNext(b);
		assertEquals("Expected reference to b but found " + a.getNext(),
				 b, a.getNext());
	}

	@Test
  public void testTraversal() {
		RecursiveNode<Character> head = null;
		for (int i = 97; i < (97+26); i++) {
			RecursiveNode<Character> n = new RecursiveNode<>((char) i);
			if (head == null)
				head = n;
			else {
				n.setNext(head);
				head = n;
			}
		}

		RecursiveNode<Character> n = head;
		for (int i = 122; i >= 97; i--) {
			assertEquals("Expected " + ((char)i) + " but found " + n.getValue(),
					new Character((char)i), n.getValue());
			n = n.getNext();
		}
	}
}
