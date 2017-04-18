package com.java.thread.puzzle;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import com.java.thread.puzzle.SequentialPuzzleSolver.Node;

public class ConcurrentPuzzleSolver<P, M> {
	
	private final Puzzle<P, M> puzzle;
	
	private final ExecutorService exec;
	
	private final ConcurrentMap<P, Boolean> seen;
	
	final ValueLatch<Node<P, M>> solution = 
				new ValueLatch<Node<P, M>>();
	
	
	public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, 
			ExecutorService exec, ConcurrentMap<P, Boolean> seen) {
		this.puzzle = puzzle;
		this.exec = exec;
		this.seen = seen;
	}
	
	
	public List<M> solve() throws InterruptedException {
		try {
			P pos = puzzle.initialPosition();
			exec.execute(newTask(pos, null, null));
			Node<P, M> solNode = solution.getValue();
			return solNode == null ? null : solNode.asMoveList();
		} finally {
			exec.shutdown();
		}
	}
	
	
	protected Runnable newTask(P p, M m, Node<P, M> pre) {
		return new SolverTask(p, m , pre);
	}
	
	
	class SolverTask extends Node<P, M> implements Runnable {

		SolverTask(P pos, M move, Node<P, M> pre) {
			super(pos, move, pre);
		}

		@Override
		public void run() {
			if (solution.isSet() || seen.putIfAbsent(pos, true) != null) {
				return;
			}
			if (puzzle.isGoal(pos)) {
				solution.setValue(this);
			} else {
				for (M m : puzzle.legalMoves(pos)) {
					exec.execute(newTask(puzzle.move(pos, m), m, this));
				}
			}
		}
		
	}

}
