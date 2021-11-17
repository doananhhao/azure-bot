package com.lexisnexis.risk.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "skypeId")
    private String skypeId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSkypeId() {
        return skypeId;
    }

    public void setSkypeId(String skypeId) {
        this.skypeId = skypeId;
    }
}
