package com.leyifu.weitoutiao.fragment.newsframgent;


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
public class EssayOrQuestionFragment extends Fragment {


    private static EssayOrQuestionFragment mInstance;

    @SuppressLint("ValidFragment")
    private EssayOrQuestionFragment() {
        // Required empty public constructor
    }

    public static EssayOrQuestionFragment getInstance() {

        if (mInstance == null) {

            synchronized (EssayOrQuestionFragment.class) {

                if (mInstance == null) {

                    mInstance = new EssayOrQuestionFragment();
                }
            }
        }
        return mInstance;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_essay_or_question, container, false);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mInstance != null) {
            mInstance = null;
        }
    }
}
