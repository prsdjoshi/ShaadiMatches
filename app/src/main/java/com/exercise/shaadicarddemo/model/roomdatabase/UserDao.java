package com.exercise.shaadicarddemo.model.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void saveAll(List<UserTable> productTableList);

    @Insert
    public void insertProduct(UserTable productTable);

    @Query("Select * from user_add_table ")
    List<UserTable> getAllUsers();

    @Query("DELETE FROM user_add_table")
    public void clearAll();

    @Delete
    void delete(UserTable productTable);

    @Update
    void update(UserTable productTable);

}
