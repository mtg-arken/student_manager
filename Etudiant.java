

public class Etudiant {

    public String Prenom;
    public String nom;
    public String email;
    public String adresse;
    public String Num;
    public String NumFix;
    public String classe;
    public String Niveau;

    public Etudiant(String Prenom,
            String nom,
            String email,
            String adresse,
            String Num,
            String NumFix,
            String classe,
            String Niveau) {
        this.Prenom = Prenom;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.Num = Num;
        this.NumFix = NumFix;
        this.classe = classe;
        this.Niveau = Niveau;

    }

    public String toString() {
        return nom;
    }

}
