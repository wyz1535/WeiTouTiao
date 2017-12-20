package com.leyifu.weitoutiao.db.table;

/**
 * Created by hahaha on 2017/12/18 0018.
 */

public class NewsChannelTable {


    public static final String TABLENAME = "NewsChannelTable";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IS_ENABLE = "isnable";
    public static final String POSITION = "position";

    public static final String CREATE_TABLE = "create table if not exists " + TABLENAME
            + "(" + ID + " text primary key, "
            + NAME + " text, "
            + IS_ENABLE + " text default '1', "
            + POSITION + " text) ";
}
