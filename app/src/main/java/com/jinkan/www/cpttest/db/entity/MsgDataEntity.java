package com.jinkan.www.cpttest.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2018/12/11.
 * LastCPT 2
 */
@Entity(tableName = "msgData")
public class MsgDataEntity {
    @PrimaryKey
    @NonNull
    public int msgID = 0;
    public String title;
    public String time;
}
