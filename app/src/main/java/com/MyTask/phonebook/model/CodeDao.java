package com.MyTask.phonebook.model;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteQuery;


@Dao
public interface CodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(CodeModel codeModel);

    @Query("SELECT * FROM Code")
    LiveData<List<CodeModel>> getAllCodeData();


   /* @Query("SELECT * FROM Code where (code LIKE :code And :code ) OR  country LIKE :Country OR capital like :Caption")
    LiveData<List<CodeModel>> getFilterCodeModelData(String code,String Country , String Caption );*/

    @RawQuery(observedEntities = CodeModel.class)
    LiveData<List<CodeModel>> getFilterCodeModelData(SupportSQLiteQuery query);

  //  rawQuery("SELECT id, name FROM people WHERE name = ? AND id = ?", new String[] {"David", "2"});

    @Query("SELECT * FROM PersonalDataModel WHERE id=:id")
    LiveData<CodeModel> getCodeData(int id);

    @Update
    void update(CodeModel codeModel);

    @Delete
    int delete(CodeModel codeModel);

}
