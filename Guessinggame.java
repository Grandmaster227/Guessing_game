import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Guessinggame {
    private int randomNumber;
    private int remainingAttempts;
    private int points;
    private int maxNumber;
    private Scanner scanner;
    private static final String history_file = "history.txt";



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
        System.out.println("Choose the difficulty level:");
        System.out.println("1 - Easy (1-50, 15 attempts)");
        System.out.println("2 - Medium (1-100, 10 attempts)");
        System.out.println("3 - Hard (1-200, 5 attempts)");
        System.out.println("Type the number of the desired difficulty: ");


        int difficulty;

        try {
            difficulty = scanner.nextInt();
            if (difficulty < 1 || difficulty > 3) {
                System.out.println("Invalid choice. defaulting to medium difficulty");
                difficulty = 2;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. defaulting to medium difficulty.");
            difficulty = 2;
            scanner.next();
        }

        switch (difficulty) {
            case 1:
                maxNumber = 50;
                remainingAttempts = 15;
                points = 750;
                break;

            case 2:
                maxNumber = 100;
                remainingAttempts = 10;
                points = 1500;
                break;

            case 3:
                maxNumber = 200;
                remainingAttempts = 5;
                points = 1500;
                break;
        }

        Random random = new Random();

        this.randomNumber = random.nextInt(maxNumber) + 1;

        System.out.println("\nTry to guess a number between 1 and " + maxNumber + ".");

        while (remainingAttempts > 0) {
            System.out.println("\nRemaining attempts: " + remainingAttempts);
            System.out.println("Current points: " + points);
            System.out.println("Type a number: ");
            int attempt;
            try {
                attempt = scanner.nextInt();

                if (attempt < 1 || attempt > maxNumber) {
                    System.out.println("Type a number between 1 and " + maxNumber + ".");
                    continue;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input, type a int number");
                scanner.next();
                continue;
            }

            if (attempt == randomNumber) {
                System.out.println("Congrats! you have guessed the right number: " + randomNumber);
                System.out.println("Your final points: " + points);
                pointsLog();
                showHistory();
                break;
            } else if (attempt < randomNumber) {
                System.out.println("The number is higher than " + attempt + ".");
            } else {
                System.out.println("The number is lower than " + attempt + ".");
            }

            remainingAttempts--;
            points -= (difficulty == 1) ? 50 : (difficulty == 2) ? 100 : 300;
        }

        if (remainingAttempts == 0) {
            System.out.println("\n You lose! the number was: " + randomNumber);
            System.out.println("Your points: 0");
            pointsLog();
            showHistory();

        }




       
        scanner.close();
    }

    private void pointsLog() {
        System.out.println("Type your nickname: ");
        String nickname = scanner.next();

        try (FileWriter writer = new FileWriter(history_file, true)) {
            writer.write(nickname + " - " + points + "\n");

        } catch (IOException e) {
            System.out.println("Not able to save the points in history");
        }
    }

    private void showHistory() {
        System.out.println("\n--- Points History ---");


        try {
            File file = new File(history_file);
            if (!file.exists()) {
                System.out.println("Points not registred");
                return;
            }

            Scanner reader = new Scanner(file);
            List<String> points = new ArrayList<>();

            while (reader.hasNextLine()) {
                points.add(reader.nextLine());
            }

            Collections.sort(points, (a, b) -> {
                int pointsA = Integer.parseInt(a.split(" - ")[1]);
                int pointsB = Integer.parseInt(b.split(" - ")[1]);
                return pointsB - pointsA;
            });

            System.out.println("Top 5 points: ");
            for (int i = 0; i < Math.min(5, points.size()); i++) {
                System.out.println(points.get(i));
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Impossible to read points history");
        }
    }


}
