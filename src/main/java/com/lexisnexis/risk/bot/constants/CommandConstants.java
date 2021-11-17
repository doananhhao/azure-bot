package com.lexisnexis.risk.bot.constants;

public class CommandConstants {

    public static final String HELP = "help";

    public class KUDO_POINT_TRACKING {
        public static final String LIST_ALL = "list";
    }

    // pattern: @anybot kudo @someone 10
    public static final String KUDO_SOMEONE_OLD = "(@\\w*\\s){1}kudo\\s(@(\\w*)\\s){1}\\d*";

    // pattern: anybot kudo someone 10
    public static final String KUDO_SOMEONE = "kudo\\s((\\w*)\\s)+\\d*";
}
