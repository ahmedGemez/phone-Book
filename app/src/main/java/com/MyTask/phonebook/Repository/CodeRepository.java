package com.MyTask.phonebook.Repository;

import android.app.Application;
import android.os.AsyncTask;

import com.MyTask.phonebook.model.CodeDao;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.model.DataBase;
import com.MyTask.phonebook.model.PersonalDataModel;
import com.MyTask.phonebook.model.PersonalDatalDao;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

public class CodeRepository {


    private CodeDao codeDao;
    private LiveData<List<CodeModel>> CodeDataModel ;

    public CodeRepository(Application application){
        DataBase db = DataBase.getDatabase(application);
        codeDao = db.CodeDatalDao();
        CodeDataModel = codeDao.getAllCodeData();

    }

    public LiveData<List<CodeModel>> getAllCodeModelData() {
        return CodeDataModel=codeDao.getAllCodeData();
    }

    public LiveData<List<CodeModel>> getFilterCodeModelData(SupportSQLiteQuery query) {
        return CodeDataModel=codeDao.getFilterCodeModelData(query);
    }



    public void insertCode(CodeModel codeModel) {
        new insertcodeAsyncTask(codeDao).execute(codeModel);
    }

    private static class insertcodeAsyncTask extends AsyncTask<CodeModel, Void, Void> {
        private CodeDao mAsyncTaskDao;
        insertcodeAsyncTask(CodeDao dao) { mAsyncTaskDao = dao; }
        @Override
        protected Void doInBackground(final CodeModel... params) { mAsyncTaskDao.insert(params[0]); return null; }

    }




}
