package com.MyTask.phonebook.viewModels;

import android.app.Application;

import com.MyTask.phonebook.Repository.CodeRepository;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.model.network.downloader;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.sqlite.db.SupportSQLiteQuery;

public class CodeViewModel extends AndroidViewModel {

    private CodeRepository codelRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    public LiveData<List<CodeModel>> codeDataModel;


    public CodeViewModel(@NonNull Application application) {
        super(application);
        codelRepository = new CodeRepository(application);
        codeDataModel = codelRepository.getAllCodeModelData();
    }

    public LiveData<List<CodeModel>> getfilterPersonalData(SupportSQLiteQuery query) {
        return codeDataModel=codelRepository.getFilterCodeModelData(query);
    }

    public void insert(CodeModel codeModel) {
        codelRepository.insertCode(codeModel);

    }


    public void Downloadcodes() {
        downloader downloader = new downloader();
        downloader.downloadcodes(codelRepository);
    }
}