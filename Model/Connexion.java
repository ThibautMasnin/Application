package Application.Model;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;

public class Connexion {
	
	// Mettre vos chemins où est votre fichier password.txt + nom du serveur, nom de la BDD et utilisateur et commenter le reste 

    private String ServerName = "localhost";
    private String DatabaseName = "m4106";
    private String User = "postgres";
    private String Password = "D:\\Bureau\\text.txt";

    public Connexion() {}

    //Méthode pour lire le nom du serveur dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getServerName() {
        return this.ServerName;
    }

    //Méthode pour lire le nom de la bdd dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getDatabaseName() {
        return this.DatabaseName;
    }

    //Méthode pour lire le nom de l'utilisateur dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getUser() {
        return this.User;
    }

    //Méthode pour lire le mdp dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getPassword() {
        String chemin = Password; // Mettre ici le chemin du fichier txt ou se trouve le mdp pour se co a la bdd
        String password = "";
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(chemin)));
            StringWriter out = new StringWriter();
            int b;
            while ((b=in.read()) != -1)
                out.write(b);
            out.flush();
            password = out.toString();
            out.close();
            in.close();
        } catch (Exception ex){
            System.err.println("Error. "+ex.getMessage());
        }
        return password;
    }
}
