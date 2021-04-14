package com.zhongxiaokang.www.po;

/**
活动主办方实体类
*/

public class Organizer {
    private String peopleName; //负责人姓名
    private Integer phoneNumber; //负责人联系方式
    private String account;    //登录账号
    private String password;   //登录密码
    private String clubName;   //社团名称
    private String clubIntroduction;  //社团简介
    private String state;    //账号状态

    public Organizer() {}

    public Organizer(String peopleName, Integer phoneNumber, String account, String password, String clubName, String clubIntroduction, String state) {
        this.peopleName = peopleName;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.password = password;
        this.clubName = clubName;
        this.clubIntroduction = clubIntroduction;
        this.state = state;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubIntroduction() {
        return clubIntroduction;
    }

    public void setClubIntroduction(String clubIntroduction) {
        this.clubIntroduction = clubIntroduction;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
