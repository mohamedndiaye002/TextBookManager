package controllers;
import static controllers.connexionControl.verifyLogin;
import static controllers.recupData.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import models.ChefDepartement;
import models.Enseignant;
import models.Personne;
import models.Responsable;

public class Main {

    // Methode pour afficher un etudiant dans la fenetre
    // COnnexion a la base de donnee
    // public static void connectToDatabase() {
    //     String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
    //     String user = "root";
    //     String password = "";
    //     // String query = "CALL AjouterEtudiant(11, 110, 'LI1')";

    //     try {
    //         DriverManager.getConnection(url, user, password);
    //         System.out.println("Connexion reussi");
    //     } catch (SQLException e) {
    //         System.out.println("Connexion Impossible");
    //     }
    // }


    // public static void addCoursDetailsFromTheMainMethod(LinkedList<Enseignant> liste){
    //     Scanner E = new Scanner(System.in);
    //     System.out.println("Donner les informations de  rmeplissage");
    //     liste.get(0).showAllClasses();
    //     String classe =  E.nextLine();
    //     liste.get(0).showAllCourses();
    //     String intitule = E.nextLine();
    //     String cdetails =E.nextLine();

    //     liste.get(0).addCoursDetails(classe, intitule, cdetails);
    // }

    // public static void toConnect(String log, String pwd) {
    //     String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
    //     String user = "root";
    //     String password = "";
    //     try {
    //         Connection connexion = DriverManager.getConnection(url, user, password);

    //         String Query = "SELECT * FROM personnel WHERE email = ?";
    //         PreparedStatement prepare = connexion.prepareStatement(Query);
    //         prepare.setString(1, log);
    //         ResultSet rs = prepare.executeQuery();

    //         if (rs.next()) {
    //             if(rs.getString("telephone").equals(pwd)){
    //                 System.out.println("Vous vous etes bien connecte");
    //             }
    //             else{
    //                 System.out.println("Mot de passe incorrect");
    //             }
    //         }
    //         else{
    //             System.out.println("Pas de connection");
    //         }

    //     } catch (SQLException e) {
    //         System.out.println("erreur de connecion");
    //     }
    // }

    public static void main(String [] args) {

        // // Les listes - ------------------------------------------------------------------
        // LinkedList<Responsable> personnelResponsables = new LinkedList<>();
        // LinkedList<ChefDepartement> personnelChefDepartements = new LinkedList<>();
        // LinkedList<Enseignant> personnelEnseignants = new LinkedList<>();
        
        // String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
        // String user = "root";
        // String password = "";
        // String ensQuery = "SELECT p.*, e.* FROM personnel p INNER JOIN enseignant e ON p.idPersonnel=e.idPersonnel";
        // String etQuery = "SELECT p.*, r.*, cl.* FROM personnel p INNER JOIN responsable r ON p.idPersonnel=r.idPersonnel INNER JOIN classe cl ON r.idClasse = cl.idClasse ORDER BY cl.classeName ASC";
        // String cdQuery = "SELECT p.*, d.* FROM personnel p INNER JOIN chefdepartment cd ON p.idPersonnel=cd.idPersonnel INNER JOIN departement d ON cd.idDepartement = d.idDepartement";

        // try {
        //     Connection conn = DriverManager.getConnection(url, user, password);
        //     System.out.println("Connexion reussi");
        //     PreparedStatement stmt1 = conn.prepareStatement(ensQuery);
        //     ResultSet rs1 = stmt1.executeQuery();
        //     PreparedStatement stmt2 = conn.prepareStatement(etQuery);
        //     ResultSet rs2 = stmt2.executeQuery();
        //     PreparedStatement stmt3 = conn.prepareStatement(cdQuery);
        //     ResultSet rs3 = stmt3.executeQuery();

        //     while (rs1.next()) {
        //         personnelEnseignants.add(new Enseignant(new Personne(rs1.getString("p.firstName"), rs1.getString("p.lastName"), rs1.getString("p.email"), rs1.getString("p.telephone")), rs1.getInt("p.idPersonnel"), rs1.getString("e.specialite")));
        //     }
        //     while(rs2.next()){
        //         personnelResponsables.add(new Responsable());
        //     }
        //     while(rs3.next()){
        //         personnelChefDepartements.add(new ChefDepartement(new Personne(rs3.getString("p.firstName"), rs3.getString("p.lastName"), rs3.getString("p.email"), rs3.getString("p.telephone")), rs3.getInt("p.idPersonnel"), rs3.getString("d.nomDepartement")));
        //     }

        // } catch (SQLException e) {
        //     e.printStackTrace();
        //     System.out.println("Connexion Impossible");
        // }

        // for (Enseignant e : personnelEnseignants) {
        //     System.out.println(e.toString());
        // }

        // personnelResponsables.get(0).showTexteBook();


        // connectToDatabase();
        // String log, pwd;
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Donner votre email");
        // log = sc.nextLine();
        // System.out.println("Donner votre mot de passe");
        // pwd = sc.nextLine();

        // toConnect(log, pwd);

        // if(verifyLogin("awa.ndiaye@example.com", "778765432"))
        // System.out.println("connexion Reussie");
        // else
        // System.out.println("Connexion non reussie");

        // System.out.println("Le personnel avec l'id 1 est : " +recupSpecialite(21));


        // // insertion Query
        // String showRespQuery = "SELECT * FROM personnel p INNER JOIN responsable r ON p.idPersonnel = r.idPersonnel INNER JOIN classe c ON c.idClasse = r.idClasse";
        // // String showProfQuery = "SELECT * FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =? AND isSigned = false";

        // try {
        //     Connection connection = DriverManager.getConnection(url, user, password);
        //     PreparedStatement statementResp = connection.prepareStatement(showRespQuery);
        //     // statementResp.setInt(1, id);
        //     ResultSet result = statementResp.executeQuery();

        //     // System.out.println("Le cahier de texte" + this.classe);
        //     while (result.next()) {
        //         System.out.println(result.getString("p.firstName"));

        //     }


        // } catch (Exception e) {
        //     e.printStackTrace();
        //     System.out.println("erreur");
        // }
        int id =28;
        System.out.println("La classe du responsable avec l'id 3 est : "+recupClasse(1));
        ChefDepartement c =new ChefDepartement(new Personne(recupFirstName(id), recupLastName(id), recupEmail(id), recupPhoneNumber(id)), id, "Informatique");
        System.out.println(c.toString());
        c.assignCourse(21, "Intelligence Artificielle");

    }
}
