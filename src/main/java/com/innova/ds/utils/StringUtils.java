package com.innova.ds.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtils {

    public static int executeRegex(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    public static Boolean isNotBlank(String str) {
        return str != null && !str.trim().isEmpty();
    }

}
