package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.dao.annotation.ReadData;
import com.lexisnexis.risk.bot.dao.annotation.WriteData;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.vm.CustomKudoPointTracking;
import com.lexisnexis.risk.bot.service.repository.KudoPointTrackingRepositoryService;
import com.lexisnexis.risk.bot.service.repository.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@ReadData
@Service
public class PointingServiceIml implements PointingService {

    @Autowired
    private UserRepositoryService userRepositoryService;

    @Autowired
    private KudoPointTrackingRepositoryService kudoPointTrackingRepositoryService;

    @WriteData
    @Override
    public KudoPointTracking savePointTracking(String givenSkypeId, String givenSkypeName, String pointedSkypeId,
                                               String pointedSkypeName, int point) {
        User givenUser = userRepositoryService.save(new User(givenSkypeId, givenSkypeName));
        User pointedUser = userRepositoryService.save(new User(pointedSkypeId, pointedSkypeName));
        return kudoPointTrackingRepositoryService.save(
                givenUser.getSkypeId(),
                givenUser.getSkypeName(),
                pointedUser.getSkypeId(),
                pointedUser.getSkypeName(),
                point);
    }

    @Override
    public List<CustomKudoPointTracking> getPointsOfCurrentMonth() {
        Calendar calendar = Calendar.getInstance();
        return kudoPointTrackingRepositoryService
                .getKudoPointByMonthAndYear(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
    }

}
