package com.jinkan.www.cpttest.db.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Sampson on 2018/12/11.
 * LastCPT 2
 */
@Entity(tableName = "wirelessTest")
public class WirelessTestEntity {
    @PrimaryKey
    @NonNull
    public String testID = "";//projectNumber_holeNumber
    public String testDate;
    public String projectNumber;
    public String holeNumber;
    public float holeHigh;
    public float waterLevel;
    public String location;
    public String tester;
    public String testType;
    public String testDataID;//projectNumber_holeNumber

    public TestEntity castToTestEntity() {
        TestEntity testModel = new TestEntity();
        testModel.testID = testID;
        testModel.testDate = testDate;
        testModel.projectNumber = projectNumber;
        testModel.holeNumber = holeNumber;
        testModel.holeHigh = holeHigh;
        testModel.waterLevel = waterLevel;
        testModel.location = location;
        testModel.tester = tester;
        testModel.testType = testType;
        testModel.testDataID = testDataID;
        return testModel;
    }
}
