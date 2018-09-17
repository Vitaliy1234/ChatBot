package iomanager;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QuestionsReader 
{
	public static Map<String, String> GetQuestionsAndAnswers(){
		BufferedReader reader;
		Map<String, String> questionsAndAnswers = new HashMap<String, String>();
		try {
			reader = new BufferedReader(new FileReader(
					"Questions.txt"));
			String line = reader.readLine();
			while (line != null) {
				int delimiterIndex = line.indexOf("|");
				String curQuestion = line.substring(0, delimiterIndex);
				String curAnswer = line.substring(delimiterIndex + 1);
				questionsAndAnswers.put(curQuestion, curAnswer);
				//System.out.println(curQuestion);
				//System.out.println(curAnswer);
				line = reader.readLine();
			}
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return questionsAndAnswers;
	}
}
