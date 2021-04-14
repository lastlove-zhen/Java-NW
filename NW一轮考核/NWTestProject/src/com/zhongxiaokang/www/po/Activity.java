package com.zhongxiaokang.www.po;

/**
活动实体类
*/

public class Activity {
    private String actName;   //活动名
    private String actContent;  //活动内容
    private String actType;   //活动类型
    private String actPlace;  //活动地点
    private String actDate;   //活动时间
    private String actDuration;  //活动时长
    private String peopleNumber;  //人数上限
    private String clubName;   //所属社团名称
    private String actState;   //活动状态

    public Activity() {}

    public Activity(String actName, String actContent, String actType, String actPlace, String actDate, String actDuration, String peopleNumber, String clubName, String actState) {
        this.actName = actName;
        this.actContent = actContent;
        this.actType = actType;
        this.actPlace = actPlace;
        this.actDate = actDate;
        this.actDuration = actDuration;
        this.peopleNumber = peopleNumber;
        this.clubName = clubName;
        this.actState = actState;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActContent() {
        return actContent;
    }

    public void setActContent(String actContent) {
        this.actContent = actContent;
    }

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getActPlace() {
        return actPlace;
    }

    public void setActPlace(String actPlace) {
        this.actPlace = actPlace;
    }

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public String getActDuration() {
        return actDuration;
    }

    public void setActDuration(String actDuration) {
        this.actDuration = actDuration;
    }

    public String getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(String people_number) {
        this.peopleNumber = people_number;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getActState() {
        return actState;
    }

    public void setActState(String actState) {
        this.actState = actState;
    }
}
