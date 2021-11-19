package com.lexisnexis.risk.bot.service.repository;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.dao.annotation.ReadData;
import com.lexisnexis.risk.bot.dao.annotation.WriteData;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.CustomKudoPointTracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@ReadData
@Service
public class KudoPointTrackingRepositoryService
        extends BaseRepositoryService<KudoPointTracking, Long, KudoPointTrackingRepository> {

    @Autowired
    private KudoRepositoryService kudoRepositoryService;

    @WriteData
    public KudoPointTracking save(String givenSkypeId, String givenSkypeName, String pointedSkypeId,
                                  String pointedSkypeName, int point) {
        return repository.save(new KudoPointTracking(
                new User(givenSkypeId),
                new User(pointedSkypeId),
                point,
                kudoRepositoryService.findByCurrentMonth()));
    }

    public List<CustomKudoPointTracking> getKudoPointByMonthAndYear(int month, int year) {
        return repository.getKudoPointByMonthAndYear(month, year);
    }

}
