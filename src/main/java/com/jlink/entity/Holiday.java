package com.jlink.entity;

import java.util.Date;

public class Holiday {
    private Integer id;

    private Date date;

    private Integer festival;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getFestival() {
        return festival;
    }

    public void setFestival(Integer festival) {
        this.festival = festival;
    }
}