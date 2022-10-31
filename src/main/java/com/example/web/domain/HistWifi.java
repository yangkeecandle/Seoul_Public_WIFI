package com.example.web.domain;

import java.sql.Date;

public class HistWifi {
    private int histNo;
    private String longitude;
    private String latitude;
    private Date searchDate;

    public int getHistNo() {
        return histNo;
    }

    public void setHistNo(int histNo) {
        this.histNo = histNo;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Date getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(Date searchDate) {
        this.searchDate = searchDate;
    }
}
