package UI;

import java.util.Scanner;

public class main {


	public static void main(String[] args) {
	Quiz quiz = new Quiz();
	Scanner userInput = new Scanner(System.in);
	User[] users = new User[2];
	System.out.println("Choose username: ");
	users[0] = new User(userInput.next());
	System.out.println("Username is " + users[0].getName());
	
	for (int i = 0; i<quiz.nrOfQuestions; i++) {
	System.out.println(quiz.questions[i]);
	System.out.println(quiz.options[i]);
	quiz.checkAnswer(users[0], userInput, i);
	System.out.println("Score: "  + users[0].score);
	}
	}
}
	
