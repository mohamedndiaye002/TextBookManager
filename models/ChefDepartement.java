package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

public class ChefDepartement extends Personne{
    public String department;
    private int idPersonnel;

    public LinkedList<Enseignant> personnelEnseignants  = new LinkedList<>();

    // Construct Departement Chief Method 
    public ChefDepartement(Personne P, int idPersonnel, String departement){
        super(P.getFirstName(), P.getLastName(), P.getEmail(), P.getPhoneNumber());
        this.department = departement;
        this.idPersonnel = idPersonnel;
    }


    public String toString(){
        return this.idPersonnel+" "+super.toString()+" "+ this.department;
    }


    // COnnexion a la base de donnee
        public void connectToDatabase() {
            Scanner E = new Scanner(System.in);

            String firstName, lastName, email, telephone, classe;
            long NCE;

            firstName = E.nextLine();
            lastName = E.nextLine();
            telephone = E.nextLine();
            email = E.nextLine();
            classe = E.nextLine();
            String url = "jdbc:mysql://localhost:3306/CahierDeTexte?useSSL=false";
            String user = "root";
            String password = "";
            String query = "CALL AjouterPersonnelEtudiant('firstName', 'lastName', 'telephone', 'email','signature22', 120, 'classe')";
    
            try {
                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    System.out.println("Connexion reussi");
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.execute();
                        System.out.println("Requête exécutée avec succès");
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Connexion Impossible");
            }
        }



    // Show a departement chief


    // Add Enseignant Method



    // Add Responsible Method

    // Assign Course to Professor Method

    // Generate file



    // Show All Professor
    public void addProfessor(LinkedList<Enseignant> liste, String firstName, String lastName, String telephone, String specialite) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";
        String queryPersonnel = "INSERT INTO Personnel(firstName, lastName, telephone, email, signature) VALUES (?, ?, ?,?, 'sign1')";
        String idquery = "SELECT * FROM personnel WHERE telephone = ?"; 
        String query = "INSERT INTO enseignant(idPersonnel, specialite) VALUES (?, ?)";
        String em = firstName+"."+lastName+"@gmail.com";


        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion reussi");
            PreparedStatement stmt = conn.prepareStatement(queryPersonnel);
            PreparedStatement stmt1 = conn.prepareStatement(idquery);
            PreparedStatement stmt2 = conn.prepareStatement(query);
            
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, telephone);
            stmt.setString(4, em);
            stmt1.setString(1, telephone);
            ResultSet rs = stmt1.executeQuery();
            stmt.executeUpdate();
            int id = rs.getInt("idPersonnel");
            stmt2.setInt(1, id);
            stmt2.setString(2, specialite);
            int rowsInserted = stmt2.executeUpdate();


            if (rowsInserted > 0) {
                System.out.println("Insertion réussie !");
            } else {
                System.out.println("Échec de l'insertion.");
            }
            liste.add(new Enseignant(new Personne(firstName, lastName, email, phoneNumber), id, "spec"));

            // System.out.println(generatedk);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connexion Impossible");
        }
    }



}