package views;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class createElement {
    public Image createImage(String src){
        Image image = new Image(getClass().getResource(src).toExternalForm());
        return image;
    }

    public ImageView createImageView(Image image){
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        return imageView;
    }



    // Create Button Method
    public static Button createButton(String name, String src){
        Image imageView = createImageView(new Image(getClass().getResource(src).toExternalForm()));
        imageView.setFitWidth(30);
        imageView.setFitHeight(30);
        Button button = new Button(name);
        button.setGraphic(imageView);
        return button;
    }

}
