package com.jpmc.midascore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private float balance;

    public UserRecord() {}

    public UserRecord(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public float getBalance() { return balance; }
    public void setBalance(float balance) { this.balance = balance; }
}