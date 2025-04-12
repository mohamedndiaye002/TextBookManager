//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import controllers.connexionControl;
//
//import javafx.application.Application;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.PasswordField;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.stage.Stage;
//
//public class ConexionPage extends Application {
//
//
//        public static void toConnect(String log, String pwd) {
//        String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
//        String user = "root";
//        String password = "";
//        try {
//            Connection connexion = DriverManager.getConnection(url, user, password);
//
//            String Query = "SELECT * FROM personnel WHERE email = ?";
//            PreparedStatement prepare = connexion.prepareStatement(Query);
//            prepare.setString(1, log);
//            ResultSet rs = prepare.executeQuery();
//
//            if (rs.next()) {
//                if(rs.getString("telephone").equals(pwd)){
//                    System.out.println("Vous vous etes bien connecte");
//                }
//                else{
//                    System.out.println("Mot de passe incorrect");
//                }
//            }
//            else{
//                System.out.println("Pas de connection");
//            }
//
//        } catch (SQLException e) {
//            System.out.println("erreur de connecion");
//        }
//    }
//
//    @Override
//    public void start(Stage stage) {
//        // Configuration de la scène;
////        stage.setTitle("Formulaire de connexion");
////        stage.setScene(scene);
////        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}