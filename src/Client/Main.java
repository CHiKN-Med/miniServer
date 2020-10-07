package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        scene2(primaryStage);
        primaryStage.setTitle("QUIZ");
        primaryStage.show();
    }

    void scene2(Stage primaryStage){
        HBox pane = new HBox(10);
        Scene scene = new Scene(pane, 300, 275);
        primaryStage.setScene(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
