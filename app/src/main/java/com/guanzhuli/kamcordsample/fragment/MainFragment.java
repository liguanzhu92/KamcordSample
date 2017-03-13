package com.guanzhuli.kamcordsample.fragment;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.guanzhuli.kamcordsample.R;
import com.guanzhuli.kamcordsample.adapter.ItemAdapter;
import com.guanzhuli.kamcordsample.model.Item;
import com.guanzhuli.kamcordsample.model.ItemList;
import com.guanzhuli.kamcordsample.utils.Constant;
import com.guanzhuli.kamcordsample.utils.DataAsyncTask;

import java.util.ArrayList;

import static com.guanzhuli.kamcordsample.utils.Constant.HEART;
import static com.guanzhuli.kamcordsample.utils.Constant.VIDEO_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private String mScreenSize;
    private ArrayList<Item> mItems;
    private ItemAdapter mAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_main, container, false);
        mScreenSize = getScreenSize();
        DataAsyncTask testAsyncTask = new DataAsyncTask(mScreenSize, new FragmentCallback() {
            @Override
            public void onTaskDone() {
                Log.d("main", "setdata");
                setContent(view);
            }
        });
        testAsyncTask.execute();
        return view;
    }

    public String getScreenSize() {
        Configuration config = getResources().getConfiguration();
        switch (config.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) {
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return Constant.SCREEN_MEDIUM;
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return Constant.SCREEN_LARGE;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return Constant.SCREEN_SMALL;
            default:
                return Constant.SCREEN_MEDIUM;
        }

    }

    private void setContent(View view) {
        mItems = ItemList.getInstance();
        mAdapter = new ItemAdapter(mItems, getContext());
        mAdapter.setOnItemClickListener(new ItemAdapter.OnCardItemClick() {
            @Override
            public void onItemClick(View view, String data) {
                Bundle bundle = new Bundle();
                bundle.putString(VIDEO_URL, mItems.get(Integer.valueOf(data)).getVideo());
                bundle.putString(HEART, mItems.get(Integer.valueOf(data)).getHeartCount());
                VideoFragment fragment = new VideoFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment)
                        .addToBackStack(getClass().getSimpleName())
                        .commit();
            }
        });
        mRecyclerView = (RecyclerView) view.findViewById(R.id.main_recycler);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setHasFixedSize(true);
    }

    public interface FragmentCallback {
        void onTaskDone();
    }
}

