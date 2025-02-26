import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandeSTOR extends Commande {
	
	public CommandeSTOR(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	
	public void execute() {
		System.out.println("je suis la");
		try {
			// Ouvrir un flux de sortie sur le fichier
			
			File file = new File(commandeArgs[0]);
			System.out.println("Receiving file: " + file.getName());
			
			FileOutputStream fos = new FileOutputStream(file);

			// Lire le contenu du fichier envoyé par le client
			fos.flush();
			BufferedReader reader = new BufferedReader(new InputStreamReader(Main.socket.getInputStream()));
			StringBuilder contentBuilder = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				contentBuilder.append(line).append("\n");
				System.out.println(line); // Ajouter cette ligne pour afficher chaque ligne reçue depuis le client
			}
			reader.close();
			String fileContent = contentBuilder.toString();
			System.out.println(fileContent);

			// Écrire le contenu du fichier dans le fichier local sur le serveur
			fos.write(fileContent.getBytes());
			
			// Fermer le flux de sortie
			fos.close();

			// Afficher un message de confirmation
			System.out.println("File saved as: " + file.getAbsolutePath());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
