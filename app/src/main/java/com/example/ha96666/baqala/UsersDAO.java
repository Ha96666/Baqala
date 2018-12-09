package com.example.ha96666.baqala;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UsersDAO {

    @Insert
    public void insert(Users users);

    @Query("SELECT * FROM users")
    public List<Users> getAllUsers();

    @Query("SELECT * FROM users WHERE user_name=:userName")
    public Users getUsers(String userName);

    @Update
    public int updateUser(Users users);

    @Delete
    public int deleteUser(Users users);

    @Query("DELETE FROM users WHERE user_name=:userName")
    public int deleteByUserName(String userName);
}
