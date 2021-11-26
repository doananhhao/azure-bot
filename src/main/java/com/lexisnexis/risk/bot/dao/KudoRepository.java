package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.Kudo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KudoRepository extends JpaRepository<Kudo, Long> {
    Kudo findKudoByMonthEqualsAndYearEquals(int month, int year);
}
