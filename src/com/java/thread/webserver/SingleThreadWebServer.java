package com.java.thread.webserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * ˳��Ĵ�������
 * �������ܽϲ�
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
