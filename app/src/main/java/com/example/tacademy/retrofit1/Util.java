package com.example.tacademy.retrofit1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Tacademy on 2015-11-11.
 */
public class Util {
    public final static String REGEX_HANGUL = "^[ㄱ-ㅎㅏ-ㅣ가-힣]{2,30}$";
    public final static String REGEX_EMAIL = "^[_a-zA-Z0-9-\\\\.]+@[\\\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
    public final static String REGEX_PHONE = "^01([0|1|6|7|8|9]?)+([0-9]{7,8})$";
    public final static String REGEX_PASSWORD = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+)(?=.*[!@#$%^*+=-]*).{8,16}$";

    public static boolean checkEmailRegularExpressions(String email) {
        String regExpStr = "^[a-zA-Z0-9]+[@][a-zA-Z0-9]+[\\.][a-zA-Z0-9]+$";
        Pattern pattern = Pattern.compile(regExpStr);
        Matcher match = pattern.matcher(email);
        return match.find();
    }

    public static boolean checkPasswrodRegularExpressions(String id) {
        String regExpStr = "^[A-Za-z0-9]{6,12}$";
        Pattern pattern = Pattern.compile(regExpStr);
        Matcher match = pattern.matcher(id);
        return match.find();
    }
}
