package com.example.guidestestapp.guides;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guidestestapp.R;
import com.example.guidestestapp.data.Guide;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GuidesAdapter extends PagedListAdapter<Guide, GuidesAdapter.GuidVH> {

    private OnItemClickListener clickListener;

    GuidesAdapter(@NonNull DiffUtil.ItemCallback<Guide> diffCallback, OnItemClickListener clickListener) {
        super(diffCallback);
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public GuidVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_item, parent, false);
        return new GuidVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidVH holder, int position) {
        holder.bind(getItem(position));
    }

    public interface OnItemClickListener{
        void onItemClicked(Guide guide);
    }

    class GuidVH extends RecyclerView.ViewHolder {

        ImageView logo;
        TextView name;
        TextView endData;

        public GuidVH(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.guide_logo);
            name = itemView.findViewById(R.id.name_value);
            endData = itemView.findViewById(R.id.end_date_value);
        }

        void bind(final Guide guide) {
            if (guide == null) {
                itemView.findViewById(R.id.item_content).setVisibility(View.GONE);
                itemView.findViewById(R.id.loading_text).setVisibility(View.VISIBLE);
            } else {
                itemView.findViewById(R.id.item_content).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.loading_text).setVisibility(View.GONE);

                name.setText(guide.getName());
                endData.setText(guide.getEndData());
                Glide.with(itemView).load(guide.getImgUrl()).into(logo);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClicked(guide);
                    }
                });
            }
        }
    }
}
