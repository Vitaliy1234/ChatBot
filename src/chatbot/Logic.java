package chatbot;

import iomanager.QuestionsFromWeb;
import iomanager.QuestionsReader;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Logic {
    public static String[] Topics = {"Случайные вопросы", "Математика", "Интересное"};
    private static int NumberOfPages = 299;

    static boolean chooseTopicHandler(String chosenTopic) throws Exception {

    }

    private static void gameCycle(Map<String, Set<String>> questionAndAnswer) throws Exception {
        ArrayList<String> allQuestions = new ArrayList<>(questionAndAnswer.keySet());
        String userAnswer = "";

        while ( != 0 && !allQuestions.isEmpty()) {
            int curIndexQuestion = new Random().nextInt(allQuestions.size());
            //System.out.println(curIndexQuestion);
            String curQuestion = allQuestions.get(curIndexQuestion);
            allQuestions.remove(curIndexQuestion);

            //userAnswer = ConsoleVersionNew.getAnswer(curQuestion);
            int correct = 0;
            Object addInfo;

            if (userAnswer.toLowerCase().equals("\\выход")) {

                addInfo = "";
                System.exit(0);
            }
            else if (questionAndAnswer.get(curQuestion).contains(userAnswer.toLowerCase())) {
                score += 1;
                correct = 1;
                addInfo = score;
            }
            else {
                hp -= 1;
                correct = 2;
                addInfo = hp;
            }

            //ConsoleVersionNew.correctOrIncorrect(correct, addInfo);
        }
    }
}
