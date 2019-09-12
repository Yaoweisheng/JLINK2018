package com.jlink.entity;

import java.util.Date;

public class Userinfo {
    private Integer userid;

    private String badgenumber;

    private String ssn;

    private String name;

    private String gender;

    private String title;

    private String pager;

    private Date birthday;

    private Date hiredday;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String ophone;

    private String fphone;

    private Short att;

    private Short inlate;

    private Short outearly;

    private Short overtime;

    private Short sep;

    private Short holiday;

    private String minzu;

    private String cardno;

    private String deptNo;

    private Date leavedate;

    private Integer defaultdeptid;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getBadgenumber() {
        return badgenumber;
    }

    public void setBadgenumber(String badgenumber) {
        this.badgenumber = badgenumber == null ? null : badgenumber.trim();
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn == null ? null : ssn.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPager() {
        return pager;
    }

    public void setPager(String pager) {
        this.pager = pager == null ? null : pager.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getHiredday() {
        return hiredday;
    }

    public void setHiredday(Date hiredday) {
        this.hiredday = hiredday;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone == null ? null : ophone.trim();
    }

    public String getFphone() {
        return fphone;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone == null ? null : fphone.trim();
    }

    public Short getAtt() {
        return att;
    }

    public void setAtt(Short att) {
        this.att = att;
    }

    public Short getInlate() {
        return inlate;
    }

    public void setInlate(Short inlate) {
        this.inlate = inlate;
    }

    public Short getOutearly() {
        return outearly;
    }

    public void setOutearly(Short outearly) {
        this.outearly = outearly;
    }

    public Short getOvertime() {
        return overtime;
    }

    public void setOvertime(Short overtime) {
        this.overtime = overtime;
    }

    public Short getSep() {
        return sep;
    }

    public void setSep(Short sep) {
        this.sep = sep;
    }

    public Short getHoliday() {
        return holiday;
    }

    public void setHoliday(Short holiday) {
        this.holiday = holiday;
    }

    public String getMinzu() {
        return minzu;
    }

    public void setMinzu(String minzu) {
        this.minzu = minzu == null ? null : minzu.trim();
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public Date getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(Date leavedate) {
        this.leavedate = leavedate;
    }

    public Integer getDefaultdeptid() {
        return defaultdeptid;
    }

    public void setDefaultdeptid(Integer defaultdeptid) {
        this.defaultdeptid = defaultdeptid;
    }
}