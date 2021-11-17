package com.lexisnexis.risk.bot.service.kudo;

import org.junit.Assert;
import org.junit.Test;

public class PromptSubmitVoteCommandServiceTests {
    @Test
    public void testValidate() {
        PromptSubmitVoteCommandService service = new PromptSubmitVoteCommandService();
        boolean pass1 = service.validate("anybot kudo someone 10");
        boolean pass2 = service.validate("anybot something kudo some one 10");
        boolean fail1 = service.validate("anybot list something someone 10");
        boolean fail2 = service.validate("anybot kudo something someone");
        Assert.assertTrue(pass1);
        Assert.assertTrue(pass2);
        Assert.assertFalse(fail1);
        Assert.assertFalse(fail2);
    }
}
