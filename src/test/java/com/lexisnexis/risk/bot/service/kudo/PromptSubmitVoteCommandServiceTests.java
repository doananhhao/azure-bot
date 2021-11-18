package com.lexisnexis.risk.bot.service.kudo;

import org.junit.Assert;
import org.junit.Test;

public class PromptSubmitVoteCommandServiceTests {
    @Test
    public void testValidate() {
        PromptSubmitVoteCommandService service = new PromptSubmitVoteCommandService();
        boolean pass1 = service.validate("kudo someone 10");
        boolean pass2 = service.validate("someone kudo someone2 10");
        boolean fail1 = service.validate("list something someone 10");
        boolean fail2 = service.validate("kudo something someone");
        boolean fail3 = service.validate("list");
        boolean fail4 = service.validate("help");
        Assert.assertTrue(pass1);
        Assert.assertTrue(pass2);
        Assert.assertFalse(fail1);
        Assert.assertFalse(fail2);
        Assert.assertFalse(fail3);
        Assert.assertFalse(fail4);
    }
}
