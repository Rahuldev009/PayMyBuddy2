package com.example.PayMyBuddy.dto;

public class ConnectionDto {

    private int userId;

    private int friendId;

    private String name;

    private String email;

    private String friendName;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName + " <" + getEmail() + ">";
    }

    @Override
    public String toString() {
        return "ConnectionDto{" +
                "userId=" + userId +
                ", friendId=" + friendId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
