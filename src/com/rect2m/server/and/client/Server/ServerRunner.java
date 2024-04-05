package com.rect2m.server.and.client.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRunner {
	public static final String HOST = ServerUtil.getLocalHost();
	public static final int PORT = 8899;

	public static void main(String[] args){
		try (ServerSocket serverSocket = ServerUtil.createServerSocket(PORT)) {
			while (true) {
				try (Socket clientSocket = ServerUtil.acceptClientSocket(serverSocket)) {
					String message = ServerUtil.readMessageFromSocket(clientSocket);
					ServerUtil.printMessage(clientSocket, message);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
