package com.example.ha96666.baqala;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "users")
public class Users {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;

    @NonNull
    @ColumnInfo(name = "user_email")
    private String userEmail;

    @NonNull
    @ColumnInfo(name = "user_phone")
    private String userPhone;

    public Users (String userName, String userEmail, String userPhone){
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }

    public void setUserName(String name){
        this.userName = name;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public void setUserPhone(String phone) {
        this.userPhone = phone;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getUserPhone(){
        return userPhone;
    }

}
