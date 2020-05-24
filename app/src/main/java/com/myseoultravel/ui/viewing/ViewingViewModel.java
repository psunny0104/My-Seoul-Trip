package com.myseoultravel.ui.viewing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Viewing my Cources fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}