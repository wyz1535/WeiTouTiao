package com.leyifu.weitoutiao.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.leyifu.weitoutiao.InitApp;
import com.leyifu.weitoutiao.R;
import com.leyifu.weitoutiao.bean.NewsChannelBean;
import com.leyifu.weitoutiao.db.DatabaseHelper;
import com.leyifu.weitoutiao.db.table.NewsChannelTable;
import constant.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hahaha on 2017/12/20 0020.
 */

public class NewsChannelDao {

    private final SQLiteDatabase db;

    public NewsChannelDao() {

        db = DatabaseHelper.SQLIteDatabase();

    }

    public void addInitData() {

        String[] categoryId = InitApp.getContext.getResources().getStringArray(R.array.mobile_news_id);
        String[] categoryName = InitApp.getContext.getResources().getStringArray(R.array.mobile_news_name);

        for (int i = 0; i < 8; i++) {
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_ENABLE, i);
        }

        for (int i = 0; i < categoryId.length; i++) {
            add(categoryId[i], categoryName[i], Constant.NEWS_CHANNEL_DISABLE, i);

        }

    }

    private boolean add(String channelId, String channelName, int isEable, int position) {

        ContentValues values = new ContentValues();

        values.put(NewsChannelTable.ID, channelId);
        values.put(NewsChannelTable.NAME, channelName);
        values.put(NewsChannelTable.IS_ENABLE, isEable);
        values.put(NewsChannelTable.POSITION, position);

        long result = db.insert(NewsChannelTable.TABLENAME, null, values);

        return result != -1;

    }

    public List<NewsChannelBean> query(int isEnable) {

        List<NewsChannelBean> list = new ArrayList<>();

        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null,
                NewsChannelTable.IS_ENABLE + "=?",
                new String[]{isEnable + ""}, null, null, null);

        while (cursor.moveToNext()) {

            NewsChannelBean bean = new NewsChannelBean();
            bean.setChannelId(cursor.getString(0));
            bean.setChannelName(cursor.getString(1));
            bean.setIsEnable(cursor.getInt(2));
            bean.setPosition(cursor.getInt(3));
            list.add(bean);
        }

        cursor.close();

        return list;

    }

    public List<NewsChannelBean> queryAll() {

        List<NewsChannelBean> list = new ArrayList<>();

        Cursor cursor = db.query(NewsChannelTable.TABLENAME, null, null, null, null, null, null);

        while (cursor.moveToNext()) {

            NewsChannelBean bean = new NewsChannelBean();

            bean.setChannelId(cursor.getString(0));
            bean.setChannelName(cursor.getString(1));
            bean.setIsEnable(cursor.getInt(2));
            bean.setPosition(cursor.getInt(3));

            list.add(bean);
        }

        cursor.close();
        return list;
    }

    public boolean removeAll() {
        int result = db.delete(NewsChannelTable.TABLENAME, null, null);

        return result != -1;
    }


}
