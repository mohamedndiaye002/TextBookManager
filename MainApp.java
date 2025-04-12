
import java.sql.SQLException;
import java.util.Objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.text.Font;

public class MainApp extends Application {

    private Stage stage; // L'objet Stage global pour gérer les scènes

    @Override
    public void start(Stage stage) throws SQLException {
        this.stage = stage; // Stocker l'objet Stage

        // Charger la scène de connexion au démarrage
        LoginScene loginScene = new LoginScene(this);
        loginScene.getScene().getStylesheets().add(Objects.requireNonNull(getClass().getResource("ressources/css/style.css")).toExternalForm());
        stage.setScene(loginScene.getScene());
        stage.setTitle("Manage Your Classes");
        // stage.setResizable(false);
        stage.setWidth(700);
        stage.setHeight(600);
        // stage.setMaximized(true);
        stage.setMinWidth(700);
        stage.setMinHeight(600);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("ressources/images/logo.png")));
        stage.show();
    }

    // Méthode pour changer de scène
    public void changeScene(Scene scene) {
        stage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
