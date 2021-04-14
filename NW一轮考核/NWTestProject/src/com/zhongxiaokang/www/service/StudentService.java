package com.zhongxiaokang.www.service;

import com.zhongxiaokang.www.dao.ActivityDao;
import com.zhongxiaokang.www.dao.Activity_StudentDao;
import com.zhongxiaokang.www.dao.StudentDao;
import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.po.Student;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * 学生的操作系统
 */
public class StudentService {
    Student student = new Student();//创建一个学生对象来执行下列操作
    StudentDao studentDao = new StudentDao();//创建一个学生方法类对象来执行与数据库相关的操作
    Activity activity = new Activity();//创建一个活动的对象
    ActivityDao activityDao = new ActivityDao();//创建一个活动方法类的对象

    //注册账号
    public void StudentRegister() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----注册账号-----");
        System.out.println("请输入账号：");
        String account = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        System.out.println("请输入学号：");
        String studentNo = sc.nextLine();
        System.out.println("请输入姓名：");
        String studentName = sc.nextLine();
        System.out.println("请输入年级：");
        String gradeName = sc.nextLine();
        System.out.println("请输入班级：");
        String className = sc.nextLine();

        student.setAccount(account);
        student.setPassword(password);
        student.setStudentNo(studentNo);
        student.setStudentName(studentName);
        student.setGradeName(gradeName);
        student.setClassName(className);
        studentDao.insert(student);
        System.out.println("-----注册成功-----");
    }

    //登录
    public void StudentLogin() throws SQLException {
        boolean i = true;
        while (i) {
            System.out.println("请输入账号：");
            Scanner sc = new Scanner(System.in);
            String account = sc.nextLine();
            Student student = studentDao.findByAccount(account);
            if (student == null) {
                System.out.println("-----该账号不存在！！！请检查您输入的账号是否有误-----");
            } else {
                System.out.println("请输入密码：");
                String password = sc.nextLine();
                if (password.equals(student.getPassword())) {
                    System.out.println("登录成功！！！");
                    i = false;
                } else {
                    System.out.println("-----密码错误！！！请检查您输入的账号密码是否有误-----");
                }
            }
        }
    }

    //查询自己的时长
    public void InquireTime() throws SQLException {
        boolean i = true;
        while (i) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的学号：");
            String studentNo = sc.nextLine();
            student = studentDao.findByStudentNo(studentNo);
            if (student == null) {
                System.out.println("-----不存在该学生信息，请您检查学号是否输入有误-----");
            } else {
                int time = Integer.parseInt(student.getActivityTime());
                String studentName = student.getStudentName();
                System.out.println(studentName + "同学，您的总时长为" + time + "小时");
                i = false;
            }
        }
    }

    //修改相关信息(学生学号和账号不允许修改)
    public void ChangeMessage() throws SQLException {
        boolean i = true;
        while (i) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的学号：");
            String studentNo = sc.nextLine();
            student = studentDao.findByStudentNo(studentNo);
            if (student == null) {
                System.out.println("-----不存在该学生信息，请您检查学号是否输入有误-----");
            } else {
                System.out.println("-----需要修改则填写修改后的相关内容，否则填写原内容即可-----");
                System.out.println("请输入姓名：");
                String studentName = sc.nextLine();
                System.out.println("请输入密码：");
                String password = sc.nextLine();
                System.out.println("请输入年级：");
                String gradeName = sc.nextLine();
                System.out.println("请输入班级：");
                String className = sc.nextLine();

                student.setStudentName(studentName);
                student.setPassword(password);
                student.setGradeName(gradeName);
                student.setClassName(className);
                studentDao.update(student);
                System.out.println("信息修改成功！！！");
                i = false;
            }
        }
    }

    //查看所有进行中的活动
    public void CheckActivity1() throws SQLException {
        List<Activity> list = activityDao.list1();
        if (list.size() == 0) {
            System.out.println("-----现在没有正在进行中的活动，请稍后再查询-----");
        } else {
            for (Activity i : list) {
                System.out.println("-----活动总览-----");
                System.out.println("活动名称\t" + "活动状态\t" + "活动类型");
                System.out.println(i.getActName() + i.getActState() + i.getActType());
            }
        }
    }

    //查看某一具体活动
    public void CheckActivity2() throws SQLException {
        System.out.println("请输入您想要查询活动的名称：");
        Scanner sc = new Scanner(System.in);
        String actName = sc.nextLine();
        activity = activityDao.findByActName1(actName);
        if (activity == null) {
            System.out.println("-----没有该活动，请检查活动名是否输入错误-----");
        } else {
            System.out.println("-----------------------------");
            System.out.println("活动名称：" + activity.getActName());
            System.out.println("活动类型：" + activity.getActType());
            System.out.println("活动地点：" + activity.getActPlace());
            System.out.println("活动时间：" + activity.getActDate());
            System.out.println("活动内容：" + activity.getActContent());
            System.out.println("活动时长：" + activity.getActDuration());
            System.out.println("所属社团：" + activity.getClubName());
            System.out.println("人数上限：" + activity.getPeopleNumber());
            System.out.println("活动状态：" + activity.getActState());
            System.out.println("-----------------------------");
        }
    }

    //报名活动
    public void ApplyActivity() throws SQLException {
        System.out.println("-----活动报名表-----");
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您要参加的活动名：");
        String actName = sc.nextLine();
        activity = activityDao.findByActName1(actName);
        if (activity == null) {
            System.out.println("-----没有该活动，请检查活动名是否输入错误-----");
        } else {
            System.out.println("请输入您的姓名：");
            String studentName = sc.nextLine();
            System.out.println("请输入您的学号：");
            String studentNo = sc.nextLine();

            student.setStudentName(studentName);
            student.setStudentNo(studentNo);
            student.setActName(actName);
            new Activity_StudentDao().insert(student);
            System.out.println("-----报名提交成功！！！-----");
        }
    }
}