package com.example.guidestestapp.data;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import com.example.guidestestapp.data.local.LocalRepository;
import com.example.guidestestapp.data.remote.RemoteRepository;

import java.util.ArrayList;
import java.util.List;

public class PosDataSource extends PositionalDataSource<Guide> implements DataSource {


    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;
    private List<Guide> cache = new ArrayList<>();


    public PosDataSource(LocalRepository localRepository, RemoteRepository remoteRepository) {

        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }


    @Override
    public void loadInitial(@NonNull final LoadInitialParams params, @NonNull final LoadInitialCallback<Guide> callback) {
        getGuides(params.requestedStartPosition, params.pageSize, new DataSource.LoadGuidesCallBack() {
            @Override
            public void onGuidesLoaded(List<Guide> guides) {
                callback.onResult(guides, 0, cache.size());
                saveGuides(guides);
            }

            @Override
            public void onLoadError(Exception err) {

            }
        });

    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull final LoadRangeCallback<Guide> callback) {
        getGuides(params.startPosition, params.loadSize, new DataSource.LoadGuidesCallBack() {
            @Override
            public void onGuidesLoaded(List<Guide> guides) {
                callback.onResult(guides);
                saveGuides(guides);
            }

            @Override
            public void onLoadError(Exception err) {

            }
        });
    }



    @Override
    public void getGuides(final int startPosition, final int loadSize, final DataSource.LoadGuidesCallBack callBack) {

        //Имитация медленного интернета
        if (!Thread.currentThread().getName().equals("main")) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        if (cache.isEmpty()) {
            remoteRepository.getGuides(-1, -1, new DataSource.LoadGuidesCallBack() {
                @Override
                public void onGuidesLoaded(List<Guide> guides) {
                    cache.addAll(guides);
                    callBack.onGuidesLoaded(prepareResponseData(startPosition, loadSize));
                }

                @Override
                public void onLoadError(Exception err) {
                    callBack.onLoadError(err);
                }
            });
        } else {
            callBack.onGuidesLoaded(prepareResponseData(startPosition, loadSize));
        }
    }

    private List<Guide> prepareResponseData(final int startPosition, final int loadSize) {
        List<Guide> responseData = new ArrayList<>();

        for (int i = startPosition; i < loadSize + startPosition; i++ ) {
            if (i >= cache.size()) break;
            responseData.add(cache.get(i));
        }


        return responseData;
    }


    @Override
    public void getGuide(DataSource.LoadGuideCallBack callBack) {

    }

    @Override
    public void saveGuides(List<Guide> guides) {
        localRepository.saveGuides(guides);
    }
}
