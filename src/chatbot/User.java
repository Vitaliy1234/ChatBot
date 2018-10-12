package chatbot;

import java.util.Map;
import java.util.Set;

public class User {
    private int Score = 0;
    private int Health = 3;
    private String Id;
    public status State = status.ChooseTopic;
    private String Topic;
    private Map<String, Set<String>> Questions;

    User(String id) {
        Id = id;
    }

    public void HealthDown() {
        Health--;
    }

    public void ScoreUp() {
        Score++;
    }

    public int GetHealth() {
        return Health;
    }

    public int GetScore() {
        return Score;
    }

    public String GetId() {
        return Id;
    }

    public void SetTopic(String topic) {
        Topic = topic;
    }

    public void SetQuestions(Map<String, Set<String>> questions) {
        Questions = questions;
    }
}

enum status {
    ChooseTopic,
    AnswerTheQuestion,
    HelpReading
}

enum version {
    Console,
    Vk
}