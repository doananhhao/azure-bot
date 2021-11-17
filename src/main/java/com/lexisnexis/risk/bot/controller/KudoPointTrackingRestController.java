package com.lexisnexis.risk.bot.controller;

import com.lexisnexis.risk.bot.dao.KudoPointTrackingRepository;
import com.lexisnexis.risk.bot.model.ct.CustomKudoPointTracking;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    List<CustomKudoPointTracking> getTotalByMonthAndYear() {
        return kudoPointTrackingRepository.getKudoPointByMonthAndYear(11,2021);
    }
}
