package com.example.guidestestapp.guides.domain;

import com.example.guidestestapp.UseCase;
import com.example.guidestestapp.data.DataSource;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.data.Repository;

import java.util.List;

public class GetGuides extends UseCase<GetGuides.RequestValues, GetGuides.ResponseValue> {

    private Repository repository;

    public GetGuides(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        repository.getGuides(new DataSource.LoadGuidesCallBack() {
            @Override
            public void onGuidesLoaded(List<Guide> guides) {
                ResponseValue responseValue = new ResponseValue(guides);
                getUseCaseCallback().onSuccess(responseValue);
            }

            @Override
            public void onLoadError(Exception err) {
                getUseCaseCallback().onError(err);
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValue {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private final List<Guide> guideList;

        public ResponseValue(List<Guide> guideList) {
            if (guideList == null)
                throw new NullPointerException();
            this.guideList = guideList;
        }

        public List<Guide> getGuideList() {
            return guideList;
        }
    }
}
