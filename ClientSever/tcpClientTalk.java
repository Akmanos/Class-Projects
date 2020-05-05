/*
 * File: tcpClientTalk.java
 * a simple TCP client for talk
 * using with tcpServerTalk.java
 *
 * send&receive uses BufferedReader/BufferedWriter
 * CSCI 437
 */

import java.io.*;
import java.net.*;

public class tcpClientTalk {
	private static Socket connSocket;

	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Usage: Server Address and PORT number should be specified");
			System.out.println("Note: Specify Server Address first, then PORT number");
			return;
		}
		try {
			connSocket = new Socket(args[0], Integer.parseInt(args[1]));

			BufferedReader input = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connSocket.getOutputStream()));

			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

			String msg;

			System.out.print("Client: ");
			while((msg = stdIn.readLine()) != null) {
				output.write(msg, 0, msg.length());
				output.newLine();
				output.flush();
				System.out.println("From Server: " + input.readLine());
				System.out.print("Client: ");
			}

			stdIn.close();
			input.close();
			output.close();
			connSocket.close();

		}
		catch(IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}