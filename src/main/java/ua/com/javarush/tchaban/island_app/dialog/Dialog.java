package ua.com.javarush.tchaban.island_app.dialog;

import ua.com.javarush.tchaban.island_app.life_cycle.LifeCycle;

import java.util.Scanner;

public class Dialog {
    private static final String HOW_LONG = "Enter the number of days the island will exist(number from 1 to 100): ";
    private static final String FINISH_GAME = "Game over. We are waiting for you on the island again. Bye!";
    private static final String WARNING = "Invalid input, please try again.";
    private static final String NOT_A_NUMBER = "The entered data is not a number. Try again.";
    private static final String START = "start";
    private static final String EXIT = "exit";

    private final LifeCycle lifeCycle = new LifeCycle();
    Scanner scanner = new Scanner(System.in);

    public void start() {
        int amountOfDays;
        while (true) {
            printStartInfo();
            String choice = scanner.nextLine();

            if (START.equalsIgnoreCase(choice)) {
                amountOfDays = askHowLong();
                for (int i = 1; i <= amountOfDays; i++) {
                    System.out.println("Day " + i);
                    lifeCycle.newDay();
                }
                finishGame();
                break;

            } else if (EXIT.equalsIgnoreCase(choice)) {
                finishGame();
                break;

            } else {
                printAWarning();
            }
        }
    }

    private void printStartInfo() {
        System.out.println("""
                Welcome to game "Island". Enter:
                 start - to start the game;
                 exit - to exit the program.
                """);
    }

    private void printAWarning() {
        System.out.println(WARNING);
    }

    private int askHowLong() {
        int days;
        while (true) {
            System.out.println(HOW_LONG);
            String enter = scanner.nextLine();
            try {
                days = Integer.parseInt(enter);
                if (isCorrectAmount(days)) {
                    return days;
                } else {
                    printAWarning();
                }
            } catch (NumberFormatException e) {
                System.out.println(NOT_A_NUMBER);
            }
        }
    }

    private boolean isCorrectAmount(int days) {
        int MIN_AMOUNT_OF_DAYS = 0;
        int MAX_AMOUNT_OF_DAYS = 100;
        return (days > MIN_AMOUNT_OF_DAYS && days <= MAX_AMOUNT_OF_DAYS);
    }

    private void finishGame() {
        System.out.println(FINISH_GAME);
    }
}
