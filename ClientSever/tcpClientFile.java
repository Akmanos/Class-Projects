/*
 * File: tcpClientFile.java
 * client sends a file to server
 * CSCI 437
 */

import java.io.*;
import java.net.*;

public class tcpClientFile {
	private static Socket connSocket;

	public static void main(String[] args) {
		if(args.length != 3) {
			System.out.println("Usage: Server Address, PORT number, and FileName should be specified");
			System.out.println("Note: Specify Server Address first, then PORT number, then FileName");
			return;
		}
		try {
			connSocket = new Socket(args[0], Integer.parseInt(args[1]));

			String fromServer;
			
			// open a file and send 1024 bytes at a time
			// doesn't receive anything from server side
			
			File myFile = new File(args[2]);
			byte byteArray[]  = new byte[1024];
			
			FileInputStream myFileInput = new FileInputStream(myFile);
			BufferedInputStream bufferInput = new BufferedInputStream(myFileInput);
			
			BufferedOutputStream output = new BufferedOutputStream(connSocket.getOutputStream());
			
			
			while(bufferInput.read(byteArray, 0, 1024) > 0) {
				output.write(byteArray, 0, byteArray.length);
				output.flush();
			}
			System.out.println("Sending complete......");

			bufferInput.close();
			myFileInput.close();
			output.close();
			connSocket.close();

		}
		catch(IOException ioe) {
			System.out.println("IOException: " + ioe);
		}
	}
}