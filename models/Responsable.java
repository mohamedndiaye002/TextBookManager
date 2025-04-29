package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Responsable extends Personne {

    private long NCE;
    private int idPersonnel;
    private String classe;

    // Construct Responsable Method 
    public Responsable(Personne P, long NCE, int idPersonnel, String classe) {
        super(P.firstName, P.lastName, P.email, P.phoneNumber);
        this.NCE = NCE;
        this.classe = classe;
        this.idPersonnel = idPersonnel;
    }

    // toString Responsible Method
    @Override
    public String toString() {
        return this.idPersonnel + " " + super.toString() + " " + this.NCE + " " + this.classe;
    }

    // Visit text book Method
    public void showTexteBook() {
        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        // insertion Query
        String ficheQuery = "SELECT * FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =? AND isSigned = 1";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(ficheQuery);
            statement.setInt(1, this.idPersonnel);
            ResultSet result = statement.executeQuery();

            System.out.println("Le cahier de texte" + this.classe);
            while (result.next()) {
                System.out.println(result.getString("f.details"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }
    }

    // valid contain added Method
    public void validateAddedCourse(int id) {
        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        String user = "root";
        String password = "";
        String validQuery = "UPDATE fichecours SET `isSigned`=1 WHERE idFiche=?";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            // System.out.println("Connexion reussi");
            PreparedStatement prep = conn.prepareStatement(validQuery);
            prep.setInt(1, id);
            ResultSet result = prep.executeQuery();
            while (result.next()) {
                System.out.println("  " + result.getInt("idPersonnel") + "  " + result.getString("firstName") + " " + result.getString("lastName") + "  " + result.getString("email") + "  " + result.getString("telephone"));
            }
        } catch (SQLException e) {
            System.out.println("Connexion Impossible");
        }
    }

    public long getNCE() {
        return NCE;
    }

    public void setNCE(int NCE) {
        this.NCE = NCE;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(int idPersonnel) {
        this.idPersonnel = idPersonnel;
    }
}
