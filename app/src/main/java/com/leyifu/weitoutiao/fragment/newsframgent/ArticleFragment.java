package com.leyifu.weitoutiao.fragment.newsframgent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leyifu.weitoutiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment {


    private static final String TAG = "ArticleFragment";
    private View view;
    private String categryId;

    public ArticleFragment() {
        // Required empty public constructor
    }

    public static Fragment getInstance(String channelId) {

        Bundle bundle = new Bundle();
        bundle.putString(TAG, channelId);
        ArticleFragment mInstance = new ArticleFragment();
        mInstance.setArguments(bundle);
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_article, container, false);
        categryId = getArguments().getString(TAG);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
