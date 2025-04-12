//
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.VBox;
//import javafx.geometry.Pos;
//
//public class DashboardScene {
//
//    private Scene scene;
//
//    public DashboardScene(MainApp mainApp) {
//        Button profileButton = new Button("Profil");
//        Button logoutButton = new Button("Déconnexion");
//
//        profileButton.setOnAction(event -> {
//            ResponsableScene profileScene = new ResponsableScene(mainApp);
//            mainApp.changeScene(profileScene.getScene());
//        });
//
//        logoutButton.setOnAction(event -> {
//            LoginScene loginScene = new LoginScene(mainApp);
//            mainApp.changeScene(loginScene.getScene());
//        });
//
//        VBox vbox = new VBox(10, profileButton, logoutButton);
//        vbox.setAlignment(Pos.CENTER);
//
//        this.scene = new Scene(vbox);
//    }
//
//    public Scene getScene() {
//        return scene;
//    }
//}
