package com.gzfgeh.socketserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	private BufferedWriter writer;
	private BufferedReader socketReader;
	
	public static void main(String[] args) {
		SocketServer server = new SocketServer();
		server.start();
	}
	
	public void start(){
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(8989);
			
			while (true){
				socket = serverSocket.accept();
				System.out.println(socket.hashCode() + " connect \n");
				connectManager(socket);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				serverSocket.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void connectManager(final Socket socket){
		new Thread(new Runnable() {
			
			public void run() {
				try {
					String receiveMsg;
					socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					while ((receiveMsg = socketReader.readLine()) != null){
						System.out.println(socket.hashCode() + " client send: " + receiveMsg);
						writer.write("server reply: " + receiveMsg + "\n");
						writer.flush();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						socketReader.close();
						writer.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
