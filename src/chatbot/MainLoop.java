package chatbot;

import java.util.Map;

public class MainLoop {
    private static Map<String, User> Users;

    static void startGame(String id) {
        User user = new User(id);
        Users.put(user.GetId(), user);
    }

    public void gameCycle() {

    }
}
