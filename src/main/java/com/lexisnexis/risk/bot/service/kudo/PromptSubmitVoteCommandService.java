package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.MessageFactory;
import com.microsoft.bot.builder.TurnContext;
import com.microsoft.bot.schema.ActionTypes;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.CardAction;
import com.microsoft.bot.schema.SuggestedActions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PromptSubmitVoteCommandService implements CommandService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject("kudo submit", "To vote a user");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message) && message.trim().equalsIgnoreCase("kudo submit");
    }

    @Override
    public Result execute(TurnContext turnContext) {
        List<User> users = userRepository.findAll();
        Activity reply = MessageFactory.text("Which user do you want to kudo?");
        List<CardAction> cardActions = new ArrayList<>();
        for (User user : users) {
            cardActions.add(createUserCard(user.getUsername(), user.getSkypeId()));
        }
        SuggestedActions actions = new SuggestedActions();
        actions.setActions(cardActions);
        reply.setSuggestedActions(actions);
        return new Result<>(true, reply);
    }

    private CardAction createUserCard(String userName, String skypeId) {
        CardAction redAction = new CardAction();
        redAction.setTitle(userName);
        redAction.setType(ActionTypes.IM_BACK);
        redAction.setValue(skypeId);
        return redAction;
    }
}
