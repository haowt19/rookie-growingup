package com.java.thread.puzzle;

import java.util.Set;

/**
 * ����
 * @author 001244
 *
 */
public interface Puzzle<P, M> {

	/** 
	 * ��ʼλ�� 
	 * */
	P initialPosition();
	
	/**
	 * �Ƿ���Ŀ��λ��
	 * @param position
	 * @return
	 */
	boolean isGoal(P position);
	
	/**
	 * �Ϸ����ƶ�λ�ü���
	 * @param position
	 * @return
	 */
	Set<M> legalMoves(P position);
	
	
	/**
	 * �ƶ�
	 * @param position
	 * @param move
	 * @return
	 */
	P move(P position, M move);
}
