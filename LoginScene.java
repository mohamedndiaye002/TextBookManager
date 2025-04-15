import controllers.Session;
import static controllers.connexionControl.verifyLogin;
import static controllers.recupData.recupID;
import static controllers.recupData.recupType;
import javafx.scene.shape.Circle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class LoginScene {

    private Scene scene;

    public LoginScene(MainApp mainApp) {
        // Charger l'image depuis le fichier
        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        // Créer un ImageView pour afficher l'image
        ImageView logoImageView = new ImageView(logoImage);
        // Ajuster la taille de l'image
        logoImageView.getStyleClass().add("logo-image");
        logoImageView.setPreserveRatio(true);
        // Arrondir les bords de l'image
        Circle clip = new Circle(64, 66, 60);
        logoImageView.setClip(clip);

        // top Panel ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        VBox topPanel = new VBox();
        topPanel.setPadding(new Insets(20));
        topPanel.getStyleClass().add("right-panel"); // Appliquer la classe CSS

        // Titre en haut ---------------------------------------------------------------------
        VBox titleBox = new VBox(30); // Espacement de 10 pixels entre les éléments
        titleBox.getStyleClass().add("title-box");
        titleBox.setAlignment(Pos.CENTER); // Centrer les éléments horizontalement

        // Titre principal
        Label titleLabel = new Label("Gestionnaire des cahier de texte");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; ");
        titleLabel.setFont(Font.loadFont(getClass().getResourceAsStream("ressources/fonts/Poppins-Medium.ttf"), 30));

        titleLabel.getStyleClass().add("title-label"); // Appliquer le style CSS

        // Sub Title
        Label subTitleLabel = new Label("Université Iba Der Thiam");
        subTitleLabel.getStyleClass().add("sub-title-label");
        subTitleLabel.setStyle("-fx-font-size: 18px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86;");
        subTitleLabel.setFont(Font.loadFont("ressources/fonts/Poppins-Black.ttf", 20));

        // Ajouter les éléments au titleBox
        titleBox.getChildren().addAll(titleLabel);

        // ConnexionBoxPanel au centre -------------------------------------------------------
        GridPane connexionBoxPanel = new GridPane();
        // connexionBoxPanel.setPrefWidth(400);
        // connexionBoxPanel.setPrefHeight(600);
        connexionBoxPanel.getStyleClass().add("connexion-box-panel");
        connexionBoxPanel.setPadding(new Insets(30));
        connexionBoxPanel.setVgap(10);
        connexionBoxPanel.setHgap(10);

        Label connexionLabel = new Label("Connexion");
        connexionLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-underline: true;-fx-text-fill: #383A86;");
        connexionLabel.setFont(Font.loadFont("ressources/fonts/PTSansNarrow-Bold.ttf", 20));

        Label emailLabel = new Label("login");
        emailLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'poppins';-fx-text-fill: #383A86;-fx-text-alignment: center;");
        TextField emailTextField = new TextField();
        emailTextField.setMinWidth(200);
        emailTextField.setStyle("-fx-pref-height: 40px;-fx-pref-width: 250px");

        Label passwordLabel = new Label("password");
        passwordLabel.setStyle("-fx-font-size: 18px; -fx-font-family: 'poppins';-fx-text-fill: #383A86;-fx-text-alignment: center;");
        PasswordField passwordTextField = new PasswordField();
        passwordTextField.setMinWidth(200);
        passwordTextField.setStyle("-fx-pref-height: 40px; -fx-pref-width: 250px");

        Hyperlink forgotPWDLabel = new Hyperlink("J'ai oublié mon mot de passe?");
        forgotPWDLabel.setStyle("-fx-underline: true;");
        Button connectButton = new Button("Se Connecter");
        connectButton.getStyleClass().add("connect-button");
        connectButton.setPrefSize(200, 45);

        connectButton.setOnAction(event -> {
            String username = emailTextField.getText();
            String pwd = passwordTextField.getText();
            if (username.isEmpty() || pwd.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Champs vides");
                alert.setHeaderText(null);
                alert.setContentText("Veuillez remplir tous les champs avant de continuer.");
                alert.showAndWait();
                return;
            }

            if (verifyLogin(username, pwd)) {
                Session session = Session.getInstance();
                // session.clearSession();
                session.setId(recupID(username));
                switch (recupType(username)) {
                    case 3 ->                         {
                            ResponsableScene profileScene = new ResponsableScene(mainApp);
                            profileScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
                            mainApp.changeScene(profileScene.getScene());
                        // mainApp.getStage().setMaximized(true);
                        }
                    case 1 ->                         {
                            ChefDepartementScene profileScene = new ChefDepartementScene(mainApp);
                            profileScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
                            mainApp.changeScene(profileScene.getScene());
                        // mainApp.getStage().setMaximized(true);
                        }
                    case 2 ->                         {
                            EnseignantScene profileScene = new EnseignantScene(mainApp);
                            profileScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
                            mainApp.changeScene(profileScene.getScene());
                        // mainApp.getStage().setMaximized(true);
                }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur de connexion");
                alert.setHeaderText(null);
                alert.setContentText("Email ou mot de passe invalide. Veuillez réessayer.");
                alert.showAndWait();
            }

        });
        // connectButton.setPrefWidth(150);
        connexionBoxPanel.setConstraints(logoImageView, 0, 0, 2, 2);
        connexionBoxPanel.add(connexionLabel, 0, 2);
        connexionBoxPanel.getChildren().add(logoImageView);
        connexionBoxPanel.add(subTitleLabel, 1, 1);
        connexionBoxPanel.add(emailLabel, 0, 3);
        connexionBoxPanel.add(emailTextField, 1, 3);
        connexionBoxPanel.add(passwordLabel, 0, 4);
        connexionBoxPanel.add(passwordTextField, 1, 4);
        connexionBoxPanel.add(forgotPWDLabel, 0, 5);
        connexionBoxPanel.add(connectButton, 1, 5);

        // Bottom Panel ----------------------------------------------------------------
        HBox bottomPanel = new HBox();
        bottomPanel.setPrefHeight(90);
        bottomPanel.getStyleClass().add("bottom-panel");

        bottomPanel.setStyle("-fx-background-color: #2E2E2E; -fx-padding: 10px; -fx-alignment: center;");
        bottomPanel.prefWidthProperty().bind(topPanel.widthProperty().multiply(0.9));

        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        bottomPanel.getChildren().addAll(footerText);

        topPanel.setAlignment(Pos.CENTER);
        topPanel.getChildren().addAll(titleBox, connexionBoxPanel);
        // Ajouter les éléments au BorderPane
        // topPanel.setTop(titleBox); // Titre en haut
        // topPanel.setCenter(connexionBoxPanel); // ConnexionBoxPanel au centre
        // topPanel.setBottom(bottomPanel); // BottomPanel en bas

        // Main Layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setPadding(new Insets(30));
        mainLayout.setCenter(topPanel);
        // mainLayout.setBottom(bottomPanel);
        mainLayout.getStyleClass().add("main");

        this.scene = new Scene(mainLayout);
    }

    public Scene getScene() {
        return scene;
    }

}
