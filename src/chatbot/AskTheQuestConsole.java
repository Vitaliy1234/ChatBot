package chatbot;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Random;

import static chatbot.ConsoleVersion.*;

class AskTheQuestConsole {

    static void askTheQuestions(String userId) {
        var curUser = Logic.Users.get(userId);
        var allQuestions = new ArrayList<>(curUser.Questions.keySet());
        var result = Logic.gameResults[0];
        var curQuestion = "";
        var userAnswer = "";
        Pair<SituationStrings, String> resultOfChecking;
        curUser.State = status.AnswerTheQuestion;

        while(curUser.Questions.size() != 0 && curUser.GetHealth() != 0) {
            curQuestion = allQuestions.get(new Random().nextInt(allQuestions.size()));
            userAnswer = getAnswer(curQuestion);
            resultOfChecking = Logic.checkUserAnswer(ConsoleUserId, curQuestion, userAnswer);
            printCallback(resultOfChecking.getKey(), resultOfChecking.getValue());
        }

        Logic.Users.remove(userId);

        if (curUser.GetHealth() == 0)
            result = Logic.gameResults[1];

        gameResults(curUser.GetScore(), result);
    }
}
