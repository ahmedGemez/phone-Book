package com.MyTask.phonebook.Repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.MyTask.phonebook.model.CodeDao;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.model.DataBase;
import com.MyTask.phonebook.model.PersonalDataModel;
import com.MyTask.phonebook.model.PersonalDatalDao;


import java.util.List;

import androidx.lifecycle.LiveData;

public class PersonalDataRepository {


    private PersonalDatalDao personalDatalDao;
    private LiveData<List<PersonalDataModel>> PersonalDataModel;

    private CodeDao codeDao;
    private LiveData<List<CodeModel>> CodeDataModel ;


    public PersonalDataRepository(Application application){
        DataBase db = DataBase.getDatabase(application);
        personalDatalDao= db.PersonalDatalDao();
        PersonalDataModel = personalDatalDao.getAllPersonalData();

    }


    public LiveData<PersonalDataModel> getPersonalDataModel(int Id) {
        return personalDatalDao.getPersonalData(Id);
    }

    public LiveData<List<PersonalDataModel>> getPersonalDataModelSorted() {
        return PersonalDataModel=personalDatalDao.getAllPersonalDataSorted();
    }


    public LiveData<List<PersonalDataModel>> getAllPersonalData() {
        return PersonalDataModel;
    }

    public void insert(PersonalDataModel PersonalData,int id) {
        new insertAsyncTask(personalDatalDao,id).execute(PersonalData);
    }


    public void update(PersonalDataModel PersonalData,int id){
        new insertAsyncTask(personalDatalDao,id).execute(PersonalData);

    }

    public void delete(PersonalDataModel PersonalData){
        new DeleteAsyncTask(personalDatalDao).execute(PersonalData);
    }


    private static class insertAsyncTask extends AsyncTask<PersonalDataModel, Void, Void> {

        private PersonalDatalDao mAsyncTaskDao;
        private int id;

        insertAsyncTask(PersonalDatalDao dao,int id) {
            mAsyncTaskDao = dao;
            this.id=id;
        }

        @Override
        protected Void doInBackground(final PersonalDataModel... params) {
            if(id==0){
                mAsyncTaskDao.insert(params[0]);
                Log.d("inserted","inserted");
            }else{
                mAsyncTaskDao.update(params[0]);
            }
            return null;
        }
    }


    private class DeleteAsyncTask extends  AsyncTask<PersonalDataModel, Void, Void> {

        private PersonalDatalDao mAsyncTaskDao;

        DeleteAsyncTask(PersonalDatalDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(PersonalDataModel... params) {
            Log.d("kjkjkj",params[0].getId()+"cscsc");
            mAsyncTaskDao.delete(params[0]);
            return null;
        }

    }







}
