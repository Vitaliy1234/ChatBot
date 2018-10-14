package chatbot;

import iomanager.QuestionsFromWeb;
import iomanager.QuestionsReader;
import javafx.util.Pair;

import java.util.*;

class Logic {
    static Map<String, User> Users = new HashMap<>();
    static String[] Topics = {"Случайные вопросы", "Математика", "Интересное"};
    static Map<SituationStrings, String> reaction;

    static String[] gameResults = {
            "Отлично поиграли! Вы выиграли! Количетсво очков : ",
            "Увы, у вас больше нет жизней. Вы проиграли. Количетсво очков : "
    };
    private static int NumberOfPages = 299;

    static void startGame(String id) {
        User user = new User(id);
        Users.put(user.GetId(), user);
        reaction = new HashMap<>();
        reaction.put(SituationStrings.Exit, "Ладно, до встречи и удачи:3");
        reaction.put(SituationStrings.RightAnswer, "Правильный ответ! Количество правильных ответов: ");
        reaction.put(SituationStrings.WrongAnswer, "К сожалению, ответ неверный. Количетсво жизней : ");
    }

    static void formQuestionsForUser(int topic, String userId) throws Exception {
        User curUser = Users.get(userId);
        Map<String, Set<String>> questionsForUser;

        if (topic == 1)
            questionsForUser = QuestionsFromWeb.quizParser(new Random().nextInt(NumberOfPages));
        else
            questionsForUser = QuestionsReader.GetDataFromFile("Questions/" + Topics[topic - 1] + ".txt");

        curUser.Questions = questionsForUser;
    }

    static Pair<SituationStrings, String> checkUserAnswer(String userId, String question, String answer) {
        User curUser = Users.get(userId);
        SituationStrings answerResult;
        String userData = "";

        if (answer.equals("\\выход"))
            answerResult = SituationStrings.Exit;
        else if (answer.equals("\\хелб"))
            answerResult = SituationStrings.Help;
        else if (curUser.Questions.get(question).contains(answer.toLowerCase())) {
            curUser.ScoreUp();
            answerResult = SituationStrings.RightAnswer;
            userData = Integer.toString(curUser.GetScore());
        }
        else {
            curUser.HealthDown();
            answerResult = SituationStrings.WrongAnswer;
            userData = Integer.toString(curUser.GetHealth());
        }

        curUser.Questions.remove(question);
        return new Pair<> (answerResult, userData);
    }
}

enum SituationStrings {
    Help,
    Exit,
    RightAnswer,
    WrongAnswer
}