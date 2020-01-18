package com.example.guidestestapp.guides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.guidestestapp.R;
import com.example.guidestestapp.ViewModelFactory;
import com.example.guidestestapp.data.Guide;
import com.example.guidestestapp.guidedetail.GuideDetailActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GuideListActivity extends AppCompatActivity implements GuidesAdapter.OnItemClickListener {

    PagedList<Guide> guideList = null;

    GuideListViewModel viewModel;
    RecyclerView guidesRecyclerView;
    Toolbar toolbar;

    public static final DiffUtil.ItemCallback<Guide> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Guide>() {
                @Override
                public boolean areItemsTheSame(@NonNull Guide oldGuide, @NonNull Guide newGuide) {
                    return oldGuide.getUrl().equals(newGuide.getUrl());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Guide oldGuide, @NonNull Guide newGuide) {
                    return oldGuide.getName().equals(newGuide.getName())
                            && oldGuide.getEndData().equals(newGuide.getEndData());
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        viewModel = ViewModelProviders.of(this, new ViewModelFactory()).get(GuideListViewModel.class);

        setUpGuidesRecyclerView();
    }

    private void setUpGuidesRecyclerView() {

        guidesRecyclerView = findViewById(R.id.guids);
        guidesRecyclerView.setHasFixedSize(true);
        guidesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final GuidesAdapter adapter = new GuidesAdapter(DIFF_CALLBACK, this);

        viewModel.loadGuides().observe(this, new Observer<PagedList<Guide>>() {
            @Override
            public void onChanged(PagedList<Guide> guides) {
                adapter.submitList(guides);
            }
        });
        guidesRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Guide guide) {
        Intent intent = new Intent(this, GuideDetailActivity.class);
        intent.putExtra("url", guide.getUrl());
        startActivity(intent);
    }
}
