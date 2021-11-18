package com.lexisnexis.risk.bot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "kudo_point_tracking")
public class KudoPointTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "point")
    private int point;

    @Column(name = "time")
    private LocalDateTime time;

    @OneToOne
    @JoinColumn(name = "pointed_skype_id", referencedColumnName = "skype_id")
    private User pointedUser;

    @ManyToOne
    @JoinColumn(name = "skype_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "kudo_id")
    private Kudo kudo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public User getPointedUser() {
        return pointedUser;
    }

    public void setPointedUser(User pointedUser) {
        this.pointedUser = pointedUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Kudo getKudo() {
        return kudo;
    }

    public void setKudo(Kudo kudo) {
        this.kudo = kudo;
    }
}
