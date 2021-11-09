package com.lexisnexis.risk.bot.model.vm;

public class Result<T> {
    private boolean success;
    private T data;

    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public Result() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
