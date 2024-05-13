package com.task.goalphakids;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void Insert(InfoModel infoModel);

    @Update
    void Update(InfoModel infoModel);

    @Query("delete from InfoModel where id= :id")
    void delete (double id);

    @Query("Select * from InfoModel")
    List<InfoModel> getAllUsers();
}
