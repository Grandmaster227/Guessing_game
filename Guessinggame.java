import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Guessinggame {
    private int randomNumber;
    private int remainingAttempts;
    private int points;
    private Scanner scanner;


    public Guessinggame() {
        Random random = new Random();
        this.randomNumber = random.nextInt(100) + 1;
        this.remainingAttempts = 10;
        this.points = 1000;
        this.scanner = new Scanner(System.in);



    }

    public void start() {
        System.out.println("Welcome to the Guessing Game");
        System.out.println("Try to guess a number between 1 and 100");


        while (remainingAttempts > 0) {
            System.out.println("\nRemaining attempts: " + remainingAttempts);
            System.out.println("Type a number: ");
            int attempt;
            try {
                attempt = scanner.nextInt();

                if (attempt < 1 || attempt > 100) {
                    System.out.println("Type a number between 1 and 100.");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, type a int number");
                scanner.next();
                continue;
            }

            if(attempt == randomNumber) {
                System.out.println("Congrats! you have guessed the right number: " + randomNumber);
                break;
            } else if (attempt < randomNumber) {
                System.out.println("The number is higher than " + attempt + ".");
            } else {
                System.out.println("The number is lower than " + attempt + ".");
            }

            remainingAttempts--;
            points -= 100;
        }

        if (remainingAttempts == 0) {
            System.out.println("\n You lose! the number was: " + randomNumber);
            System.out.println("Your points: 0");
        }

        scanner.close();
    }
}
