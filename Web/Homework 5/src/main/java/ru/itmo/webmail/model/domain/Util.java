package ru.itmo.webmail.model.domain;

import java.util.Random;

public class Util {

    public static Random random = new Random();

    public static final String EMAIL_PATTERN = "[a-z0-9]+@[a-z0-9]+[.][a-z0-9]+";

    private static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3];
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public static String[] randomWords = generateRandomWords(1000);

}
