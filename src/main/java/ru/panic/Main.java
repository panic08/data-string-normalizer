package ru.panic;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please enter the path to your .txt file");

        Scanner scanner = new Scanner(System.in);

        String firstFileName = scanner.nextLine();

        List<String> firstFileStrings = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(firstFileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                firstFileStrings.add(cleanString(line));
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File newFile = new File("normalizedFile.txt");

        try {
            FileWriter fileWriter = new FileWriter(newFile);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String s : firstFileStrings) {
                String[] cleanedStrings = s.split("\\:");

                if (cleanedStrings.length > 1 && !cleanedStrings[0].equalsIgnoreCase("unknown") &&
                        !cleanedStrings[0].contains("steamcommunity") &&
                        !cleanedStrings[0].contains("http") &&
                        !cleanedStrings[0].equals("android") &&
                        !cleanedStrings[1].contains("json") && cleanedStrings[0].length() >= 4 && cleanedStrings[1].length() >= 5) {

                    System.out.println(s);
                    bufferedWriter.write(s);
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String cleanString(String input) {
        // Создаем регулярное выражение для поиска символов, которые нужно оставить
        String regex = "[a-zA-Z0-9!@#$%^&*:.?()\"%\\-=+*&^$#@!'\\[\\]{}\\(\\),/|><~_#\\s\\\\]";

        // Удаляем все символы, кроме указанных в регулярном выражении
        return input.replaceAll("[^" + regex + "]", "");
    }
}