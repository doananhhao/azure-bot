package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.Mention;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
public class PromptSubmitVoteCommandService implements CommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KudoPointTrackingRepository kudoPointTrackingRepository;

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
        String skypeNameFrom = turnContext.getActivity().getText();
        String skypeIdForm = turnContext.getActivity().getFrom().getId();
        System.out.println("Kudo from: " + skypeNameFrom);

        Mention to = turnContext.getActivity().getMentions().get(1);
        String skypeNameTo = to.getText();
        String skypeIdTo = to.getMentioned().getId();

        System.out.println("Kudo to: " + skypeNameTo);

        String text = turnContext.getActivity().getText();
        String[] attributes = text.split(" ");
        String pointAsString = attributes[attributes.length-1];
        int point = Integer.parseInt(pointAsString);
        System.out.println("Kudo with point: " + point);

        //savePointTracking(from, to, point);

        StringBuilder resultString = new StringBuilder();
        resultString.append(skypeNameFrom);
        resultString.append(" kudo ");
        resultString.append(point);
        resultString.append(" to ");
        resultString.append(skypeNameTo);
        resultString.append("!");
        resultString.append(" additional info:");
        resultString.append(" skypeIdForm: ");
        resultString.append(skypeIdForm);
        resultString.append(" skypeIdTo: ");
        resultString.append(skypeIdTo);
        System.out.println("Return message: " + resultString);

        return new Result<>(true, resultString.toString());
    }

    private KudoPointTracking savePointTracking(String givenSkypeId, String pointedSkypeId, int point) {
        try {
            //using id
            //using user.findById...
            User givenUser = new User();
            givenUser.setSkypeId(givenSkypeId);
            User pointedUser = new User();
            pointedUser.setSkypeId(pointedSkypeId);
            KudoPointTracking kudoPointTracking = new KudoPointTracking();
            kudoPointTracking.setTime(LocalDateTime.now());
            kudoPointTracking.setUser(givenUser);
            kudoPointTracking.getPointedUser().setSkypeId(pointedSkypeId);
            kudoPointTracking.setPoint(point);
            return kudoPointTrackingRepository.save(kudoPointTracking);
        } catch (Exception ex) {
            return null;
        }
    }

}
