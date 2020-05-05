/*
 * File: tcpServerFile.java
 * server receives a file from client
 * NOTE: server knows client will send a png file
 * CSCI 437
 */

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class tcpServerFile {
	private static ServerSocket listener;
	private static Socket connSocket;
	

	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: PORT number should be specified");
			return;
		}
		long index = 0;

		try {
			listener = new ServerSocket(Integer.parseInt(args[0]));
			connSocket = listener.accept();

			System.out.println("Received a client connects from " + connSocket.getRemoteSocketAddress().toString());
			
			// current time to be the file name
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = new Date();
			String fileName = new String(dateFormat.format(date));
			fileName += ".png";
			
			// create a file and prepare to write bytes in
			FileOutputStream myFileOutput = new FileOutputStream(fileName);
			BufferedOutputStream bufferOutput = new BufferedOutputStream(myFileOutput);
			byte byteArray[] = new byte[1024];
			
			BufferedInputStream input = new BufferedInputStream(connSocket.getInputStream());

			int bytesRead;
			
			do {
				bytesRead = input.read(byteArray, 0, byteArray.length);
				if(bytesRead > 0) {
					bufferOutput.write(byteArray, 0, bytesRead);
					bufferOutput.flush();
				}
			} while(bytesRead != -1);
			
			System.out.println("Writing complete......");


			bufferOutput.close();
			input.close();
			myFileOutput.close();
			connSocket.close();
			listener.close();
		}
		catch(IOException eio) {
			System.out.println("IOException: " + eio);
		}
	}
}
