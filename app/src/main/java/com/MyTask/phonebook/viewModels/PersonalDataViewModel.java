package com.MyTask.phonebook.viewModels;

import android.app.Application;


import com.MyTask.phonebook.Repository.PersonalDataRepository;
import com.MyTask.phonebook.model.PersonalDataModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PersonalDataViewModel extends AndroidViewModel {

    private PersonalDataRepository personalDataRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<PersonalDataModel>> PersonalDataModel;


    public PersonalDataViewModel(@NonNull Application application) {
        super(application);
        personalDataRepository = new PersonalDataRepository(application);
        PersonalDataModel = personalDataRepository.getAllPersonalData();
    }

    public void insert(PersonalDataModel personalDataModel,int id) {
        personalDataRepository.insert(personalDataModel,id);
    }

    public void update(PersonalDataModel personalDataModel,int id) {
        personalDataRepository.update(personalDataModel,id);
    }

    public void delete(PersonalDataModel personalDataModel) {
        personalDataRepository.delete(personalDataModel);
    }





    public LiveData<List<PersonalDataModel>> getAllPersonalData() {
        return PersonalDataModel;
    }

    public LiveData<PersonalDataModel> getPersonalData(int PersonalId) {
        return personalDataRepository.getPersonalDataModel(PersonalId);
    }

    public LiveData<List<PersonalDataModel>> getPersonalDataSorted() {
        return PersonalDataModel = personalDataRepository.getPersonalDataModelSorted();
    }

}
