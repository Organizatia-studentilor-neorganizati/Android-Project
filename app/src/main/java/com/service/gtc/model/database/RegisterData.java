package com.service.gtc.model.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "registerData")
public class RegisterData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int accountId;
    private String fullName;
    private String emailAddress;
    private int phoneNo;
    private int password;

    public RegisterData(String fullName, String emailAddress, int phoneNo, int password) {
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    @Ignore
    public RegisterData(int accountId, String fullName, String emailAddress, int phoneNo, int password) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public int getPassword() {
        return password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "accountId=" + accountId +
                ", fullName='" + fullName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNo=" + phoneNo +
                ", password=" + password +
                '}';
    }
}
