package com.example.guidestestapp;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.guidestestapp.guides.GuideListViewModel;
import com.example.guidestestapp.guides.domain.GetGuides;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == GuideListViewModel.class) {
            GetGuides getGuides = new GetGuides(Injection.provideRepository());
            return (T) new GuideListViewModel(getGuides);
        }
        return super.create(modelClass);
    }
}
