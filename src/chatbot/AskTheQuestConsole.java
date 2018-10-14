package chatbot;

import static chatbot.ConsoleVersion.ConsoleUserId;
import static chatbot.ConsoleVersion.*;

class AskTheQuestConsole {

    static void askTheQuestions(String userId, int numberOfTopic) throws Exception {
        User curUser = Logic.Users.get(userId);

        while(curUser.Questions.size() != 0) {
            String curQuestion = Logic.getQuestionForUser(numberOfTopic, ConsoleUserId);
            String userAnswer = getAnswer(curQuestion);
            String[] resultOfChecking = Logic.checkUserAnswer(ConsoleUserId, curQuestion, userAnswer);
            correctOrIncorrect(Integer.parseInt(resultOfChecking[0]), resultOfChecking[1]);

            if (resultOfChecking[2].equals("end")) {
                boolean win = false;

                if (resultOfChecking[3].equals("win"))
                    win = true;

                gameResults(win, resultOfChecking[4]);
                break;
            }
        }
    }
}
