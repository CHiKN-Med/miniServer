package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;

public class Controller {
    @FXML
    private GridPane rootPane;

    @FXML
    private TextField userName;

    public void signInButton(ActionEvent actionEvent) throws IOException {
        GridPane root2 = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        rootPane.getChildren().setAll(root2);
        String UserName = userName.getText();
        System.out.println(UserName);
    }

}
