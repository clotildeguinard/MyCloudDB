package ui;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;

import test.TestLogging;
import tools.Loggable;

public class Application extends Loggable {

	/**
	 * @Return Launches the CLI
	 **/
	public void main(){

	}

	public static void main(String[] args) throws IOException {
		openCLI();
	}
	private static void openCLI() throws IOException {

		  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		  System.out.print("EchoClient> ");
	        String s = br.readLine();
	        try{
		        System.out.println("got " + s);
	        }catch(NumberFormatException nfe){
	            System.err.println("Invalid Format!");
	        }
	}
}
