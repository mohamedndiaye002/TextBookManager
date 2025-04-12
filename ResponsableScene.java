import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ResponsableScene {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdt";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Scene scene;

    public ResponsableScene(MainApp mainApp) {
        int id = 55; // Exemple d'ID pour le responsable
        String firstName = recupNames(id);
        String departementName = recupDepartment(id);

        HBox primaryPanel = new HBox();
        primaryPanel.getStyleClass().add("primary-panel");

        // Left Panel
        BorderPane leftPanel = createLeftPanel();

        // Right Panel
        VBox rightPanel = createRightPanel(firstName, departementName);

        primaryPanel.getChildren().addAll(leftPanel, rightPanel);
        leftPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.15));
        rightPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.85));

        this.scene = new Scene(primaryPanel);
    }

    public Scene getScene() {
        return scene;
    }

    private BorderPane createLeftPanel() {
        BorderPane leftBorderPane = new BorderPane();
        leftBorderPane.getStyleClass().add("left-border-pane");

        VBox topSection = createLeftPanelTop();
        VBox centerSection = createLeftPanelCenter();

        leftBorderPane.setTop(topSection);
        leftBorderPane.setCenter(centerSection);

        return leftBorderPane;
    }

    private VBox createLeftPanelTop() {
        VBox leftBorderPaneTop = new VBox();
        leftBorderPaneTop.getStyleClass().add("left-border-pane-top");
        leftBorderPaneTop.setAlignment(Pos.CENTER);
        leftBorderPaneTop.setPrefHeight(200);

        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true);

        leftBorderPaneTop.getChildren().add(logoImageView);
        return leftBorderPaneTop;
    }

    private VBox createLeftPanelCenter() {
        VBox leftBorderPaneCenter = new VBox(20);
        leftBorderPaneCenter.setPadding(new Insets(20));
        leftBorderPaneCenter.getStyleClass().add("left-border-pane-center");
        leftBorderPaneCenter.setAlignment(Pos.TOP_CENTER);

        Button homeButton = createButton("Home", "ressources/images/home1.png");
        Button notificationButton = createButton("Notification", "ressources/images/notification2.png");
        Button accountButton = createButton("Settings", "ressources/images/setting.png");

        leftBorderPaneCenter.getChildren().addAll(homeButton, notificationButton, accountButton);
        return leftBorderPaneCenter;
    }

    private Button createButton(String text, String iconPath) {
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(30);
        iconView.setFitHeight(30);

        Button button = new Button(text);
        button.setGraphic(iconView);
        button.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");
        return button;
    }

    private VBox createRightPanel(String firstName, String departementName) {
        VBox rightPanelBox = new VBox(20);
        rightPanelBox.setPadding(new Insets(20));
        rightPanelBox.getStyleClass().add("right-panel-box");

        HBox topSection = createRightPanelTop(firstName);
        HBox middleSection = createRightPanelMiddle(departementName);
        HBox centerSection = createRightPanelCenter();
        HBox bottomSection = createRightPanelBottom();

        rightPanelBox.getChildren().addAll(topSection, middleSection, centerSection, bottomSection);
        return rightPanelBox;
    }

    private HBox createRightPanelTop(String firstName) {
        HBox rightPanelBoxTop = new HBox();
        rightPanelBoxTop.setPadding(new Insets(20));
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top");
        rightPanelBoxTop.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Dashboard - Espace Responsable");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Century Gothic';");

        HBox accountBox = new HBox(20);
        accountBox.setPadding(new Insets(0, 20, 0, 20));
        Label accountLabel = new Label(firstName);
        accountLabel.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: white; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        Image accountIcon = new Image(getClass().getResource("ressources/images/account.png").toExternalForm());
        ImageView accountIconView = new ImageView(accountIcon);
        accountIconView.setFitWidth(30);
        accountIconView.setFitHeight(30);

        accountBox.getChildren().addAll(accountLabel, accountIconView);
        rightPanelBoxTop.getChildren().addAll(titleLabel, accountBox);

        return rightPanelBoxTop;
    }

    private HBox createRightPanelMiddle(String departementName) {
        HBox rightPanelBoxMiddle = new HBox(20);
        rightPanelBoxMiddle.setPadding(new Insets(20));
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle");
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT);

        Label departement = new Label("Departement : " + departementName);
        departement.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        rightPanelBoxMiddle.getChildren().add(departement);
        return rightPanelBoxMiddle;
    }

    private HBox createRightPanelCenter() {
        HBox rightPanelBoxCenter = new HBox(2);
        rightPanelBoxCenter.setPadding(new Insets(2));
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center");
        rightPanelBoxCenter.setAlignment(Pos.CENTER);

        VBox manageClassesBox = createManageClassesBox();
        VBox manageStudentsBox = createManageStudentsBox();

        VBox actionBox = createActionBox(manageClassesBox, manageStudentsBox, rightPanelBoxCenter);

        rightPanelBoxCenter.getChildren().addAll(actionBox, manageStudentsBox);
        return rightPanelBoxCenter;
    }

    private VBox createManageClassesBox() {
        VBox manageClassesBox = new VBox(10);
        manageClassesBox.setPadding(new Insets(10));
        manageClassesBox.setAlignment(Pos.CENTER_LEFT);

        Label header = new Label("Gestion des Classes");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> classList = new ListView<>();
        classList.getItems().addAll("Classe 1", "Classe 2", "Classe 3");

        manageClassesBox.getChildren().addAll(header, classList);
        return manageClassesBox;
    }

    private VBox createManageStudentsBox() {
        VBox manageStudentsBox = new VBox(10);
        manageStudentsBox.setPadding(new Insets(10));
        manageStudentsBox.setAlignment(Pos.CENTER);

        Label header = new Label("Gestion des Étudiants");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> studentList = new ListView<>();
        studentList.getItems().addAll("Étudiant 1", "Étudiant 2", "Étudiant 3");

        manageStudentsBox.getChildren().addAll(header, studentList);
        return manageStudentsBox;
    }

    private VBox createActionBox(VBox manageClassesBox, VBox manageStudentsBox, HBox rightPanelBoxCenter) {
        VBox actionBox = new VBox(20);
        actionBox.setPadding(new Insets(20));
        actionBox.setAlignment(Pos.CENTER);

        Button manageClassesButton = new Button("Gérer les Classes");
        manageClassesButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().clear();
            rightPanelBoxCenter.getChildren().addAll(actionBox, manageClassesBox);
        });

        Button manageStudentsButton = new Button("Gérer les Étudiants");
        manageStudentsButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().clear();
            rightPanelBoxCenter.getChildren().addAll(actionBox, manageStudentsBox);
        });

        actionBox.getChildren().addAll(manageClassesButton, manageStudentsButton);
        return actionBox;
    }

    private HBox createRightPanelBottom() {
        HBox rightPanelBoxBottom = new HBox();
        rightPanelBoxBottom.setPadding(new Insets(20));
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom");
        rightPanelBoxBottom.setAlignment(Pos.CENTER);

        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        rightPanelBoxBottom.getChildren().add(footerText);

        return rightPanelBoxBottom;
    }

    private ObservableList<String> getAllCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList();
        String query = "SELECT intitule FROM cours";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(resultSet.getString("intitule"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courses;
    }

    private ObservableList<String> getAllStudents() {
        ObservableList<String> students = FXCollections.observableArrayList();
        String query = "SELECT firstName, lastName FROM etudiant";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String fullName = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
                students.add(fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }
}