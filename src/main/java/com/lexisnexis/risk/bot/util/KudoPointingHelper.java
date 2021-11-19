package com.lexisnexis.risk.bot.util;

import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Mention;

import java.util.List;

public class KudoPointingHelper {

    /**
     * Gets the mentioned user in group
     * E.g: @Bot kudo @User 10
     *      => length = 2
     */
    public static Mention getMentionedUser(TurnContext turnContext) {
        List<Mention> mentions = turnContext.getActivity().getMentions();
        if (mentions.size() == 2) {
            return mentions.get(1);
        }
        return null;
    }

    /**
     * E.g: @Bot kudo @User 10
     *      => point = 10
     *      => last position
     */
    public static int extractPoint(TurnContext turnContext, int defaultPoint) {
        String[] attributes = turnContext.getActivity().getText().split(" ");
        return NumberUtil.parseInt(attributes[attributes.length - 1], defaultPoint);
    }

}
