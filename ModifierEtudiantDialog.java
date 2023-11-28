import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridLayout;

public class ModifierEtudiantDialog extends JDialog {

    public String Prenom;
    public String Nom;
    public String Email;
    public String Adresse;
    public String Portable;
    public String Fix;
    public String Classe;
    public String Niveau;
    public int test = 1;

    public ModifierEtudiantDialog(JFrame parent, Etudiant etudiant) {
        super(parent);
        setTitle("Modifier Etudiant");
        JButton OkButton = new JButton("ok");
        JButton CancelButton = new JButton("annuler");

        JTextField tfPrenom = new JTextField(etudiant.Prenom);
        JTextField tfNom = new JTextField(etudiant.nom);
        JTextField tfEmail = new JTextField(etudiant.email);
        JTextField tfAdresse = new JTextField(etudiant.adresse);
        JTextField tfPortable = new JTextField(etudiant.Num);
        JTextField tfFix = new JTextField(etudiant.NumFix);
        JTextField tfClasse = new JTextField(etudiant.classe);
        JTextField tfNiveau = new JTextField(etudiant.Niveau);

        JPanel addEmployeePanel = new JPanel();
        addEmployeePanel.setLayout(new GridLayout(0, 2));

        addEmployeePanel.add(new JLabel("Modifier Etudiant"));
        addEmployeePanel.add(new JLabel());
        addEmployeePanel.add(new JLabel("Prenom"));
        addEmployeePanel.add(tfPrenom);
        addEmployeePanel.add(new JLabel("Nom"));
        addEmployeePanel.add(tfNom);
        addEmployeePanel.add(new JLabel("Email"));
        addEmployeePanel.add(tfEmail);
        addEmployeePanel.add(new JLabel("Adresse"));
        addEmployeePanel.add(tfAdresse);
        addEmployeePanel.add(new JLabel("Poratable"));
        addEmployeePanel.add(tfPortable);
        addEmployeePanel.add(new JLabel("Fix"));
        addEmployeePanel.add(tfFix);
        addEmployeePanel.add(new JLabel("Classe"));
        addEmployeePanel.add(tfClasse);
        addEmployeePanel.add(new JLabel("Niveau"));
        addEmployeePanel.add(tfNiveau);
        addEmployeePanel.add(OkButton);
        addEmployeePanel.add(CancelButton);

        OkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Prenom = tfPrenom.getText();
                Nom = tfNom.getText();
                Email = tfEmail.getText();
                Adresse = tfAdresse.getText();
                Portable = tfPortable.getText();
                Fix = tfFix.getText();
                Classe = tfClasse.getText();
                Niveau = tfNiveau.getText();
                test = 2;
                dispose();
            }
        });
        CancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setContentPane(addEmployeePanel);
        pack();
        setModal(true);
        // setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
