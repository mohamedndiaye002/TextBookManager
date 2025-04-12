
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
// import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Screen;

import javafx.scene.shape.Circle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class check extends Application {

    @Override
    public void start(Stage primaryStage) {

        VBox components = new VBox(10);
        ChoiceBox<String> countries = new ChoiceBox<>();

        countries.getItems().addAll("France", "Japon", "italie");

        components.getChildren().addAll(countries);

        HBox mainPane = new HBox();
        
        mainPane.getChildren().addAll(components);
        mainPane.setStyle("-fx-border-color: red;-fx-border-width: 2; -fx-border-style: solid;");
        mainPane.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Page de Connexion");
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
