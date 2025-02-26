import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class CommandeGET extends Commande {
	
	public CommandeGET(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		try {
            // Ouvrir un flux d'entrée sur le fichier
			BufferedReader in = new BufferedReader(new InputStreamReader(Main.socket.getInputStream()));
            OutputStream out = Main.socket.getOutputStream();

            String fileName = commandeArgs[0];
            out.write(("get " + fileName + "\n").getBytes());
            FileOutputStream fos = new FileOutputStream(fileName);
			// Lire les données du fichier dans un tableau de bytes
			byte[] buffer = new byte[1024];
			int bytesRead;
			
			while ((bytesRead = Main.socket.getInputStream().read(buffer)) != -1) {
			    fos.write(buffer, 0, bytesRead);
			}
			fos.close();
			in.close();
			out.close();

			System.out.println("Le fichier " + fileName + " a été téléchargé avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
            ps.println("550 Requested action not taken. File unavailable.");
        }
	}

}
