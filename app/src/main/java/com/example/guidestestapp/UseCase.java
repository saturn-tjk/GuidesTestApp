package com.example.guidestestapp;

public abstract class UseCase<Q extends UseCase.RequestValue, P extends UseCase.ResponseValue> {

    private Q requestValues;
    private UseCaseCallback<P> useCaseCallback;

    public Q getRequestValues() {
        return requestValues;
    }

    public void setRequestValues(Q requestValues) {
        this.requestValues = requestValues;
    }

    public UseCaseCallback<P> getUseCaseCallback() {
        return useCaseCallback;
    }

    public void setUseCaseCallback(UseCaseCallback<P> useCaseCallback) {
        this.useCaseCallback = useCaseCallback;
    }

    void run() {
        executeUseCase(requestValues);
    }

    protected abstract void executeUseCase(Q requestValues);

    public interface RequestValue {

    }

    public interface ResponseValue {

    }

    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError(Exception err);
    }
}
