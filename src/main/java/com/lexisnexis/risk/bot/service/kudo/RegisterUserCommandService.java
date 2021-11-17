//package com.lexisnexis.risk.bot.service.kudo;
//
//import com.lexisnexis.risk.bot.constants.CommandConstants;
//import com.lexisnexis.risk.bot.dao.UserRepository;
//import com.lexisnexis.risk.bot.model.User;
//import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
//import com.lexisnexis.risk.bot.model.vm.Result;
//import com.lexisnexis.risk.bot.service.CommandService;
//import com.microsoft.bot.builder.TurnContext;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RegisterUserCommandService implements CommandService<String> {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public HelpCommandObject getInstruction() {
//        return new HelpCommandObject(CommandConstants.USER.REGISTER, "To register current user");
//    }
//
//    @Override
//    public boolean validate(String message) {
//        return StringUtils.isNotEmpty(message)
//                && message.trim().equalsIgnoreCase(CommandConstants.USER.REGISTER);
//    }
//
//    @Override
//    public Result<String> execute(TurnContext turnContext) {
//        User user = userRepository.findBySkypeId(turnContext.getActivity().getFrom().getId());
//        if (user == null) {
//            user = new User(
//                    null,
//                    turnContext.getActivity().getFrom().getName(),
//                    turnContext.getActivity().getFrom().getId());
//            userRepository.save(user);
//            return new Result<>(true, String.format("Added successfully new user named %s", user.getUsername()));
//        }
//        return new Result<>(false, "You are already registered!!!");
//    }
//}
