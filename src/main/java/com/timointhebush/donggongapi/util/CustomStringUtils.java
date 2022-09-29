package com.timointhebush.donggongapi.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class CustomStringUtils {
    private static final Random random = new Random();
    private static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";

    public static synchronized String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static String replaceWhiteSpaceAndSpecialChar(String str) {
        return str
                .replaceAll("[\\\\/:*?\"<>|]", "")
                .replace(" ", "_");
    }
}
