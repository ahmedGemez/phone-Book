package com.MyTask.phonebook.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PersonalDatalDao {

    @Insert
    void insert(PersonalDataModel personalDataModel);

    @Query("SELECT * FROM PersonalDataModel")
    LiveData<List<PersonalDataModel>> getAllPersonalData();

    @Query("SELECT * FROM PersonalDataModel order by firstName,lastName asc")
    LiveData<List<PersonalDataModel>> getAllPersonalDataSorted();



    @Query("SELECT * FROM PersonalDataModel WHERE id=:id")
    LiveData<PersonalDataModel> getPersonalData(int id);

    @Query("UPDATE PersonalDataModel SET image=:image WHERE id=:id")
    void UpdateimageById (String image, int id);

    @Update
    void update(PersonalDataModel personalDataModel);

    @Delete
    int delete(PersonalDataModel PersonalDataModel);
}
