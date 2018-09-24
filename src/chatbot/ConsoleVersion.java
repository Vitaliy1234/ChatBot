package chatbot;
import iomanager.*;

import java.util.*;

public class ConsoleVersion {

	private static final int numbersOfTopics = 4;
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		System.out.println("Сыграем?(Д\\Н)");
		String userAnswer = input.nextLine();

		while(true) {
			//Сравнение строк с разными кодировками. ЗАГУГЛИТЬ!
			if (userAnswer.toLowerCase().equals("д")){
				break;
			}
			else if (userAnswer.toLowerCase().equals("н")){
				System.out.println("Ладно, в другой раз! Удачи:3");
				System.exit(0);
			}

			System.out.println("Не та клавиша, попробуйте ещё раз)");
			userAnswer = input.nextLine();
		}

		System.out.println("На выбор " + numbersOfTopics + " тем(-ы): ");
		System.out.println("1. Случайные вопросы.\n" +
				"2. Математика.\n" +
				"3. Интересные факты.\n" +
                "4. Случайные вопросы (при наличии интернета).");

		userAnswer = input.nextLine();
		String curFileName = "";
		boolean networkQuestions = false;

		while(true) {
			switch (userAnswer){
				case "1":
					curFileName = "Random";
					break;
				case "2":
					curFileName = "Maths";
					break;
				case "3":
					curFileName = "Interesting";
					break;
                case "4":
                    networkQuestions = true;
                    break;
				default:
					System.out.println("Мимо, попробуй ещё раз)");
					userAnswer = input.nextLine();
					continue;
			}

			break;
		}

        Map<String, Set<String>> qAndA = new HashMap<>();
        List<String> allQuestions = new ArrayList<>();

        //qAndA = Questions and Answers.
		if (!networkQuestions){
            qAndA = QuestionsReader.GetDataFromFile("Questions/" + curFileName + ".txt");
        }
        else {
            qAndA = GetNetQuestions();
        }

        allQuestions = new ArrayList<>(qAndA.keySet());

//		System.out.println(allQuestions);
		System.out.println();
		System.out.println("Чтобы выйти напишите в любой момент \"\\выход\"");
		System.out.println();

		int score = 0;
		int hp = 3;

		System.out.println("Количетсво жизней : " + hp);

		while (hp != 0 && !allQuestions.isEmpty()) {
            int curIndexQuestion = new Random().nextInt(allQuestions.size());
            //System.out.println(curIndexQuestion);
            String curQuestion = allQuestions.get(curIndexQuestion);
            System.out.println(curQuestion);
            allQuestions.remove(curIndexQuestion);

			userAnswer = input.nextLine();

			if (userAnswer.toLowerCase().equals("\\выход")) {
				System.out.println("Ладно, до встречи и удачи:3");
				System.exit(0);
			}
			else if (qAndA.get(curQuestion).contains(userAnswer.toLowerCase())) {
				score += 1;
				System.out.println("Правильный ответ! Количество правильных ответов: " + score);
			}
			else {
				hp -= 1;
				System.out.println("К сожалению, ответ неверный. Количетсво жизней : " + hp);
			}

			System.out.println();
		}

		if (hp == 0) {
			System.out.println("Вы проиграли, но держались молодцом! Количество очков : " + score);
		}
		else
			System.out.println("Отлично поиграли! Вы выиграли! Количетсво очков : " + score);

		System.out.println("Аригато годзаимас!:3 До свидания.");
	}

	private static Map<String, Set<String>> GetNetQuestions() throws Exception {
        int curNumPage = new Random().nextInt(299);
	    return QuestionsFromWeb.quizParser(curNumPage);
    }
}
