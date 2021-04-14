package com.zhongxiaokang.www.service;

import com.zhongxiaokang.www.dao.ActivityDao;
import com.zhongxiaokang.www.dao.Activity_StudentDao;
import com.zhongxiaokang.www.dao.OrganizerDao;
import com.zhongxiaokang.www.dao.StudentDao;
import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.po.Organizer;
import com.zhongxiaokang.www.po.Student;
import com.zhongxiaokang.www.util.PropertiesUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 活动主办方的操作系统
 */

public class OrganizerService {
    Organizer organizer = new Organizer();//创建一个活动主办方的对象来执行下列操作
    OrganizerDao organizerDao = new OrganizerDao();//创建一个活动主办方方法类的对象来执行和数据库相关的操作
    Activity activity = new Activity();//创建一个活动的对象
    ActivityDao activityDao = new ActivityDao();//创建一个活动方法类的对象

    //注册账号
    public void OrganizerRegister() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----注册账号-----");
        System.out.println("请输入账号：");
        String account = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        System.out.println("请输入负责人姓名：");
        String peopleName = sc.nextLine();
        System.out.println("请输入负责人的联系方式：");
        String phoneNumber = sc.nextLine();
        System.out.println("请输入社团名称：");
        String clubName = sc.nextLine();
        System.out.println("请输入社团简介");
        String clubIntroduction = sc.nextLine();

        organizer.setAccount(account);
        organizer.setPassword(password);
        organizer.setPeopleName(peopleName);
        organizer.setPhoneNumber(Integer.valueOf(phoneNumber));
        organizer.setClubName(clubName);
        organizer.setClubIntroduction(clubIntroduction);
        organizerDao.insert(organizer);
        System.out.println("-----已提交账号注册申请，请等待管理员通过-----");
    }

    //登录
    public void OrganizerLogin() throws SQLException {
        boolean i = true;
        while (i) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入账号：");
            String account = sc.nextLine();
            Organizer organizer1 = organizerDao.findByAccount(account);
            if (organizer1 == null) {
                System.out.println("-----该账号不存在或还未经过审核！！！请检查您输入的账号-----");
            } else {
                System.out.println("请输入密码：");
                String password = sc.nextLine();
                if (password.equals(organizer1.getPassword())) {
                    System.out.println("登录成功！！！");
                    i = false;
                } else {
                    System.out.println("-----密码错误！！！请检查您输入的账号密码是否有误-----");
                }
            }
        }
    }

    //申请一个活动
    public void ApplyActivity() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----活动申请表-----");
        System.out.println("请输入活动名称：");
        String actName = sc.nextLine();
        System.out.println("请输入活动类型：");
        String actType = sc.nextLine();
        System.out.println("请输入活动地点：");
        String actPlace = sc.nextLine();
        System.out.println("请输入活动时间：");
        String actDate = sc.nextLine();
        System.out.println("请输入活动时长：");
        String actDuration = sc.nextLine();
        System.out.println("请输入活动的人数上限：");
        String peopleNumber = sc.nextLine();
        System.out.println("请输入活动所属社团名称：");
        String clubName = sc.nextLine();
        System.out.println("请输入活动内容：");
        String actContent = sc.nextLine();

        activity.setActName(actName);
        activity.setActPlace(actPlace);
        activity.setActType(actType);
        activity.setActDate(actDate);
        activity.setActDuration(actDuration);
        activity.setActContent(actContent);
        activity.setPeopleNumber(peopleNumber);
        activity.setClubName(clubName);
        activityDao.insert(activity);
        System.out.println("-----提交活动申请成功，请等待申请结果-----");
    }

    //撤销活动申请
    public void RepealActivity() throws SQLException {
        boolean i = true;
        while (i) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您要撤销的活动名：");
            String actName = sc.nextLine();
            activity = activityDao.findByActName(actName);
            if (activity == null) {
                System.out.println("-----撤销失败，请您检查活动名是否输入错误，或检查活动是否通过已经审核-----");
            } else {
                activityDao.delete(actName);
                System.out.println("活动撤销成功！！！");
                i = false;
            }
        }
    }

    //修改相关信息(活动主办方的账号不允许修改)
    public void ChangeMessage() throws SQLException {
        boolean i = true;
        while (i){
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的账号：");
            String account = sc.nextLine();
            organizer = organizerDao.findByAccount(account);
            if(organizer == null){
                System.out.println("-----您输入的账号用户不存在，请检查账号是否输入错误-----");
            }else{
                System.out.println("-----需要修改则填写修改后的相关内容，否则填写原内容即可-----");
                System.out.println("请输入社团名称：");
                String clubName = sc.nextLine();
                System.out.println("请输入社团简介：");
                String clubIntroduction = sc.nextLine();
                System.out.println("请输入负责人姓名：");
                String peopleName = sc.nextLine();
                System.out.println("请输入负责人联系方式：");
                String phoneNumber = sc.nextLine();
                System.out.println("请输入密码：");
                String password = sc.nextLine();

                organizer.setClubName(clubName);
                organizer.setClubIntroduction(clubIntroduction);
                organizer.setPeopleName(peopleName);
                organizer.setPhoneNumber(Integer.valueOf(phoneNumber));
                organizer.setPassword(password);
                organizerDao.update(organizer);
                System.out.println("信息修改成功！！！");
                i = false;
            }
        }
    }

    //查看所有报名参加活动的学生
    public void CheckApplication() throws SQLException {
        List<Student> list = new Activity_StudentDao().list();
        if(list.size() == 0){
            System.out.println("-----当前没有报名信息需要审核-----");
        }else{
            for (Student s : list){
                System.out.println("-----想要审核的活动报名-----");
                System.out.println("学生姓名\t"+"学生学号\t"+"活动名称");
                System.out.println(s.getStudentName()+s.getStudentNo()+s.getActName());
            }
        }
    }

    //审核报名申请
    public void ApplicationService(String actName,Student student){
        try {
            //开启事务
            PropertiesUtils.getConnection();
            //删除报名申请表审核表中的内容
            new Activity_StudentDao().delete(actName);
            //向通过报名申请的标准添加该内容
            new Activity_StudentDao().insert1(student);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                PropertiesUtils.rollback(); //事务回滚
            } catch (Exception e1) {
                e1.printStackTrace();
            }finally {
                try {
                    PropertiesUtils.CommitAndReleased(); //事务提交与释放
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    //发放时长
    public void GrantTime(Student student,String actName) throws SQLException {
        String studentNo = student.getStudentNo();
        Student student1 = new StudentDao().findByStudentNo(studentNo);
        Activity activity1 = new ActivityDao().findByActName(actName);
        Integer actDuration = Integer.valueOf(activity1.getActDuration());
        Integer activityTime = Integer.valueOf(student1.getActivityTime());
        student1.setActivityTime(String.valueOf(activityTime+actDuration));
        new StudentDao().updateTime(student1);
    }
}
