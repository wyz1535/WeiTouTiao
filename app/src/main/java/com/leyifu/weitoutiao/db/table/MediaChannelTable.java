package com.leyifu.weitoutiao.db.table;

/**
 * Created by hahaha on 2017/12/18 0018.
 */

public class MediaChannelTable {

    private static final String TABLENAME = "MediaChannelTable";

    /**
     * 字段部分
     */
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String AVATAR = "avatar";
    public static final String TYPE = "type";
    public static final String FOLLOWCOUNT = "followCount";
    public static final String DESCTEXT = "descText";
    public static final String URL = "url";

    public static final String CREATE_TABLE = "create table if not exists" + TABLENAME
            + "(" + ID + " text primary key ,"
            + NAME + " text, "
            + AVATAR + " text, "
            + TYPE + " text, "
            + FOLLOWCOUNT + " text, "
            + DESCTEXT + " text, "
            + URL + " text,)";
}
