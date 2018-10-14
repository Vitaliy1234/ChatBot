package chatbot;

import javafx.util.Pair;

import java.util.Scanner;

public class ConsoleVersion {
    private static final Scanner input = new Scanner(System.in);
    private static String[] situationStrings = {
            "Ладно, до встречи и удачи:3",
            "Правильный ответ! Количество правильных ответов: ",
            "К сожалению, ответ неверный. Количетсво жизней : "
    };
    static String ConsoleUserId = "0";

    public static void main(String[] args) throws Exception {
        System.out.println("Сыграем?(Д\\Н)");

        while (true) {
            String userAnswer = input.nextLine();

            if (userAnswer.toLowerCase().equals("д")) {
                break;
            }
            else if (userAnswer.toLowerCase().equals("н")) {
                System.out.println("Не хочешь - как хочешь.");
                System.exit(0);
                break;
            }
            else
                System.out.println("Нажми букаву \"Д\", если хочешь играть и букаву \"Н\", если не хочешь");
        }

        Logic.startGame(ConsoleUserId);
        int numberOfTopic = chooseTheme(Logic.Topics);
        Logic.formQuestionsForUser(numberOfTopic, ConsoleUserId);
        AskTheQuestConsole.askTheQuestions(ConsoleUserId, numberOfTopic);
//        String curQuestion = Logic.getQuestionForUser(numberOfTopic, ConsoleUserId);
//        String userAnswer = getAnswer(curQuestion);
//        String[] resultOfChecking = Logic.checkUserAnswer(ConsoleUserId, curQuestion, userAnswer);
//        correctOrIncorrect(Integer.parseInt(resultOfChecking[0]), resultOfChecking[1]);
//
//        if (resultOfChecking[2].equals("end")) {
//            boolean win = false;
//
//            if (resultOfChecking[3].equals("win"))
//                win = true;
//
//            gameResults(win, resultOfChecking[4]);
//        }
    }

    static int chooseTheme(String[] themes) {
        System.out.println("Выберите тему:");
        int numberOfTopics = themes.length;

        for(int i = 0; i < numberOfTopics; i++)
            System.out.println(i + 1 + " " + themes[i]);

        return Integer.parseInt(input.nextLine());
    }

    static String getAnswer(String question) {
        System.out.println(question);
        return input.nextLine();
    }

    static void correctOrIncorrect(int correct, String addInfo) {
        System.out.println(situationStrings[correct] + addInfo);
        System.out.println();
    }

    static void gameResults(boolean win, String score) {
        if (win)
            System.out.println("Отлично поиграли! Вы выиграли! Количетсво очков : " + score);
        else
            System.out.println("Увы, у вас больше нет жизней. Вы проиграли. Количетсво очков : " + score);

        System.out.println("Аригато годзаимас!:3 До свидания.");
    }
}
