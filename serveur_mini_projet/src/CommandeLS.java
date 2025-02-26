import java.io.File;
import java.io.PrintStream;

public class CommandeLS extends Commande {
	
	public CommandeLS(PrintStream ps, String commandeStr) {
		super(ps, commandeStr);
	}

	public void execute() {
		// Obtenez un objet File représentant le répertoire courant
		File fic = new File(".");
		String path = fic.getAbsolutePath();
		ps.println(path);
	
		File directory = new File(path); // specify the directory path
        File[] files = directory.listFiles(); 
		
				
		// Obtenez le tableau des fichiers et répertoires dans le répertoire courant
		
				
		// Affichez le contenu du répertoire courant
		for (File fichier_Repertoire : files) {
			String nomFichier_Repertoire = fichier_Repertoire.getName();
			ps.println(nomFichier_Repertoire);
	}

	}
}