package com.leyifu.weitoutiao.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hahaha on 2017/12/6 0006.
 */

public class ActivityCollections {

    private static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {

        if (activity != null) {

            activities.add(activity);

        }
    }

    public static void removeActivity(Activity activity) {

        if (activity != null) {

            activities.add(activity);

        }
    }

    public static void finishAllActivity() {

        for (Activity activity : activities) {

            if (!activity.isFinishing()) {

                activity.finish();

            }

        }
    }

}
