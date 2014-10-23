package ui;

/**
 *
 * @author nadiastraton
 */

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.sun.xml.internal.ws.message.ByteArrayAttachment;

public class Application {

	static String command;
	public static Socket sock;
	public enum Command {
		CONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT, EXIT
	}

	public static void main(String[] args) throws IOException {
		new ClientLogic();
		Scanner sc = new Scanner(System.in);
		run(sc);
	}

	/**
	 * Open the command line interface to the echo client.
	 * @param sc
	 * @throws IOException
	 */
	private static void run(Scanner sc) throws IOException {

		System.out
		.println("Milestone1: Connection and interation with TCP server");

		System.out.println("\n-------------------Please select one of the commands-------------------------------------"
		+ "\nCONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT");

		boolean done = false;
		while (!done) {
			print("");
			String in = sc.nextLine();
			List<String> words = Arrays.asList(in.split("\\s+"));

			String cmd = words.get(0).toUpperCase();

			switch (cmd) {

			case "CONNECT":
				if (words.size() >= 3) {
					try {
						tryConnect(words.get(1), words.get(2));
					}

					catch (Exception e) {
						System.out.println(e);
						print("Bad input, please try again ");
						sc.nextLine();
					}
				} else {
					print("Wrong number of arguments; please give ipAddress and portNumber.");
				}
				break;

			case "DISCONNECT":
				print(cmd);
				ClientLogic.disconnect(sock);
				break;

			case "SEND":
				if(sock == null || !sock.isConnected()){
					print("Sending impossible because socket currently not connected; "
							+ "please connect to a server.");
					break;
				}

				String message = new LinkedList<>(words).remove(0);
				if (message == null || message.length() == 0) {
					print("Message has length 0. Try again with non-empty message.");
					break;
				}

				byte[] MessageInBytes = message.getBytes(Charset.forName("UTF-8"));
				if (!ClientLogic.send(MessageInBytes)) {
					print("Failed to send message. Please try again.");
				} else {
					byte[] serverAnswer = ClientLogic.receive();
					print(new String(serverAnswer));
				}
				break;

			case "LOGLEVEL":
				if (words.size() > 1) {
					ClientLogic.setMainAppenderLevel(words.get(1));
				}
				print("Log level is set to " + ClientLogic.getMainAppenderLevel());
				break;

			case "HELP":
				ClientLogic.help();
				break;

			case "QUIT":
				print("Do you really want to disconnect and shut down? Type \'y\' for \'yes\'.");
				List<String> answer = Arrays.asList(sc.nextLine().split("\\s+"));
				if (!answer.get(0).toUpperCase().substring(0, 1).equals("Y")) {
					print("Aborted. The system will not quit.");
					break;
				}
				done = ClientLogic.quit();
				if (done) {
					print("Application quit!");
				}
				break;

			default:
				ClientLogic.unknownCommand(in);
				print("Unknown command, please try again or type \'HELP\'");
			}
		}
	}

	private static void tryConnect(String ipArgument, String portAsAString) throws UnknownHostException, IOException {

		if (!ipArgument.equals("localhost") && !isIpAdress(ipArgument)) {
			print("Entered value for IP adress is not valid;"
					 		+ "\n must be in n.n.n.n format (0 <= n <= 255) or \"localhost\" ");
			return;
		}

		int portArgument = -1;
		try {
			portArgument = Integer.parseInt(portAsAString);
		} catch (NumberFormatException e) {
			// do nothing: handled hereafter
		}
		if (portArgument < 0 || portArgument > 65535) {
			print("Entered value for Port is not valid, must be between 0 and 65535 inclusive.");
			return;
		}

		print("CONNECT "
				+ ipArgument + " " + portArgument);
		sock = ClientLogic.connect(ipArgument, portArgument);
		if (sock == null || !sock.isConnected()) {
			print("Connection failed. Try again. ");
		}
	}

	private static boolean isIpAdress(String ipString) {
		StringTokenizer token = new StringTokenizer(ipString, ".");
		if (token.countTokens() != 4) {
			return false;
		}
		while (token.hasMoreElements()) {
			String n = token.nextToken();
			try {
				int ipVal = Integer.valueOf(n).intValue();
				if ( ipVal < 0 || ipVal > 255)
					return false;
			}
			catch (NumberFormatException ex) {
				return false;
			}
		}
		return true;
	}

	public static void print(String message) {
		System.out.println("\n>EchoClient " + message);
	}
}
