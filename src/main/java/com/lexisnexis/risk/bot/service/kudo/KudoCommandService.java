package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.lexisnexis.risk.bot.service.noname.KudoPointTrackingService;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Mention;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class KudoCommandService implements CommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KudoPointTrackingRepository kudoPointTrackingRepository;

    @Autowired
    private KudoPointTrackingService kudoPointTrackingService;

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject("kudo [@someone] [number_point]", "To kudo someone with a number of point");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message) && message.trim().matches(CommandConstants.KUDO_SOMEONE);
    }

    @Override
    public Result execute(TurnContext turnContext) {
        String skypeNameFrom = turnContext.getActivity().getFrom().getName();
        System.out.println("Kudo from: " + skypeNameFrom);

        List<Mention> mentionList = turnContext.getActivity().getMentions();
        if (mentionList.size() < 2) {
            return new Result<>(true, "Sorry, we cannot recognize who you want to kudo.");
        } else if (mentionList.size() > 2) {
            return new Result<>(true, "Sorry, you can only kudo one person at a time.");
        } else {
            String skypeNameTo = mentionList.get(1).getText();
            System.out.println("Kudo to: " + skypeNameTo);

            String text = turnContext.getActivity().getText();
            String[] attributes = text.split(" ");
            String pointAsString = attributes[attributes.length - 1];
            int point = Integer.parseInt(pointAsString);
            System.out.println("Kudo with point: " + point);

            //savePointTracking(from, to, point);

            StringBuilder resultString = new StringBuilder();
            resultString.append("<at>" + skypeNameFrom + "</at>");
            resultString.append(" ");
            resultString.append("<at>@" + skypeNameFrom + "</at>");
            resultString.append(" kudo ");
            resultString.append(point);
            resultString.append(" to ");
            resultString.append(skypeNameTo);
            resultString.append("!");
            System.out.println("Return message: " + resultString);

            return new Result<>(true, resultString.toString());
        }
    }


}
