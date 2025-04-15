
import controllers.Session;
import static controllers.recupData.*;
import models.*;

// import static models.Fiche.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;

import static controllers.recupData.recupNames;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class ChefDepartementScene {

    private Scene scene;

    public ChefDepartementScene(MainApp mainApp) {

        // Session session = Session.getInstance();
        // int id = session.getId();
        // String firstName = recupNames(id);
        // System.out.println(id+' '+firstName);
        Session session = Session.getInstance();
        int id = session.getId();
        String firstName = recupNames(id);
        System.out.println("Session ID: " + id + ", First Name: " + firstName);
        // chargement des images
        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        // Créer un ImageView pour afficher l'image
        ImageView logoImageView = new ImageView(logoImage);
        // Ajuster la taille de l'image
        logoImageView.getStyleClass().add("logo-image");
        logoImageView.setPreserveRatio(true);
        // Arrondir les bords de l'image
        Circle clip = new Circle(64, 66, 60);
        logoImageView.setClip(clip);

        Image homeIcon = new Image(getClass().getResource("ressources/images/home1.png").toExternalForm());
        ImageView homeIconView = new ImageView(homeIcon);
        homeIconView.setFitWidth(30);
        homeIconView.setFitHeight(30);

        Image notificationIcon = new Image(getClass().getResource("ressources/images/notification2.png").toExternalForm());
        ImageView notificationIconView = new ImageView(notificationIcon);
        notificationIconView.setFitWidth(30);
        notificationIconView.setFitHeight(30);

        Image accountIcon = new Image(getClass().getResource("ressources/images/account.png").toExternalForm());
        Image accountIconleftPanel = new Image(getClass().getResource("ressources/images/setting.png").toExternalForm());
        ImageView accountIconView = new ImageView(accountIcon);
        ImageView accountIconLeftPanelView = new ImageView(accountIconleftPanel);
        accountIconView.setFitWidth(30);
        accountIconLeftPanelView.setFitWidth(30);
        accountIconView.setFitHeight(30);
        accountIconLeftPanelView.setFitHeight(30);

        HBox primaryPanel = new HBox();
        // primaryPanel.setPadding(new Insets(20));
        primaryPanel.getStyleClass().add("primary-panel");

        // left Part ===================================================================
        BorderPane leftBorderPane = new BorderPane();
        // leftBorderPane.setPadding(new Insets(20));
        leftBorderPane.getStyleClass().add("left-border-pane");

        VBox leftBorderPaneTop = new VBox();
        // leftBorderPaneTop.setPadding(new Insets(0, 20, 0, 20));
        leftBorderPaneTop.getStyleClass().add("left-border-pane-top");
        leftBorderPaneTop.setAlignment(Pos.CENTER);
        leftBorderPaneTop.setPrefHeight(200);

        leftBorderPaneTop.getChildren().add(logoImageView);

        VBox leftBorderPaneCenter = new VBox(20);
        leftBorderPaneCenter.setPadding(new Insets(20, 20, 20, 20));
        leftBorderPaneCenter.getStyleClass().add("left-border-pane-center");
        leftBorderPaneCenter.setPrefHeight(300);
        leftBorderPaneCenter.setAlignment(Pos.TOP_CENTER);

        Button homeButton = new Button("Home");
        homeButton.setGraphic(homeIconView);
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        Button notificationButton = new Button("Notification");
        notificationButton.setGraphic(notificationIconView);
        notificationButton.setAlignment(Pos.CENTER_LEFT);
        notificationButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        Button accountButton = new Button("Settings");
        accountButton.setGraphic(accountIconLeftPanelView);
        accountButton.setAlignment(Pos.CENTER_LEFT);
        accountButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        leftBorderPaneCenter.getChildren().addAll(homeButton, notificationButton, accountButton);
        leftBorderPaneCenter.setAlignment(Pos.CENTER_LEFT);
        VBox leftBorderPaneBottom = new VBox();
        // leftBorderPaneBottom.setPadding(new Insets(0, 20, 0, 20));
        leftBorderPaneBottom.getStyleClass().add("left-border-pane-bottom");
        leftBorderPaneBottom.setPrefHeight(300);

        // Ajout des composant au leftBorderPaneTop
        leftBorderPane.setTop(leftBorderPaneTop);
        leftBorderPane.setCenter(leftBorderPaneCenter);
        leftBorderPane.setBottom(leftBorderPaneBottom);

        // Right Part ===================================================================
        // This section defines the right part of the UI, which includes various panels and components.
        VBox rightPanelBox = new VBox(20); // Main container for the right panel with vertical spacing of 20.
        rightPanelBox.setPadding(new Insets(20)); // Adds padding around the container.
        rightPanelBox.getStyleClass().add("right-panel-box"); // Adds a CSS style class for styling.

        HBox rightPanelBoxTop = new HBox(); // Top section of the right panel.
        rightPanelBoxTop.setPadding(new Insets(20)); // Adds padding around the top section.
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top"); // Adds a CSS style class for styling.
        rightPanelBoxTop.setAlignment(Pos.CENTER); // Centers the content horizontally.
        rightPanelBoxTop.setPrefHeight(60); // Sets the preferred height of the top section.

        // Title at the top of the right panel.
        Label titleLabel = new Label("Dashboard - Espace Chef De Departement"); // Title text.
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Century Gothic';"); // Styling for the title.
        titleLabel.setFont(Font.loadFont("ressources/fonts/Poppins-Black.ttf", 30)); // Loads a custom font for the title.
        titleLabel.getStyleClass().add("title-label"); // Adds a CSS style class for styling.

        HBox accountBox = new HBox(20); // Container for the account information.
        accountBox.setPadding(new Insets(0, 20, 0, 20)); // Adds padding around the account box.
        Label accountLabel = new Label(firstName); // Displays the user's first name.
        accountLabel.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: white; -fx-font-weight: bold;-fx-font-family: 'Poppins';"); // Styling for the account label.
        accountBox.getStyleClass().add("account-box"); // Adds a CSS style class for styling.
        accountBox.setAlignment(Pos.CENTER_RIGHT); // Aligns the content to the right.
        accountBox.getChildren().addAll(accountLabel, accountIconView); // Adds the account label and icon to the container.

        rightPanelBoxTop.getChildren().addAll(titleLabel, accountBox); // Adds the title and account box to the top section.
        titleLabel.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.7)); // Sets the width of the title relative to the top section.
        accountBox.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.3)); // Sets the width of the account box relative to the top section.

        HBox rightPanelBoxMiddle = new HBox(20); // Middle section of the right panel.
        rightPanelBoxMiddle.setPadding(new Insets(20)); // Adds padding around the middle section.
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle"); // Adds a CSS style class for styling.
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT); // Aligns the content to the left.
        rightPanelBoxMiddle.setPrefHeight(40); // Sets the preferred height of the middle section.

        Label classe = new Label("Departement :  Informatique"); // Displays the class information.
        classe.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';"); // Styling for the class label.

        rightPanelBoxMiddle.getChildren().addAll(classe); // Adds the class label to the middle section.

        HBox rightPanelBoxCenter = new HBox(2); // Center section of the right panel.
        rightPanelBoxCenter.setPadding(new Insets(2)); // Adds padding around the center section.
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center"); // Adds a CSS style class for styling.
        rightPanelBoxCenter.setAlignment(Pos.CENTER); // Centers the content horizontally.
        rightPanelBoxCenter.setPrefHeight(450); // Sets the preferred height of the center section.

        TitledPane rightPanelBoxCenterTitledPane = new TitledPane(); // Titled pane for the "Cahier de Texte".
        rightPanelBoxCenterTitledPane.setText("Cahier de Texte"); // Sets the title text.
        rightPanelBoxCenterTitledPane.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;"); // Styling for the titled pane.

        VBox rightPanelBoxCenterLeft = new VBox(20); // Left section of the center panel.
        rightPanelBoxCenterLeft.setPadding(new Insets(20)); // Adds padding around the left section.
        rightPanelBoxCenterLeft.getStyleClass().add("right-panel-box-center-left"); // Adds a CSS style class for styling.
        rightPanelBoxCenterLeft.setAlignment(Pos.CENTER); // Centers the content horizontally.
        rightPanelBoxCenterTitledPane.setContent(rightPanelBoxCenterLeft); // Sets the content of the titled pane.

        VBox addPersonalBox = new VBox(); // Center section of the center panel.
        addPersonalBox.setPadding(new Insets(20)); // Adds padding around the center section.
        addPersonalBox.getStyleClass().add("right-panel-box-center-center"); // Adds a CSS style class for styling.
        // addPersonalBox.setAlignment(Pos.Left); // Centers the content horizontally.
        // addPersonalBox.setStyle("-fx-border-witdh: 1px; -fx-border-color: blue; -fx-border-style: solid");

        HBox addPersonalBoxHead = new HBox();
        addPersonalBox.setPadding(new Insets(20)); // Adds padding around the center section.
        addPersonalBoxHead.setPrefHeight(50);
        addPersonalBoxHead.setStyle("-fx-background-color: gray;");

        
        // le champ du prenom
        TextField persFirstName = new TextField();
        TextField persLastName = new TextField();
        TextField persPhone = new TextField();
        TextField persEmail = new TextField();
        TextField persSpecialite = new TextField();
        Button addProfButton = new Button("Ajouter Professeur");
        
        addPersonalBox.getChildren().addAll(addPersonalBoxHead, new Label("Prenom"), persFirstName, new Label("Nom"), persLastName, new Label("Telephone"), persPhone, new Label("Email"), persEmail, new Label("Specialite"), persSpecialite, addProfButton);
        // addPersonalBox.setConstraints(addPersonalBoxHead, 0, 0, 2, 1);
        // addPersonalBox.getChildren().add(addPersonalBoxHead);
        
        addPersonalBox.setMargin(persFirstName, new Insets(0, 0, 20, 0));
        // addPersonalBox.add(new Label("Prenom"), 0, 1);
        // addPersonalBox.add(persFirstName, 1, 1);
        // // persFirstName.setPrefHeight(40);
        
        addPersonalBox.setMargin(persLastName, new Insets(0, 0, 20, 0));
        // addPersonalBox.add(new Label("Nom"), 0, 2);
        // addPersonalBox.add(persLastName, 1, 2);
        // // persLastName.setPrefHeight(40);
        
        addPersonalBox.setMargin(persPhone, new Insets(0, 0, 20, 0));
        // addPersonalBox.add(new Label("Telephone"), 0, 3);
        // addPersonalBox.add(persPhone, 1, 3);
        // // persPhone.setPrefHeight(40);
        
        addPersonalBox.setMargin(persEmail, new Insets(0, 0, 20, 0));
        // addPersonalBox.add(new Label("Email"), 0, 4);
        // addPersonalBox.add(persEmail, 1, 4);
        // // persEmail.setPrefHeight(40);
        
        addPersonalBox.setMargin(persSpecialite, new Insets(0, 0, 20, 0));
        // addPersonalBox.add(new Label("Specialite"), 0, 5);
        // addPersonalBox.add(persSpecialite, 1, 5);
        // // persSpecialite.setPrefHeight(40);

        // addPersonalBox.add(addProfButton, 0, 6);

        ScrollPane scrollPane = new ScrollPane(); // Scroll pane for the "Cahier de Texte".
        scrollPane.setContent(rightPanelBoxCenterTitledPane); // Sets the content of the scroll pane.
        scrollPane.setFitToWidth(true); // Ensures the content fits the width of the scroll pane.
        scrollPane.setPannable(true); // Allows panning within the scroll pane.

        GridPane actionGridPane = new GridPane();
        actionGridPane.setVgap(10);
        actionGridPane.setHgap(20);
        actionGridPane.setPadding(new Insets(20));
        actionGridPane.setAlignment(Pos.CENTER);
        actionGridPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px;");




        // Créer un ScrollPane et y placer le VBox
        VBox rightPanelBoxCenterRight = new VBox(20);
        rightPanelBoxCenterRight.setPadding(new Insets(20));
        rightPanelBoxCenterRight.getStyleClass().add("right-panel-box-center-right");
        rightPanelBoxCenterRight.setAlignment(Pos.CENTER);

        // Charger les images des bouton
        Image addPersonnelIcon = new Image(getClass().getResource("ressources/images/adduser.png").toExternalForm());
        ImageView addPersonnelIconView = new ImageView(addPersonnelIcon);
        addPersonnelIconView.setFitWidth(30);
        addPersonnelIconView.setFitHeight(30);

        Image assignCourseIcon = new Image(getClass().getResource("ressources/images/validate.png").toExternalForm());
        ImageView assignCourseIconView = new ImageView(assignCourseIcon);
        assignCourseIconView.setFitWidth(30);
        assignCourseIconView.setFitHeight(30);

        Image logOutIcon = new Image(getClass().getResource("ressources/images/logOut2.png").toExternalForm());
        ImageView logOutIconView = new ImageView(logOutIcon);
        logOutIconView.setFitWidth(30);
        logOutIconView.setFitHeight(30);

        Button addPersonnelIconButton = new Button("Ajouter Responable/Professeur");
        addPersonnelIconButton.setGraphic(addPersonnelIconView);
        addPersonnelIconButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        addPersonnelIconButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().remove(scrollPane);
            rightPanelBoxCenter.getChildren().add(addPersonalBox);
        });

        Button assignCourseButton = new Button("Assigner un Cours");
        assignCourseButton.setGraphic(assignCourseIconView);
        assignCourseButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        assignCourseButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().add(scrollPane);
            rightPanelBoxCenter.getChildren().remove(addPersonalBox);
        });

        Button logOutButton = new Button("Se Deconnecter");
        logOutButton.setGraphic(logOutIconView);
        logOutButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");
        logOutButton.setOnAction(event -> {
            // session.clearSession();
            LoginScene loginScene = new LoginScene(mainApp);
            loginScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
            mainApp.changeScene(loginScene.getScene());
        });

        rightPanelBoxCenterRight.getChildren().addAll(addPersonnelIconButton, assignCourseButton, logOutButton);

        rightPanelBoxCenter.getChildren().addAll(rightPanelBoxCenterRight);
        rightPanelBoxCenter.setAlignment(Pos.CENTER_LEFT);
        scrollPane.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        addPersonalBox.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        rightPanelBoxCenterRight.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.3));

        HBox rightPanelBoxBottom = new HBox();
        rightPanelBoxBottom.setPadding(new Insets(20));
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom");
        rightPanelBoxBottom.setAlignment(Pos.CENTER);
        rightPanelBoxBottom.setPrefHeight(70);
        rightPanelBoxBottom.getStyleClass().add("bottom-panel");
        rightPanelBoxBottom.setStyle("-fx-background-color: #2E2E2E; -fx-padding: 10px; -fx-alignment: center;");
        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        rightPanelBoxBottom.getChildren().addAll(footerText);

        rightPanelBox.getChildren().addAll(rightPanelBoxTop, rightPanelBoxMiddle, rightPanelBoxCenter, rightPanelBoxBottom);

        // Ajout du leftBorderPane au primaryPanel
        primaryPanel.getChildren().addAll(leftBorderPane, rightPanelBox);
        leftBorderPane.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.15));
        rightPanelBox.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.85));

        // Button backButton = new Button("Retour au tableau de bord");
        // backButton.setOnAction(event -> {
        //     DashboardScene dashboardScene = new DashboardScene(mainApp);
        //     mainApp.changeScene(dashboardScene.getScene());
        // });
        // VBox vbox = new VBox(10, primaryPanel);
        // vbox.setAlignment(Pos.CENTER);
        this.scene = new Scene(primaryPanel);
    }

    public Scene getScene() {
        return scene;
    }
}
