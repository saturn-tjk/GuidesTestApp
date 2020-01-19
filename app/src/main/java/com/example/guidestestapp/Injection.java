package com.example.guidestestapp;

import com.example.guidestestapp.data.PosDataSource;
import com.example.guidestestapp.data.local.LocalData;
import com.example.guidestestapp.data.local.LocalRepository;
import com.example.guidestestapp.data.remote.RemoteRepository;
import com.example.guidestestapp.util.AppExecutors;

public class Injection {

    public static PosDataSource provideRepository() {
        return new PosDataSource(
                provideLocalRepository(),
                provideRemoteRepository()
        );
    }

    public static LocalRepository provideLocalRepository() {
        return LocalRepository.getInstance(
                new AppExecutors(),
                LocalData.getInstance(GuideApplication.getContext()).dao()
        );
    }

    public static RemoteRepository provideRemoteRepository() {
        return RemoteRepository.getInstance(GuideApplication.getContext());
    }

}
