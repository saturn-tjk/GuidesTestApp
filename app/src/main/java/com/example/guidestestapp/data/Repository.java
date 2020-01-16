package com.example.guidestestapp.data;

import com.example.guidestestapp.data.local.LocalRepository;
import com.example.guidestestapp.data.remote.RemoteRepository;

import java.util.List;

public class Repository implements DataSource {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public Repository(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }


    @Override
    public void getGuides(final LoadGuidesCallBack callBack) {
        remoteRepository.getGuides(new LoadGuidesCallBack() {
            @Override
            public void onGuidesLoaded(List<Guide> guides) {
                saveGuides(guides);
                callBack.onGuidesLoaded(guides);
            }

            @Override
            public void onLoadError(Exception err) {
                callBack.onLoadError(err);
            }
        });
    }

    @Override
    public void getGuide(LoadGuideCallBack callBack) {

    }

    @Override
    public void saveGuides(List<Guide> guides) {
        localRepository.saveGuides(guides);
    }


}
