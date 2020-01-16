package com.example.guidestestapp.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "guides")
public class Guide {

    @PrimaryKey
    private String url;
    @ColumnInfo(name = "endData")
    private String endData;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "imgUrl")
    private String imgUrl;

    public Guide(String url, String endData, String name, String imgUrl) {
        this.url = url;
        this.endData = endData;
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEndData() {
        return endData;
    }

    public void setEndData(String endData) {
        this.endData = endData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}
