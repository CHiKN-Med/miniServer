package Client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        HBox textPane = new HBox(10);
        HBox knapPane = new HBox(10);
        HBox mainPane = new HBox(10);
        TextField ta = new TextField();
        TextField ta2 = new TextField();
        Button knap = new Button("press here");
        textPane.getChildren().addAll(ta, ta2);
        knapPane.getChildren().addAll(knap);
        mainPane.getChildren().addAll(textPane, knapPane);
        textPane.setAlignment(Pos.TOP_LEFT);
        knapPane.setAlignment(Pos.BOTTOM_RIGHT);
        Scene scene = new Scene(mainPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("QUIZ");
        primaryStage.show();
    }






    public static void main(String[] args) {
        launch(args);
    }
}
