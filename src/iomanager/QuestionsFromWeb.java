package iomanager;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class QuestionsFromWeb {

    public static Map<String, Set<String>> quizParser(int pageNumber) throws Exception {

        Map<String, Set<String>> questionAndAnswer = new HashMap<>();
        URL quizSite;
        if (pageNumber == 0)
            quizSite = new URL("https://baza-otvetov.ru/categories/view/1/");
        else
            quizSite = new URL("https://baza-otvetov.ru/categories/view/1/" + pageNumber + "0");
        BufferedReader in = new BufferedReader(new InputStreamReader(quizSite.openStream()));

        String inputLine;
        StringBuilder quizSiteText = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            quizSiteText.append(inputLine);
        in.close();
        String quizText = quizSiteText.toString();
        String categories = quizText.substring(quizText.indexOf("q-list__table") + "q-list__table".length() + 2);
        String cats = categories.substring(0, categories.indexOf("</table>"));

        Pattern quotPattern = Pattern.compile("&quot;");
        Matcher quotMatcher = quotPattern.matcher(cats);
        cats = quotMatcher.replaceAll("");

        Pattern questionPattern = Pattern.compile("<a href.+?<\\/a>");
        Matcher m = questionPattern.matcher(cats);

        Pattern varianstsPattern = Pattern.compile("quiz-answers.+?<");
        Matcher variantsMatcher = varianstsPattern.matcher(cats);

        Pattern answerPattern = Pattern.compile("<td>\\D+?<\\/td>");
        Matcher answerMatcher = answerPattern.matcher(cats);

        while (m.find() && answerMatcher.find() && variantsMatcher.find()) {
            String curLine = m.group();
            String curVariants = variantsMatcher.group();
            String curLineAns = answerMatcher.group();

            String quest = curLine.substring(curLine.indexOf(">") + 1, curLine.lastIndexOf("<"));
            String variants = curVariants.substring(curVariants.indexOf(":") + 2, curVariants.lastIndexOf("<"));
            String ans = curLineAns.substring(curLineAns.indexOf(">") + 1, curLineAns.lastIndexOf("<"));

            variants = variants.replaceAll("\\s", " ");
            ArrayList<String> variantsList = new ArrayList<>(Arrays.asList(variants.split(",")));
            variantsList.add(ans);
            Collections.shuffle(variantsList);
            Set<String> answerSet = new HashSet<>();
            answerSet.add(ans.toLowerCase());
            questionAndAnswer.put(quest + variantsList, answerSet);
        }

        return questionAndAnswer;
    }
}
