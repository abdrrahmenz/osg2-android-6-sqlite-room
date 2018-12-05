package com.educa62.simpleroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "teams")
class Teams {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id_;

    @ColumnInfo(name = "team")
    private String team_;

    @ColumnInfo(name = "year")
    private String year_;

    @ColumnInfo(name = "website")
    private String website_;

    @ColumnInfo(name = "gender")
    private String gender_;

    @ColumnInfo(name = "country")
    private String country_;

    int getId_() {
        return id_;
    }

    void setId_(int id_) {
        this.id_ = id_;
    }

    String getTeam_() {
        return team_;
    }

    void setTeam_(String team_) {
        this.team_ = team_;
    }

    String getYear_() {
        return year_;
    }

    void setYear_(String year_) {
        this.year_ = year_;
    }

    String getWebsite_() {
        return website_;
    }

    void setWebsite_(String website_) {
        this.website_ = website_;
    }

    String getGender_() {
        return gender_;
    }

    void setGender_(String gender_) {
        this.gender_ = gender_;
    }

    String getCountry_() {
        return country_;
    }

    void setCountry_(String country_) {
        this.country_ = country_;
    }

}
