/*
 * File: tcpServerTalk.java
 * a simple TCP server for talk
 * using with tcpClientTalk.java
 *
 * send&receive uses BufferedReader/BufferedWriter
 * CSCI 437
 */

import java.io.*;
import java.net.*;

public class tcpServerTalk {
	private static ServerSocket listener;
	private static Socket connSocket;

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: PORT number should be specified");
			return;
		}
		try {
			listener = new ServerSocket(Integer.parseInt(args[0]));
			connSocket = listener.accept();

			System.out.println("Received a client connects from " + connSocket.getRemoteSocketAddress().toString());
			String fromClient;
			String response = "this is server";
			BufferedReader input = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connSocket.getOutputStream()));

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("From Client: " + input.readLine());
			System.out.print("Server: ");
			while((response = stdIn.readLine()) != null) {
				output.write(response, 0, response.length());
				output.newLine();
				output.flush();
				System.out.println("From Client: " + input.readLine());
				System.out.print("Server: ");
			}

			stdIn.close();
			input.close();
			output.close();
			connSocket.close();
			listener.close();

		}
		catch(IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}