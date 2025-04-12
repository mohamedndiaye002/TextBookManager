package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Enseignant extends Personne {

    private int idPersonnel;
    public String Specialite;

    // Construct Enseignant Method /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Enseignant(Personne P, int idPersonnel, String Specialite) {
        super(P.firstName, P.lastName, P.email, P.phoneNumber);
        this.idPersonnel = idPersonnel;
        this.Specialite = Specialite;
    }

    // Show Professor Method /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String toString() {
        return  this.idPersonnel+" "+super.toString() + " " + this.Specialite;
    }

    // Show courses Method /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showAllCourses() {
        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        // insertion Query
        String allCourses = "SELECT c.intitule FROM personnel p INNER JOIN enseignant e ON p.idPersonnel = e.idPersonnel INNER JOIN cours c ON e.idPersonnel = c.idPersonnel WHERE p.idPersonnel = ?;";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(allCourses);
            statement.setInt(1, this.idPersonnel);
            ResultSet result = statement.executeQuery();

            System.out.println("Les cours du prof "+this.firstName+" "+this.lastName);
            while (result.next()) {
                System.out.println(result.getString("c.intitule"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
    }

    public void showAllClasses() {
        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        // insertion Query
        String allClasses = "SELECT DISTINCT cl.classeName FROM enseignant e INNER JOIN cours c ON e.idPersonnel = c.idPersonnel INNER JOIN fichecours f ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE e.idPersonnel = ? ORDER BY cl.classeName ASC;";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(allClasses);
            statement.setInt(1, this.idPersonnel);
            ResultSet result = statement.executeQuery();

            System.out.println("Les classes du prof "+this.firstName+" "+this.lastName);
            while (result.next()) {
                System.out.println(result.getString("cl.classeName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
    }

    // Add course or course/info Method /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Fiche addCoursDetails(String classeName, String intitule, String details) {
        // URL, user and the user password
        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        String courses = "SELECT idCours FROM cours WHERE intitule = ?";
        String cdt ="SELECT DISTINCT cdt.idcahierdetexte FROM fichecours f INNER JOIN cours c ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE cl.classeName = ?";
        // insertion Query
        String sql = "INSERT INTO fichecours (isSigned, details, idCours, idCahierDeTexte) VALUES (0, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement1 = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(courses);
            PreparedStatement statement3 = connection.prepareStatement(cdt);
            statement2.setString(1, intitule);
            statement3.setString(1, classeName);
            ResultSet result2 = statement2.executeQuery();
            ResultSet result3 = statement3.executeQuery();
            result2.next();
            result3.next();
            
            
            // ResultSet result = statement1.executeQuery();
            statement1.setString(1, details); // Colonne details
            statement1.setInt(2, result2.getInt("idCours")); // Colonne idCours
            statement1.setInt(3, result3.getInt("cdt.idCahierDeTexte")); // Colonne idCahierDeTexte    

            // Exécuter la requête
            int rowsInserted = statement1.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Insertion réussie !");
                return new Fiche(1, false, details, result2.getInt("idCours"), result3.getInt("cdt.idCahierDeTexte"));
            } else {
                System.out.println("Échec de l'insertion.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
        return null;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Main Method  /////////////////////////////////////////////////////////////////////////////////////////////////////////


}
