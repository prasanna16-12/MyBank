package com.example.mybank.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Account")
public class Account {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Cust_name", defaultValue = "")
    private String name;

    @ColumnInfo(name = "Cust_E-mail", defaultValue = "")
    private String email;

    @ColumnInfo(name = "Cust_mPin", defaultValue = "")
    private int Pin;

    @ColumnInfo(name = "Cust_Balance")
    private int balance;

    @ColumnInfo(name = "Cust_PhoneNumber")
    private String phoneNumber;

    @ColumnInfo(name = "Cust_AccNo")
    private String acc_no;

    @ColumnInfo(name = "Cust_IFSC")
    private String ifsc;




    public Account(String name, String email, int Pin, int balance, String phoneNumber, String acc_no, String ifsc) {
        this.name = name;
        this.email = email;
        this.Pin = Pin;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.acc_no = acc_no;
        this.ifsc = ifsc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getPin() {
        return Pin;
    }

    public void setPin(int Pin) {
        this.Pin = Pin;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

}
