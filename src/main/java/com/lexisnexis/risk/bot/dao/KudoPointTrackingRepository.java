package com.lexisnexis.risk.bot.dao;

import com.lexisnexis.risk.bot.model.KudoPointTracking;
import com.lexisnexis.risk.bot.model.ct.CustomKudoPointTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KudoPointTrackingRepository extends JpaRepository<KudoPointTracking, Long> {
    @Query(value = "select A.username, earned_point as earnedPoint, ((select distinct(maximum_point) from kudo where month = :month and year = :year) - given_point) as remainPoint from\n" +
            "(select username, COALESCE(sum(point),0) as given_point\n" +
            "from users left join (select * from kudo_point_tracking where EXTRACT(MONTH FROM time) = :month and EXTRACT(YEAR from time) = :year) as kpt on users.id = kpt.user_id\n" +
            "group by username\n" +
            "order by username) A left join\n" +
            "(select username, COALESCE(sum(point),0) as earned_point\n" +
            "from kudo_point_tracking right join users on kudo_point_tracking.pointed_user_id = users.id\n" +
            "where EXTRACT(MONTH FROM time) = :month and EXTRACT(YEAR from time) = :year\n" +
            "group by username\n" +
            "order by username) B on A.username = B.username; ", nativeQuery = true)
    List<CustomKudoPointTracking> getKudoPointByMonthAndYear(int month, int year);
}
