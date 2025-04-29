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

    private Image createImage(String src) {
        return new Image(getClass().getResource(src).toExternalForm());
    }

    private ImageView createImageView(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        return imageView;
    }

    // Create Button Method
    private Button createButton(String name, String src) {
        ImageView imageView = createImageView(createImage(src));
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        Button button = new Button(name);
        button.setGraphic(imageView);
        return button;
    }

    public VBox createLeftPanelCenter() {
        VBox leftBorderPaneCenter = new VBox(20);
        leftBorderPaneCenter.setPadding(new Insets(20, 20, 20, 20));
        leftBorderPaneCenter.getStyleClass().add("left-border-pane-center");
        leftBorderPaneCenter.setPrefHeight(300);
        leftBorderPaneCenter.setAlignment(Pos.TOP_CENTER);

        Button homeButton = createButton("Home", "ressources/images/home1.png");
        homeButton.setAlignment(Pos.CENTER_LEFT);
        homeButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        Button notificationButton = createButton("Notification", "ressources/images/notification2.png");
        notificationButton.setAlignment(Pos.CENTER_LEFT);
        notificationButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        Button accountButton = createButton("Settings", "ressources/images/setting.png");
        accountButton.setAlignment(Pos.CENTER_LEFT);
        accountButton.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");

        leftBorderPaneCenter.getChildren().addAll(homeButton, notificationButton, accountButton);
        leftBorderPaneCenter.setAlignment(Pos.CENTER_LEFT);
        return leftBorderPaneCenter;
    }

    private VBox createLeftPanelTop() {
        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        Circle clip = new Circle(64, 66, 60);
        logoImageView.setClip(clip);
        logoImageView.getStyleClass().add("logo-image");
        logoImageView.setPreserveRatio(true);
        VBox leftBorderPaneTop = new VBox();
        leftBorderPaneTop.getStyleClass().add("left-border-pane-top");
        leftBorderPaneTop.setAlignment(Pos.CENTER);
        leftBorderPaneTop.setPrefHeight(200);
        leftBorderPaneTop.getChildren().add(logoImageView);
        return leftBorderPaneTop;
    }

    private VBox createLeftPanelBottom() {
        VBox leftBorderPaneBottom = new VBox();
        leftBorderPaneBottom.getStyleClass().add("left-border-pane-bottom");
        leftBorderPaneBottom.setPrefHeight(300);
        return leftBorderPaneBottom;
    }

    private BorderPane createLeftBorderPane(VBox top, VBox center, VBox bottom) {
        BorderPane leftBorderPane = new BorderPane();
        leftBorderPane.getStyleClass().add("left-border-pane");
        leftBorderPane.setTop(top);
        leftBorderPane.setCenter(center);
        leftBorderPane.setBottom(bottom);
        return leftBorderPane;
    }

    private HBox createRightPanelBoxTop(String role, String fName) {
        HBox rightPanelBoxTop = new HBox();
        rightPanelBoxTop.setPadding(new Insets(20)); // Adds padding around the top section.
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top"); // Adds a CSS style class for styling.
        rightPanelBoxTop.setAlignment(Pos.CENTER); // Centers the content horizontally.
        // Title at the top of the right panel.
        Label titleLabel = new Label("Dashboard - Espace " + role); // Title text.
        titleLabel.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86;"); // Styling for the title.
        titleLabel.setFont(Font.loadFont("ressources/fonts/Poppins-Black.ttf", 30)); // Loads a custom font for the title.
        titleLabel.getStyleClass().add("title-label"); // Adds a CSS style class for styling.

        HBox accountBox = new HBox(20); // Container for the account information.
        accountBox.setPadding(new Insets(0, 20, 0, 20)); // Adds padding around the account box.
        Label accountLabel = new Label(fName); // Displays the user's first name.
        accountLabel.setStyle("-fx-font-size: 18px;-fx-font-smoothing-type: lcd;-fx-text-fill: white;"); // Styling for the account label.
        accountBox.getStyleClass().add("account-box"); // Adds a CSS style class for styling.
        accountBox.setAlignment(Pos.CENTER_RIGHT); // Aligns the content to the right.
        accountBox.getChildren().addAll(accountLabel, createImageView(createImage("ressources/images/account.png"))); // Adds the account label and icon to the container.

        rightPanelBoxTop.getChildren().addAll(titleLabel, accountBox); // Adds the title and account box to the top section.
        titleLabel.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.7)); // Sets the width of the title relative to the top section.
        accountBox.prefWidthProperty().bind(rightPanelBoxTop.widthProperty().multiply(0.3)); // Sets the width of the account box relative to the top section.
        return rightPanelBoxTop;
    }

    private HBox createRightPanelBoxMiddle(String className) {
        HBox rightPanelBoxMiddle = new HBox();
        rightPanelBoxMiddle.setPadding(new Insets(20)); // Adds padding around the middle section.
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle"); // Adds a CSS style class for styling.
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT); // Aligns the content to the left.
        // rightPanelBoxMiddle.setPrefHeight(40); // Sets the preferred height of the middle section.

        Label classe = new Label("Classe: " + className); // Displays the class information.
        classe.setStyle("-fx-font-size: 18px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-family: 'Poppins';"); // Styling for the class label.

        rightPanelBoxMiddle.getChildren().addAll(classe); // Adds the class label to the middle section.
        return rightPanelBoxMiddle;
    }

    public HBox createViewTextBookBoxHead(String title) {
        Label viewTextBookBoxHeadTitle = new Label(title);
        viewTextBookBoxHeadTitle.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: poppins");
        HBox viewTextBookBoxHead = new HBox();
        viewTextBookBoxHead.setPadding(new Insets(20)); // Adds padding around the center section.
        viewTextBookBoxHead.setPrefHeight(50);
        viewTextBookBoxHead.setStyle("-fx-background-color: gray; -fx-background-radius: 10px 10px 0 0;");
        viewTextBookBoxHead.getChildren().add(viewTextBookBoxHeadTitle);
        return viewTextBookBoxHead;
    }

    public ScrollPane createViewTextBookBoxScrollable(VBox container) {
        ScrollPane viewTextBookBoxScrollable = new ScrollPane();
        viewTextBookBoxScrollable.setFitToWidth(true); // Ajuste la largeur au contenu
        viewTextBookBoxScrollable.setPannable(true); // Permet le défilement
        viewTextBookBoxScrollable.setContent(container); // Ajout du VBox au ScrollPane
        return viewTextBookBoxScrollable;
    }

    private HBox createValidCourseBoxHead(String title) {
        Label validCourseBoxHeadTitle = new Label(title);
        validCourseBoxHeadTitle.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: poppins");
        HBox validCourseBoxHead = new HBox();
        validCourseBoxHead.setPadding(new Insets(20)); // Adds padding around the center section.
        validCourseBoxHead.setPrefHeight(50);
        validCourseBoxHead.setStyle("-fx-background-color: gray; -fx-background-radius: 10px 10px 0 0;");
        validCourseBoxHead.getChildren().add(validCourseBoxHeadTitle);
        return validCourseBoxHead;
    }

    public VBox createViewTextBookBox(HBox head, ScrollPane content) {
        VBox viewTextBookBox = new VBox(); // Center section of the center panel.
        viewTextBookBox.setPadding(new Insets(20)); // Adds padding around the center section.
        viewTextBookBox.getStyleClass().add("right-panel-box-center-center"); // Adds a CSS style class for styling.
        viewTextBookBox.getChildren().addAll(head, content);
        return viewTextBookBox;
    }

    private VBox createValidCourseBox(HBox head, ScrollPane content) {
        VBox validCourseBox = new VBox(); // Center section of the center panel.
        validCourseBox.setPadding(new Insets(20)); // Adds padding around the center section.
        validCourseBox.getStyleClass().add("right-panel-box-center-center"); // Adds a CSS style class for styling.
        validCourseBox.getChildren().addAll(head, content);
        return validCourseBox;
    }

    private ScrollPane createValidCourseBoxScrollable(VBox container) {
        ScrollPane validCourseBoxScrollable = new ScrollPane(); // Scroll pane for "Contenu ajouter par l'enseignant".
        validCourseBoxScrollable.setFitToWidth(true); // Ensures the content fits the width of the scroll pane.
        validCourseBoxScrollable.setPannable(true); // Allows dpanning within the scroll pane.
        validCourseBoxScrollable.setContent(container); // Ajout du VBox au ScrollPane
        return validCourseBoxScrollable;
    }

    public ResponsableScene(MainApp mainApp) {
        // int id = 20;
        Session session = Session.getInstance();
        int id = session.getId();
        String firstName = recupNames(id);
        String classeName = recupClasse(id);
        System.out.println("Session ID: " + id + ", First Name: " + firstName + ", classe: " + classeName);
        // chargement des images

        HBox primaryPanel = new HBox();
        primaryPanel.getStyleClass().add("primary-panel");

        BorderPane leftBorderPane = createLeftBorderPane(createLeftPanelTop(), createLeftPanelCenter(), createLeftPanelBottom());

        // Right Part ===================================================================
        // This section defines the right part of the UI, which includes various panels and components.
        VBox rightPanelBox = new VBox(20); // Main container for the right panel with vertical spacing of 20.
        rightPanelBox.setPadding(new Insets(20)); // Adds padding around the container.
        rightPanelBox.getStyleClass().add("right-panel-box"); // Adds a CSS style class for styling.

        HBox rightPanelBoxTop = createRightPanelBoxTop("Etudiant", firstName); // Top section of the right panel.

        HBox rightPanelBoxMiddle = createRightPanelBoxMiddle(classeName); // Middle section of the right panel.

        HBox rightPanelBoxCenter = new HBox(2); // Center section of the right panel.
        rightPanelBoxCenter.setPadding(new Insets(2)); // Adds padding around the center section.
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center"); // Adds a CSS style class for styling.
        rightPanelBoxCenter.setAlignment(Pos.CENTER); // Centers the content horizontally.

        VBox rightPanelBoxCenterLeft = new VBox(20); // Left section of the center panel.
        rightPanelBoxCenterLeft.setPadding(new Insets(20)); // Adds padding around the left section.
        rightPanelBoxCenterLeft.getStyleClass().add("right-panel-box-center-left"); // Adds a CSS style class for styling.
        rightPanelBoxCenterLeft.setAlignment(Pos.CENTER); // Centers the content horizontally.

        // Creation du box d'affichage des cahiers de texte
        HBox viewTextBookBoxHead = createViewTextBookBoxHead("Show The Text Book");
        VBox viewTextBookContent = new VBox(10); // Espacement vertical de 10
        viewTextBookContent.setPadding(new Insets(10)); // Ajout de marges internes
        ScrollPane viewTextBookBoxScrollable = createViewTextBookBoxScrollable(viewTextBookContent);

        VBox viewTextBookBox = createViewTextBookBox(viewTextBookBoxHead, viewTextBookBoxScrollable);

        // ----------------------------------------------------------------------------------------------
        HBox validCourseBoxHead = createValidCourseBoxHead("Validate a content added");
        VBox validCourseContent = new VBox(10); // Espacement vertical de 10
        validCourseContent.setPadding(new Insets(10)); // Ajout de marges internes
        ScrollPane validCourseBoxScrollable = createValidCourseBoxScrollable(validCourseContent);

        VBox validCourseBox = createValidCourseBox(validCourseBoxHead, validCourseBoxScrollable);

        LinkedList<Fiche> Fiches = new LinkedList<>();

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
                GridPane courseList = new GridPane();
                courseList.setVgap(1);
                courseList.setHgap(1);
                courseList.getStyleClass().add("right-panel-box-center-left-grid");
                courseList.setAlignment(Pos.CENTER_LEFT);
                courseList.setPrefWidth(50);

                Label courseLabel = new Label(result.getString("f.details"));
                courseLabel.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
                LocalDate today = LocalDate.now();
                LocalDate courseDate = today.plusDays(i);
                String courseDateString = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Label courseDateLabel = new Label(courseDateString);
                courseDateLabel.setStyle("-fx-font-size: 10px; -fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-family: 'Poppins';");

                courseList.setConstraints(courseLabel, 0, 0, 3, 1);
                courseList.getChildren().add(courseLabel);
                courseList.add(courseDateLabel, 0, 1);
                if (result.getBoolean("f.isSigned") == true) {
                    Image validIcon = new Image(getClass().getResource("ressources/images/validate2.png").toExternalForm());
                    ImageView validIconView = new ImageView(validIcon);
                    validIconView.setFitWidth(20);
                    validIconView.setFitHeight(20);
                    courseList.add(validIconView, 1, 1);
                    courseList.setMargin(validIconView, new Insets(0, 0, 0, 450));
                } else {
                    Image unvalidIcon = new Image(getClass().getResource("ressources/images/unValidate.png").toExternalForm());
                    ImageView unvalidIconView = new ImageView(unvalidIcon);
                    unvalidIconView.setFitWidth(20);
                    unvalidIconView.setFitHeight(20);
                    courseList.add(unvalidIconView, 1, 1);
                    courseList.setMargin(unvalidIconView, new Insets(0, 0, 0, 450));
                }
                viewTextBookContent.getChildren().add(courseList);
                courseList.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");
                i++;
            }

            ResultSet resultValid = statementValid.executeQuery();

            i = 0;
            // -- --------------------------------------------------------------------------------------------------------
            while (resultValid.next()) {
                GridPane uncheckedCourseList = new GridPane();
                uncheckedCourseList.setVgap(1);
                uncheckedCourseList.setHgap(1);
                uncheckedCourseList.getStyleClass().add("right-panel-box-center-center-grid");
                uncheckedCourseList.setAlignment(Pos.CENTER_LEFT);
                uncheckedCourseList.setPrefWidth(50);

                Label courseLabelValid = new Label(resultValid.getString("f.details"));
                courseLabelValid.setStyle("-fx-font-size: 14px;-fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-weight: bold;-fx-font-family: 'Poppins';");
                LocalDate today = LocalDate.now();
                LocalDate courseDate = today.plusDays(i);
                String courseDateString = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Label courseDateLabel = new Label(courseDateString);
                courseDateLabel.setStyle("-fx-font-size: 10px; -fx-font-smoothing-type: lcd;-fx-text-fill: black; -fx-font-family: 'Poppins';");
                Button verifyCourseDetailsButton = new Button("Verifier le contenu");
                verifyCourseDetailsButton.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-background-color: #383A86; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

                uncheckedCourseList.setConstraints(courseLabelValid, 0, 0, 3, 1);
                uncheckedCourseList.setConstraints(verifyCourseDetailsButton, 1, 0, 1, 2);
                uncheckedCourseList.getChildren().add(courseLabelValid);
                uncheckedCourseList.add(courseDateLabel, 0, 1);
                uncheckedCourseList.getChildren().add(verifyCourseDetailsButton);
                uncheckedCourseList.setMargin(verifyCourseDetailsButton, new Insets(0, 0, 0, 300));

                validCourseContent.getChildren().add(uncheckedCourseList);
                uncheckedCourseList.setStyle(" -fx-padding: 20px; -fx-text-fill: black; -fx-font-size: 18px;");

                verifyCourseDetailsButton.setOnAction(event -> {
                    System.out.println("clique du bouton");
                });

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erreur");
        }

        // Créer un ScrollPane et y placer le VBox
        VBox rightPanelBoxCenterRight = new VBox(20);

        rightPanelBoxCenterRight.setPadding(
                new Insets(20));
        rightPanelBoxCenterRight.getStyleClass()
                .add("right-panel-box-center-right");
        rightPanelBoxCenterRight.setAlignment(Pos.CENTER);

        // Create The Right Panel Box Button--------------------------------------------
        Button viewTextBookButton = createButton("Visiter le cahier de texte", "ressources/images/visitBook.png");
        viewTextBookButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        Button validateContentButton = createButton("Valider un contenu", "ressources/images/validate.png");
        validateContentButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        Button logOutButton = createButton("Se Deconnecter", "ressources/images/logOut2.png");
        logOutButton.setStyle("-fx-content-display: top; -fx-pref-width: 200px; -fx-font-size: 14px;");

        // Right Panel Button Event
        viewTextBookButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().add(viewTextBookBox);
            rightPanelBoxCenter.getChildren().remove(validCourseBox);
        });
        validateContentButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().remove(viewTextBookBox);
            rightPanelBoxCenter.getChildren().add(validCourseBox);
        });
        logOutButton.setOnAction(event -> {
            // session.clearSession();
            LoginScene loginScene = new LoginScene(mainApp);
            loginScene.getScene().getStylesheets().add(getClass().getResource("ressources/css/style.css").toExternalForm());
            mainApp.changeScene(loginScene.getScene());
        });

        rightPanelBoxCenterRight.getChildren().addAll(viewTextBookButton, validateContentButton, logOutButton);

        rightPanelBoxCenter.getChildren().addAll(rightPanelBoxCenterRight);
        rightPanelBoxCenter.setAlignment(Pos.CENTER_LEFT);
        viewTextBookBoxScrollable.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        validCourseBox.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.6));
        rightPanelBoxCenterRight.prefWidthProperty().bind(rightPanelBoxCenter.widthProperty().multiply(0.3));

        HBox rightPanelBoxBottom = new HBox();
        rightPanelBoxBottom.setPadding(new Insets(20));
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom");
        rightPanelBoxBottom.setAlignment(Pos.CENTER);
        // rightPanelBoxBottom.setPrefHeight(70);
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
