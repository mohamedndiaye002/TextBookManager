
import controllers.Session;
import static controllers.recupData.*;
import models.*;

// import static models.Fiche.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.shape.Circle;
import javafx.scene.image.ImageView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
// import static models.Responsable.validateAddedCourse;


public class ResponsableScene {

    private Scene scene;

    public ResponsableScene(MainApp mainApp) {

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
        Label titleLabel = new Label("Dashboard - Espace Responsable"); // Title text.
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

        Label classe = new Label("Classe: Licence 2 Informatique"); // Displays the class information.
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

        TitledPane rightPanelBoxCenterTitledPaneValid = new TitledPane(); // Titled pane for "Contenu ajouter par l'enseignant".
        rightPanelBoxCenterTitledPaneValid.setText("Contenu ajouter par l'enseignant"); // Sets the title text.
        rightPanelBoxCenterTitledPaneValid.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;"); // Styling for the titled pane.

        VBox rightPanelBoxCenterLeft = new VBox(20); // Left section of the center panel.
        rightPanelBoxCenterLeft.setPadding(new Insets(20)); // Adds padding around the left section.
        rightPanelBoxCenterLeft.getStyleClass().add("right-panel-box-center-left"); // Adds a CSS style class for styling.
        rightPanelBoxCenterLeft.setAlignment(Pos.CENTER); // Centers the content horizontally.
        rightPanelBoxCenterTitledPane.setContent(rightPanelBoxCenterLeft); // Sets the content of the titled pane.

        VBox rightPanelBoxCenterCenter = new VBox(20); // Center section of the center panel.
        rightPanelBoxCenterCenter.setPadding(new Insets(20)); // Adds padding around the center section.
        rightPanelBoxCenterCenter.getStyleClass().add("right-panel-box-center-center"); // Adds a CSS style class for styling.
        rightPanelBoxCenterCenter.setAlignment(Pos.CENTER); // Centers the content horizontally.
        rightPanelBoxCenterTitledPaneValid.setContent(rightPanelBoxCenterCenter); // Sets the content of the titled pane.

        ScrollPane scrollPane = new ScrollPane(); // Scroll pane for the "Cahier de Texte".
        scrollPane.setContent(rightPanelBoxCenterTitledPane); // Sets the content of the scroll pane.
        scrollPane.setFitToWidth(true); // Ensures the content fits the width of the scroll pane.
        scrollPane.setPannable(true); // Allows panning within the scroll pane.

        ScrollPane scrollPaneValid = new ScrollPane(); // Scroll pane for "Contenu ajouter par l'enseignant".
        scrollPaneValid.setContent(rightPanelBoxCenterTitledPaneValid); // Sets the content of the scroll pane.
        scrollPaneValid.setFitToWidth(true); // Ensures the content fits the width of the scroll pane.
        scrollPaneValid.setPannable(true); // Allows panning within the scroll pane.

        GridPane actionGridPane = new GridPane();
        actionGridPane.setVgap(10);
        actionGridPane.setHgap(20);
        actionGridPane.setPadding(new Insets(20));
        actionGridPane.setAlignment(Pos.CENTER);
        actionGridPane.setStyle("-fx-border-color: black; -fx-border-width: 2px; -fx-border-radius: 5px; -fx-padding: 10px;");

        // Labels for headers
        Label dateHeader = new Label("Date");
        Label courseHeader = new Label("Cours");
        Label teacherHeader = new Label("Enseignant");
        Label detailsHeader = new Label("Details");

        // Styling headers
        dateHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-underline: true;");
        courseHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-underline: true;");
        teacherHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-underline: true;");
        detailsHeader.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-underline: true;");

        // Adding headers
        actionGridPane.add(dateHeader, 0, 0);
        actionGridPane.add(courseHeader, 0, 1);
        actionGridPane.add(teacherHeader, 0, 2);
        actionGridPane.add(detailsHeader, 0, 3);

        Label dateValue = new Label("12/10/2023");
        Label courseValue = new Label("Mathematiques");
        Label teacherValue = new Label("M. Dupont");
        Label detailsValue = new Label("Introduction aux matrices");

        // Styling data labels
        dateValue.setStyle("-fx-font-size: 14px;");
        courseValue.setStyle("-fx-font-size: 14px;");
        teacherValue.setStyle("-fx-font-size: 14px;");
        detailsValue.setStyle("-fx-font-size: 14px;");

        // Adding data to the gridPane
        actionGridPane.add(dateValue, 2, 0);
        actionGridPane.add(courseValue, 2, 1);
        actionGridPane.add(teacherValue, 2, 2);
        actionGridPane.add(detailsValue, 2, 3);
        LinkedList<Fiche> Fiches = new LinkedList<>();

        Button rejectButton = new Button("Refuser");
        Button validateButton = new Button("Valider");

        // Styling buttons
        validateButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");
        rejectButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px;");

        // Adding buttons to the grid
        actionGridPane.add(validateButton, 0, 4, 2, 1);
        actionGridPane.add(rejectButton, 2, 4, 2, 1);

        // Aligning buttons
        GridPane.setHalignment(validateButton, HPos.CENTER);
        GridPane.setHalignment(rejectButton, HPos.CENTER);

        // Adding the gridPane to the rightPanelBoxCenter
        // rightPanelBoxCenterCenter.getChildren().add(actionGridPane);
        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        // insertion Query
        String ficheQuery = "SELECT * FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =?";
        String validFicheQuery = "SELECT * FROM fichecours f INNER JOIN cahierdetexte cdt ON f.`idCahierDeTexte` = cdt.idcahierdetexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse INNER JOIN responsable r ON cl.`idClasse` = r.`idClasse` WHERE r.`idPersonnel` =? AND isSigned = false";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(ficheQuery);
            PreparedStatement statementValid = connection.prepareStatement(validFicheQuery);
            statement.setInt(1, id);
            statementValid.setInt(1, id);
            ResultSet result = statement.executeQuery();

            // System.out.println("Le cahier de texte" + this.classe);
            int i = 0;
            while (result.next()) {
                GridPane rightPanelBoxCenterLeftGrid = new GridPane();
                rightPanelBoxCenterLeftGrid.setVgap(1);
                rightPanelBoxCenterLeftGrid.setHgap(1);
                rightPanelBoxCenterLeftGrid.getStyleClass().add("right-panel-box-center-left-grid");
                rightPanelBoxCenterLeftGrid.setAlignment(Pos.CENTER_LEFT);
                rightPanelBoxCenterLeftGrid.setPrefWidth(50);

                Label courseLabel = new Label(result.getString("f.details"));
                courseLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
                LocalDate today = LocalDate.now();
                LocalDate courseDate = today.plusDays(i);
                String courseDateString = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Label courseDateLabel = new Label(courseDateString);
                courseDateLabel.setStyle("-fx-font-size: 10px; -fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-family: 'Poppins';");

                rightPanelBoxCenterLeftGrid.setConstraints(courseLabel, 0, 0, 3, 1);
                rightPanelBoxCenterLeftGrid.getChildren().add(courseLabel);
                rightPanelBoxCenterLeftGrid.add(courseDateLabel, 0, 1);
                if (result.getBoolean("f.isSigned") == true) {
                    Image validIcon = new Image(getClass().getResource("ressources/images/validate2.png").toExternalForm());
                    ImageView validIconView = new ImageView(validIcon);
                    validIconView.setFitWidth(20);
                    validIconView.setFitHeight(20);
                    rightPanelBoxCenterLeftGrid.add(validIconView, 1, 1);
                    rightPanelBoxCenterLeftGrid.setMargin(validIconView, new Insets(0, 0, 0, 450));
                } else {
                    Image unvalidIcon = new Image(getClass().getResource("ressources/images/unValidate.png").toExternalForm());
                    ImageView unvalidIconView = new ImageView(unvalidIcon);
                    unvalidIconView.setFitWidth(20);
                    unvalidIconView.setFitHeight(20);
                    rightPanelBoxCenterLeftGrid.add(unvalidIconView, 1, 1);
                    rightPanelBoxCenterLeftGrid.setMargin(unvalidIconView, new Insets(0, 0, 0, 450));
                }
                rightPanelBoxCenterLeft.getChildren().add(rightPanelBoxCenterLeftGrid);
                rightPanelBoxCenterLeftGrid.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");
                i++;
            }

            ResultSet resultValid = statementValid.executeQuery();

            i = 0;
            // -- --------------------------------------------------------------------------------------------------------
            while (resultValid.next()) {
                GridPane rightPanelBoxCenterCenterGrid = new GridPane();
                rightPanelBoxCenterCenterGrid.setVgap(1);
                rightPanelBoxCenterCenterGrid.setHgap(1);
                rightPanelBoxCenterCenterGrid.getStyleClass().add("right-panel-box-center-center-grid");
                rightPanelBoxCenterCenterGrid.setAlignment(Pos.CENTER_LEFT);
                rightPanelBoxCenterCenterGrid.setPrefWidth(50);

                Label courseLabelValid = new Label(resultValid.getString("f.details"));
                courseLabelValid.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
                LocalDate today = LocalDate.now();
                LocalDate courseDate = today.plusDays(i);
                String courseDateString = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Label courseDateLabel = new Label(courseDateString);
                courseDateLabel.setStyle("-fx-font-size: 10px; -fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-family: 'Poppins';");
                Button verifyCourseDetailsButton = new Button("Verifier le contenu");
                verifyCourseDetailsButton.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-background-color: #383A86; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

                rightPanelBoxCenterCenterGrid.setConstraints(courseLabelValid, 0, 0, 3, 1);
                rightPanelBoxCenterCenterGrid.setConstraints(verifyCourseDetailsButton, 1, 0, 1, 2);
                rightPanelBoxCenterCenterGrid.getChildren().add(courseLabelValid);
                rightPanelBoxCenterCenterGrid.add(courseDateLabel, 0, 1);
                rightPanelBoxCenterCenterGrid.getChildren().add(verifyCourseDetailsButton);
                rightPanelBoxCenterCenterGrid.setMargin(verifyCourseDetailsButton, new Insets(0, 0, 0, 300));

                rightPanelBoxCenterCenter.getChildren().add(rightPanelBoxCenterCenterGrid);
                rightPanelBoxCenterCenterGrid.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");

                verifyCourseDetailsButton.setOnAction(event -> {
                    if (rightPanelBoxCenterCenterGrid.getChildren().contains(actionGridPane)) {
                        rightPanelBoxCenterCenterGrid.getChildren().remove(actionGridPane);
                        verifyCourseDetailsButton.setText("Verifier le contenu");
                    } else {
                        rightPanelBoxCenterCenterGrid.add(actionGridPane, 0, 2, 2, 1);
                        verifyCourseDetailsButton.setText("Masquer le contenu");
                    }
                    // System.out.println("Vérification du contenu : " + courseLabelValid.getText());
                });
                Fiches.add(new Fiche(resultValid.getInt("f.idFiche"), resultValid.getBoolean("f.isSigned"), resultValid.getString("f.details"), resultValid.getInt("f.idCours"), resultValid.getInt("f.idCahierDeTexte")));

                // Declaration of action's Buttons
                // Button validateButton = new Button("Valider");
                // validateButton.setOnAction(event -> {
                //     Responsable responsable = new Responsable(new Personne(recupFirstName(id), recupLastName(id), recupEmail(id), recupPhoneNumber(id)), id, 0, null);
                //     responsable.validateAddedCourse(Fiches.get(i).getIdFiche());

                // });

                // verifyCourseDetailsButton.setOnAction(event -> {
                //     rightPanelBoxCenterCenterGrid.add(actionGridPane, 0, 2, 2, 1);
                //     System.out.println("Vérification du contenu : " + courseLabelValid.getText());
                // });
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }

        // Créer un ScrollPane et y placer le VBox
        VBox rightPanelBoxCenterRight = new VBox(20);
        rightPanelBoxCenterRight.setPadding(new Insets(20));
        rightPanelBoxCenterRight.getStyleClass().add("right-panel-box-center-right");
        rightPanelBoxCenterRight.setAlignment(Pos.CENTER);

        // Charger les images des bouton
        Image viewTextBookIcon = new Image(getClass().getResource("ressources/images/visitBook.png").toExternalForm());
        ImageView viewTextBookIconView = new ImageView(viewTextBookIcon);
        viewTextBookIconView.setFitWidth(30);
        viewTextBookIconView.setFitHeight(30);

        Image validateContentIcon = new Image(getClass().getResource("ressources/images/validate.png").toExternalForm());
        ImageView validateContentIconView = new ImageView(validateContentIcon);
        validateContentIconView.setFitWidth(30);
        validateContentIconView.setFitHeight(30);

        Image logOutIcon = new Image(getClass().getResource("ressources/images/logOut2.png").toExternalForm());
        ImageView logOutIconView = new ImageView(logOutIcon);
        logOutIconView.setFitWidth(30);
        logOutIconView.setFitHeight(30);

        Button viewTextBookButton = new Button("Visiter le cahier de texte");
        viewTextBookButton.setGraphic(viewTextBookIconView);
        viewTextBookButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        viewTextBookButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().add(scrollPane);
            rightPanelBoxCenter.getChildren().remove(scrollPaneValid);
        });

        Button validateContentButton = new Button("Valider un contenu");
        validateContentButton.setGraphic(validateContentIconView);
        validateContentButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        validateContentButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().remove(scrollPane);
            rightPanelBoxCenter.getChildren().add(scrollPaneValid);
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

        rightPanelBoxCenterRight.getChildren().addAll(viewTextBookButton, validateContentButton, logOutButton);

        rightPanelBoxCenter.getChildren().addAll(rightPanelBoxCenterRight);
        rightPanelBoxCenter.setAlignment(Pos.CENTER_LEFT);
        scrollPane.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        rightPanelBoxCenterCenter.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
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
