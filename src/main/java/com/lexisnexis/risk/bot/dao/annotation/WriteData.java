package com.lexisnexis.risk.bot.dao.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface WriteData {
}
