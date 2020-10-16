package miniproject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

public class Server {
  ArrayList<UserThread> users;
  private ServerSocket serverSocket;
  private int port;


  public Server(){
    port = 8000;
    users = new ArrayList<>();
  }

  public static void main(String[] args) {
    Server server = new Server();
    server.initiateServer();
  }


  public void initiateServer(){
      try {
        // Create a server socket
        serverSocket = new ServerSocket(port);

        System.out.println("Server started at " + new Date() + "\n");
        while (true) {
          // Listen for a connection request
          Socket socket = serverSocket.accept();
          // Create data input and output streams
          UserThread user = new UserThread(this, socket);
          users.add(user);
          user.start();
          // ta.appendText("\n" + users.size());
          //});
        }

      }
      catch(IOException ex) {
        ex.printStackTrace();
      }


    }

  public void sendAll(String message){
    for(UserThread userThread : users){
      userThread.sendMessage(message);
    }
  }

  public ArrayList<UserThread> getUsers() {
    return users;
  }



  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */

}

class UserThread extends Thread{
  private DataInputStream dis;
  private DataOutputStream dos;
  private Socket s;
  private Server server;
  String name;
  int score;

  public UserThread(Server server, Socket s) {
    this.s = s;
    this.server = server;
    score=0;
  }


  @Override
  public void run()
  {
    try {
    dis = new DataInputStream(s.getInputStream());
    dos = new DataOutputStream(s.getOutputStream());
    System.out.println("\nThread has started");
    setUserName(readMessage());
    //System.out.println("\nNew user joined: " + name);
    server.sendAll("\nNew user joined: " + name);
    while (true) {
    String clientMessage = readMessage();
    server.sendAll("\n" + getUserName() + ": " + clientMessage);
    }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

  int getScore(){
    return score;
  }

  void setScore(int score){
    this.score=score;
  }

  String getUserName(){
    return name;
  }

  void setUserName(String name){
    this.name=name;
  }

  public void sendMessage(String m){
    try {
      dos.writeUTF(m);
      dos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String readMessage(){
    String m = null;
    try {
      m = dis.readUTF();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return m;
  }


  }

