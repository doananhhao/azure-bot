package com.lexisnexis.risk.bot.controller;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.model.ct.CustomKudoPointTracking;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class KudoPointTrackingRestController {

    private final KudoPointTrackingRepository kudoPointTrackingRepository;

    public KudoPointTrackingRestController(KudoPointTrackingRepository kudoPointTrackingRepository) {
        this.kudoPointTrackingRepository = kudoPointTrackingRepository;
    }


    /*
    Only for testing
     */
    @GetMapping
    KudoPointTracking getTotalByMonthAndYear() {
        return savePointTracking("skype_id1","skype_id2",20);
    }

    @Transactional
    KudoPointTracking savePointTracking(String givenSkypeId, String pointedSkypeId, int point) {
        try {
            //using id
            //using user.findById...
            User givenUser = new User();
            givenUser.setSkypeId(givenSkypeId);
            User pointedUser = new User();
            pointedUser.setSkypeId(pointedSkypeId);
            Kudo kudo = new Kudo();
            kudo.setId(1L);
            KudoPointTracking kudoPointTracking = new KudoPointTracking();
            kudoPointTracking.setTime(LocalDateTime.now());
            kudoPointTracking.setUser(givenUser);
            kudoPointTracking.setPointedUser(pointedUser);
            kudoPointTracking.setPoint(point);
            kudoPointTracking.setKudo(kudo);
            return kudoPointTrackingRepository.save(kudoPointTracking);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
