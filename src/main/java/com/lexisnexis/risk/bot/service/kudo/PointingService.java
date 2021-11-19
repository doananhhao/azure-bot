package com.lexisnexis.risk.bot.service.kudo;

import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.vm.CustomKudoPointTracking;

import java.util.List;

public interface PointingService {

    KudoPointTracking savePointTracking(String givenSkypeId, String givenSkypeName, String pointedSkypeId,
                                        String pointedSkypeName, int point);

    List<CustomKudoPointTracking> getPointsOfCurrentMonth();
}
