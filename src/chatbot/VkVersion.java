package chatbot;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;


public class VkVersion {
    private static Group group = new Group(171821026,
            "8c20fd3bb43b51bf31d8704d967360ab105082caccb01b1b7ad3c14d4f5f3131d6bedb9aac123765961eb");
    private static int authorId;
    private static Object userCallback;
    private static String[] situationStrings = {
            "Ладно, до встречи и удачи:3",
            "Правильный ответ! Количество правильных ответов: ",
            "К сожалению, ответ неверный. Количетсво жизней : "
    };

    public static void main(String[] args) throws Exception {


        group.onSimpleTextMessage(message -> {
            authorId = message.authorId();
            new Message()
                    .from(group)
                    .to(authorId)
                    .text("Хочешь сыграть - введи \"старт\".")
                    .send();

        });

        group.onCommand("/старт", message -> {
            try {
                startGame(group);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    static void startGame(Group group) throws Exception {
        new Message()
                .from(group)
                .to(authorId)
                .text("Ну давай сыграем.")
                .send();

        Logic.startGame(1);
    }

    static int chooseTheme(String[] themes){
        StringBuilder themeList = new StringBuilder();
        themeList.append("Выберите тему:\n");

        for(int i = 0; i < themes.length; i++)
            themeList.append(i + 1 + " " + themes[i] + "\n");

        new Message()
                .from(group)
                .to(authorId)
                .text(themeList)
                .send();

        group.onSimpleTextMessage(message ->
            userCallback = Integer.parseInt(message.getText())
        );
        return (int) userCallback;
    }

    static String answer(String question) {
        new Message()
                .from(group)
                .to(authorId)
                .text(question)
                .send();

        group.onSimpleTextMessage(message ->
                userCallback = message.getText()
        );
        return (String) userCallback;
    }

    static void correctOrIncorrect(int correct, Object addInfo) {
        new Message()
                .from(group)
                .to(authorId)
                .text(situationStrings[correct] + addInfo)
                .send();
    }

    static void gameResults(boolean win, int score) {
        String resultText = "";
        if (win)
            resultText = "Отлично поиграли! Вы выиграли! Количетсво очков : " + score;

        else
            resultText = "Увы, у вас больше нет жизней. Вы проиграли. Количетсво очков : " + score;

        new Message()
                .from(group)
                .to(authorId)
                .text(resultText + "\n" + "Аригато годзаимас!:3 До свидания.")
                .send();
    }
}
