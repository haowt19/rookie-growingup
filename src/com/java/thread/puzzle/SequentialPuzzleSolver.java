package com.java.thread.puzzle;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * 顺序化谜题解决方案
 * 在谜题空间中执行一个深度优先搜索，
 * 并在找到一个答案（不必是最短答案）后终止
 * @author 001244
 *
 */
public class SequentialPuzzleSolver<P, M> {

	/** 谜题 */
	private final Puzzle<P, M> puzzle;
	
	/** 已被走过的节点集合 */
	private final Set<P> seen = new HashSet<P>();
	
	
	public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
		this.puzzle = puzzle;
	}
	
	
	public List<M> solve() {
		P pos = puzzle.initialPosition();
		return search(new Node<P, M>(pos, null, null));
	}
	
	/**
	 * 顺序话递归查找路线
	 * @param node
	 * @return
	 */
	private List<M> search(Node<P, M> node) {
		if (!seen.contains(node.pos) ) {
			seen.add(node.pos);
			if ( puzzle.isGoal(node.pos) ) {
				return node.asMoveList();
			}
			for (M move : puzzle.legalMoves(node.pos)) {
				P pos = puzzle.move(node.pos, move);
				Node<P, M> child = new Node<P, M>(pos, move, node);
				List<M> result = search(child);
				if (result != null) {
					return result;
				}
			}
		}
		return null;
	}
	
	
	static class Node<P, M> {
		final P pos;
		final M move;
		final Node<P, M> pre;
		
		Node(P pos, M move, Node<P, M> pre) {
			this.pos = pos;
			this.move = move;
			this.pre = pre;
		}
		
		/**
		 * 获取结果路线
		 * @return
		 */
		List<M> asMoveList() {
			List<M> solution = new LinkedList<M>();
			for (Node<P, M> n = this;  n.move != null; n = n.pre) {
				solution.add(0, n.move);
			}
			return solution;
		}
		
	}
	
}
