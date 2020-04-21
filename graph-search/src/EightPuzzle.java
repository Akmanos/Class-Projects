/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener
 */

package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import graphs.BaseGraph;
import search.SearchProblem;
import search.Searcher;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */
	List<Integer> startingValues;
	
	public EightPuzzle(List<Integer> startingValues) {
		if(startingValues.isEmpty()) {
			throw new IllegalArgumentException();
		}
		
		this.startingValues = startingValues;
	}

	
	@Override
	public List<Integer> getInitialState() {
		// TODO
		return startingValues;
	}

	
	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		// TODO
		List<List<Integer>> successors = new ArrayList<List<Integer>>();
		
		if(!currentState.isEmpty()) {
			List<Integer> neighbors = new ArrayList<Integer>();
			while(!isGoal(neighbors)) {
				for(int i=0; i<currentState.size(); i++) {
					if(currentState.get(i) == 0) {
						if(i == 8) {
							neighbors = currentState;
							int temp = neighbors.get(5);
							neighbors.set(5, neighbors.get(i));
							neighbors.set(8, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(7);
							neighbors.set(7, neighbors.get(i));
							neighbors.set(8, temp);
							successors.add(neighbors);
							
								break;
						}
						
						if(i == 7) {
							neighbors = currentState;
							int temp = neighbors.get(8);
							neighbors.set(8, neighbors.get(i));
							neighbors.set(7, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(6);
							neighbors.set(6, neighbors.get(i));
							neighbors.set(7, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(4);
							neighbors.set(4, neighbors.get(i));
							neighbors.set(7, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 6) {
							neighbors = currentState;
							int temp = neighbors.get(3);
							neighbors.set(3, neighbors.get(i));
							neighbors.set(6, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(7);
							neighbors.set(7, neighbors.get(i));
							neighbors.set(6, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 5) {
							neighbors = currentState;
							int temp = neighbors.get(2);
							neighbors.set(2, neighbors.get(i));
							neighbors.set(5, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(8);
							neighbors.set(8, neighbors.get(i));
							neighbors.set(5, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(4);
							neighbors.set(4, neighbors.get(i));
							neighbors.set(5, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 4) {
							neighbors = currentState;
							int temp = neighbors.get(1);
							neighbors.set(1, neighbors.get(i));
							neighbors.set(4, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(3);
							neighbors.set(3, neighbors.get(i));
							neighbors.set(4, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(5);
							neighbors.set(5, neighbors.get(i));
							neighbors.set(4, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(7);
							neighbors.set(7, neighbors.get(i));
							neighbors.set(4, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 3) {
							neighbors = currentState;
							int temp = neighbors.get(0);
							neighbors.set(0, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(4);
							neighbors.set(4, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(6);
							neighbors.set(6, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 2) {
							neighbors = currentState;
							int temp = neighbors.get(1);
							neighbors.set(1, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(5);
							neighbors.set(5, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 1) {
							neighbors = currentState;
							int temp = neighbors.get(0);
							neighbors.set(0, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(2);
							neighbors.set(2, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(4);
							neighbors.set(4, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
						
						if(i == 0) {
							neighbors = currentState;
							int temp = neighbors.get(1);
							neighbors.set(1, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
							
							neighbors = currentState;
							temp = neighbors.get(3);
							neighbors.set(3, neighbors.get(i));
							neighbors.set(i, temp);
							successors.add(neighbors);
							
							if(isGoal(neighbors)) {
								break;
							}
						}
					}
				}
				break;
			}
			
		}
		printState(currentState);
		return successors;
	}

	
	@Override
	public boolean isGoal(List<Integer> state) {
		// TODO
		if(state.isEmpty() || state == null || state.size()<1) {
			return false;
		}
		
		for(int i=0; i<state.size()-1; i++) {
			if(state.get(i) != (i+1)){
				return false;
			}
			
			if(state.get(state.size()-1) != 0) {
				return false;
			}
		}

		return true;
	}
	
	public static void printState(List<Integer> state) {
		String rowOne = state.get(0).toString() + state.get(1).toString() + state.get(2).toString();
		String rowTwo = state.get(3).toString() + state.get(4).toString() + state.get(5).toString();
		String rowThree = state.get(6).toString() + state.get(7).toString() + state.get(8).toString();
		System.out.println(rowOne);
		System.out.println(rowTwo);
		System.out.println(rowThree);
		System.out.println();
	}
	
	public static void main(String[] args) {
		EightPuzzle eightPuzzle = new EightPuzzle(Arrays.asList(new Integer[] {1, 2, 3, 4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> solution = new Searcher<List<Integer>>(eightPuzzle).findSolution();
		for (List<Integer> state : solution) {
			//System.out.println(state);
			printState(state);
		}
		System.out.println(solution.size() + " states in solution");
	}
}
