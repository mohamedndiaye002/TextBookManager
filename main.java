//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//public class main {
//    public static void main(String[] args) {
//        String url = "jdbc:mysql://localhost:3306/cdt";
//        String user = "root";
//        String password = "";
//
//        // insertion Query
//        String showRespQuery = "SELECT * FROM personnel p INNER JOIN responsable r ON p.idPersonnel = r.idPersonnel INNER JOIN classe c ON c.idClasse = r.idClasse WHERE p.`idPersonnel` =27";
//        // String showProfQuery = "SELECT * FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =? AND isSigned = false";
//
//        try {
//            Connection connection = DriverManager.getConnection(url, user, password);
//            PreparedStatement statementResp = connection.prepareStatement(showRespQuery);
//            // statementResp.setInt(1, id);
//            ResultSet result = statementResp.executeQuery();
//
//            // System.out.println("Le cahier de texte" + this.classe);
//            while (result.next()) {
//                // GridPane addPersonnalVBoxGrid = new GridPane();
//                // addPersonnalVBoxGrid.setVgap(1);
//                // addPersonnalVBoxGrid.setHgap(1);
//                // addPersonnalVBoxGrid.getStyleClass().add("right-panel-box-center-left-grid");
//                // addPersonnalVBoxGrid.setAlignment(Pos.CENTER_LEFT);
//                // addPersonnalVBoxGrid.setPrefWidth(50);
//
//                // Label prenomLabel = new Label(result.getString("p.prenom"));
//                // prenomLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
//                // Label nomLabel = new Label(result.getString("p.nom"));
//                // nomLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
//                // Label emailLabel = new Label(result.getString("p.email"));
//                // emailLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
//                // Label telephoneLabel = new Label(result.getString("p.telephone"));
//                // telephoneLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
//                // Label classeLabel = new Label(result.getString("c.classeName"));
//                // classeLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
//
//                // addPersonnalVBoxGrid.add(prenomLabel, 0, 0);
//                // addPersonnalVBoxGrid.add(nomLabel, 0, 1);
//                // addPersonnalVBoxGrid.add(emailLabel, 0, 2);
//                // addPersonnalVBoxGrid.add(telephoneLabel, 0, 3);
//                // addPersonnalVBoxGrid.add(classeLabel, 0, 4);
//                // addPersonnalVBox.getChildren().add(addPersonnalVBoxGrid);
//                // addPersonnalVBoxGrid.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");
//                System.out.println(result.getString("p.prenom"));
//
//                // ObservableList<String> mangas = FXCollections.observableArrayList(
//                //         "Naruto",
//                //         "One Piece",
//                //         "Bleach",
//                //         "Death Note"
//                // );
//
//                // ListView mangaNamesListView = new ListView<String>(mangas);
//                // mangaNamesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("erreur");
//        }
//    }
//}
