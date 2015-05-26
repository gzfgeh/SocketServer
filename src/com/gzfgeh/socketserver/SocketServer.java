package com.gzfgeh.socketserver;

import java.io.IOException;
import java.net.ServerSocket;

public class SocketServer {

	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.start();
	}
	
	public void start(){
		try {
			ServerSocket socket = new ServerSocket();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
