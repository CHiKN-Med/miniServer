package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Main extends Application {
    DataOutputStream toServer = null;
    DataInputStream fromServer = null;
    String theMessage = null;

    boolean lobbyLoop = false;
    boolean gameLoop = false;

    @Override
    public void start(Stage primaryStage) throws Exception{


        // SCENE 1

        // Panes - >
        HBox textPane = new HBox();
        HBox knapPane = new HBox(10);
        HBox mainPane = new HBox(10);

        // Components
        TextField ta = new TextField();
        Text t = new Text("Enter username");
        Button knap = new Button("press here");

        // Adding compenents
        textPane.getChildren().addAll(t,ta);
        textPane.setMaxSize(300,275);
        knapPane.getChildren().addAll(knap);
        knapPane.setMaxSize(300,275);
        mainPane.getChildren().addAll(textPane, knapPane);

        // Alignement
        textPane.setAlignment(Pos.CENTER);
        knapPane.setAlignment(Pos.BOTTOM_CENTER);
        mainPane.setAlignment(Pos.CENTER);

        // Setting new Scene
        Scene scene = new Scene(mainPane, 500, 275);
        primaryStage.setScene(scene);
        primaryStage.setTitle("QUIZ");
        primaryStage.show();

        // SCENE 2 --

        // Panes - >
        HBox mainPane2 = new HBox(10);

        // Components
        Text ta2 = new Text();
        HBox welcomeText = new HBox(ta2);
        welcomeText.setMaxSize(100,30);

        TextArea chatBox = new TextArea(); chatBox.setEditable(false); chatBox.setMaxSize(200,300);
        HBox chatText = new HBox(chatBox);

        TextField sendMessageField = new TextField();
        HBox sendMessage = new HBox(sendMessageField);

        Button sendText = new Button("Send");
        Button startGame = new Button("Start Game");
        HBox buttonBox = new HBox(sendText, startGame);

        // Adding compenents
        mainPane2.getChildren().addAll(welcomeText, chatText,sendMessage,buttonBox);

        // Setting new Scene
        Scene scene2 = new Scene(mainPane2, 600, 275);

        // SCENE 3 --

        // Panes - >
        HBox mainPane3 = new HBox(10);

        // Components
        TextArea quizText = new TextArea(); chatBox.setEditable(false); chatBox.setMaxSize(200,300);
        HBox quizBox = new HBox(quizText);

        Button sendAnswer1 = new Button("1");
        Button sendAnswer2 = new Button("2");
        Button sendAnswer3 = new Button("3");
        Button sendAnswer4 = new Button("4");
        HBox answerBox = new HBox(sendAnswer1, sendAnswer2, sendAnswer3, sendAnswer4);

        // Adding compenents
        mainPane3.getChildren().addAll(quizBox, answerBox);

        // Setting new Scene
        Scene scene3 = new Scene(mainPane3, 600, 275);


        // write thread
        new Thread(() -> {
        // buttons in scene 1 (welcome scene) -- >
        knap.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setNewScene(scene2, primaryStage);
                String userName = ta.getText();
                ta2.setText("\nWelcome " + userName + "\n");
                try {
                    toServer.writeUTF(userName);
                    toServer.flush(); // send the message
                    chatBox.appendText(theMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        });

        //button in scene 2 (lobby)
        sendText.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeUTF(sendMessageField.getText());
                    toServer.flush(); // send the message
                    sendMessageField.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeUTF("STARTTHEGAME");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //button in scene 3 (game)
        sendAnswer1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeBoolean(true);
                    toServer.writeInt(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sendAnswer2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeBoolean(true);
                    toServer.writeInt(2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sendAnswer3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeBoolean(true);
                    toServer.writeInt(3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sendAnswer4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    toServer.writeBoolean(true);
                    toServer.writeInt(4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        }).start();



        try {

            // Create a socket to connect to the server
            Socket socket = new Socket("localhost", 8000);


            // Create an input stream to receive data from the server
            fromServer = new DataInputStream(socket.getInputStream());

            // Create an output stream to send data to the server
            toServer = new DataOutputStream(socket.getOutputStream());


            // read thread
            new Thread(() -> {

                while(true){
                    try {
                        String serverMessage = fromServer.readUTF();
                        if(serverMessage.equalsIgnoreCase("STARTTHEGAME"))
                        {
                            Platform.runLater(() -> {
                                setNewScene(scene3, primaryStage);
                            });
                            break;
                        }
                        chatBox.appendText(serverMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }



                while(true){
                    try {
                        String serverMessage = fromServer.readUTF();
                        if(serverMessage.equalsIgnoreCase("STARTTHEGAME")){
                            serverMessage = fromServer.readUTF();
                        }
                        quizText.appendText(serverMessage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }).start();



    }
        catch (IOException ex) {
            //  ta.appendText(ex.toString() + '\n');
        }



    }



    public static void main(String[] args) {
        launch(args);
    }


    public void setNewScene(Scene scene, Stage stage){
        stage.setScene(scene);
    }



}

