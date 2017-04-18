package com.java.thread.puzzle;

import java.util.Set;

/**
 * 谜题
 * @author 001244
 *
 */
public interface Puzzle<P, M> {

	/** 
	 * 初始位置 
	 * */
	P initialPosition();
	
	/**
	 * 是否是目的位置
	 * @param position
	 * @return
	 */
	boolean isGoal(P position);
	
	/**
	 * 合法的移动位置集合
	 * @param position
	 * @return
	 */
	Set<M> legalMoves(P position);
	
	
	/**
	 * 移动
	 * @param position
	 * @param move
	 * @return
	 */
	P move(P position, M move);
}
