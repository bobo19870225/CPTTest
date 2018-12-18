package com.jinkan.www.cpttest.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2018/12/18.
 * CPTTest
 */
@Entity(tableName = "testData")
public class TestDataEntity {
    @PrimaryKey
    @NonNull
    public String testDataID = "";
    public String probeID;
    public float deep;
    public float qc;
    public float fs;
    public float fa;
}
