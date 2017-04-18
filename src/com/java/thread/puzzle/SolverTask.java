package com.java.thread.puzzle;

import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import com.java.thread.puzzle.SequentialPuzzleSolver.Node;

public class SolverTask<P, M> extends Node<P, M> implements Runnable{

	private final ValueLatch latch;
	
	private final ConcurrentMap<P, Boolean> seen;
	
	private final Puzzle<P, M> puzzle;
	
	private final ExecutorService exec;
	
	SolverTask(P pos, M move, Node<P, M> pre, Puzzle<P, M> puzzle,
			ValueLatch latch, ConcurrentMap<P, Boolean> seen, 
			ExecutorService exec) {
		super(pos, move, pre);
		this.latch = latch;
		this.seen = seen;
		this.puzzle = puzzle;
		this.exec = exec;
	}

	@Override
	public void run() {
		if (latch.isSet() || seen.putIfAbsent(pos, true) != null) {
			return;//已经找到一个解决方案，或该位置曾经到达过
		}
		if (puzzle.isGoal(pos)) {
			latch.setValue(pos);
		} else {
			for (M m : puzzle.legalMoves(pos)) {
				exec.execute(command);
			}
		}
	}

}
