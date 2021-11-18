package com.lexisnexis.risk.bot.service.kudo;

import org.junit.Assert;
import org.junit.Test;

public class KudoCommandServiceTests {
    @Test
    public void testValidate() {
        KudoCommandService service = new KudoCommandService();
        boolean pass1 = service.validate("kudo someone 10");
        boolean pass2 = service.validate("someone kudo someone2 10");
        boolean pass3 = service.validate("someone kudo Đức 10");
        boolean pass4 = service.validate("Bot backup kudo Duong 10");
        boolean pass5 = service.validate("Bot backup kudo Đức 10");
        boolean pass6 = service.validate("ilaam_kudo_bot kudo Duong 10");
        boolean pass7 = service.validate("ilaam-kudo-bot kudo ilaam-kudo-bot 10");
        boolean pass8 = service.validate("@Bot backup kudo @Duong 10");
        boolean pass9 = service.validate("@Bot backup kudo @Đức 10");
        boolean fail1 = service.validate("list something someone 10");
        boolean fail2 = service.validate("kudo something someone");
        boolean fail3 = service.validate("list");
        boolean fail4 = service.validate("help");
        boolean fail5 = service.validate("Bot @backup kudo @Duong 10");
        Assert.assertTrue(pass1);
        Assert.assertTrue(pass2);
        Assert.assertTrue(pass3);
        Assert.assertTrue(pass4);
        Assert.assertTrue(pass5);
        Assert.assertTrue(pass6);
        Assert.assertTrue(pass7);
        Assert.assertTrue(pass8);
        Assert.assertTrue(pass9);
        Assert.assertFalse(fail1);
        Assert.assertFalse(fail2);
        Assert.assertFalse(fail3);
        Assert.assertFalse(fail4);
        Assert.assertFalse(fail5);
    }
}
