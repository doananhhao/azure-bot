package com.lexisnexis.risk.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "kudo")
public class Kudo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "month")
    private int month;

    @Column(name = "year")
    private int year;

    @Column(name = "maximum_point")
    private int maximumPoint;

    @OneToMany(mappedBy = "kudo", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<KudoPointTracking> kudoPointTrackings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMaximumPoint() {
        return maximumPoint;
    }

    public void setMaximumPoint(int maximumPoint) {
        this.maximumPoint = maximumPoint;
    }

    public List<KudoPointTracking> getKudoPointTrackings() {
        return kudoPointTrackings;
    }

    public void setKudoPointTrackings(List<KudoPointTracking> kudoPointTrackings) {
        this.kudoPointTrackings = kudoPointTrackings;
    }


}
