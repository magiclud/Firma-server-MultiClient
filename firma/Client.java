package firma;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Runnable {

	private static Socket clientSocket = null;
	private static PrintStream os = null;
	public static DataInputStream is = null;
	private static BufferedReader inputLine = null;
	private static boolean closed = false;

	public static void mian(String[] args) {

		int portNumber = 4000; // default PortNr
		String host = "localhost";

		/* open a socket on a given host and port, open input end output sterams */
		try {
			clientSocket = new Socket(host, portNumber);
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * after inicliazization, we can write data to the socket we have opened
		 * a connection to on the portNumber
		 */

		if (clientSocket != null && os != null && is != null) {
			try {
				new Thread(new Client()).start();
				while (!closed) {

					os.println(inputLine.readLine().trim());
				}
				/* close - output stream, input stream, socket */
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/* create a thread to read from the server */
	@Override
	public void run() {

		/*
		 * Keep on reading from the socket till we recive "End" from the server.
		 * After that we break.
		 */
		String responseLine;
		try {
			while ((responseLine = is.readLine()) != null) {
				System.out.println(responseLine);
				if (responseLine.indexOf("*** Bye") != -1) {
					break;
				}
				if (responseLine.startsWith("from")) {
					System.out.println("dziala");
				}
			}
			closed = true;

		} catch (IOException e) {
			System.out.println("IOException:  " + e);
		}
	}
}
