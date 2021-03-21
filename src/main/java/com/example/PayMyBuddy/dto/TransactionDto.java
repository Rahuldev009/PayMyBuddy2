package com.example.PayMyBuddy.dto;

public class TransactionDto {

    private String name;

    private String description;

    private int userId;

    private int receiverId;

    private double amount;

    private String currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}
