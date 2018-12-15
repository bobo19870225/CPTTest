package com.jinkan.www.cpttest.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2018/12/10.
 * LastCPT 2
 */
@Entity(tableName = "test")
public class TestEntity {
    @PrimaryKey
    @NonNull
    public String testID = "1";
    public String testDate = "1";
    public String projectNumber = "1";
    public String holeNumber = "1";
    public float holeHigh = 33;
    public float waterLevel = 44;
    public String location = "1";
    public String tester = "1";
    public String testType = "1";
    public String testProbeType = "1";
    public String testDataID = "1";
}
