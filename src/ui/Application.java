package ui;

/**
 *
 * @author nadiastraton
 */


import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {

	static String command;
	public static Socket sock;
	public enum Command {

		CONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT, EXIT
	}

	private static void run(Scanner sc) throws IOException {

		System.out
				.println("Milestone1: Connection and interation with TCP server");
		// String command; // ready for the input

		boolean done = false; // ready for the menu loop
		while (!done) { // keep on until done

			System.out
					.println("\n-------------------Please select one of the commands-------------------------------------");
			System.out
					.println("\nCONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT, EXIT");
//			Console console = System.console();
			List<String> words = Arrays.asList(sc.nextLine().split("\\s+"));
			System.out.println(words);
//			command = sc.nextLine(); // take user input
//			Command cmd = null;
			String cmd = words.get(0);

			try {
				cmd = String.valueOf(cmd.toUpperCase());
			} catch (IllegalArgumentException e) {
				// System.out.println("Invalid input");
				continue;

			}
			switch (cmd) {

			case "EXIT": // exit menu
				done = true;// condition for breaking the loop
				break;

			case "CONNECT":
				
//				System.out.println("\nIP adress: ");
//				String ipAdress = sc.next(); // user Input
				String ipAdress = words.get(1);

//				System.out.println("\nPort: ");

//				int portNumber = sc.nextInt();// user Input
				int portNumber = Integer.parseInt(words.get(2));

				// if (portNumber <= 0) &&

				try {

					if (ipAdress == null || ipAdress.length() < 7
							|| ipAdress.length() > 15) {

						System.out.println("\n IP adress length < 7 || > 15");
					}

					if (portNumber <= 0) {

						System.out
								.println("\n Entered value for Port is negative number ");
					}
					String check = "localhost";
					// Check the address string, should be n.n.n.n format
					if(!"localhost".equals(words.get(1))){
						StringTokenizer token = new StringTokenizer(ipAdress, ".");
						if (token.countTokens() != 4) {
							System.out.println("\n Entered value for "
									+ "\n IP adress is not in n.n.n.n format or localhost ");
						}
					}

					// while ( token.hasMoreTokens()) {
					//
					// // Get the current token and convert to an integer value
					//
					// String ipAdress = token.nextToken();
					//
					// try {
					// int ipVal = Integer.valueOf(ipAdress).intValue();
					// if ( ipVal < 0 || ipVal > 255)
					// return;
					// }
					// catch (NumberFormatException ex) {
					// return;
					// }
					// }

					// Looks like a valid IP address

					else {

						System.out.println("\nEcoClient>" + " " + cmd + " "
								+ ipAdress + " " + portNumber);
						sock = IvanClass.connect(ipAdress, portNumber);
						

					}
				}

				catch (Exception e) {// throw exception in case of illogical
					// input
					System.out.println("\nBad input, please try again ");
					sc.nextLine(); // remove leftover "\n"
				}

				break;

			case "DISCONNECT":

				System.out.println("\nEcoClient>" + " " + cmd);
				IvanClass.disconnect(sock);

				break;

			case "SEND":

				System.out.println("\nPlease enter " + " Hello_World ");
				try {
					String greeting = sc.next(); // user Input

					if (greeting != "Hello_World") {
						System.out
								.println("\nCorrect input Hello_World, please try again");
					} else {
						System.out.println("\nEcoClient>" + " " + cmd + " "
								+ greeting);

					}
				} catch (Exception e) {// throw exception in case of illogical
					// input

					System.out.println("\nBad input, please try again ");
					sc.nextLine(); // remove leftover "\n"
				}
				break;

			case "LOGLEVEL":
				try {
					System.out.println("\nEcoClient>" + " " + cmd + "< "
							+ "current log status" + " >");
				} catch (Exception e) {// throw exception in case of illogical
					// input

					System.out.println("\nBad input, please try again ");
					sc.nextLine(); // remove leftover "\n"

				}
				break;

			case "HELP":
				try {
					System.out
							.println("\nFollowing set of commands provide following functionalities:"
									+ "\nconnect: establishes connection to the eco server "
									+ "\ndisconnect: disconnects from the server and receives confirmation message "
									+ "\nsend: sends the message to the server "
									+ "\nlogLevel: prints out current log status"
									+ "\nquit: quits and notifies user about program shut down "
									+ "\nexit: cancel the input");

				} catch (Exception e) {// throw exception in case of illogical
					// input

					System.out.println("\nBad input, please try again ");
					sc.nextLine(); // remove leftover "\n"
				}
				break;

			case "QUIT":

				try {
					System.out.println(" EcoClient> " + cmd);

				} catch (Exception e) {// throw exception in case of illogical
					// input

					System.out.println("\nBad input, please try again ");
					sc.nextLine(); // remove leftover "\n"

				}
				break;

			default:
				System.out.println("\nDoes not recognise "
						+ "the input, pl. try again");

			}

		}
	}

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);// will take user input
//		Scanner scanner = new Scanner(console.reader());

		run(sc);

	}
}
