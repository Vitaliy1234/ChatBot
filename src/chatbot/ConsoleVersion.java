package chatbot;

import java.util.Scanner;

public class ConsoleVersion {
    private static final Scanner input = new Scanner(System.in);
    static String ConsoleUserId = "0";

    public static void main(String[] args) throws Exception {
        System.out.println("Сыграем(Д\\Н)? Для вывода справки напиши \"\\хелб\". ");
        String userAnswer;

        while (true) {
            userAnswer = input.nextLine();

            if (userAnswer.toLowerCase().equals("д")) {
                System.out.println("Для выхода из игры в любой момент набери \"\\выход\"");
                System.out.println();
                break;
            }
            else if (userAnswer.toLowerCase().equals("\\хелб"))
                printHelp();
            else if (userAnswer.toLowerCase().equals("н")) {
                System.out.println("Не хочешь - как хочешь.");
                System.exit(0);
            }
            System.out.println();
            System.out.println("Нажми букаву \"Д\", если хочешь играть и букаву \"Н\", если не хочешь");
        }

        Logic.startGame(ConsoleUserId);
        int numberOfTopic = chooseTopic(Logic.Topics);
        Logic.formQuestionsForUser(numberOfTopic, ConsoleUserId);
        AskTheQuestConsole.askTheQuestions(ConsoleUserId);
    }

    private static int chooseTopic(String[] topics) {
        System.out.println("Выберите тему:");

        for(int i = 0; i < topics.length; i++)
            System.out.println(i + 1 + " " + topics[i]);

        String userAnswer = input.nextLine();

        while (true) {
            if (isInt(userAnswer))
                break;
            else if (userAnswer.equals("\\выход"))
                System.exit(0);
            else
                System.out.println("Попробуй еще раз.");

            userAnswer = input.nextLine();
        }
        return Integer.parseInt(userAnswer);
    }

    private static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException exc) {
            return false;
        }
    }

    static String getAnswer(String question) {
        System.out.println(question);
        return input.nextLine();
    }

    static void printCallback(SituationStrings callback, String addInfo) {
        System.out.println(Logic.reaction.get(callback) + addInfo);
        System.out.println();

        if (callback == SituationStrings.Exit)
            System.exit(0);

        if (callback == SituationStrings.Help)
            printHelp();
    }

    private static void printHelp() {
        System.out.println("Хочешь выйти - набери \"\\выход\"\n" +
                "При выборе темы набирай номер темы\n" +
                "Удачной игры!:33");
    }

    static void gameResults(int score, String resultString) {
        System.out.println(resultString + score);
        System.out.println("Аригато годзаимас!:3 До свидания.");
    }
}
