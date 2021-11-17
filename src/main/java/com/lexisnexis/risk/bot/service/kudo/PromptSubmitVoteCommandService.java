package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.ActionTypes;
import com.microsoft.bot.schema.CardAction;
import com.microsoft.bot.schema.Mention;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PromptSubmitVoteCommandService implements CommandService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KudoPointTrackingRepository kudoPointTrackingRepository;

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject("kudo submit", "To vote a user");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message) && message.trim().contains("kudo submit");
    }

    @Override
    public Result execute(TurnContext turnContext) {
        List<Mention> mentions = turnContext.getActivity().getMentions();
        List<String> mentioned = new ArrayList<>();
        mentioned.add("==Result: ");
        for (Mention mention : mentions) {
            mentioned.add(String.format("%s | %s | %s | %s",
                    mention.getMentioned().getId(),
                    mention.getMentioned().getName(),
                    mention.getMentioned().getAadObjectId(),
                    mention.getMentioned().getProperties()));
        }

//        Get user who sent kudo action???
//        turnContext.getActivity().getFrom();

        return new Result<>(true,
                turnContext.getActivity().getText() + "\n\n" + String.join("\n\n", mentioned));
    }

    private CardAction createUserCard(String userName, String skypeId) {
        CardAction redAction = new CardAction();
        redAction.setTitle(userName);
        redAction.setType(ActionTypes.IM_BACK);
        redAction.setValue(skypeId);
        return redAction;
    }

    private KudoPointTracking savePointTracking(String givenSkypeId, String pointedSkypeId, int point) {
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
    }
}
