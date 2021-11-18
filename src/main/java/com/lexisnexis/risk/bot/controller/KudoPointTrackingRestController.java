package com.lexisnexis.risk.bot.controller;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.model.Kudo;
import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.User;
import com.lexisnexis.risk.bot.service.kudo.KudoCommandService;
import com.lexisnexis.risk.bot.service.noname.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class KudoPointTrackingRestController {

    private final KudoPointTrackingRepository kudoPointTrackingRepository;

    private final KudoCommandService kudoCommandService;

    private final UserService userService;

    public KudoPointTrackingRestController(KudoPointTrackingRepository kudoPointTrackingRepository, KudoCommandService kudoCommandService, UserService userService) {
        this.kudoPointTrackingRepository = kudoPointTrackingRepository;
        this.kudoCommandService = kudoCommandService;
        this.userService = userService;
    }


    /*
    Only for testing
     */
    @GetMapping
    User getTotalByMonthAndYear() {
//        return kudoCommandService.savePointTracking("skype_id1","A","skype_id2","B",20);
        return userService.saveUser(new User("skype_id4","Hellorr"));
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
