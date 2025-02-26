/*
 * TP JAVA RIP
 * Min Serveur FTP
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static Socket socket;
	
	public static void main(String[] args) throws IOException {
		System.out.println("Le Serveur FTP");
		
		ServerSocket serveurFTP = new ServerSocket(9871);
		Main.socket = serveurFTP.accept();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(Main.socket.getInputStream()));
		PrintStream ps = new PrintStream(Main.socket.getOutputStream());
		
		ps.println("1 Bienvenue ! ");
		ps.println("1 Serveur FTP Personnel.");
		ps.println("0 Authentification : ");
		
		String commande = "";
		
		while (true) {
			if (br.ready()) {
				if ((commande=br.readLine()).equals("bye"))
					break;
				
				CommandExecutor.executeCommande(ps, commande);
			}
		}
		
		serveurFTP.close();
		Main.socket.close();
	}

}
