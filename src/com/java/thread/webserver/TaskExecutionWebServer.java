package com.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 利用线程池来管理线程数量，那么关键问题来了
 * 如何配置线程池中的线程数量，使得系统的效率最高
 * @author 001244
 *
 */
public class TaskExecutionWebServer {
	
	private static final int NTHREADS = 100;
	
	private static final Executor exec = 
			Executors.newFixedThreadPool(NTHREADS);

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while (true) {
			final Socket connection = socket.accept();
			exec.execute(new Runnable() {
				@Override
				public void run() {
					handleRequest(connection);
				}
			});
		}
	}
	
	private static void handleRequest(Socket connection) {
			
		}

}
