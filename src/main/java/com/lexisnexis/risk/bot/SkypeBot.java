package com.lexisnexis.risk.bot;

import com.codepoetics.protonpack.collectors.CompletableFutures;
import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.constants.StyleTemplate;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.*;
import com.microsoft.bot.schema.Activity;
import com.microsoft.bot.schema.ChannelAccount;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * This class implements the functionality of the Bot.
 *
 * <p>
 * This is where application specific logic for interacting with the users would be added. For this
 * sample, the {@link #onMessageActivity(TurnContext)} echos the text back to the user. The {@link
 * #onMembersAdded(List, TurnContext)} will send a greeting to new conversation participants.
 * </p>
 */
public class SkypeBot extends ActivityHandler {

    private final BotState userState;
    private final BotState conversationState;
    private List<CommandService> commandServices;

    public SkypeBot(UserState userState, ConversationState conversationState, List<CommandService> commandServices) {
        this.userState = userState;
        this.conversationState = conversationState;
        this.commandServices = commandServices;
    }

    @Override
    protected CompletableFuture<Void> onMessageActivity(TurnContext turnContext) {
        if (turnContext.getActivity().removeRecipientMention().trim()
                .equalsIgnoreCase(CommandConstants.HELP)) {
            return sendMessage(turnContext, getHelp());
        }
        try {
            for (CommandService<?> commandService : commandServices) {
                if (commandService.validate(turnContext.getActivity().removeRecipientMention())) {
                    Result<?> result = commandService.execute(turnContext);
                    return sendMessage(turnContext, result);
                }
            }
        } catch (Exception e) {
            return sendMessage(turnContext, "Error: " + e.getMessage());
        }

        return sendMessage(turnContext, "Sorry, I do not understand your command. Type \"help\" for assistance.");
    }

    private CompletableFuture<Void> sendMessage(TurnContext turnContext, Result<?> result) {
        if (result.getData() instanceof Activity) {
            return turnContext
                    .sendActivity((Activity) result.getData())
                    .thenApply(sendResult -> null);
        }
        return sendMessage(turnContext, (String) result.getData());
    }

    private CompletableFuture<Void> sendMessage(TurnContext turnContext, String message) {
        return turnContext
                .sendActivity(MessageFactory.text(message))
                .thenApply(sendResult -> null);
    }

    private String getHelp() {
        StringBuilder response = new StringBuilder();
        for (CommandService<?> commandService : commandServices) {
            HelpCommandObject instruction = commandService.getInstruction();
            response.append(String.format(StyleTemplate.ITALIC + " ===> %s\n\n",
                    instruction.getCommand(), instruction.getDescription()));
        }
        response.append(String.format("\n\n" + StyleTemplate.BOLD + ": %s",
                CommandConstants.HELP, "To list all commands"));
        return response.toString();
    }

    @Override
    protected CompletableFuture<Void> onMembersAdded(
            List<ChannelAccount> membersAdded,
            TurnContext turnContext
    ) {
        return membersAdded.stream()
                .filter(
                        member -> !StringUtils
                                .equals(member.getId(), turnContext.getActivity().getRecipient().getId())
                ).map(channel -> turnContext.sendActivity(MessageFactory.text(getHelp())))
                .collect(CompletableFutures.toFutureList()).thenApply(resourceResponses -> null);
    }
}
