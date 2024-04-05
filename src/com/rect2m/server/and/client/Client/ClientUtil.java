package com.rect2m.server.and.client.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public final class ClientUtil {

	public static Socket openSocket(String host, int port) {
		try{
			return new Socket(host, port);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static BufferedReader openConsoleReader() {
		InputStreamReader consoleInputStream = new InputStreamReader(System.in);
		return new BufferedReader(consoleInputStream);
	}

	public static String readMessage(BufferedReader reader) {
		System.out.print("Напишіть повідомлення (q для виходу): ");
		try {
			return reader.readLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeToSocket(String message, Socket socket) {
		try (OutputStream outputStream = socket.getOutputStream()) {
			if (!socket.isClosed()) {
				byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
				outputStream.write(bytes);
			}
		} catch (IOException e) {
			throw new RuntimeException("Не вдалося записати повідомлення в socket", e);
		}
	}


}
