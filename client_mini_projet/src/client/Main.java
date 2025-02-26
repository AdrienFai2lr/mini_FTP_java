
package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
        System.out.println("Client FTP");
        
        // Connexion au serveur FTP
        Socket socket = new Socket("localhost", 9871);
        
        // Flux de communication avec le serveur FTP
        BufferedReader recevoir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream envoi = new PrintStream(socket.getOutputStream());
        
        // Commande de connexion initiale
        envoi.println("user personne");
        envoi.println("pass abcd");   
        String reponse = recevoir.readLine();
        if (reponse.startsWith("1 Bienvenue ! ")) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Erreur de connexion : " + reponse);
           
        }             
        Scanner scanner = new Scanner(System.in);
        String commande = "";
        commande = scanner.nextLine();
        if (commande.startsWith("stor")) {
            String[] arguments = commande.split(" ");
            String filePath = arguments[1];
            // Envoi de la commande STOR au serveur
            envoi.println("stor " + filePath);
            // Attente de la réponse du serveur
            reponse = recevoir.readLine();
            System.out.println(reponse);
            // Envoi du contenu du fichier au serveur
            try {
                BufferedOutputStream dataOut = new BufferedOutputStream(socket.getOutputStream());

                FileInputStream fis = new FileInputStream(filePath);
                byte[] buffer = new byte[1024];
                int bytesRead = 0;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    dataOut.write(buffer, 0, bytesRead);
                    dataOut.flush();
                }

                fis.close();
                
                dataOut.close();

                System.out.println("Le fichier a été transféré avec succès.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
        	    envoi.println(commande);
        	    envoi.flush(); // Vider le tampon de sortie
        	    reponse = recevoir.readLine();
        	    System.out.println(reponse);
        	    recevoir.reset(); // Vider le tampon d'entrée
        	}
        


        reponse = recevoir.readLine();
        while(reponse != null) {
            System.out.println(reponse);
            reponse = recevoir.readLine();
        }
        
        // Fermeture de la connexion avec le serveur FTP
        scanner.close();
        envoi.close();
        recevoir.close();
        socket.close();
    }
}