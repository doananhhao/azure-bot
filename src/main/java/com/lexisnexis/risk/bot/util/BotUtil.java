package com.lexisnexis.risk.bot.util;

import com.lexisnexis.risk.bot.model.vm.Result;

public class BotUtil {

    public static <T> Result<T> buildSuccessResult(T data) {
        return new Result<>(true, data);
    }

    public static <T> Result<T> buildFailedResult(T data) {
        return new Result<>(false, data);
    }

}
