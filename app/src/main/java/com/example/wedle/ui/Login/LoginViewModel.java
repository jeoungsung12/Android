package com.example.wedle.ui.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wedle.MainScreen;
import com.example.wedle.NavigationActivity;
import com.example.wedle.R;
import com.example.wedle.RegisterActivity;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public LoginViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}