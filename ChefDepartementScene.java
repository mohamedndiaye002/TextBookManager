// Importing necessary classes and static methods
import static controllers.recupData.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Class definition for ChefDepartementScene
public class ChefDepartementScene {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdt";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    // Scene object to hold the UI
    private Scene scene;

    // Constructor for initializing the scene
    public ChefDepartementScene(MainApp mainApp) {
        int id = 29; // Hardcoded ID for demonstration
        String firstName = recupNames(id); // Retrieve first name using ID
        String departementName = recupDepartment(id); // Retrieve department name using ID

        // Create the primary panel and set it as the scene
        HBox primaryPanel = createPrimaryPanel(firstName, departementName);
        this.scene = new Scene(primaryPanel);
    }

    // Getter for the scene
    public Scene getScene() {
        return scene;
    }

    // Method to create the primary panel
    private HBox createPrimaryPanel(String firstName, String departementName) {
        HBox primaryPanel = new HBox(); // Horizontal box layout
        primaryPanel.getStyleClass().add("primary-panel"); // Add CSS class

        // Create left and right panels
        BorderPane leftPanel = createLeftPanel();
        VBox rightPanel = createRightPanel(firstName, departementName);

        // Add panels to the primary panel
        primaryPanel.getChildren().addAll(leftPanel, rightPanel);
        leftPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.15)); // Set width proportion
        rightPanel.prefWidthProperty().bind(primaryPanel.widthProperty().multiply(0.85)); // Set width proportion

        return primaryPanel;
    }

    // Method to create the left panel
    private BorderPane createLeftPanel() {
        BorderPane leftBorderPane = new BorderPane(); // BorderPane layout
        leftBorderPane.getStyleClass().add("left-border-pane"); // Add CSS class

        // Create top and center sections
        VBox topSection = createLeftPanelTop();
        VBox centerSection = createLeftPanelCenter();

        // Add sections to the BorderPane
        leftBorderPane.setTop(topSection);
        leftBorderPane.setCenter(centerSection);

        return leftBorderPane;
    }

    // Method to create the top section of the left panel
    private VBox createLeftPanelTop() {
        VBox leftBorderPaneTop = new VBox(); // Vertical box layout
        leftBorderPaneTop.getStyleClass().add("left-border-pane-top"); // Add CSS class
        leftBorderPaneTop.setAlignment(Pos.CENTER); // Center alignment
        leftBorderPaneTop.setPrefHeight(200); // Set preferred height

        // Load and display logo image
        Image logoImage = new Image(getClass().getResource("ressources/images/uidt.jpeg").toExternalForm());
        ImageView logoImageView = new ImageView(logoImage);
        logoImageView.setPreserveRatio(true); // Preserve aspect ratio
        Circle clip = new Circle(64, 66, 60); // Circular clip for the image
        logoImageView.setClip(clip);

        leftBorderPaneTop.getChildren().add(logoImageView); // Add image to the VBox
        return leftBorderPaneTop;
    }

    // Method to create the center section of the left panel
    private VBox createLeftPanelCenter() {
        VBox leftBorderPaneCenter = new VBox(20); // Vertical box layout with spacing
        leftBorderPaneCenter.setPadding(new Insets(20)); // Set padding
        leftBorderPaneCenter.getStyleClass().add("left-border-pane-center"); // Add CSS class
        leftBorderPaneCenter.setAlignment(Pos.TOP_CENTER); // Top-center alignment

        // Create buttons for navigation
        Button homeButton = createButton("Home", "ressources/images/home1.png");
        Button notificationButton = createButton("Notification", "ressources/images/notification2.png");
        Button accountButton = createButton("Settings", "ressources/images/setting.png");

        // Add buttons to the VBox
        leftBorderPaneCenter.getChildren().addAll(homeButton, notificationButton, accountButton);
        return leftBorderPaneCenter;
    }

    // Method to create a button with an icon
    private Button createButton(String text, String iconPath) {
        // Load and set icon image
        Image icon = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(30); // Set icon width
        iconView.setFitHeight(30); // Set icon height

        // Create button and set properties
        Button button = new Button(text);
        button.setGraphic(iconView); // Set icon as graphic
        button.setStyle("-fx-content-display: left; -fx-font-size: 14px; -fx-pref-width: 160px; -fx-font-size: 18px;");
        return button;
    }

    // Method to create the right panel
    private VBox createRightPanel(String firstName, String departementName) {
        VBox rightPanelBox = new VBox(20); // Vertical box layout with spacing
        rightPanelBox.setPadding(new Insets(20)); // Set padding
        rightPanelBox.getStyleClass().add("right-panel-box"); // Add CSS class

        // Create sections for the right panel
        HBox topSection = createRightPanelTop(firstName);
        HBox middleSection = createRightPanelMiddle(departementName);
        HBox centerSection = createRightPanelCenter();
        HBox bottomSection = createRightPanelBottom();

        // Add sections to the VBox
        rightPanelBox.getChildren().addAll(topSection, middleSection, centerSection, bottomSection);
        return rightPanelBox;
    }

    // Method to create the top section of the right panel
    private HBox createRightPanelTop(String firstName) {
        HBox rightPanelBoxTop = new HBox(); // Horizontal box layout
        rightPanelBoxTop.setPadding(new Insets(20)); // Set padding
        rightPanelBoxTop.getStyleClass().add("right-panel-box-top"); // Add CSS class
        rightPanelBoxTop.setAlignment(Pos.CENTER); // Center alignment

        // Create title label
        Label titleLabel = new Label("Dashboard - Espace Chef De Departement");
        titleLabel.setStyle("-fx-font-size: 30px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Century Gothic';");

        // Create account box with name and icon
        HBox accountBox = new HBox(20); // Horizontal box layout with spacing
        accountBox.setPadding(new Insets(0, 20, 0, 20)); // Set padding
        Label accountLabel = new Label(firstName); // Label for the account name
        accountLabel.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: white; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        // Load and set account icon
        Image accountIcon = new Image(getClass().getResource("ressources/images/account.png").toExternalForm());
        ImageView accountIconView = new ImageView(accountIcon);
        accountIconView.setFitWidth(30); // Set icon width
        accountIconView.setFitHeight(30); // Set icon height

        // Add account name and icon to the account box
        accountBox.getChildren().addAll(accountLabel, accountIconView);
        rightPanelBoxTop.getChildren().addAll(titleLabel, accountBox); // Add title and account box to the HBox

        return rightPanelBoxTop;
    }

    // Method to create the middle section of the right panel
    private HBox createRightPanelMiddle(String departementName) {
        HBox rightPanelBoxMiddle = new HBox(20); // Horizontal box layout with spacing
        rightPanelBoxMiddle.setPadding(new Insets(20)); // Set padding
        rightPanelBoxMiddle.getStyleClass().add("right-panel-box-middle"); // Add CSS class
        rightPanelBoxMiddle.setAlignment(Pos.CENTER_LEFT); // Left alignment

        // Create label for the department name
        Label departement = new Label("Departement : " + departementName);
        departement.setStyle("-fx-font-size: 24px;-fx-font-smoothing-type: lcd;-fx-text-fill: #383A86; -fx-font-weight: bold;-fx-font-family: 'Poppins';");

        rightPanelBoxMiddle.getChildren().add(departement); // Add label to the HBox
        return rightPanelBoxMiddle;
    }

    // Method to create the center section of the right panel
    private HBox createRightPanelCenter() {
        HBox rightPanelBoxCenter = new HBox(2); // Horizontal box layout with spacing
        rightPanelBoxCenter.setPadding(new Insets(2)); // Set padding
        rightPanelBoxCenter.getStyleClass().add("right-panel-box-center"); // Add CSS class
        rightPanelBoxCenter.setAlignment(Pos.CENTER); // Center alignment

        // Create choice boxes for professors and courses
        ChoiceBox<String> profList = new ChoiceBox<>();
        ChoiceBox<String> courseList = new ChoiceBox<>();

        // Populate choice boxes with data
        profList.getItems().addAll(getAllProfessors());
        courseList.getItems().addAll(getAllCourses());

        // Set styles and default values for choice boxes
        profList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");
        profList.setValue("Sélectionnez un professeur");
        courseList.setStyle("-fx-font-size: 14px; -fx-pref-width: 200px; -fx-padding: 10px; -fx-background-color: #383A86; -fx-text-fill: white;");
        courseList.setValue("Sélectionnez un cours");

        // Add choice boxes to the HBox
        rightPanelBoxCenter.getChildren().addAll(profList, courseList);
        return rightPanelBoxCenter;
    }

    // Method to create the bottom section of the right panel
    private HBox createRightPanelBottom() {
        HBox rightPanelBoxBottom = new HBox(); // Horizontal box layout
        rightPanelBoxBottom.setPadding(new Insets(20)); // Set padding
        rightPanelBoxBottom.getStyleClass().add("right-panel-box-bottom"); // Add CSS class
        rightPanelBoxBottom.setAlignment(Pos.CENTER); // Center alignment

        // Create footer label
        Label footerText = new Label("© 2025 Manage Your Classes - All rights reserved");
        footerText.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
        rightPanelBoxBottom.getChildren().add(footerText); // Add label to the HBox

        return rightPanelBoxBottom;
    }

    // Method to retrieve all courses from the database
    private ObservableList<String> getAllCourses() {
        ObservableList<String> courses = FXCollections.observableArrayList(); // Observable list for courses
        String query = "SELECT intitule FROM cours"; // SQL query to fetch course titles

        // Execute query and populate the list
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                courses.add(resultSet.getString("intitule")); // Add course title to the list
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace
        }

        return courses; // Return the list of courses
    }

    // Method to retrieve all professors from the database
    private ObservableList<String> getAllProfessors() {
        ObservableList<String> professors = FXCollections.observableArrayList(); // Observable list for professors
        String query = "SELECT p.firstName, p.lastName FROM personnel p INNER JOIN enseignant e ON p.idPersonnel = e.idPersonnel"; // SQL query to fetch professor names

        // Execute query and populate the list
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                String fullName = resultSet.getString("firstName") + " " + resultSet.getString("lastName"); // Concatenate first and last names
                professors.add(fullName); // Add full name to the list
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print exception stack trace
        }

        return professors; // Return the list of professors
    }
}