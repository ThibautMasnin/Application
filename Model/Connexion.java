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
	
    //KEVIN
    private String ServerName = "localhost";
    private String DatabaseName = "m4106";
    private String User = "postgres";
    private String Password = "C:\\Users\\kevin\\eclipse-workspace\\Kingdomino\\password.txt";

    //VALENTIN
    /*private String ServerName = "";
    private String DatabaseName = "";
    private String User = "";
    private String Password = "";
    */

    //EFEKAN
    /*private String ServerName = "";
    private String DatabaseName = "";
    private String User = "";
    private String Password = "";
    */

    //THIBAUT
    /*private String ServerName = "";
    private String DatabaseName = "";
    private String User = "";
    private String Password = "";
    */

    //AYMERIC
   /* private String ServerName = "C:\\Users\\Aymer\\IdeaProjects\\Kingdomino\\servername.txt";
    private String DatabaseName = "C:\\Users\\Aymer\\IdeaProjects\\Kingdomino\\databasename.txt";
    private String User = "C:\\Users\\Aymer\\IdeaProjects\\Kingdomino\\user.txt";
    private String Password = "C:\\Users\\Aymer\\IdeaProjects\\Kingdomino\\password.txt";*/

    public Connexion() {

    }

    //Méthode pour lire le nom du serveur dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getServerName() {
//        String chemin = ServerName; // Mettre ici le chemin du fichier txt ou se trouve le nom du serveur pour se co a la bdd
//        String servername = "";
//        try {
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(chemin)));
//            StringWriter out = new StringWriter();
//            int b;
//            while ((b=in.read()) != -1)
//                out.write(b);
//            out.flush();
//            servername = out.toString();
//            out.close();
//            in.close();
//        } catch (Exception ex){
//            System.err.println("Error. "+ex.getMessage());
//        }
        return this.ServerName;
    }

    //Méthode pour lire le nom de la bdd dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getDatabaseName() {
//        String chemin = DatabaseName; // Mettre ici le chemin du fichier txt ou se trouve le nom de la bdd pour se co a la bdd
//        String databasename = "";
//        try {
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(chemin)));
//            StringWriter out = new StringWriter();
//            int b;
//            while ((b=in.read()) != -1)
//                out.write(b);
//            out.flush();
//            databasename = out.toString();
//            out.close();
//            in.close();
//        } catch (Exception ex){
//            System.err.println("Error. "+ex.getMessage());
//        }
        return this.DatabaseName;
    }

    //Méthode pour lire le nom de l'utilisateur dans un fichier txt, plutot que de le mettre en clair dans le code
    public String getUser() {
//        String chemin = User; // Mettre ici le chemin du fichier txt ou se trouve le nom de l'utilisateur pour se co a la bdd
//        String user = "";
//        try {
//            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(chemin)));
//            StringWriter out = new StringWriter();
//            int b;
//            while ((b=in.read()) != -1)
//                out.write(b);
//            out.flush();
//            user = out.toString();
//            out.close();
//            in.close();
//        } catch (Exception ex){
//            System.err.println("Error. "+ex.getMessage());
//        }
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
