package com.example.guidestestapp;

public abstract class UseCase<Q extends UseCase.RequestValue, P extends UseCase.ResponseValue> {

    private Q requestValues;


    public Q getRequestValues() {
        return requestValues;
    }

    public void setRequestValues(Q requestValues) {
        this.requestValues = requestValues;
    }

    public abstract void executeUseCase(Q requestValues, UseCaseCallback<P> callback);



    public interface RequestValue {

    }

    public interface ResponseValue {

    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError(Exception err);
    }
}
