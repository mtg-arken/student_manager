import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOEtudiants {
    public IOEtudiants() {

    }

    public static  void ajouterEtudiant(String prenom, String nom, String email, String adresse, String telephone,
            String fix, String classe, String niveau) {
        try {
            FileWriter fileWriter = new FileWriter("C:/Users/MTG/Desktop/Java/java/Tp7/fsm.txt", true);
            fileWriter.write(prenom + ',' + nom + ',' + email + ',' + adresse + ',' + telephone + ',' + fix + ','
                    + classe + ',' + niveau + ":");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static  void afficherEtudiants() {
        try {
            FileReader fileReader = new FileReader("C:/Users/MTG/Desktop/Java/java/Tp7/fsm.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String ligne;
            if ((ligne = bufferedReader.readLine()) != null) {
                String[] etudiant = ligne.split(":");
                for (int i = 0; i < etudiant.length; i++) {
                    String[] infos = etudiant[i].split(",");
                    System.out.println(infos[0] + " " + infos[1] + " :");
                    System.out.println("    email = " + infos[2]);
                    System.out.println("    adress = " + infos[3]);
                    System.out.println("    telephone = " + infos[4]);
                    System.out.println("    fix = " + infos[5]);
                    System.out.println("    classe = " + infos[6]);
                    System.out.println("    niveau = " + infos[7]);
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        IOEtudiants.ajouterEtudiant("ali", "dridi", "email@email", "adress static ", "num tel static ", "fix static ",
                "classe static ", "niveau static ");
        IOEtudiants.afficherEtudiants();

    }


}