package ui;

import java.io.*;
import java.net.*;

/**
*
* @author nadiastraton
*/

public class IvanClass {

	public static Socket socket;
	
	public static Socket connect(String ip_adress, int port) throws UnknownHostException, IOException {
		// connects to a server. return socket where all action is happening.
		Socket ClientSocket = new Socket(ip_adress, port);
		return ClientSocket;
	}
	
	public static void disconnect(Socket Sock) throws IOException
	{
		Sock.close();
	}
	
	
	public static void send(byte[] myByteArray) throws IOException {
		int start = 0;
		int len = myByteArray.length;

	    OutputStream out = socket.getOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(out);

	    dos.writeInt(len);
	    if (len > 0) {
	        dos.write(myByteArray, start, len);
	    }
	}

	
	public boolean logLevel(String log_status){
		return false;
	}
	
	public static void help(){
		System.out
		.println("\nFollowing set of commands provide following functionalities:"
				+ "\nconnect: establishes connection to the eco server "
				+ "\ndisconnect: disconnects from the server and receives confirmation message "
				+ "\nsend: sends the message to the server "
				+ "\nlogLevel: prints out current log status"
				+ "\nquit: quits and notifies user about program shut down "
				+ "\nexit: cancel the input");

	}
	
	public static boolean quit(){
		System.out.println("Application exit!");
		return false;
	}
	
	public boolean unknownCommand(String any)
	{
		return false;
		
	}
	
}
 
