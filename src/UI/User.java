package UI;

public class User {
String username; 
int score;
// Scanner userInput = new Scanner(System.in);

User(String username){
setName(username);
}

String getName() {
return username;
}

void setName(String username) {
this.username=username;
}

int getScore() {
return score;  }

void setScore(int score) {
this.score=score;
}


}
