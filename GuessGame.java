package number;

import java.util.Random;
import java.util.Scanner;

public class GuessGame 
{
    private static final int MAX_ATTEMPTS = 10; 
    private static int score = 0; 

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        do 
        {
            playAgain = playRound(scanner);
        } while (playAgain);

        System.out.println("Thank you for playing! Your final score is: " + score);
        scanner.close();
    }

    private static boolean playRound(Scanner scanner) 
    {
        Random random = new Random();
        int numberToGuess = random.nextInt(100) + 1;
        int attempts = 0;
        boolean hasGuessedCorrectly = false;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100. Can you guess it?");

        while (attempts < MAX_ATTEMPTS && !hasGuessedCorrectly) {
            System.out.print("Enter your guess (Attempt " + (attempts + 1) + " of " + MAX_ATTEMPTS + "): ");
            int userGuess = scanner.nextInt();
            attempts++;

            if (userGuess < 1 || userGuess > 100)
            {
                System.out.println("Please enter a number between 1 and 100.");
                attempts--;
                continue;
            }

            if (userGuess < numberToGuess)
            {
                System.out.println("Too low! Try again.");
            } else if (userGuess > numberToGuess) 
            {
                System.out.println("Too high! Try again.");
            } else 
            {
                hasGuessedCorrectly = true;
                System.out.println("Congratulations! You've guessed the number: " + numberToGuess);
                int points = calculatePoints(attempts);
                score += points;
                System.out.println("You earned " + points + " points!");
            }
        }

        if (!hasGuessedCorrectly) 
        {
            System.out.println("Sorry! You've used all your attempts. The number was: " + numberToGuess);
        }

        return playAgain(scanner);
    }

    private static int calculatePoints(int attempts) 
    {
        return Math.max(0, MAX_ATTEMPTS - attempts + 1); // Points based on remaining attempts
    }

    private static boolean playAgain(Scanner scanner)
    {
        System.out.print("Do you want to play again? (yes/no): ");
        String response = scanner.next().toLowerCase();
        return response.equals("yes");
    }
}
