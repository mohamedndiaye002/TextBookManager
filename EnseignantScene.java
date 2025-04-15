

import controllers.Session;
import static controllers.recupData.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.*;

public class EnseignantScene {

    private Scene scene;

    @SuppressWarnings("CallToPrintStackTrace")
    public EnseignantScene(MainApp mainApp) {
        Session session = Session.getInstance();
        int id = session.getId();
        // int id = 24;
        String firstName = recupNames(id);
        String specialite = recupSpecialite(id);
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

        // Right Part ==================================================================================================================
        VBox rightPanelBox = new VBox(20);
        rightPanelBox.setPadding(new Insets(20));
        rightPanelBox.getStyleClass().add("right-panel-box");

        HBox rightPanelBoxTop = new HBox();
        rightPanelBoxTop.setPadding(new Insets(20));
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top");
        rightPanelBoxTop.setAlignment(Pos.CENTER);
        // rightPanelBoxTop.setPrefHeight(70);

        // Titre en haut ---------------------------------------------------------------------
        Label titleLabel = new Label("Dashboard - Espace Enseignant");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
        titleLabel.setFont(Font.loadFont("ressources/fonts/Poppins-Black.ttf", 30));
        titleLabel.getStyleClass().add("title-label");

        HBox accountBox = new HBox(20);
        accountBox.setPadding(new Insets(0, 20, 0, 20));
        Label accountLabel = new Label(firstName);
        accountLabel.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: white; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
        accountBox.getStyleClass().add("account-box");
        accountBox.setAlignment(Pos.CENTER_RIGHT);
        accountBox.getChildren().addAll(accountLabel, accountIconView);

        rightPanelBoxTop.getChildren().addAll(titleLabel, accountBox);
        titleLabel.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.7));
        accountBox.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.3));

        HBox rightPanelBoxMiddle = new HBox(20);
        rightPanelBoxMiddle.setPadding(new Insets(20));
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle");
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT);
        // rightPanelBoxMiddle.setPrefHeight(100);

        Label classe = new Label("Specialite : "+ specialite);
        classe.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        rightPanelBoxMiddle.getChildren().addAll(classe);

        HBox rightPanelBoxCenter = new HBox(20);
        rightPanelBoxCenter.setPadding(new Insets(20));
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center");
        rightPanelBoxCenter.setAlignment(Pos.CENTER_LEFT);
        // rightPanelBoxCenter.setPrefHeight(400);

        TitledPane titledPane = new TitledPane();
        titledPane.setText("Activites Recentes");
        VBox rightPanelBoxCenterLeft = new VBox(20);
        rightPanelBoxCenterLeft.setPadding(new Insets(20));
        rightPanelBoxCenterLeft.getStyleClass().add("right-panel-box-center-left");
        rightPanelBoxCenterLeft.setAlignment(Pos.CENTER);
        titledPane.setContent(rightPanelBoxCenterLeft);

        String url = "jdbc:mysql://localhost:3306/cdt";
        String user = "root";
        String password = "";

        // insertion Query
        String allClasses = "SELECT DISTINCT cl.classeName, c.intitule FROM enseignant e INNER JOIN cours c ON e.idPersonnel = c.idPersonnel INNER JOIN fichecours f ON c.idCours = f.idCours INNER JOIN cahierdetexte cdt ON f.idCahierDeTexte = cdt.idCahierDeTexte INNER JOIN classe cl ON cdt.idClasse = cl.idClasse WHERE e.idPersonnel = ? ORDER BY cl.classeName ASC;";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(allClasses);
            statement.setInt(1, id);
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

                Label classeLabel = new Label(result.getString("cl.classeName"));
                classeLabel.setStyle("-fx-font-size: 12px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
                Label courseLabel = new Label(result.getString("c.intitule"));
                courseLabel.setStyle("-fx-font-size: 16spx;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

                rightPanelBoxCenterLeftGrid.setConstraints(classeLabel, 0, 1, 3, 1);
                rightPanelBoxCenterLeftGrid.getChildren().add(classeLabel);
                rightPanelBoxCenterLeftGrid.setConstraints(courseLabel, 0, 0, 3, 1);
                rightPanelBoxCenterLeftGrid.getChildren().add(courseLabel);

                rightPanelBoxCenterLeft.getChildren().add(rightPanelBoxCenterLeftGrid);
                rightPanelBoxCenterLeftGrid.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }


        VBox rightPanelBoxCenterCenter = new VBox(20);
        rightPanelBoxCenterCenter.setPadding(new Insets(20));
        rightPanelBoxCenterCenter.getStyleClass().add("right-panel-box-center-center");
        rightPanelBoxCenterCenter.setAlignment(Pos.CENTER_LEFT);

        ChoiceBox<String> classeList = new ChoiceBox<>();
        ChoiceBox<String> courseList = new ChoiceBox<>();
        TextArea detailsArea = new TextArea();
        detailsArea.setPromptText("Ajouter les details du cours ici...");
        detailsArea.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 0px; -fx-text-fill: black;");
        Button addDetailsButton = new Button("Ajouter");
        addDetailsButton.setStyle("-fx-font-size: 14px; -fx-pref-width: 150px; -fx-text-fill: black;");

        addDetailsButton.setOnAction(event -> {
            String classeName = classeList.getValue();
            String intitule = courseList.getValue();
            String details = detailsArea.getText();
            // System.out.println(classeName + " " + intitule + " " + details);
            // Appel de la méthode pour ajouter le cours et les détails
            Enseignant e =new Enseignant(new Personne(recupFirstName(id), recupLastName(id), recupEmail(id), recupPhoneNumber(id)), id, recupSpecialite(id));
            @SuppressWarnings("unused")
            Fiche f = e.addCoursDetails(classeName, intitule, details);
        });


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(allClasses);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            // System.out.println("Le cahier de texte" + this.classe)
            while (result.next()) {
                classeList.getItems().add(result.getString("cl.classeName"));
                courseList.getItems().add(result.getString("c.intitule"));

            }
            classeList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");
            classeList.setValue("Sélectionnez une classe"); // Valeur par défaut
            courseList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");
            courseList.setValue("Sélectionnez un cours"); // Valeur par défaut

            rightPanelBoxCenterCenter.getChildren().addAll(classeList, courseList);
            classeList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-text-fill: black;");
            courseList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-text-fill: black;");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }

        rightPanelBoxCenterCenter.getChildren().addAll(detailsArea, addDetailsButton);
        // Créer un ScrollPane et y placer le VBox
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(titledPane);

        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);

        VBox rightPanelBoxCenterRight = new VBox(20);
        rightPanelBoxCenterRight.setPadding(new Insets(20));
        rightPanelBoxCenterRight.getStyleClass().add("right-panel-box-center-right");
        rightPanelBoxCenterRight.setAlignment(Pos.CENTER);

        // Charger les images des bouton
        Image viewTextBookIcon = new Image(getClass().getResource("ressources/images/visitBook.png").toExternalForm());
        ImageView viewTextBookIconView = new ImageView(viewTextBookIcon);
        viewTextBookIconView.setFitWidth(30);
        viewTextBookIconView.setFitHeight(30);

        Image validateContentIcon = new Image(getClass().getResource("ressources/images/validContent.png").toExternalForm());
        ImageView validateContentIconView = new ImageView(validateContentIcon);
        validateContentIconView.setFitWidth(30);
        validateContentIconView.setFitHeight(30);

        Image logOutIcon = new Image(getClass().getResource("ressources/images/logOut1.png").toExternalForm());
        ImageView logOutIconView = new ImageView(logOutIcon);
        logOutIconView.setFitWidth(30);
        logOutIconView.setFitHeight(30);

        Button showCourses = new Button("Voir la liste de mes cours");
        showCourses.setGraphic(viewTextBookIconView);
        showCourses.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        showCourses.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().add(scrollPane);
            rightPanelBoxCenter.getChildren().remove(rightPanelBoxCenterCenter);
        });




        Button logOutButton = new Button("Se Deconnecter");
        logOutButton.setGraphic(logOutIconView);
        logOutButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");
        logOutButton.setOnAction(event -> {
            LoginScene loginScene = new LoginScene(mainApp);
            loginScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
            mainApp.changeScene(loginScene.getScene());
        });




        Button addCourseContent = new Button("Ajouter un Contenu");
        addCourseContent.setGraphic(validateContentIconView);
        addCourseContent.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        addCourseContent.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().add(rightPanelBoxCenterCenter);
            rightPanelBoxCenter.getChildren().remove(scrollPane);
        });
        rightPanelBoxCenterRight.getChildren().addAll(showCourses, addCourseContent, logOutButton);



        rightPanelBoxCenter.getChildren().add(rightPanelBoxCenterRight);
        rightPanelBoxCenterLeft.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        rightPanelBoxCenterCenter.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        rightPanelBoxCenterRight.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.3));

        HBox rightPanelBoxBottom = new HBox();
        rightPanelBoxBottom.setPadding(new Insets(20));
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom");
        rightPanelBoxBottom.setAlignment(Pos.CENTER);
        // rightPanelBoxBottom.setPrefHeight(90);
        rightPanelBoxBottom.getStyleClass().add("bottom-panel");
        rightPanelBoxBottom.setStyle("-fx-background-color: #2E2E2E; -fx-padding: 10px; -fx-alignment: center;");
        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        rightPanelBoxBottom.getChildren().addAll(footerText);

        rightPanelBox.getChildren().addAll(rightPanelBoxTop, rightPanelBoxMiddle, rightPanelBoxCenter, rightPanelBoxBottom);
                rightPanelBoxTop.prefHeightProperty().bind(rightPanelBox.heightProperty().multiply(0.1));
        rightPanelBoxMiddle.prefHeightProperty().bind(rightPanelBox.heightProperty().multiply(0.1));
        rightPanelBoxCenter.prefHeightProperty().bind(rightPanelBox.heightProperty().multiply(0.7));
        rightPanelBoxBottom.prefHeightProperty().bind(rightPanelBox.heightProperty().multiply(0.1));



        // Ajout du leftBorderPane au primaryPanel
        primaryPanel.getChildren().addAll(leftBorderPane, rightPanelBox);
        leftBorderPane.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.15));
        rightPanelBox.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.85));

        this.scene = new Scene(primaryPanel);
    }

    public Scene getScene() {
        return scene;
    }
}
