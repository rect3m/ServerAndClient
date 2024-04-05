package com.rect2m.server.and.client.Client;

import static com.rect2m.server.and.client.Client.ClientUtil.*;

import com.rect2m.server.and.client.Server.ServerRunner;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientRunner {
	private static final String SERVER_ADDRESS = ServerRunner.HOST;
	private static final int SERVER_PORT = ServerRunner.PORT;

	public static void main(String[] args) {
		try (BufferedReader reader = openConsoleReader()) {
			String message = readMessage(reader);

			while (!message.equals("q")) {
				try (Socket socket = openSocket(SERVER_ADDRESS, SERVER_PORT)) {
					writeToSocket(message, socket);
				}
				message = readMessage(reader);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

