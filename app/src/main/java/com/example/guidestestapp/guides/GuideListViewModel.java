package com.example.guidestestapp.guides;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.guidestestapp.UseCase;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.guides.domain.GetGuides;


public class GuideListViewModel extends ViewModel {

    private GetGuides getGuides;
    private MutableLiveData<PagedList<Guide>> guides = new MutableLiveData<>();

    public GuideListViewModel(GetGuides getGuides) {
        this.getGuides = getGuides;
    }

    public LiveData<PagedList<Guide>> loadGuides() {
        GetGuides.RequestValues requestValues = new GetGuides.RequestValues(3);
        getGuides.executeUseCase(requestValues, new UseCase.UseCaseCallback<GetGuides.ResponseValue>() {
            @Override
            public void onSuccess(GetGuides.ResponseValue response) {
                guides.setValue(response.getGuides());
            }

            @Override
            public void onError(Exception err) {
                Log.d("myLog", "GuideListViewModel->executeUseCase->onError");
            }
        });
        return guides;
    }

}
