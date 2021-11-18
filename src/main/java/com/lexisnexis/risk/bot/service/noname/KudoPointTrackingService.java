package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.model.KudoPointTracking;

public interface KudoPointTrackingService {
    KudoPointTracking savePointTracking(String givenSkypeId, String pointedSkypeId, int point);
}
