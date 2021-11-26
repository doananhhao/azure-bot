package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.constants.SkypeConstants;
import com.lexisnexis.risk.bot.dao.annotation.ReadData;
import com.lexisnexis.risk.bot.model.vm.CustomKudoPointTracking;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.util.BotUtil;
import com.microsoft.bot.builder.TurnContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListUserPointCommandService implements CommandService<String> {

    private final PointingService pointingService;

    public ListUserPointCommandService(PointingService pointingService) {
        this.pointingService = pointingService;
    }

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject(CommandConstants.KUDO_POINT_TRACKING.LIST_ALL, "To list all existing users");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message)
                && message.trim().endsWith(CommandConstants.KUDO_POINT_TRACKING.LIST_ALL);
    }

    @ReadData
    @Override
    public Result<String> execute(TurnContext turnContext) {
        try {
            List<CustomKudoPointTracking> trackingPoints = pointingService.getPointsOfCurrentMonth();
            if (!CollectionUtils.isEmpty(trackingPoints)) {
                List<String> collect = trackingPoints.stream()
                        .sorted((o1, o2) -> (int) o2.getEarnedPoint() - (int) o1.getEarnedPoint())
                        .map(u -> String.format(SkypeConstants.MESSAGE.REPORT_TEMPLATE,
                                u.getSkypeName(), u.getEarnedPoint(), u.getRemainPoint()))
                        .collect(Collectors.toList());
                return BotUtil.buildSuccessResult(String.join("\n\n", collect));
            }
        } catch (Exception ex) {
            return BotUtil.buildFailedResult("lỗi rồi nhé baby!" + ex.getMessage());
        }

        return BotUtil.buildSuccessResult("hẻm có gì hết, return default nè baby");
    }
}
