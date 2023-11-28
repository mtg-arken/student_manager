import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionnaireEtudiants extends JFrame {
    Etudiant info;

    // textsfields
    JTextField Prenom = new JTextField();
    JTextField nom = new JTextField();
    JTextField email = new JTextField();
    JTextField adresse = new JTextField();
    JTextField Num = new JTextField();
    JTextField NumFix = new JTextField();
    JTextField classe = new JTextField();
    JTextField Niveau = new JTextField();
    JTextField Securite = new JTextField();
    JTextField Reseau = new JTextField();
    JTextField Java = new JTextField();
    JTextField PHP = new JTextField();
    JTextField Android = new JTextField();
    JTextField SGBD = new JTextField();
    JTextField MATH = new JTextField();
    JTextField Math = new JTextField();
    JTextField Francais = new JTextField();
    JTextField Anglais = new JTextField();
    JTextField PFE = new JTextField();

    // jpanels
    JPanel jpTabEtudiant = new JPanel();
    JPanel jpTabNotes = new JPanel();
    JPanel jpFormulaire = new JPanel();
    JPanel jpArbreEtInfo = new JPanel();
    JPanel jpLeftPart = new JPanel();
    JPanel jpRightPart = new JPanel();
    JPanel jpInfos = new JPanel();
    JPanel jpButtons = new JPanel();
    JPanel jpLabelColumns = new JPanel();
    JPanel jpValuesLabel = new JPanel();
    JPanel jpTable = new JPanel();
    JPanel jpTabNotesNord = new JPanel();
    JPanel jpTabNotesSud = new JPanel();

    // jLabel
    Label nomEtudiant = new Label("");
    Label prenomEtudiant = new Label("");
    Label classeEtudiant = new Label("");
    Label emailEtudiant = new Label("");
    Label telephoneEtudiant = new Label("");
    Label adresseEtudiant = new Label("");
    Label NomPrenomNote = new Label("");
    Label EmailNote = new Label("");

    // Buttons
    JButton EtudiantTabOkButton = new JButton("ok");
    JButton EtudiantTabCancelButton = new JButton("annuler");
    JButton EtudiantTabRefresh = new JButton("actualiser");
    JButton EtudiantTabSaveButton = new JButton("enregistrer");
    JButton NoteTabSaveButton = new JButton("enregistrer");
    JButton NoteTabCancelButton = new JButton("annuler");
    JButton NoteTabClearButton = new JButton("vider");
    JButton JinfosModifyButton = new JButton("Modifier");
    JButton JinfosDeletButton = new JButton("Supprimer");
    // Jtree
    DefaultMutableTreeNode racine = new DefaultMutableTreeNode("fsm");
    DefaultTreeModel treeModel = new DefaultTreeModel(racine);
    JTree tree = new JTree(treeModel);
    JScrollPane treePane = new JScrollPane(tree);

    // Jtable
    // Racine table
    String[] RacineColumnNames = { "Classe", "NumEtudiants" };
    DefaultTableModel racineTable = new DefaultTableModel(new Object[0][0], RacineColumnNames);
    JTable tableRacine = new JTable(racineTable);
    JScrollPane RacinetableScrollPane = new JScrollPane(tableRacine);
    // Classe table
    String[] ClasseColumnNames = { "Prenom", "nom", "Telephone" };
    DefaultTableModel ClasseTable = new DefaultTableModel(new Object[0][0], ClasseColumnNames);
    JTable tableClasse = new JTable(ClasseTable);
    JScrollPane ClassetableScrollPane = new JScrollPane(tableClasse);

    // creating Menu
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Fichier");
    JMenuItem Ouvrir = new JMenuItem("Ouvrir", 'N');
    JMenuItem Enregistrer = new JMenuItem("Enregistrer", 'N');
    JMenuItem Quitter = new JMenuItem("Quitter", 'N');

    // Creating Tab
    JTabbedPane tabbedPane = new JTabbedPane();

    public void viderTabEtudiant() {
        nom.setText("");
        Prenom.setText("");
        email.setText("");
        adresse.setText("");
        Num.setText("");
        NumFix.setText("");
        Niveau.setText("");
        classe.setText("");

    }

    public void viderTabNotes() {
        Securite.setText("");
        Reseau.setText("");
        Java.setText("");
        PHP.setText("");
        Android.setText("");
        SGBD.setText("");
        MATH.setText("");
        Math.setText("");
        Francais.setText("");
        Anglais.setText("");
        PFE.setText("");

    }

    public void CloseApp() {
        System.exit(0);
    }

    /*
     * public void AfficherContenu() {
     * System.out.println("Root name is : " + racine.toString() + "\n");
     * for (int j = 0; j < racine.getChildCount(); j++) {
     * DefaultMutableTreeNode classeChild = (DefaultMutableTreeNode)
     * racine.getChildAt(j);
     * System.out.println("Noeud Name is  : " + classeChild.toString() + "\n");
     * System.out.println("Leafs Names is  : \n");
     * for (int i = 0; i < classeChild.getChildCount(); i++) {
     * DefaultMutableTreeNode Leaf = (DefaultMutableTreeNode)
     * classeChild.getChildAt(i);
     * System.out.println(Leaf.toString());
     * }
     * }
     * }
     */

    public void ajouterEtudiant() {
        try {
            FileWriter fileWriter = new FileWriter("C:/Users/MTG/Desktop/Java/java/Tp7/fsm.txt");
            // fileWriter.write(racine + "\n");
            for (int j = 0; j < racine.getChildCount(); j++) {
                DefaultMutableTreeNode classeTest = (DefaultMutableTreeNode) racine.getChildAt(j);
                fileWriter.write(classeTest + ":");
                for (int i = 0; i < classeTest.getChildCount(); i++) {
                    DefaultMutableTreeNode etudiantTest = (DefaultMutableTreeNode) classeTest.getChildAt(i);
                    Etudiant info = (Etudiant) etudiantTest.getUserObject();
                    fileWriter.write(info.Prenom + ',' + info.nom + ',' + info.email + ',' + info.adresse + ','
                            + info.Num + ',' + info.NumFix + ','
                            + info.classe + ',' + info.Niveau + ";");
                }
                fileWriter.write(".\n");

            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void afficherEtudiants() {
        try {

            FileReader fileReader = new FileReader("C:/Users/MTG/Desktop/Java/java/Tp7/fsm.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String file;
            ArrayList<String> classes = new ArrayList<String>();
            while ((file = bufferedReader.readLine()) != null) {
                classes.add(file);
            }
            for (int i = 0; i < classes.size(); i++) {
                String[] etudiants = classes.get(i).split(":");
                DefaultMutableTreeNode newValueClass = new DefaultMutableTreeNode(etudiants[0]);
                String[] etudiant = etudiants[1].split(";");
                for (int k = 0; k < etudiant.length - 1; k++) {
                    String[] infos = etudiant[k].split(",");
                    Etudiant newEtudiant = new Etudiant(infos[0], infos[1], infos[2],
                            infos[3], infos[4], infos[5], infos[6], infos[7]);
                    DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(newEtudiant);
                    newValueClass.add(newValuePrenom);
                    racine.add(newValueClass);
                    treeModel.reload();
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * public void initialiserArbre() {
     * 
     * DefaultMutableTreeNode Class1 = new DefaultMutableTreeNode("ISI1");
     * DefaultMutableTreeNode Class2 = new DefaultMutableTreeNode("SRA1");
     * DefaultMutableTreeNode Class3 = new DefaultMutableTreeNode("LFI3");
     * DefaultMutableTreeNode Leaf1 = new DefaultMutableTreeNode(
     * new Etudiant("Ahmed", "Heni", "a", "a", "a", "a", "ISI1", "a"));
     * DefaultMutableTreeNode Leaf2 = new DefaultMutableTreeNode(
     * new Etudiant("Ali", "Dridi", "a", "a", "a", "a", "ISI1", "a"));
     * DefaultMutableTreeNode Leaf3 = new DefaultMutableTreeNode(
     * new Etudiant("Ons", "Khayat", "a", "a", "a", "a", "ISI1", "a"));
     * DefaultMutableTreeNode Leaf4 = new DefaultMutableTreeNode(
     * new Etudiant("Abir", "Torki", "a", "a", "a", "a", "SRA1", "a"));
     * DefaultMutableTreeNode Leaf5 = new DefaultMutableTreeNode(
     * new Etudiant("Aglem", "Kilani", "a", "a", "a", "a", "SRA1", "a"));
     * DefaultMutableTreeNode Leaf6 = new DefaultMutableTreeNode(
     * new Etudiant("Hichem", "Weslati", "a", "a", "a", "a", "SRA1", "a"));
     * DefaultMutableTreeNode Leaf7 = new DefaultMutableTreeNode(
     * new Etudiant("Ines", "Trabelsi", "a", "a", "a", "a", "LFI3", "a"));
     * DefaultMutableTreeNode Leaf8 = new DefaultMutableTreeNode(
     * new Etudiant("Karim", "Sayadi", "a", "a", "a", "a", "LFI3", "a"));
     * DefaultMutableTreeNode Leaf9 = new DefaultMutableTreeNode(
     * new Etudiant("Olfa", "ben salah", "a", "a", "a", "a", "LFI3", "a"));
     * 
     * racine.removeAllChildren();
     * Class1.add(Leaf1);
     * Class1.add(Leaf2);
     * Class1.add(Leaf3);
     * Class2.add(Leaf4);
     * Class2.add(Leaf5);
     * Class2.add(Leaf6);
     * Class3.add(Leaf7);
     * Class3.add(Leaf8);
     * Class3.add(Leaf9);
     * racine.add(Class1);
     * racine.add(Class2);
     * racine.add(Class3);
     * treeModel.reload();
     * treeModel.reload();
     * }
     */

    String MYSQL_SERVER_URL = "jdbc:mysql://localhost:3306/";
    String DB_URL = "jdbc:mysql://localhost:3306/MyFaculty";
    String USERNAME = "root";
    String PASSWORD = "";
    Connection connection = null;
    Statement statement = null;

    public void creerDbEtTable() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(MYSQL_SERVER_URL,
                    USERNAME, PASSWORD);
            statement = connection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS MyFaculty");
            connection.close();
            statement.close();
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS notes ("
                    + "id INT( 10 ) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(200) NOT NULL,"
                    + "email VARCHAR(200) NOT NULL UNIQUE,"
                    + "securite VARCHAR(200),"
                    + "reseau VARCHAR(200),"
                    + "Java VARCHAR(200),"
                    + "php VARCHAR(200),"
                    + "android VARCHAR(200),"
                    + "sgbd VARCHAR(200),"
                    + "math VARCHAR(200),"
                    + "francais VARCHAR(200),"
                    + "anglais VARCHAR(200),"
                    + "pfe VARCHAR(200)"
                    + ")";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherNotesEtudiant(String nom, String email) throws SQLException {
        // read all rows from table users
        connection = DriverManager.getConnection(DB_URL,
                USERNAME, PASSWORD);
        statement = connection.createStatement();
        String request = "SELECT * FROM notes WHERE  email ='" + email + "';";
        ResultSet resultSet = statement.executeQuery(request);
        if (resultSet.next()) {
            Securite.setText(resultSet.getString("securite"));
            Reseau.setText(resultSet.getString("reseau"));
            Java.setText(resultSet.getString("Java"));
            PHP.setText(resultSet.getString("php"));
            Android.setText(resultSet.getString("android"));
            SGBD.setText(resultSet.getString("sgbd"));
            Math.setText(resultSet.getString("math"));
            Francais.setText(resultSet.getString("francais"));
            Anglais.setText(resultSet.getString("anglais"));
            PFE.setText(resultSet.getString("pfe"));
        }else {
            Securite.setText("");
            Reseau.setText("");
            Java.setText("");
            PHP.setText("");
            Android.setText("");
            SGBD.setText("");
            Math.setText("");
            Francais.setText("");
            Anglais.setText("");
            PFE.setText("");
        }
        statement.close();

    }

    void sauvegarderNotesEtudiant(String email) throws SQLException {

        connection = DriverManager.getConnection(DB_URL,
                USERNAME, PASSWORD);
        statement = connection.createStatement();
        String request = "SELECT * FROM notes WHERE  email ='" + email + "';";
        ResultSet resultSelect = statement.executeQuery(request);
        if (resultSelect.next()) {
            String sqlUpdate = "UPDATE notes SET securite ='" + Securite.getText() + "',reseau='" + Reseau.getText()
                    + "',java='" + Java.getText() + "',php='" + PHP.getText()
                    + "', android= '" + Android.getText() + "',sgbd='" + SGBD.getText() + "',math='" + Math.getText()
                    + "',francais='" + Francais.getText() + "',anglais ='" + Anglais.getText() + "',pfe='"
                    + PFE.getText() + "' WHERE email='" + email + "';";
                    int resultSet = statement.executeUpdate(sqlUpdate);
                    if (resultSet > 0) {
                        System.out.println("Row updated successfully");
                    }

        } else {
            String sqlInsert2 = "INSERT INTO notes(name,email,securite,reseau,Java,php,android,sgbd,math,francais,anglais,pfe) VALUES (name,'"
                    + email + "','"
                    + Securite.getText() + "','" + Reseau.getText() + "','" + Java.getText() + "','" + PHP.getText()
                    + "','" + Android.getText() + "','" + SGBD.getText() + "','" + Math.getText() + "','"
                    + Francais.getText() + "','" + Anglais.getText() + "','" + PFE.getText() + "')";
            int resultSet = statement.executeUpdate(sqlInsert2);
            if (resultSet > 0) {
                System.out.println("Row added successfully");
            }
        }

        statement.close();
    }

    public void initialize() throws ClassNotFoundException {
        setLayout(new BorderLayout(5, 5));
        jpTabEtudiant.setLayout(new BorderLayout(5, 5));
        jpFormulaire.setLayout(new GridLayout(0, 4));
        jpArbreEtInfo.setLayout(new GridLayout(0, 2));

        jpLeftPart.setLayout(new GridLayout(0, 1));
        jpRightPart.setLayout(new GridLayout(2, 0));

        jpInfos.setLayout(new GridLayout(0, 2));

        jpLabelColumns.setLayout(new GridLayout(7, 1));
        jpValuesLabel.setLayout(new GridLayout(7, 1));

        jpTabEtudiant.setLayout(new GridLayout(0, 1));
        jpTabNotes.setLayout(new GridLayout(0, 1));
        jpTabNotesNord.setLayout(new GridLayout(0, 4));
        jpTabNotesSud.setLayout(new GridLayout(1, 6));

        // formulaire
        jpFormulaire.add(new JLabel("Prenom"));
        jpFormulaire.add(Prenom);

        jpFormulaire.add(new JLabel("nom"));
        jpFormulaire.add(nom);

        jpFormulaire.add(new JLabel("email"));
        jpFormulaire.add(email);

        jpFormulaire.add(new JLabel("adresse"));
        jpFormulaire.add(adresse);

        jpFormulaire.add(new JLabel("numero portable "));
        jpFormulaire.add(Num);

        jpFormulaire.add(new JLabel("numero fix"));
        jpFormulaire.add(NumFix);

        jpFormulaire.add(new JLabel("classe"));
        jpFormulaire.add(classe);

        jpFormulaire.add(new JLabel("Niveau"));
        jpFormulaire.add(Niveau);

        jpFormulaire.add(new JLabel("* champ obligatoire"));
        jpFormulaire.add(new JLabel());
        jpFormulaire.add(new JLabel());
        jpFormulaire.add(new JLabel());
        jpFormulaire.add(new JLabel());

        jpFormulaire.add(EtudiantTabOkButton);
        jpFormulaire.add(EtudiantTabCancelButton);

        EtudiantTabOkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Prenom.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  prenom");

                } else if (nom.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  nom");
                } else if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ email");
                } else if (adresse.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ adresse ");
                } else if (Num.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  numero portable");
                } else if (NumFix.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  numero fix");
                } else if (classe.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  classe");
                } else if (Niveau.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Remplire le champ  niveau ");
                } else {
                    Boolean test = false;
                    Etudiant newEtudiant = new Etudiant(Prenom.getText(), nom.getText(), email.getText(),
                            adresse.getText(), Num.getText(), NumFix.getText(), classe.getText(), Niveau.getText());
                    for (int j = 0; j < racine.getChildCount(); j++) {
                        DefaultMutableTreeNode classeTest = (DefaultMutableTreeNode) racine.getChildAt(j);
                        if (newEtudiant.classe.equals(classeTest.toString())) {
                            DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(newEtudiant);
                            classeTest.add(newValuePrenom);
                            treeModel.reload();
                            test = true;
                            break;
                        }
                    }
                    if (test == false) {
                        DefaultMutableTreeNode newValueClass = new DefaultMutableTreeNode(newEtudiant.classe);
                        DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(newEtudiant);
                        newValueClass.add(newValuePrenom);
                        racine.add(newValueClass);
                        treeModel.reload();

                    }
                    viderTabEtudiant();
                }
            }
        });

        EtudiantTabCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viderTabEtudiant();

            }
        });
        jpFormulaire.add(new JLabel());

        // sousformulaire
        jpArbreEtInfo.add(jpLeftPart);
        jpArbreEtInfo.add(jpRightPart);
        jpRightPart.add(jpInfos);

        // partie droit

        // tree
        jpLeftPart.add(treePane);
        // buttons sous tree
        jpLeftPart.add(jpButtons);

        EtudiantTabRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Bouton Actualiser");
                for (int i = 0; i < tree.getRowCount(); i++) {
                    tree.expandRow(i);
                }
                for (int i = 0; i < racine.getChildCount(); i++) {
                    DefaultMutableTreeNode categorie = (DefaultMutableTreeNode) racine.getChildAt(i);
                    System.out.println(" - " + categorie.toString());
                }

            }
        });

        jpButtons.add(EtudiantTabSaveButton);
        jpButtons.add(Box.createHorizontalGlue());
        jpButtons.add(EtudiantTabRefresh);
        jpButtons.add(Box.createHorizontalGlue());

        EtudiantTabSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ajouterEtudiant();
            }
        });

        // partie gauche

        jpLabelColumns.add(new JLabel("nom :"));
        jpLabelColumns.add(new JLabel("Prenom :"));
        jpLabelColumns.add(new JLabel("Classe :"));
        jpLabelColumns.add(new JLabel("Email :"));
        jpLabelColumns.add(new JLabel("Telephone :"));
        jpLabelColumns.add(new JLabel("Adresse :"));
        jpLabelColumns.add(JinfosModifyButton);
        jpInfos.add(jpLabelColumns, BorderLayout.WEST);

        jpValuesLabel.add(nomEtudiant);
        jpValuesLabel.add(prenomEtudiant);
        jpValuesLabel.add(classeEtudiant);
        jpValuesLabel.add(emailEtudiant);
        jpValuesLabel.add(telephoneEtudiant);
        jpValuesLabel.add(adresseEtudiant);
        jpValuesLabel.add(JinfosDeletButton);
        /*
         * jpValuesLabel.add(new JLabel("nom :"));
         * jpValuesLabel.add(new JLabel("prenom :"));
         * jpValuesLabel.add(new JLabel("3 :"));
         * jpValuesLabel.add(new JLabel("4 :"));
         * jpValuesLabel.add(new JLabel("5 :"));
         * jpValuesLabel.add(new JLabel("6 :"));
         */

        jpInfos.add(jpValuesLabel, BorderLayout.EAST);
        TreeSelectionListener arbreListener = new TreeSelectionListener() {
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null) {
                    return;
                }
                DefaultMutableTreeNode racine = (DefaultMutableTreeNode) tree.getModel().getRoot();

                if (node.isLeaf()) {
 

                    jpArbreEtInfo.remove(RacinetableScrollPane);
                    jpArbreEtInfo.remove(ClassetableScrollPane);
                    jpArbreEtInfo.add(jpRightPart);
                    jpArbreEtInfo.revalidate();
                    jpArbreEtInfo.repaint();

                    info = (Etudiant) node.getUserObject();
                    nomEtudiant.setText(info.nom);
                    prenomEtudiant.setText(info.Prenom);
                    classeEtudiant.setText(info.classe);
                    emailEtudiant.setText(info.email);
                    telephoneEtudiant.setText(info.Num);
                    adresseEtudiant.setText(info.adresse);
                    NomPrenomNote.setText(info.nom + " " + info.Prenom);
                    EmailNote.setText(info.email);
                    try {
                        afficherNotesEtudiant(info.nom, info.email);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                } else if (node.isRoot()) {

                    racineTable.setRowCount(0);
                    for (int j = 0; j < racine.getChildCount(); j++) {
                        DefaultMutableTreeNode classeChild = (DefaultMutableTreeNode) racine.getChildAt(j);
                        racineTable.addRow(new Object[] { classeChild.toString(), classeChild.getChildCount() });
                    }
                    jpArbreEtInfo.remove(jpRightPart);
                    jpArbreEtInfo.remove(ClassetableScrollPane);
                    jpArbreEtInfo.add(RacinetableScrollPane);
                    jpArbreEtInfo.revalidate();
                    jpArbreEtInfo.repaint();
                } else {

                    ClasseTable.setRowCount(0);
                    for (int j = 0; j < node.getChildCount(); j++) {
                        DefaultMutableTreeNode LeafChild = (DefaultMutableTreeNode) node.getChildAt(j);
                        Etudiant info = (Etudiant) LeafChild.getUserObject();
                        ClasseTable.addRow(new Object[] { info.Prenom, info.nom, info.Num });
                    }
                    jpArbreEtInfo.remove(jpRightPart);
                    jpArbreEtInfo.remove(RacinetableScrollPane);
                    jpArbreEtInfo.add(ClassetableScrollPane);
                    jpArbreEtInfo.revalidate();
                    jpArbreEtInfo.repaint();
                }
            }
        };
        JinfosModifyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ModifierEtudiantDialog addDialog = new ModifierEtudiantDialog(null, info);
                if (addDialog.test == 2) {
                    info.nom = addDialog.Nom;
                    info.Prenom = addDialog.Prenom;
                    info.email = addDialog.Email;
                    info.adresse = addDialog.Adresse;
                    info.Num = addDialog.Portable;
                    info.NumFix = addDialog.Fix;
                    info.classe = addDialog.Classe;
                    info.Niveau = addDialog.Niveau;
                    nomEtudiant.setText(info.nom);
                    prenomEtudiant.setText(info.Prenom);
                    classeEtudiant.setText(info.classe);
                    emailEtudiant.setText(info.email);
                    telephoneEtudiant.setText(info.Num);
                    adresseEtudiant.setText(info.adresse);
                    NomPrenomNote.setText(info.nom + " " + info.Prenom);
                    EmailNote.setText(info.email);
                    treeModel.reload();

                }
            }
        });
        /*
         * JinfosModifyButton.addActionListener(new ActionListener() {
         * public void actionPerformed(ActionEvent e) {
         * ModifierEtudiantDialog addDialog = new ModifierEtudiantDialog(null, info);
         * if (addDialog.test == 2) {
         * info.nom = addDialog.Nom;
         * info.Prenom = addDialog.Prenom;
         * info.email = addDialog.Email;
         * info.adresse = addDialog.Adresse;
         * info.Num = addDialog.Portable;
         * info.NumFix = addDialog.Fix;
         * info.classe = addDialog.Classe;
         * info.Niveau = addDialog.Niveau;
         * nomEtudiant.setText(info.nom);
         * prenomEtudiant.setText(info.Prenom);
         * classeEtudiant.setText(info.classe);
         * emailEtudiant.setText(info.email);
         * telephoneEtudiant.setText(info.Num);
         * adresseEtudiant.setText(info.adresse);
         * NomPrenomNote.setText(info.nom + " " + info.Prenom);
         * EmailNote.setText(info.email);
         * boolean test = false;
         * for (int j = 0; j < racine.getChildCount(); j++) {
         * DefaultMutableTreeNode classeTest = (DefaultMutableTreeNode)
         * racine.getChildAt(j);
         * if (info.classe.equals(classeTest.toString())) {
         * DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(info);
         * classeTest.add(newValuePrenom);
         * for (int i = 0; i < classeTest.getChildCount(); i++) {
         * DefaultMutableTreeNode etudiantTest = (DefaultMutableTreeNode)
         * classeTest.getChildAt(i);
         * if (info.nom.equals(etudiantTest.toString())) {
         * classeTest.remove(etudiantTest);
         * if(classeTest.getChildCount()==0){
         * racine.remove(classeTest);
         * }
         * nomEtudiant.setText("");
         * prenomEtudiant.setText("");
         * classeEtudiant.setText("");
         * emailEtudiant.setText("");
         * telephoneEtudiant.setText("");
         * adresseEtudiant.setText("");
         * }
         * }
         * break;
         * }else {
         * DefaultMutableTreeNode newValueClass = new
         * DefaultMutableTreeNode(info.classe);
         * DefaultMutableTreeNode newValuePrenom = new DefaultMutableTreeNode(info);
         * newValueClass.add(newValuePrenom);
         * racine.add(newValueClass);
         * }
         * 
         * }
         * treeModel.reload();
         * }
         * }
         * });
         */

        JinfosDeletButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null,
                        "Do you want to delete the student?",
                        "Answer this Question",
                        JOptionPane.YES_NO_OPTION);
                if (result == 0) {
                    for (int j = 0; j < racine.getChildCount(); j++) {
                        DefaultMutableTreeNode classeTest = (DefaultMutableTreeNode) racine.getChildAt(j);
                        if (info.classe.equals(classeTest.toString())) {
                            for (int i = 0; i < classeTest.getChildCount(); i++) {
                                DefaultMutableTreeNode etudiantTest = (DefaultMutableTreeNode) classeTest.getChildAt(i);
                                if (info.nom.equals(etudiantTest.toString())) {
                                    classeTest.remove(etudiantTest);
                                    nomEtudiant.setText("");
                                    prenomEtudiant.setText("");
                                    classeEtudiant.setText("");
                                    emailEtudiant.setText("");
                                    telephoneEtudiant.setText("");
                                    adresseEtudiant.setText("");
                                    treeModel.reload();
                                }

                            }

                        }
                    }
                }

            }

        });
        // TabNord
        jpTabNotesNord.add(new JLabel("Nom et Prenom:"));
        jpTabNotesNord.add(NomPrenomNote);
        // jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Email:"));
        // jpTabNotesNord.add(emailEtudiant);
        jpTabNotesNord.add(EmailNote);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Securite:"));
        jpTabNotesNord.add(Securite);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Reseau:"));
        jpTabNotesNord.add(Reseau);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Java:"));
        jpTabNotesNord.add(Java);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("PHP:"));
        jpTabNotesNord.add(PHP);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Android:"));
        jpTabNotesNord.add(Android);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("SGBD:"));
        jpTabNotesNord.add(SGBD);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("MATH:"));
        jpTabNotesNord.add(Math);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Francais:"));
        jpTabNotesNord.add(Francais);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("Anglais:"));
        jpTabNotesNord.add(Anglais);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        jpTabNotesNord.add(new JLabel("PFE:"));
        jpTabNotesNord.add(PFE);
        jpTabNotesNord.add(new JLabel());
        jpTabNotesNord.add(new JLabel());

        // tab Sub

        NoteTabClearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viderTabNotes();
            }
        });
        NoteTabCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    afficherNotesEtudiant(nomEtudiant.getText(), emailEtudiant.getText());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
        NoteTabSaveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    sauvegarderNotesEtudiant(emailEtudiant.getText());
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });

        jpTabNotesSud.add(NoteTabSaveButton);
        jpTabNotesSud.add(Box.createHorizontalGlue());
        jpTabNotesSud.add(NoteTabCancelButton);
        jpTabNotesSud.add(Box.createHorizontalGlue());
        jpTabNotesSud.add(NoteTabClearButton);
        jpTabNotesSud.add(Box.createHorizontalGlue());
        jpTabNotesSud.add(new JLabel());
        jpTabNotesSud.add(new JLabel());
        jpTabNotesSud.add(new JLabel());

        tree.addTreeSelectionListener(arbreListener);
        jpTabEtudiant.setLayout(new BorderLayout());
        jpTabEtudiant.add(jpFormulaire, BorderLayout.NORTH);
        jpTabEtudiant.add(jpArbreEtInfo, BorderLayout.CENTER);
        jpTabNotes.setLayout(new BorderLayout());
        jpTabNotes.add(jpTabNotesNord, BorderLayout.NORTH);
        jpTabNotes.add(jpTabNotesSud, BorderLayout.SOUTH);

        // adding menus
        ActionListener OuvrirListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                treeModel.reload();
                afficherEtudiants();
            }
        };
        ActionListener EnregistrerListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ajouterEtudiant();
            }

        };
        ActionListener QuitterListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                CloseApp();
            }
        };

        Ouvrir.addActionListener(OuvrirListener);
        Enregistrer.addActionListener(EnregistrerListener);
        Quitter.addActionListener(QuitterListener);
        fileMenu.add(Ouvrir);
        fileMenu.add(Enregistrer);
        fileMenu.add(Quitter);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // adding totabs
        tabbedPane.add("Etudiant", jpTabEtudiant);
        tabbedPane.add("Notes", jpTabNotes);
        add(tabbedPane);

        setTitle("App");

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 200);

        creerDbEtTable();
        pack();
    }

    public static void main(String[] args) throws ClassNotFoundException {
        GestionnaireEtudiants frame = new GestionnaireEtudiants();
        frame.initialize();
    }

}
