package com.lexisnexis.risk.bot.model.vm;

public class HelpCommandObject {
    private String command;
    private String description;

    public HelpCommandObject(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public HelpCommandObject() {
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
