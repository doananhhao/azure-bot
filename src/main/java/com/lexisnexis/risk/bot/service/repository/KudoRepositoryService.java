package com.lexisnexis.risk.bot.service.repository;

import com.lexisnexis.risk.bot.dao.KudoRepository;
import com.lexisnexis.risk.bot.dao.annotation.ReadData;
import com.lexisnexis.risk.bot.model.Kudo;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@ReadData
public class KudoRepositoryService extends BaseRepositoryService<Kudo, Long, KudoRepository> {

    public Kudo findByCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return repository.findKudoByMonthEqualsAndYearEquals(
                calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

}
