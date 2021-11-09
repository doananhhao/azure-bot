package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.constants.CommandConstants;
import com.lexisnexis.risk.bot.dao.KudoRepository;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.dao.VoteRepository;
import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.Vote;
import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.lexisnexis.risk.bot.service.CommandService;
import com.microsoft.bot.builder.TurnContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GetStatisticCommandService implements CommandService<String> {

    @Autowired
    private KudoRepository kudoRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public HelpCommandObject getInstruction() {
        return new HelpCommandObject(CommandConstants.GET_STATISTIC, "To get statistic of this month");
    }

    @Override
    public boolean validate(String message) {
        return StringUtils.isNotEmpty(message)
                && message.trim().equalsIgnoreCase(CommandConstants.GET_STATISTIC);
    }

    @Override
    public Result<String> execute(TurnContext turnContext) {
        LocalDate now = LocalDate.now();
        Kudo kudo = kudoRepository.findByMonthAndYear(now.getMonthValue(), now.getYear());
        if (kudo == null) {
            return new Result<>(false, String.format("Does not have any kudos in %s/%s", now.getMonthValue(), now.getYear()));
        }
        List<Vote> votes = voteRepository.findByKudoId(kudo.getId());
        if (CollectionUtils.isEmpty(votes)) {
            return new Result<>(false, String.format("Does not have any kudos in %s/%s", now.getMonthValue(), now.getYear()));
        }
        Map<String, List<Vote>> userVoteMap = new HashMap<>();
        for (Vote vote : votes) {
            List<Vote> list = userVoteMap.computeIfAbsent(vote.getForUserId(), k -> new ArrayList<>());
            list.add(vote);
        }
        List<User> users = userRepository.findAll();
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(User::getId, u -> u));
        List<String> result = new ArrayList<>();
        userVoteMap.forEach((userId, list) -> {
            User user = userMap.get(userId);
            int point = 0;
            if (!CollectionUtils.isEmpty(list)) {
                point = list.stream().mapToInt(Vote::getPoint).sum();
            }
            result.add(String.format("%s: %s point(s)", user.getUsername(), point));
        });
        return new Result<>(true, String.join("\n\n", result));
    }
}
