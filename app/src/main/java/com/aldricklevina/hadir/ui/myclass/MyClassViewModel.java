package com.aldricklevina.hadir.ui.myclass;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyClassViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyClassViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}