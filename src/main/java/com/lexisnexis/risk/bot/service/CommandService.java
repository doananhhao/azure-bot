package com.lexisnexis.risk.bot.service;

import com.lexisnexis.risk.bot.model.vm.HelpCommandObject;
import com.lexisnexis.risk.bot.model.vm.Result;
import com.microsoft.bot.builder.TurnContext;

public interface CommandService<T> {

    public HelpCommandObject getInstruction();

    public boolean validate(String message);

    public Result<T> execute(TurnContext turnContext);

}
