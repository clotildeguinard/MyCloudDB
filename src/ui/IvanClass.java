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
	    if (len < 0)
	        throw new IllegalArgumentException("Negative length not allowed");
	    if (start < 0 || start >= myByteArray.length)
	        throw new IndexOutOfBoundsException("Out of bounds: " + start);

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
	
	public boolean help(String help_text){
		return false;
	}
	
	public boolean quit()
	{
		return false;
	}
	
	public boolean unknownCommand(String any)
	{
		return false;
		
	}
	
}
 
