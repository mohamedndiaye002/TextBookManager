//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.ColumnConstraints;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.RowConstraints;
//import javafx.stage.Stage;
//
//public class GridPaneDifferentSizes extends Application {
//    @Override
//    public void start(Stage stage) {
//        GridPane gridPane = new GridPane();
//
//        // Définir les contraintes des colonnes
//        ColumnConstraints col1 = new ColumnConstraints();
//        col1.setPercentWidth(30); // 30% de la largeur totale
//        ColumnConstraints col2 = new ColumnConstraints();
//        col2.setPercentWidth(70); // 70% de la largeur totale
//
//        gridPane.getColumnConstraints().addAll(col1, col2);
//
//        // Définir les contraintes des lignes
//        RowConstraints row1 = new RowConstraints();
//        row1.setPrefHeight(100); // Hauteur préférée de 100 pixels
//        RowConstraints row2 = new RowConstraints();
//        row2.setPrefHeight(200); // Hauteur préférée de 200 pixels
//
//        gridPane.getRowConstraints().addAll(row1, row2);
//
//        // Ajouter des boutons dans la grille
//        gridPane.add(new Button("Petit"), 0, 0); // Colonne 0, Ligne 0
//        gridPane.add(new Button("Grand"), 1, 0); // Colonne 1, Ligne 0
//        gridPane.add(new Button("Normal"), 0, 1); // Colonne 0, Ligne 1
//        gridPane.add(new Button("Custom"), 1, 1); // Colonne 1, Ligne 1
//
//        Scene scene = new Scene(gridPane, 400, 300);
//        stage.setTitle("GridPane avec tailles différentes");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}