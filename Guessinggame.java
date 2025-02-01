import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;


public class Guessinggame {
    private int randomNumber;
    private int remainingAttempts;
    private Scanner scanner;


    public Guessinggame() {
        Random random = new Random();
        this.randomNumber = random.nextInt(100) + 1;
        this.remainingAttempts = 10;
        this.scanner = new Scanner(System.in);



    }

    public void start() {
        System.out.println("Welcome to the Guessing Game");
        System.out.println("Try to guess a number between 1 and 100");


        while (remainingAttempts > 0) {
            System.out.println("\nRemaining attempts: " + remainingAttempts);
            System.out.println("Type a number: ");
            int attempt = scanner.nextInt();

            if(attempt == randomNumber) {
                System.out.println("Congrats! you have guessed the right number: " + randomNumber);
                break;
            } else if (attempt < randomNumber) {
                System.out.println("The number is higher than " + attempt + ".");
            } else {
                System.out.println("The number is lower than " + attempt + ".");
            }

            remainingAttempts--;
        }

        if (remainingAttempts == 0) {
            System.out.println("\n You lose! the number was: " + randomNumber);
        }

        scanner.close();
    }
}
