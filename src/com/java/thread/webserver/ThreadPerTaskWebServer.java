package com.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 每个任务启动一个线程
 * 请求过多，资源有限，导致系统挂掉
 * @author 001244
 *
 */
public class ThreadPerTaskWebServer {

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while(true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				@Override
				public void run() {
					handleRequest(connection);
				}
			};
			new Thread(task).start();
		}
	}
	
	
	private static void handleRequest(Socket connection) {
			
		}
}
