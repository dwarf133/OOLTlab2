
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static boolean first(String input) {
        try {
            Long.decode(input);
//            Long.parseLong(input, 16);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }

    private static String second(String input) {
        byte[] bytes = input.getBytes();
        ArrayList<Byte> max = new ArrayList<Byte>();
        ArrayList<Byte> temp = new ArrayList<Byte>();
        byte lastByte = bytes[0];
        temp.add(lastByte);
        for (int i=1; i < bytes.length; i++) {
            if (bytes[i] > lastByte) {
                temp.add(lastByte);
                lastByte = bytes[i];
            }
            else {
                if (max.size() < temp.size()) {
                    max = new ArrayList<Byte>(temp); // (ArrayList<Byte>) temp.clone();
                }
                temp.clear();
                lastByte = bytes[i];
            }
        }
        byte[] resultBytes = new byte[max.size()];
        for(int i = 0; i < max.size(); i++) {
            resultBytes[i] = max.get(i).byteValue();
        }
        return new String(resultBytes);
    }

    private static ResultThird third(String input) {
        String[] inputArray = input.split(" ");
        int count = 0;
        for(int i = 0; i < inputArray.length; i++ ){
            if (inputArray[i].startsWith("a")) {
                count += 1;
            }
            if (inputArray[i].startsWith("A")) {
                count += 1;
                inputArray[i] = inputArray[i].replaceFirst("A", "a");
            }
        }
        return new ResultThird(inputArray, count);
    }

    private static boolean fourth(String input) {
        Pattern pattern = Pattern.compile("abcdefghijklmnopqrstuv18340");
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    private static void runFirst(Scanner in) {
        System.out.print("Введите строку: ");
        String input = in.next();
        input = input.replaceAll("\\\\n", "");
        if (first(input)) {
            System.out.printf("%s - является 16-ричным числом", input);
        }
        else {
            System.out.printf("%s - не является 16-ричным числом", input);
        }
    }

    private static void runSecond(Scanner in) {
        System.out.print("Введите строку: ");
        String input = in.next();
        input = input.replaceAll("\\\\n", "");
        System.out.printf("Наибольшая возрастающая последовательность: %s", second(input));
    }

    private static void runThird(Scanner in) throws IOException {
        System.out.println("Введите строку:");
        BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
        String input = bi.readLine();
//        String input = in.nextLine();
        input = input.replaceAll("\\\\n", "");
        ResultThird res = third(input);
        StringBuilder sb = new StringBuilder();
        for (String str: res.arr) {
            sb.append(str).append(" ");
        }
        System.out.printf("Колличество слов с а: %d, текст: %s", res.count, sb.toString());
    }

    private static void runFourth(Scanner in) {
        System.out.print("Введите строку: ");
        String input = in.next();
        input = input.replaceAll("\\\\n", "");
        if (fourth(input)) {
            System.out.printf("%s - является abcdefghijklmnopqrstuv18340 строкой", input);
        }
        else {
            System.out.printf("%s - не является abcdefghijklmnopqrstuv18340 строкой", input);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите номер задания: ");
        int taskNumber = in.nextInt();
        switch (taskNumber) {
            case 1: runFirst(in);
            case 2: runSecond(in);
            case 3: runThird(in);
            case 4: runFourth(in);
        }
    }
}