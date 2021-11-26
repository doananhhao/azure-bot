package com.lexisnexis.risk.bot.util;

import com.lexisnexis.risk.bot.constants.SkypeConstants;

public class UserFormatHelper {

    /**
     * Build user with skype format
     */
    public static String forSkype(String id, String name) {
        return String.format(SkypeConstants.USER_FORMAT, id, name);
    }

}
