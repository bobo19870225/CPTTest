package com.jinkan.www.cpttest.db.entity;

import androidx.room.Entity;

/**
 * Created by Sampson on 2018/12/11.
 * LastCPT 2
 */
@Entity(tableName = "memoryData")
public class MemoryDataEntity {
    public String probeID;
    public String probeNo;
    public String type;
    public String forceType;
    public int ADValue;
}
