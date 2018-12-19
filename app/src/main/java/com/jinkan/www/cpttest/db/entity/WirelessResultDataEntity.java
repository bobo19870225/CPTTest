package com.jinkan.www.cpttest.db.entity;

import androidx.room.Entity;

/**
 * Created by Sampson on 2018/12/11.
 * LastCPT 2
 */
@Entity(tableName = "wirelessResultData")
public class WirelessResultDataEntity {
    //strProjectNumber + "_" + strHoleNumber.
    public String testDataID;
    public String probeNumber;
    public float deep;
    public float qc;
    public float fs;
    public float fa;
}
