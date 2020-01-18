package com.example.guidestestapp.guides;

import androidx.recyclerview.widget.DiffUtil;

import com.example.guidestestapp.data.Guide;

import java.util.List;

public class GuideDiffUtilCallback extends DiffUtil.Callback {

    private final List<Guide> oldList;
    private final List<Guide> newList;

    public GuideDiffUtilCallback(List<Guide> oldList, List<Guide> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Guide oldItem = oldList.get(oldItemPosition);
        Guide newItem = newList.get(newItemPosition);
        return oldItem.getUrl().equals(newItem.getUrl());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Guide oldItem = oldList.get(oldItemPosition);
        Guide newItem = newList.get(newItemPosition);

        return oldItem.getName().equals(newItem.getName())
                && oldItem.getEndData().equals(newItem.getEndData());
    }
}
