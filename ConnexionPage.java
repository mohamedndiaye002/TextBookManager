//
//import javafx.scene.effect.Reflection;
//import javafx.scene.shape.Circle;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.scene.text.Font;
//
//public class ConnexionPage {
//
//    private Scene scene;
//
//    public ConnexionPage(MainApp mainApp) {
//        // Left Panel  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        VBox leftPanel = new VBox(10);
//        leftPanel.setPadding(new Insets(10));
//        leftPanel.getStyleClass().add("left-panel");
//
//        // chargement des images
//        Image homeIcon = new Image(getClass().getResource("ressources/images/home.png").toExternalForm());
//        Image notificationIcon = new Image(getClass().getResource("ressources/images/notification.png").toExternalForm());
//        // Image infoIcon = new Image(getClass().getResource("ressources/images/notification.png").toExternalForm());
//        Image accountIcon = new Image(getClass().getResource("ressources/images/account.png").toExternalForm());
//        Image loginIcon = new Image(getClass().getResource("ressources/images/login.png").toExternalForm());
//        Image passwordIcon = new Image(getClass().getResource("ressources/images/password.png").toExternalForm());
//
//        ImageView loginIconView = new ImageView(loginIcon);
//        loginIconView.setFitWidth(100);
//        loginIconView.setFitHeight(100);
//        loginIconView.setPreserveRatio(true);
//
//        ImageView passwordIconView = new ImageView(passwordIcon);
//        loginIconView.setFitWidth(50);
//        loginIconView.setFitHeight(50);
//        loginIconView.setPreserveRatio(true);
//
//        // Charger l'image
//        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
//
//        // Créer un ImageView pour afficher l'image
//        ImageView logoImageView = new ImageView(logoImage);
//
//        // Ajuster la taille de l'image
//        logoImageView.getStyleClass().add("logo-image");
//        logoImageView.setPreserveRatio(true);
//        Circle clip = new Circle(64, 66, 60);
//        logoImageView.setClip(clip);
//        // Right Panel ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        BorderPane topPanel = new BorderPane();
//        topPanel.setPadding(new Insets(20));
//        topPanel.getStyleClass().add("right-panel"); // Appliquer la classe CSS
//
//// Titre en haut ---------------------------------------------------------------------
//        VBox titleBox = new VBox(30); // Espacement de 10 pixels entre les éléments
//        titleBox.getStyleClass().add("title-box");
//        titleBox.setAlignment(Pos.CENTER); // Centrer les éléments horizontalement
//
//// Titre principal
//        Label titleLabel = new Label("UNIVERSITE IBA DER THIAM DE THIES");
//        titleLabel.getStyleClass().add("title-label"); // Appliquer le style CSS
//
//        // Sub Title
//        Label subTitleLabel = new Label("Gestionnaire des cahier de texte");
//        // subTitleLabel.getStyleClass().add("sub-title-label");
//        subTitleLabel.setStyle("-fx-font-size: 20px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86;");
//        subTitleLabel.setFont(Font.loadFont("ressources/fonts/Poppins-Black.ttf", 20));
//
//// Ajouter les éléments au HBox
//        titleBox.getChildren().addAll(titleLabel, subTitleLabel);
//
//        // ConnexionBoxPanel au centre -------------------------------------------------------
//        GridPane connexionBoxPanel = new GridPane();
//        connexionBoxPanel.getStyleClass().add("connexion-box-panel");
//        connexionBoxPanel.setPadding(new Insets(30));
//        connexionBoxPanel.setVgap(10);
//        connexionBoxPanel.setHgap(10);
//
//        Label connexionLabel = new Label("Connexion");
//        connexionLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-underline: true;-fx-text-fill: #383A86;");
//        connexionLabel.setFont(Font.loadFont("ressources/fonts/PTSansNarrow-Bold.ttf", 20));
//
//        Label emailLabel = new Label("login");
//        emailLabel.setStyle("-fx-font-size: 18px;-fx-font-smoothing-type: lcd; -fx-font-family: 'poppins';-fx-text-fill: #383A86;");
//        TextField emailTextField = new TextField();
//        emailTextField.setMinWidth(200);
//        emailTextField.setStyle("-fx-pref-height: 40px;-fx-pref-width: 250px");
//
//        Label passwordLabel = new Label("password");
//        passwordLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'poppins';-fx-text-fill: #383A86;");
//        PasswordField passwordTextField = new PasswordField();
//        passwordTextField.setMinWidth(200);
//        passwordTextField.setStyle("-fx-pref-height: 40px; -fx-pref-width: 250px");
//
//        Hyperlink forgotPWDLabel = new Hyperlink("J'ai oublié mon mot de passe?");
//        forgotPWDLabel.setStyle("-fx-underline: true;");
//        Button connectButton = new Button("Se Connecter");
//        connectButton.getStyleClass().add("connect-button");
//        connectButton.setPrefSize(200, 45);
//
//        connectButton.setOnAction(event -> {
//
//            String url = "jdbc:mysql://localhost:3306/cdt?useSSL=false";
//            String user = "root";
//            String password = "";
//            try {
//                String username = emailTextField.getText();
//                String pwd = passwordTextField.getText();
//
//                Connection connexion = DriverManager.getConnection(url, user, password);
//
//                String Query = "SELECT * FROM personnel WHERE email = ?";
//                PreparedStatement prepare = connexion.prepareStatement(Query);
//                prepare.setString(1, username);
//                ResultSet rs = prepare.executeQuery();
//
//                if (rs.next()) {
//                    if (rs.getString("telephone").equals(pwd)) {
//                        DashboardScene dashboardScene = new DashboardScene(mainApp);
//                        mainApp.changeScene(dashboardScene.getScene());
//                    } else {
//                        System.out.println("Mot de passe incorrect");
//                    }
//                } else {
//                    System.out.println("Pas de connection");
//                }
//
//            } catch (SQLException e) {
//                System.out.println("erreur de connecion");
//            }
//            // Validation fictive
//            // if ("admin".equals(emailTextField.getText()) && "1234".equals(passwordTextField.getText())) {
//            //     DashboardScene dashboardScene = new DashboardScene(mainApp);
//            //     mainApp.changeScene(dashboardScene.getScene());
//            // } else {
//            //     System.out.println("Nom d'utilisateur ou mot de passe incorrect.");
//            // }
//        });
//        // connectButton.setPrefWidth(150);
//        connexionBoxPanel.setConstraints(logoImageView, 3, 1, 2, 2);
//        connexionBoxPanel.add(connexionLabel, 0, 0);
//        connexionBoxPanel.getChildren().add(logoImageView);
//        connexionBoxPanel.add(emailLabel, 0, 1);
//        connexionBoxPanel.add(emailTextField, 1, 1);
//        connexionBoxPanel.add(passwordLabel, 0, 2);
//        connexionBoxPanel.add(passwordTextField, 1, 2);
//        connexionBoxPanel.add(forgotPWDLabel, 0, 3);
//        connexionBoxPanel.add(connectButton, 1, 3);
//
//        // connexionBoxPanel.getChildren().addAll(connexionLabel, emailBox, passwordBox, actionBox);
//        topPanel.setMargin(connexionBoxPanel, new Insets(50, 320, 50, 320));
//
//        // Bottom Panel ----------------------------------------------------------------
//        HBox bottomPanel = new HBox();
//        bottomPanel.setPrefHeight(90);
//        bottomPanel.getStyleClass().add("bottom-panel");
//
//        bottomPanel.setStyle("-fx-background-color: #2E2E2E; -fx-padding: 10px; -fx-alignment: center;");
//        bottomPanel.prefWidthProperty().bind(topPanel.widthProperty().multiply(0.9));
//
//        Label footerText = new Label("© 2025 Manage Your Classes - Tous droits réservés");
//        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
//        bottomPanel.getChildren().addAll(footerText);
//        // Ajouter les éléments au BorderPane
//        topPanel.setTop(titleBox); // Titre en haut
//        topPanel.setCenter(connexionBoxPanel); // ConnexionBoxPanel au centre
//        // topPanel.setBottom(bottomPanel); // BottomPanel en bas
//
//        // Main Layout
//        BorderPane mainLayout = new BorderPane();
//        mainLayout.setPadding(new Insets(30));
//        mainLayout.setCenter(topPanel);
//        mainLayout.setBottom(bottomPanel);
//        mainLayout.getStyleClass().add("main");
//
//        this.scene = new Scene(mainLayout);
//    }
//
//    public Scene getScene() {
//        return scene;
//    }
//
//}
