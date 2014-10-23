ppackage ui;

<<<<<<< HEAD
import java.util.Scanner;
import java.util.StringTokenizer;

public class Application {
    
	static String command;
    
	public enum Command {
        
		CONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT, EXIT
	}
    
	private static void run(Scanner sc) {
        
		System.out.println("Milestone1: Connection and interation with TCP server");
		// String command; // ready for the input
        
		boolean done = false; // ready for the menu loop
		while (!done) { // keep on until done
            
			System.out
            .println("\n-------------------Please select one of the commands-------------------------------------");
			System.out
            .println("\nCONNECT, DISCONNECT, SEND, LOGLEVEL, HELP, QUIT, EXIT");
            
			command = sc.next(); // take user input
            Command cmd = null;
            
            try {
				cmd = Command.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
				
                System.out.println("\nDo not recognise "
                                   + "the input, pl. try again");
                
                continue;
                
            }
			
			switch (cmd) {
                    
                case EXIT: // exit menu
                    done = true;// condition for breaking the loop
                    break;
                    
                case CONNECT:
                    
                    System.out.println("\nIP adress: ");
                    String ipAdress = sc.next(); // user Input
                    
                    System.out.println("\nPort: ");
                    
                    int portNumber = sc.nextInt();// user Input
                    
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
                        // Check the address string, should be n.n.n.n format
                        
                        StringTokenizer token = new StringTokenizer(ipAdress, ".");
                        if (token.countTokens() != 4) {
                            System.out.println("\n Entered value for "
                                               + "\n IP adress is not in n.n.n.n format ");
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
                            
                            System.out.println("\nEcoClient>" + " " + command + " "
                                               + ipAdress + " " + portNumber);
                            
                        }
                    }
                    
                    catch (Exception e1) {// throw exception in case of illogical
                        // input
                        System.out.println("\nBad input, please try again ");
                        sc.nextLine(); // remove leftover "\n"
                    }
                    
                    break;
                    
                case DISCONNECT:
                    
                    System.out.println("\nEcoClient>" + " " + command);
                    
                    break;
                    
                case SEND:
                    
                    System.out.println("\nPlease enter " + " HelloWorld");
                    try {
                        String greeting = sc.next(); // user Input
                        
                        //					if (!greeting.equals("HelloWorld"))
                        //							{
                        //						System.out.
                        //								.println("\nIncorrect input, please try again");
                        //					} else {
						System.out.println("\nEcoClient>" + " " + command + " "
                                           + greeting);
                        
                        
                    } catch (Exception e1) {// throw exception in case of illogical
                        // input
                        
                        System.out.println("\nBad input, please try again ");
                        sc.next(); // remove leftover "\n"
                    }
                    break;
                    
                case LOGLEVEL:
                    try {
                        System.out.println("\nEcoClient>" + " " + command + "< "
                                           + "current log status" + " >");
                    } catch (Exception e1) {// throw exception in case of illogical
                        // input
                        
                        System.out.println("\nBad input, please try again ");
                        sc.nextLine(); // remove leftover "\n"
                        
                    }
                    break;
                    
                case HELP:
                    try {
                        System.out
                        .println("\nFollowing set of commands provide following functionalities:"
                                 + "\nconnect: establishes connection to the eco server "
                                 + "\ndisconnect: disconnects from the server and receives confirmation message "
                                 + "\nsend: sends the message to the server "
                                 + "\nlogLevel: prints out current log status"
                                 + "\nquit: quits and notifies user about program shut down "
                                 + "\nexit: cancel the input");
                        
                    } catch (Exception e1) {// throw exception in case of illogical
                        // input
                        
                        System.out.println("\nBad input, please try again ");
                        sc.nextLine(); // remove leftover "\n"
                    }
                    break;
                    
                case QUIT:
                    
                    try {
                        System.out.println(" EcoClient> " + command);
                        
                    } catch (Exception e1) {// throw exception in case of illogical
                        // input
                        
                        System.out.println("\nBad input, please try again ");
                        sc.nextLine(); // remove leftover "\n"
                        
                    }
                    break;
                    
                    
			}
            
		}}
    
	public static void main(String[] args) {
        
		Scanner sc = new Scanner(System.in);// will take user input
        
		run(sc);
        
=======
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
>>>>>>> FETCH_HEAD
	}
}
