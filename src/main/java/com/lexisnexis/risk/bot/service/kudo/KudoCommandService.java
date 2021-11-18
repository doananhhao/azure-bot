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
import com.lexisnexis.risk.bot.service.noname.UserService;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Mention;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KudoCommandService implements CommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KudoPointTrackingRepository kudoPointTrackingRepository;

    @Autowired
    private KudoPointTrackingService kudoPointTrackingService;

    @Autowired
    private UserService userService;

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
        List<Mention> mentionList = turnContext.getActivity().getMentions();
        if (mentionList.size() < 2) {
            return new Result<>(true, "Sorry, we cannot recognize who you want to kudo.");
        } else if (mentionList.size() > 2) {
            return new Result<>(true, "Sorry, you can only kudo one person at a time.");
        } else {
            String skypeIdFrom = turnContext.getActivity().getFrom().getId();
            String skypeNameFrom = turnContext.getActivity().getFrom().getName();
            String skypeTextFrom = new StringBuilder()
                    .append("<at id=\"")
                    .append(skypeIdFrom)
                    .append("\">")
                    .append(skypeNameFrom)
                    .append("</at>").toString();
            System.out.println("Kudo from: " + skypeTextFrom);

            Mention mentionTo = mentionList.get(1);
            String skypeTextTo = mentionTo.getText();
            String skypeIdTo = mentionTo.getMentioned().getId();
            String skypeNameTo = mentionTo.getMentioned().getName();
            System.out.println("Kudo to: " + skypeTextTo);

            String text = turnContext.getActivity().getText();
            String[] attributes = text.split(" ");
            String pointAsString = attributes[attributes.length - 1];
            int point = Integer.parseInt(pointAsString);
            System.out.println("Kudo with point: " + point);

            KudoPointTracking kudo = savePointTracking(skypeIdFrom, skypeNameFrom, skypeIdTo, skypeNameTo, point);

            StringBuilder resultString = new StringBuilder();
            if (kudo != null) {
                resultString.append(skypeTextFrom);
                resultString.append(" kudo ");
                resultString.append(point);
                resultString.append(" to ");
                resultString.append(skypeTextTo);
                resultString.append("!");
                System.out.println("Return message: " + resultString);
            } else {
                resultString.append("Error: cannot save to database!");
            }

            return new Result<>(true, resultString.toString());
        }
    }

    //handle exception
    private KudoPointTracking savePointTracking(String givenSkypeId, String givenSkypeName, String pointedSkypeId, String pointedSkypeName, int point) {
        try {
            User givenUser = userService.saveUser(new User(givenSkypeId, givenSkypeName));
            User pointedUser = userService.saveUser(new User(pointedSkypeId, pointedSkypeName));
            return kudoPointTrackingService.savePointTracking(givenUser.getSkypeId(), givenUser.getSkypeName(), pointedUser.getSkypeId(), pointedUser.getSkypeName(), point);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
