package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.constants.SkypeConstants;
import com.lexisnexis.risk.bot.dao.annotation.WriteData;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.util.BotUtil;
import com.lexisnexis.risk.bot.util.KudoPointingHelper;
import com.lexisnexis.risk.bot.util.UserFormatHelper;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Mention;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class KudoCommandService implements CommandService<String> {

    private final PointingService pointingService;

    public KudoCommandService(PointingService pointingService) {
        this.pointingService = pointingService;
    }

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject("kudo [@someone] [number_point]", "To kudo someone with a number of point");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message) && message.trim().matches(CommandConstants.KUDO_SOMEONE);
    }

    @WriteData
    @Override
    public Result<String> execute(TurnContext turnContext) {
        Mention mentionTo = KudoPointingHelper.getMentionedUser(turnContext);
        if (mentionTo == null) {
            return BotUtil.buildFailedResult("Sorry, we cannot recognize who you want to kudo.");
        } else {
            int point = KudoPointingHelper.extractPoint(turnContext, SkypeConstants.DEFAULT.KUDO_POINT);

            String skypeIdFrom = turnContext.getActivity().getFrom().getId();
            String skypeNameFrom = turnContext.getActivity().getFrom().getName();
            pointingService.savePointTracking(
                    skypeIdFrom,
                    skypeNameFrom,
                    mentionTo.getMentioned().getId(),
                    mentionTo.getMentioned().getName(),
                    point);

            return BotUtil.buildSuccessResult(String.format(SkypeConstants.MESSAGE.KUDO_SUCCESS_MESSAGE,
                    UserFormatHelper.forSkype(skypeIdFrom, skypeNameFrom), point, mentionTo.getText()));
        }
    }

}
