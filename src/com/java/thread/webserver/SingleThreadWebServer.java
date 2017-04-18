package com.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * 顺序的处理请求
 * 并发性能较差
 * @author 001244
 *
 */
public class SingleThreadWebServer {

	public static void main(String[] args) throws IOException {
		ServerSocket socket = new ServerSocket(80);
		while(true) {
			Socket connection = socket.accept();
			handleRequest(connection);
		}
	}
	
	
	private static void handleRequest(Socket connection) {
		
	}

}
