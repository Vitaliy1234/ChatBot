package chatbot;
import iomanager.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Start {

	public static void main(String[] args) {
		System.out.println("ׁûדנאול?");
		Map<String, String> qAndA = QuestionsReader.GetQuestionsAndAnswers();
		System.out.println(qAndA);
	}

}
