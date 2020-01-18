package com.example.guidestestapp.data;

import com.example.guidestestapp.data.local.LocalRepository;
import com.example.guidestestapp.data.remote.RemoteRepository;

import java.util.ArrayList;
import java.util.List;

//Класс не используется. Он был заменен на PosDataSource
public class Repository implements DataSource {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;
    private List<Guide> cache = new ArrayList<>();

    public Repository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }


    @Override
    public void getGuides(final int startPosition, final int loadSize, final LoadGuidesCallBack callBack) {

        if (cache.isEmpty()) {
            remoteRepository.getGuides(-1, -1, new LoadGuidesCallBack() {
                @Override
                public void onGuidesLoaded(List<Guide> guides) {
                    //saveGuides(guides);
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
        if (startPosition == cache.size() || loadSize == cache.size()) return responseData;

        for (int i = startPosition; i <= loadSize; i++ ) {
            responseData.add(cache.get(i));
        }
        return responseData;
    }


    @Override
    public void getGuide(LoadGuideCallBack callBack) {

    }

    @Override
    public void saveGuides(List<Guide> guides) {
        localRepository.saveGuides(guides);
    }


}
