package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.TurnContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListUsersCommandService implements CommandService<String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject(CommandConstants.USER.LIST_ALL, "To list all existing users");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message)
                && message.trim().equalsIgnoreCase(CommandConstants.USER.LIST_ALL);
    }

    @Override
    public Result<String> execute(TurnContext turnContext) {
        List<User> users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            List<String> collect = users.stream()
                    .map(u -> String.format("**%s**", u.getUsername()))
                    .collect(Collectors.toList());
            return new Result<>(true, String.join("\n\n", collect));
        }
        return new Result<>();
    }
}
