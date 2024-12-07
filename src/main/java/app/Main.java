package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class Main extends Application {
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage primaryStage) {
        try {
            // Charger le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));
            Parent root = loader.load();

            // Configurer la scène et le stage
            Scene scene = new Scene(root);
            root.setOnMousePressed((MouseEvent event) ->{
                x=event.getSceneX();
                y=event.getSceneY();
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    primaryStage.setX(event.getScreenX() - x);
                    primaryStage.setY(event.getScreenY() - y);
                }
            });

            primaryStage.initStyle(StageStyle.TRANSPARENT); //enlever les bordures par defaut de windows
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Lance l'application JavaFX
    }
}
