import logic.QuestionsHandler;

import java.util.Scanner;

public class Main {

    private static QuestionsHandler questionsHandler = new QuestionsHandler();

    public static void main(String[] args) {

        String input;
        Scanner scanner = new Scanner(System.in);

        // Main entry point.
        do {
            System.out.println("--------------------------------------------------------------");
            System.out.println("Welcome to questions");
            System.out.println("Choose from the below options:");
            System.out.println("1 - Ask question");
            System.out.println("2 - Add question");
            System.out.println("x - Exit\n");
            System.out.print("> ");
            input = scanner.nextLine();

            switch (input) {

                case "1":
                    System.out.println("You want to ask a question.");
                    askQuestion(scanner);
                    break;

                case "2":
                    System.out.println("You want to add a question.");
                    addQuestion(scanner);
                    break;

                case "x":
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Incorrect input.");

            }

        } while(!input.equals("x"));
    }

    /**
     * Ask a question mode.
     * Option to ask another question.
     *
     * @param scanner
     */
    private static void askQuestion(Scanner scanner) {

        String input;

        do {
            System.out.println("---------------------Ask-Question-----------------------------");
            System.out.print("> ");

            input = scanner.nextLine();

            questionsHandler.getAnswers(input).forEach(answer -> {
                System.out.println("- " + answer);
            });

            System.out.println("");
            System.out.println("Would you like to ask another question?");
            System.out.println("y - Yes");
            System.out.println("n - No");

            System.out.print("> ");

            input = scanner.nextLine();

            while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                System.out.println("Unknown choice: " + input);

                System.out.print("> ");

                input = scanner.nextLine();
            }

        } while (!input.equalsIgnoreCase("n"));
    }

    /**
     * Add question mode.
     * Option to add another question.
     *
     * @param scanner
     */
    private static void addQuestion(Scanner scanner) {

        String input;
        boolean saveResult;

        do {
            System.out.println("---------------------Add-Question-----------------------------");
            System.out.print("> ");

            input = scanner.nextLine();

            saveResult = questionsHandler.addQuestion(input);
            if (!saveResult) {
                System.out.println("Error in adding question, please try again.");
            }

            System.out.println("");
            System.out.println("Would you like to add another question?");
            System.out.println("y - Yes");
            System.out.println("n - No");

            System.out.print("> ");

            input = scanner.nextLine();

            while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n")) {
                System.out.println("Unknown choice: " + input);

                System.out.print("> ");

                input = scanner.nextLine();
            }

        } while (!input.equalsIgnoreCase("n"));
    }

}
