package com.example.guidestestapp.data;

import java.util.List;

public interface DataSource {

    void getGuides(int startPosition, int loadSize, LoadGuidesCallBack callBack);

    void getGuide(LoadGuideCallBack callBack);

    void saveGuides(List<Guide> guides);

    interface LoadGuidesCallBack {
        void onGuidesLoaded(List<Guide> guides);
        void onLoadError(Exception err);
    }

    interface LoadGuideCallBack {
        void onGuideLoaded(Guide guide);
        void onLoadError(Exception err);
    }

}
