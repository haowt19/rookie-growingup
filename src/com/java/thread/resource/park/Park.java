package com.java.thread.resource.park;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Park {

	public static void main(String[] args) {
		int nThreads = 4;
		ExecutorService exec = Executors.newFixedThreadPool(nThreads);
		
		for (int i=0;i<4;i++) {
			exec.execute(new GateStatistic(i));
		}
		
		try {
			TimeUnit.MILLISECONDS.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ParkCount.setCancel(true);
		System.out.println("已经关闭公园各出入口，停止计数");
		
		exec.shutdown();
		
		try {
			if (!exec.awaitTermination(10, TimeUnit.MILLISECONDS)) {
				System.out.println("有任务并没有结束");
				if (!exec.awaitTermination(10, TimeUnit.MILLISECONDS)) {
					System.out.println("还是有任务并没有结束+1");
					if (!exec.awaitTermination(10, TimeUnit.MILLISECONDS)) {	
						System.out.println("还是有任务并没有结束+2");
					}
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
