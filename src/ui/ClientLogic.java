package ui;

import java.io.*;
import java.net.*;

import tools.Loggable;

/**
*
* @author nadiastraton
*/

public class ClientLogic extends Loggable {

	public static Socket socket;

	/**
	 * 
	 * @param ipAddress
	 * @param port
	 * @return ClientSocket after connecting to the server or null if failed
	 */
	public static Socket connect(String ipAddress, int port) {
		logger.debug("Connection attempt...");
		try {
			socket = new Socket(ipAddress, port);
			logger.info("Connection successful with parameters " + ipAddress + " and " + port);
		} catch (Exception e) {
			logger.error("Failed to connect with parameters " + ipAddress + " and " + port + " : " + e);
		}
		return socket;
	}

/**
 * 
 * @param sock
 * @return True if socket null or successfully closed, false otherwise.
 */
	public static boolean disconnect(Socket sock)
	{
		logger.debug("Disconnection attempt...");
		if (sock != null) {
			try {
				sock.close();
			} catch (IOException ioex) {
				logger.error("Failed to disconnect : " + ioex);
				return false;
			}
		}
		logger.info("Successfully disconnected");
		return true;
	}
	
	/**
	 * Send non-null byte array through the socket
	 * @param myByteArray
	 * @throws IOException
	 */
	public static boolean send(byte[] myByteArray) {
		logger.info("Sending an array of bytes, length : " + myByteArray.length);
		int start = 0;
		int len = myByteArray.length;

		try {
			OutputStream out = socket.getOutputStream(); 
			DataOutputStream dos = new DataOutputStream(out);

			dos.writeInt(len);
			if (len > 0) {
				dos.write(myByteArray, start, len);
			}
			return true;
		} catch (IOException ioex) {
			logger.error("Failed to send array of bytes, length " + myByteArray.length + " : " + ioex);
			return false;
		}
	}

	/**
	 * 
	 * @return A message from the server
	 */
	public static byte[] receive() {
		logger.info("Receiving answer...");
		try {
			byte[] b = new byte[16384];
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			InputStream in = socket.getInputStream();
			DataInputStream dis = new DataInputStream(in);
			while (in.read(b) != -1) {
				buffer.write(b);
			}
			buffer.flush();
			logger.debug("Received answer");
			return buffer.toByteArray();
		} catch (IOException e) {
			logger.error("Failed to receive array of bytes : " + e);
			return null;
		}
	}
	
	public static void help(){
		logger.debug("Providing help.");
		System.out
		.println("\nFollowing set of commands provide following functionalities:"
				+ "\nconnect <ipAddress> <portNumber>: establishes connection to the echo server "
				+ "\ndisconnect: disconnects from the server and receives confirmation message "
				+ "\nsend <message>: sends the message to the server "
				+ "\nlogLevel: prints out current log level"
				+ "\nlogLevel <logLevel>: changes log level to one of the following : ALL, DEBUG, INFO, WARN, ERROR, FATAL, OFF"
				+ "\nquit: tears down the active connection and shut down the system ");
	}

	/**
	 * 
	 * @return True if no socket remains connected, false if the connected socket fails to close.
	 */
	public static boolean quit(){
		logger.info("Attempt to quit; asking to disconnect...");
		return disconnect(socket);
	}
	
	public static boolean unknownCommand(String any)
	{
		logger.warn("Unknown command: " + any);
		return false;
	}
	
}