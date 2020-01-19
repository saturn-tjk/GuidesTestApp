package com.example.guidestestapp.guides.domain;

import com.example.guidestestapp.UseCase;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.data.PosDataSource;

import java.util.List;


//Класс не используется, так как не в нем нужды
public class SaveGuides extends UseCase<SaveGuides.RequestValues, SaveGuides.ResponseValue> {

    private final PosDataSource repository;

    public SaveGuides(PosDataSource repository) {
        if (repository == null) throw new NullPointerException();
        this.repository = repository;
    }

    @Override
    public void executeUseCase(RequestValues requestValues, UseCaseCallback<ResponseValue> caseCallback) {
        List<Guide> guides = requestValues.getGuides();
        repository.saveGuides(guides);
    }

    public static final class RequestValues implements UseCase.RequestValue {
        private final List<Guide> guides;

        public RequestValues(List<Guide> guides) {
            this.guides = guides;
        }

        public List<Guide> getGuides() {
            return guides;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {

    }

}
