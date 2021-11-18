package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.ct.CustomKudoPointTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface KudoPointTrackingRepository extends JpaRepository<KudoPointTracking, Long> {

    @Transactional
    @Query(value = "select skype_name as skypeName, earned_point as earnedPoint, remain_point as remainPoint\n" +
            "from users left join \n" +
            "(select A.skype_id, earned_point, COALESCE(((select distinct(maximum_point) from kudo where month = :month and year = :year) - given_point), 0) as remain_point from\n" +
            "(select users.skype_id as skype_id, COALESCE(sum(point),0) as given_point\n" +
            "from users left join \n" +
            "(select * from kudo_point_tracking where EXTRACT(MONTH FROM time) = :month and EXTRACT(YEAR from time) = :year) as kpt on users.skype_id = kpt.skype_id\n" +
            "group by users.skype_id\n" +
            "order by users.skype_id) A left join\n" +
            "(select users.skype_id as skype_id, COALESCE(earned_point,0) as earned_point\n" +
            "from users left join \n" +
            "(select users.skype_id as skype_id, COALESCE(sum(point),0) as earned_point\n" +
            "from kudo_point_tracking right join users on kudo_point_tracking.pointed_skype_id = users.skype_id\n" +
            "where EXTRACT(MONTH FROM time) = :month and EXTRACT(YEAR from time) = :year\n" +
            "group by users.skype_id\n" +
            "order by users.skype_id) as e on users.skype_id = e.skype_id) B on A.skype_id = B.skype_id) as t\n" +
            "on users.skype_id = t.skype_id;" , nativeQuery = true)
    List<CustomKudoPointTracking> getKudoPointByMonthAndYear(int month, int year);
}
