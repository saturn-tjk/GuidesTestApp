package com.example.guidestestapp.guides.domain;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.example.guidestestapp.UseCase;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.data.PosDataSource;
import com.example.guidestestapp.util.AppExecutors;


public class GetGuides extends UseCase<GetGuides.RequestValues, GetGuides.ResponseValue> {

    private final PosDataSource dataSource;

    public GetGuides(PosDataSource repository) {
        this.dataSource = repository;
    }


    @Override
    public void executeUseCase(RequestValues requestValues,
                               final UseCaseCallback<ResponseValue> callback) {
        /*
        dataSource.getGuides(requestValues.startPosition, requestValues.loadSize,
                new DataSource.LoadGuidesCallBack() {
                    @Override
                    public void onGuidesLoaded(List<Guide> guides) {
                        ResponseValue responseValue = new ResponseValue(guides);
                        callback.onSuccess(responseValue);
                    }

                    @Override
                    public void onLoadError(Exception err) {
                        callback.onError(err);
                    }
                });

         */
        callback.onSuccess(new ResponseValue(dataSource, requestValues.getPageSize()));

    }



    public static class RequestValues implements UseCase.RequestValue {

        private int pageSize;

        public RequestValues(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageSize() {
            return pageSize;
        }
    }

    public static class ResponseValue implements UseCase.ResponseValue {
        /*
        private final List<Guide> guideList;



        public ResponseValue(List<Guide> guideList) {
            if (guideList == null)
                throw new NullPointerException();
            this.guideList = guideList;
        }

        public List<Guide> getGuideList() {
            return guideList;
        }

         */
        private PagedList<Guide> guides;

        public ResponseValue(PosDataSource dataSource, int pageSize) {
            guides = createPageList(dataSource, createPagedListConfig(pageSize));
        }

        private PagedList.Config createPagedListConfig(int pageSize) {
            return new PagedList.Config.Builder()
                    .setEnablePlaceholders(true)
                    .setPageSize(pageSize)
                    .build();
        }

        private PagedList<Guide> createPageList(PosDataSource dataSource, PagedList.Config config) {
            AppExecutors appExecutors = new AppExecutors();

            return new PagedList.Builder<>(dataSource, config)
                    .setNotifyExecutor(appExecutors.getMainThread())
                    .setFetchExecutor(appExecutors.getDiscIO())
                    .build();

        }

        public PagedList<Guide> getGuides() {
            return guides;
        }
    }
}
