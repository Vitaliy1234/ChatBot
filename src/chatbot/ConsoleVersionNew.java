package chatbot;

import iomanager.IOmanager;

import java.text.ParseException;
import java.util.Scanner;

public class ConsoleVersionNew {
    private static final Scanner input = new Scanner(System.in);
    private static String[] situationStrings = {
            "Ладно, до встречи и удачи:3",
            "Правильный ответ! Количество правильных ответов: ",
            "К сожалению, ответ неверный. Количетсво жизней : "
    };

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

        MainLoop.startGame("0");
        chooseTheme(Logic.Topics);

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

    static void correctOrIncorrect(int correct, Object addInfo) {
        System.out.println(situationStrings[correct] + addInfo);
        System.out.println();
    }

    static void gameResults(boolean win, int score) {
        if (win)
            System.out.println("Отлично поиграли! Вы выиграли! Количетсво очков : " + score);
        else
            System.out.println("Увы, у вас больше нет жизней. Вы проиграли. Количетсво очков : " + score);

        System.out.println("Аригато годзаимас!:3 До свидания.");
    }
}
