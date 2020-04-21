/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package search;

import java.util.Set;

import mazes.Cell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 * @author liberato
 *
 * @param <T> the type for each vertex in the search graph
 */
public class Searcher<T> {
	private final SearchProblem<T> searchProblem;
	
	/**
	 * Instantiates a searcher.
	 * 
	 * @param searchProblem
	 *            the search problem for which this searcher will find and
	 *            validate solutions
	 */
	public Searcher(SearchProblem<T> searchProblem) {
		this.searchProblem = searchProblem;
	}

	/**
	 * Finds and return a solution to the problem, consisting of a list of
	 * states.
	 * 
	 * The list should start with the initial state of the underlying problem.
	 * Then, it should have one or more additional states. Each state should be
	 * a successor of its predecessor. The last state should be a goal state of
	 * the underlying problem.
	 * 
	 * If there is no solution, then this method should return an empty list.
	 * 
	 * @return a solution to the problem (or an empty list)
	 */
	
	public List<T> findSolution() {		
		// TODO
		List<T> solution = new ArrayList<T>();
		Queue<T> paths = new LinkedList<>();
		paths.add(searchProblem.getInitialState());
		T start = searchProblem.getInitialState();
		Map<T, T> predecessor = new HashMap<>();
		predecessor.put(start, null);
		
		while(!paths.isEmpty()) {
			T current = paths.remove();
			
			for(T next : searchProblem.getSuccessors(current)) {
				if(!predecessor.containsKey(next)) {
					paths.add(next);
					predecessor.put(next, current);
				}
			}
			
			if(searchProblem.isGoal(current)) {
				solution.add(current);
				T previous = predecessor.get(current);
				while(previous != null) {
					solution.add(0, previous);
					previous = predecessor.get(previous);
				}
				break;
			}
		}
		return solution;
	}
	
	/**
	 * Determines what states are reachable from the start state in a
	 * certain number of moves
	 * 
	 * @param m the number of moves from the start state
	 * @return a set of nodes
	 */
	public Set<T> findReachableSet(int m) {		
		// TODO
		Set<T> reachable = new HashSet<T>();
		T start = searchProblem.getInitialState();
		Queue<T> states = new LinkedList<T>();
		states.add(start);
		Map<T, T> predecessor = new HashMap<>();
		predecessor.put(start, null);
		int n = 0;
		
		if( m == 0) {
			if(!states.isEmpty()) {
				T current = states.peek();
				
				for(T next : searchProblem.getSuccessors(current)) {
					if(!predecessor.containsKey(next)) {						
						reachable.add(current);
						return reachable;
					}
				}
			}
		}
		
		while(n<=m) {
			if(!states.isEmpty()) {
				T curr = states.remove();
				
				for(T next : searchProblem.getSuccessors(curr)) {
					if(!predecessor.containsKey(next)) {
						states.add(next);
						predecessor.put(next, curr);
						reachable.add(next);
						reachable.add(curr);
						
					}
					n++;
				}
				
			}
		}
		
		return reachable;
	}

	/**
	 * Checks that a solution is valid.
	 * 
	 * A valid solution consists of a list of states. The list should start with
	 * the initial state of the underlying problem. Then, it should have one or
	 * more additional states. Each state should be a successor of its
	 * predecessor. The last state should be a goal state of the underlying
	 * problem.
	 * 
	 * @param solution
	 * @return true iff this solution is a valid solution
	 * @throws NullPointerException
	 *             if solution is null
	 */
	public final boolean isValidSolution(List<T> solution) {
		// TODO
		if(solution.equals(null) || solution == null) {
			throw new NullPointerException();
		}
		
		T c = searchProblem.getInitialState();
		
		if(solution.get(0).equals(c)) {
			for(int i=0; i<solution.size()-1; i++) {
				List<T> successors = searchProblem.getSuccessors(solution.get(i));
				if(!successors.contains(solution.get(i+1))) {
					return false;
				}
			}
			
			boolean v = searchProblem.isGoal(solution.get(solution.size()-1));
			
			if(v == false) {
				return false;
			}
			return true;
		}
		return false;
	}
}
