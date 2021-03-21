package com.example.PayMyBuddy.model;

import javax.persistence.*;

@Entity
@Table(name = "connection", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "friend_id"}))
public class Connection {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "id")
    private User friend;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", user=" + user +
                ", friend=" + friend +
                '}';
    }
}
