package iomanager;

import java.text.ParseException;

public class IOmanager {
    public static boolean correctChooseTopic(String input, int topics) throws ParseException {
        boolean result = false;
        while (!result) {
            int intInput = Integer.parseInt(input);
            if ((intInput <= topics) && (intInput > 0))
                result = true;
        }
        return result;
    }
}
