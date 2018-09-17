package console;

import java.util.Scanner;

public class DataFromUser {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String userAnswer = input.nextLine();
        System.out.println(userAnswer);
    }

}
