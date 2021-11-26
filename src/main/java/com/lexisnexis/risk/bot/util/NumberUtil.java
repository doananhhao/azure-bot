package com.lexisnexis.risk.bot.util;

public class NumberUtil {

    public static int parseInt(String number, int defaultValue) {
        try {
            return Integer.parseInt(number);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
