package com.lexisnexis.risk.bot.service.noname;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.dao.KudoRepository;
import com.lexisnexis.risk.bot.dao.UserRepository;
import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

//This is temporarily => Will be structured
@Service
public class KudoPointTrackingServiceImpl implements KudoPointTrackingService{

    private final KudoPointTrackingRepository kudoPointTrackingRepository;

    private final KudoRepository kudoRepository;

    private final UserRepository userRepository;

    public KudoPointTrackingServiceImpl(KudoPointTrackingRepository kudoPointTrackingRepository, KudoRepository kudoRepository, UserRepository userRepository) {
        this.kudoPointTrackingRepository = kudoPointTrackingRepository;
        this.kudoRepository = kudoRepository;
        this.userRepository = userRepository;
    }

    //This is temporarily => Will be structured
    @Transactional
    @Override
    public KudoPointTracking savePointTracking(String givenSkypeId, String givenSkypeName, String pointedSkypeId, String pointedSkypeName, int point) {
        try {
            Calendar calendar = Calendar.getInstance();
            //checking current kudo board using
            Kudo kudo = kudoRepository.findKudoByMonthEqualsAndYearEquals(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.YEAR));
            //
            User givenUser = new User();
            givenUser.setSkypeId(givenSkypeId);
            User pointedUser = new User();
            pointedUser.setSkypeId(pointedSkypeId);
            KudoPointTracking kudoPointTracking = new KudoPointTracking();
            kudoPointTracking.setTime(LocalDateTime.now());
            kudoPointTracking.setUser(givenUser);
            kudoPointTracking.setPointedUser(pointedUser);
            kudoPointTracking.setPoint(point);
            kudoPointTracking.setKudo(kudo);
            return kudoPointTrackingRepository.save(kudoPointTracking);
        } catch (Exception ex) {
            // Will handle this
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
