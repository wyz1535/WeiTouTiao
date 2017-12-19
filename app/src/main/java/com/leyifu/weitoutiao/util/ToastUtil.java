package com.leyifu.weitoutiao.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by hahaha on 2017/12/6 0006.
 */

public class ToastUtil {


    private static Toast toast;

    public static void toast(Context ctx , String msg){

        if (toast == null) {

            toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);

            toast.show();

        } else {

            toast.setText(msg);

            toast.setDuration(Toast.LENGTH_SHORT);

            toast.show();
        }

     }
}
