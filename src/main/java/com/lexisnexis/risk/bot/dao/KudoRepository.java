package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KudoRepository extends JpaRepository<Kudo, Long> {
    Kudo findKudoByMonthEqualsAndYearEquals(int month, int year);
}
