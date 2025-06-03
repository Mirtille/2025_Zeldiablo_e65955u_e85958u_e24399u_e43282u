package gameLaby.laby;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPrincipal extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ZELDIABLO");

        Button jouerBtn = new Button("Jouer");
        Button quitterBtn = new Button("Quitter");

        jouerBtn.setOnAction(e -> {
            try {
                MainLaby.main(null); // lance la vraie fenêtre de jeu
                primaryStage.close(); // ferme le menu
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        quitterBtn.setOnAction(e -> primaryStage.close());

        VBox root = new VBox(20, jouerBtn, quitterBtn);
        root.setStyle("-fx-padding: 40; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
