package chatbot;

import iomanager.QuestionsFromWeb;
import iomanager.QuestionsReader;

import java.util.*;

class Logic {
    static Map<String, User> Users = new HashMap<>();
    static String[] Topics = {"Случайные вопросы", "Математика", "Интересное"};
    private static int NumberOfPages = 299;

    static void startGame(String id) {
        User user = new User(id);
        Users.put(user.GetId(), user);
    }

    static String getQuestionForUser(int topic, String userId) throws Exception {
        User curUser = Users.get(userId);
        ArrayList<String> allQuestions = new ArrayList<>(curUser.Questions.keySet());
        String curQuestion = allQuestions.get(new Random().nextInt(allQuestions.size()));
        return curQuestion;
    }

    static void formQuestionsForUser(int topic, String userId) throws Exception {
        User curUser = Users.get(userId);
        curUser.State = status.AnswerTheQuestion;
        Map<String, Set<String>> questionsForUser;

        if (topic == 1)
            questionsForUser = QuestionsFromWeb.quizParser(new Random().nextInt(NumberOfPages));
        else
            questionsForUser = QuestionsReader.GetDataFromFile(Topics[topic - 1] + ".txt");

        curUser.Questions = questionsForUser;
    }

    static String[] checkUserAnswer(String userId, String question, String answer) {
        User curUser = Users.get(userId);
        String endGame = "noEnd";
        String answerResult = "";
        String userData = "";
        String winOrLose = "";

        if (answer.equals("\\выход"))
            answerResult = "0";
        else if (curUser.Questions.get(question).contains(answer.toLowerCase())) {
            answerResult = "1";
            curUser.ScoreUp();
            userData = Integer.toString(curUser.GetScore());
        }
        else {
            curUser.HealthDown();
            answerResult = "2";
            userData = Integer.toString(curUser.GetHealth());
        }

        if (curUser.Questions.size() == 0 || curUser.GetHealth() == 0) {
            endGame = "end";
            Users.remove(userId);

            if (curUser.GetHealth() == 0)
                winOrLose = "lose";
            else
                winOrLose = "win";
        }

        curUser.Questions.remove(question);
        return new String[] {answerResult, userData, endGame, winOrLose, Integer.toString(curUser.GetScore())};
    }

//    private static void gameCycle(Map<String, Set<String>> questionAndAnswer) throws Exception {
//        ArrayList<String> allQuestions = new ArrayList<>(questionAndAnswer.keySet());
//        String userAnswer = "";
//
//        while ( != 0 && !allQuestions.isEmpty()) {
//            int curIndexQuestion = new Random().nextInt(allQuestions.size());
//            //System.out.println(curIndexQuestion);
//            String curQuestion = allQuestions.get(curIndexQuestion);
//            allQuestions.remove(curIndexQuestion);
//
//            //userAnswer = ConsoleVersion.getAnswer(curQuestion);
//            int correct = 0;
//            Object addInfo;
//
//            if (userAnswer.toLowerCase().equals("\\выход")) {
//
//                addInfo = "";
//                System.exit(0);
//            }
//            else if (questionAndAnswer.get(curQuestion).contains(userAnswer.toLowerCase())) {
//                score += 1;
//                correct = 1;
//                addInfo = score;
//            }
//            else {
//                hp -= 1;
//                correct = 2;
//                addInfo = hp;
//            }
//
//            //ConsoleVersion.correctOrIncorrect(correct, addInfo);
//        }
//    }
}
