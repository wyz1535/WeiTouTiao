package com.leyifu.weitoutiao.db.table;

/**
 * Created by hahaha on 2017/12/18 0018.
 */

public class SearchHistoryTable {

    private static final String TABLENAME = "SearchHistoryTable";

    /**
     * 字段部分
     */
    public static final String ID = "id";
    public static final String KEYWORD = "keyWord";
    public static final String TIME = "time";

    public static final String CREATE_TABLE = "create table if not exists" + TABLENAME
            + "(" + ID + " text auto_increment, "
            + KEYWORD + " text primary key, "
            + TIME + " text) ";
}
