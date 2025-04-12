// Importing necessary JavaFX classes
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

// Importing necessary SQL classes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Main class for EnseignantScene
public class EnseignantScene {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdt";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Scene object for the UI
    private Scene scene;

    // Constructor for EnseignantScene
    public EnseignantScene(MainApp mainApp) {
        // Example ID for the teacher
        int id = 42;
        // Retrieve teacher's first name
        String firstName = recupNames(id);
        // Retrieve teacher's department name
        String departementName = recupDepartment(id);

        // Main horizontal layout
        HBox primaryPanel = new HBox();
        primaryPanel.getStyleClass().add("primary-panel");

        // Create left panel
        BorderPane leftPanel = createLeftPanel();

        // Create right panel
        VBox rightPanel = createRightPanel(firstName, departementName);

        // Add left and right panels to the main layout
        primaryPanel.getChildren().addAll(leftPanel, rightPanel);
        // Set width proportions for left and right panels
        leftPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.15));
        rightPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.85));

        // Initialize the scene with the main layout
        this.scene = new Scene(primaryPanel);
    }

    // Getter for the scene
    public Scene getScene() {
        return scene;
    }

    // Method to create the left panel
    private BorderPane createLeftPanel() {
        // Create a BorderPane for the left panel
        BorderPane leftBorderPane = new BorderPane();
        leftBorderPane.getStyleClass().add("left-border-pane");

        // Create top and center sections for the left panel
        VBox topSection = createLeftPanelTop();
        VBox centerSection = createLeftPanelCenter();

        // Add top and center sections to the left panel
        leftBorderPane.setTop(topSection);
        leftBorderPane.setCenter(centerSection);

        return leftBorderPane;
    }

    // Method to create the top section of the left panel
    private VBox createLeftPanelTop() {
        // Create a VBox for the top section
        VBox leftBorderPaneTop = new VBox();
        leftBorderPaneTop.getStyleClass().add("left-border-pane-top");
        leftBorderPaneTop.setAlignment(Pos.CENTER);
        leftBorderPaneTop.setPrefHeight(200);

        // Add a logo image to the top section
        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true);

        leftBorderPaneTop.getChildren().add(logoImageView);
        return leftBorderPaneTop;
    }

    // Method to create the center section of the left panel
    private VBox createLeftPanelCenter() {
        // Create a VBox for the center section
        VBox leftBorderPaneCenter = new VBox(20);
        leftBorderPaneCenter.setPadding(new Insets(20));
        leftBorderPaneCenter.getStyleClass().add("left-border-pane-center");
        leftBorderPaneCenter.setAlignment(Pos.TOP_CENTER);

        // Create buttons for the center section
        Button homeButton = createButton("Home", "ressources/images/home1.png");
        Button notificationButton = createButton("Notification", "ressources/images/notification2.png");
        Button accountButton = createButton("Settings", "ressources/images/setting.png");

        // Add buttons to the center section
        leftBorderPaneCenter.getChildren().addAll(homeButton, notificationButton, accountButton);
        return leftBorderPaneCenter;
    }

    // Method to create a button with an icon
    private Button createButton(String text, String iconPath) {
        // Load the icon image
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(30);
        iconView.setFitHeight(30);

        // Create a button and set its properties
        Button button = new Button(text);
        button.setGraphic(iconView);
        button.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");
        return button;
    }

    // Method to create the right panel
    private VBox createRightPanel(String firstName, String departementName) {
        // Create a VBox for the right panel
        VBox rightPanelBox = new VBox(20);
        rightPanelBox.setPadding(new Insets(20));
        rightPanelBox.getStyleClass().add("right-panel-box");

        // Create sections for the right panel
        HBox topSection = createRightPanelTop(firstName);
        HBox middleSection = createRightPanelMiddle(departementName);
        HBox centerSection = createRightPanelCenter();
        HBox bottomSection = createRightPanelBottom();

        // Add sections to the right panel
        rightPanelBox.getChildren().addAll(topSection, middleSection, centerSection, bottomSection);
        return rightPanelBox;
    }

    // Method to create the top section of the right panel
    private HBox createRightPanelTop(String firstName) {
        // Create an HBox for the top section
        HBox rightPanelBoxTop = new HBox();
        rightPanelBoxTop.setPadding(new Insets(20));
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top");
        rightPanelBoxTop.setAlignment(Pos.CENTER);

        // Add a title label to the top section
        Label titleLabel = new Label("Dashboard - Espace Enseignant");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Century Gothic';");

        // Create an account box with the teacher's name and icon
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

    // Method to create the middle section of the right panel
    private HBox createRightPanelMiddle(String departementName) {
        // Create an HBox for the middle section
        HBox rightPanelBoxMiddle = new HBox(20);
        rightPanelBoxMiddle.setPadding(new Insets(20));
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle");
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT);

        // Add a label for the department name
        Label departement = new Label("Departement : " + departementName);
        departement.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        rightPanelBoxMiddle.getChildren().add(departement);
        return rightPanelBoxMiddle;
    }

    // Method to create the center section of the right panel
    private HBox createRightPanelCenter() {
        // Create an HBox for the center section
        HBox rightPanelBoxCenter = new HBox(2);
        rightPanelBoxCenter.setPadding(new Insets(2));
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center");
        rightPanelBoxCenter.setAlignment(Pos.CENTER);

        // Create boxes for assigning courses and showing students
        VBox assignCourseMainBox = createAssignCourseBox();
        VBox showStudentMainBox = createShowStudentBox();

        // Create buttons to toggle between the two boxes
        VBox actionBox = createActionBox(assignCourseMainBox, showStudentMainBox, rightPanelBoxCenter);

        rightPanelBoxCenter.getChildren().addAll(actionBox, showStudentMainBox);
        return rightPanelBoxCenter;
    }

    // Method to create the box for assigning courses
    private VBox createAssignCourseBox() {
        // Create a VBox for the assign course box
        VBox assignCourseMainBox = new VBox(10);
        assignCourseMainBox.setPadding(new Insets(10));
        assignCourseMainBox.setAlignment(Pos.CENTER_LEFT);

        // Create choice boxes for professors and courses
        ChoiceBox<String> profList = new ChoiceBox<>();
        ChoiceBox<String> courseList = new ChoiceBox<>();

        // Populate the choice boxes with data
        profList.getItems().addAll(getAllProfessors());
        courseList.getItems().addAll(getAllCourses());

        // Set styles for the choice boxes
        profList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");
        courseList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");

        assignCourseMainBox.getChildren().addAll(profList, courseList);
        return assignCourseMainBox;
    }

    // Method to create the box for showing students
    private VBox createShowStudentBox() {
        // Create a VBox for the show student box
        VBox showStudentMainBox = new VBox(10);
        showStudentMainBox.setPadding(new Insets(10));
        showStudentMainBox.setAlignment(Pos.CENTER);

        // Add a header label and a list view for students
        Label header = new Label("Liste des étudiants");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        ListView<String> studentList = new ListView<>();
        studentList.getItems().addAll("Étudiant 1", "Étudiant 2", "Étudiant 3");

        showStudentMainBox.getChildren().addAll(header, studentList);
        return showStudentMainBox;
    }

    // Method to create the action box for toggling between assign course and show student boxes
    private VBox createActionBox(VBox assignCourseMainBox, VBox showStudentMainBox, HBox rightPanelBoxCenter) {
        // Create a VBox for the action box
        VBox actionBox = new VBox(20);
        actionBox.setPadding(new Insets(20));
        actionBox.setAlignment(Pos.CENTER);

        // Create buttons for toggling
        Button assignCourseButton = new Button("Assigner un cours");
        assignCourseButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().clear();
            rightPanelBoxCenter.getChildren().addAll(actionBox, assignCourseMainBox);
        });

        Button showStudentButton = new Button("Afficher les étudiants");
        showStudentButton.setOnAction(event -> {
            rightPanelBoxCenter.getChildren().clear();
            rightPanelBoxCenter.getChildren().addAll(actionBox, showStudentMainBox);
        });

        actionBox.getChildren().addAll(assignCourseButton, showStudentButton);
        return actionBox;
    }

    // Method to create the bottom section of the right panel
    private HBox createRightPanelBottom() {
        // Create an HBox for the bottom section
        HBox rightPanelBoxBottom = new HBox();
        rightPanelBoxBottom.setPadding(new Insets(20));
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom");
        rightPanelBoxBottom.setAlignment(Pos.CENTER);

        // Add a footer label
        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        rightPanelBoxBottom.getChildren().add(footerText);

        return rightPanelBoxBottom;
    }

    // Method to retrieve all courses from the database
    private ObservableList<String> getAllCourses() {
        // Create an observable list for courses
        ObservableList<String> courses = FXCollections.observableArrayList();
        String query = "SELECT intitule FROM cours";

        // Execute the query and populate the list
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

    // Method to retrieve all professors from the database
    private ObservableList<String> getAllProfessors() {
        // Create an observable list for professors
        ObservableList<String> professors = FXCollections.observableArrayList();
        String query = "SELECT p.firstName, p.lastName FROM personnel p INNER JOIN enseignant e ON p.idPersonnel = e.idPersonnel";

        // Execute the query and populate the list
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String fullName = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
                professors.add(fullName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return professors;
    }
}