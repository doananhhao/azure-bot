package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.base.BaseServiceImpl;
import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class KudoPointTrackingServiceImpl extends BaseServiceImpl<KudoPointTracking, Long> implements KudoPointTrackingService{

    private final KudoPointTrackingRepository kudoPointTrackingRepository;

    public KudoPointTrackingServiceImpl(KudoPointTrackingRepository kudoPointTrackingRepository) {
        this.kudoPointTrackingRepository = kudoPointTrackingRepository;
    }

    @Override
    protected JpaRepository<KudoPointTracking, Long> getRepository() {
        return null;
    }
}
