package com.lexisnexis.risk.bot.constants;

public class SkypeConstants {

    public static final String BOLD = "**%s**";
    public static final String ITALIC = "*%s*";
    public static final String USER_FORMAT = "<at id=\"%s\">%s</at>";

    public static class MESSAGE {
        public static final String KUDO_SUCCESS_MESSAGE = "%s kudo %d to %s!";
        public static final String REPORT_TEMPLATE = "**%s** earned: %d, remain: %d";
    }

    public static class DEFAULT {
        public static final int KUDO_POINT = 0;
    }

}
