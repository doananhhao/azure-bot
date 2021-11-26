// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.lexisnexis.risk.bot;

import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.service.kudo.KudoCommandService;
import com.lexisnexis.risk.bot.service.repository.UserRepositoryService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ApplicationTests {

	@Autowired
	private UserRepositoryService userRepositoryService;

	@Autowired
	private KudoCommandService kudoCommandService;

	@Test
	public void testDatabase() {
		byte[] array = new byte[7];
		new Random().nextBytes(array);
		String sId = new String(array, StandardCharsets.UTF_8);
		String name = "Test User";

		User newUser = new User();
		newUser.setSkypeId(sId);
		newUser.setSkypeName(name);
		userRepositoryService.save(newUser);

		Optional<User> user = userRepositoryService.findById(sId);
		assertThat(user.isPresent()).isTrue();
		assertThat(user.get().getSkypeId()).isEqualTo(sId);

		userRepositoryService.delete(newUser);
		user = userRepositoryService.findById(sId);
		assertThat(user.isPresent()).isFalse();
	}

	@Test
	public void testValidate() {
		boolean pass1 = kudoCommandService.validate("kudo someone 10");
		boolean pass2 = kudoCommandService.validate("someone kudo someone2 10");
		boolean pass3 = kudoCommandService.validate("someone kudo Đức 10");
		boolean pass4 = kudoCommandService.validate("Bot backup kudo Duong 10");
		boolean pass5 = kudoCommandService.validate("ilaam_kudo_bot kudo Duong 10");
		boolean pass6 = kudoCommandService.validate("ilaam-kudo-bot kudo ilaam-kudo-bot 10");
		boolean pass7 = kudoCommandService.validate("@ilaam-kudo-bot kudo @Đức 10");
		boolean pass8 = kudoCommandService.validate("@ilaam bot kudo @Đức 10");
		boolean fail1 = kudoCommandService.validate("list something someone 10");
		boolean fail2 = kudoCommandService.validate("kudo something someone");
		boolean fail3 = kudoCommandService.validate("list");
		boolean fail4 = kudoCommandService.validate("help");
		Assert.assertTrue(pass1);
		Assert.assertTrue(pass2);
		Assert.assertTrue(pass3);
		Assert.assertTrue(pass4);
		Assert.assertTrue(pass5);
		Assert.assertTrue(pass6);
		Assert.assertTrue(pass7);
		Assert.assertTrue(pass8);
		Assert.assertFalse(fail1);
		Assert.assertFalse(fail2);
		Assert.assertFalse(fail3);
		Assert.assertFalse(fail4);
	}

}
