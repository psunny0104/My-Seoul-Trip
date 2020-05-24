package com.myseoultravel.ui.making;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MakingViewModel extends ViewModel {

    private MutableLiveData<String> mTextOne;
    private MutableLiveData<String> mTextTwo;

    public MakingViewModel() {
        mTextOne = new MutableLiveData<>();
        mTextTwo = new MutableLiveData<>();
        mTextOne.setValue("MAKE YOUR OWN");
        mTextTwo.setValue("KOREAN TRAVEL ROUTE!");
    }

    public MutableLiveData[] getText() {
        return new MutableLiveData[]{mTextOne, mTextTwo};
    }
}