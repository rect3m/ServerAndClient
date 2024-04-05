package com.rect2m.server.and.client.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ServerUtil {

	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(
	    "HH:mm:ss");

	private ServerUtil() {
	}

	public static String getLocalHost() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	public static ServerSocket createServerSocket(int port) {
		try {
			return new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static Socket acceptClientSocket(ServerSocket serverSocket) {
		try {
			return serverSocket.accept();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readMessageFromSocket(Socket socket) {
		try (InputStream inputStream = socket.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException("Помилка при читанні повідомлення з socket", e);
		}
	}
	public static void printMessage(Socket socket, String message) {
		InetAddress clientAddress = socket.getInetAddress();
		System.out.print(LocalDateTime.now().format(TIME_FORMATTER) + " ");
		System.out.printf("[%s]", clientAddress.getHostAddress());
		System.out.println(" -- " + message);
	}
}
