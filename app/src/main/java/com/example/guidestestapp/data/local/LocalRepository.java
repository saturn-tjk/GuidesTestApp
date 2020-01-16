package com.example.guidestestapp.data.local;

import com.example.guidestestapp.data.DataSource;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.util.AppExecutors;

import java.util.List;

public class LocalRepository implements DataSource {

    private static LocalRepository INSTANCE;

    private Dao dao;
    private AppExecutors appExecutors;

    private LocalRepository(Dao dao, AppExecutors appExecutors) {
        this.dao = dao;
        this.appExecutors = appExecutors;
    }

    public static LocalRepository getInstance(AppExecutors executors, Dao dao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(dao, executors);
        }
        return INSTANCE;
    }

    @Override
    public void getGuides(final LoadGuidesCallBack callBack) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<Guide> guides = dao.getGuides();
                appExecutors.getMainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onGuidesLoaded(guides);
                    }
                });
            }
        };
        appExecutors.getDiscIO().execute(runnable);
    }

    @Override
    public void getGuide(LoadGuideCallBack callBack) {

    }

    @Override
    public void saveGuides(final List<Guide> guides) {
        appExecutors.getDiscIO().execute(new Runnable() {
            @Override
            public void run() {
                dao.insertGuide(guides);
            }
        });
    }
}
