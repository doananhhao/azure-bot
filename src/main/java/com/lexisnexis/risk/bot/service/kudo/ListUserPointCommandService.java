package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.model.ct.CustomKudoPointTracking;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.lexisnexis.risk.bot.service.noname.KudoPointTrackingService;
import com.lexisnexis.risk.bot.service.noname.UserService;
import com.microsoft.bot.builder.TurnContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListUserPointCommandService implements CommandService<String> {

    private final KudoPointTrackingRepository kudoPointTrackingRepository;

    @Autowired
    private KudoPointTrackingService kudoPointTrackingService;

    @Autowired
    private UserService userService;

    public ListUserPointCommandService(KudoPointTrackingRepository kudoPointTrackingRepository) {
        this.kudoPointTrackingRepository = kudoPointTrackingRepository;
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

    @Override
    public Result<String> execute(TurnContext turnContext) {
        Calendar calendar = Calendar.getInstance();
        try {
            List<CustomKudoPointTracking> customKudoPointTrackings = kudoPointTrackingRepository.getKudoPointByMonthAndYear(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
            if (!CollectionUtils.isEmpty(customKudoPointTrackings)) {
                List<String> collect = customKudoPointTrackings.stream()
                        .sorted(new Comparator<CustomKudoPointTracking>() {
                            @Override
                            public int compare(CustomKudoPointTracking o1, CustomKudoPointTracking o2) {
                                return (int) o2.getEarnedPoint() - (int) o1.getEarnedPoint();
                            }
                        })
                        .map(u -> String.format("**%s** earned: %d, remain: %d", u.getSkypeName(), u.getEarnedPoint(), u.getRemainPoint()))
                        .collect(Collectors.toList());
                return new Result<>(true, String.join("\n\n", collect));
            }
        } catch (Exception ex) {
            return new Result<>(true, "lỗi rồi nhé baby!" + ex.getMessage());
        }

        return new Result<>(true, "hẻm có gì hết, return default nè baby");
    }
}
