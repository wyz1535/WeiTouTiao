package com.leyifu.weitoutiao.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leyifu.weitoutiao.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends Fragment {


    private static VideoFragment mInstance;
    private View view;

    @SuppressLint("ValidFragment")
    private VideoFragment() {
        // Required empty public constructor
    }

    public static VideoFragment getInstance() {

        if (mInstance == null) {

            synchronized (VideoFragment.class) {

                mInstance = new VideoFragment();
            }
        }

        return mInstance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video, container, false);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mInstance != null) {

            mInstance = null;
        }
    }

}
