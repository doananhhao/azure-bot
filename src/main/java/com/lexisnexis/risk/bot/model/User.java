package com.lexisnexis.risk.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "skype_id")
    private String skypeId;

    @Column(name = "skype_name")
    private String skypeName;

    @OneToOne(mappedBy = "pointedUser")
    @JsonIgnore
    private KudoPointTracking kudoPointTracking;

    @OneToMany(mappedBy = "user")
    private List<KudoPointTracking> kudoPointTrackings;

    public List<KudoPointTracking> getKudoPointTrackings() {
        return kudoPointTrackings;
    }

    public void setKudoPointTrackings(List<KudoPointTracking> kudoPointTrackings) {
        this.kudoPointTrackings = kudoPointTrackings;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public KudoPointTracking getKudoPointTracking() {
        return kudoPointTracking;
    }

    public void setKudoPointTracking(KudoPointTracking kudoPointTracking) {
        this.kudoPointTracking = kudoPointTracking;
    }
}
