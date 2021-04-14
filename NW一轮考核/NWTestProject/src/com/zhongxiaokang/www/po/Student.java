package com.zhongxiaokang.www.po;

/**
 * 学生实体类
 */

public class Student {
    private String studentName; //学生姓名
    private String studentNo;   //学生学号
    private String gradeName;   //学生年级
    private String className;   //学生班级
    private String account;     //登录账号
    private String password;    //登录密码
    private String activityTime;//时长
    private String actName;     //活动名称(学生参加过的活动)

    public Student() {
    }    //无参构造方法

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
