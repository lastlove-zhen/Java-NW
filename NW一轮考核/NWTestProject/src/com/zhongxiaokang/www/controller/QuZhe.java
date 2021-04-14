package com.zhongxiaokang.www.controller;

import com.zhongxiaokang.www.dao.*;
import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.po.Administrator;
import com.zhongxiaokang.www.po.Organizer;
import com.zhongxiaokang.www.po.Student;
import com.zhongxiaokang.www.service.AdministratorService;
import com.zhongxiaokang.www.service.OrganizerService;
import com.zhongxiaokang.www.service.StudentService;

import java.sql.SQLException;
import java.util.Scanner;

public class QuZhe {
    public static void main(String[] args) throws SQLException {
        boolean i = true;
        while (i) {
            //主界面
            System.out.println("-----欢迎使用趣柘校园-----");
            System.out.println("请问您的身份是：");
            System.out.println("1 管理员");
            System.out.println("2 活动主办方");
            System.out.println("3 学生");
            System.out.println("4 退出趣柘校园");
            System.out.println("请输入您的操作指令:");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();

            switch (line) {
                case "1"://管理员操作界面
                    boolean i1 = true;
                    while(i1) {
                        Administrator administrator = new Administrator();
                        AdministratorDao administratorDao = new AdministratorDao();
                        AdministratorService administratorService = new AdministratorService();
                        System.out.println("-----管理员您好-----");
                        System.out.println("1 注册");
                        System.out.println("2 登录");
                        System.out.println("3 返回主界面");
                        System.out.println("请输入您的操作指令：");

                        Scanner sc1 = new Scanner(System.in);
                        String line1 = sc1.nextLine();
                        switch (line1) {
                            case "1":
                                administratorService.AdministratorRegister();
                                break;
                            case "2":
                                System.out.println("-----登录-----");
                                administratorService.AdministratorLogin();
                                boolean i11 = true;
                                while(i11){
                                    System.out.println("-----管理员操作页面-----");
                                    System.out.println("1 审核主办方的注册信息");
                                    System.out.println("2 审核活动申请");
                                    System.out.println("3 返回上一级界面");
                                    System.out.println("请输入您的操作指令：");

                                    Scanner sc11 = new Scanner(System.in);
                                    String line11 = sc11.nextLine();
                                    switch (line11){
                                        case "1":
                                            boolean i12 = true;
                                            while (i12){
                                                System.out.println("-----审核注册信息-----");
                                                System.out.println("1 查看所有等待审核的注册信息");
                                                System.out.println("2 注册信息审核");
                                                System.out.println("3 返回上一级界面");
                                                System.out.println("请输入您的操作指令：");

                                                Scanner sc12 = new Scanner(System.in);
                                                String line12 = sc12.nextLine();
                                                switch (line12){
                                                    case "1":
                                                        administratorService.CheckRegister();
                                                        break;
                                                    case "2":
                                                        System.out.println("请输入您想要通过审核的注册信息的社团名称：");
                                                        Scanner sc121 = new Scanner(System.in);
                                                        String clubName = sc121.nextLine();
                                                        Organizer organizer = new OrganizerDao().findByClubName(clubName);
                                                        if(organizer == null){
                                                            System.out.println("-----该注册信息不存在-----");
                                                        }else {
                                                            administratorService.RegisterService(clubName, organizer);
                                                            System.out.println("-----注册信息审核通过-----");
                                                        }
                                                        break;
                                                    case "3":
                                                        i12 = false;
                                                        break;
                                                }
                                            }
                                            break;
                                        case "2":
                                            boolean i13 = true;
                                            while (i13){
                                                System.out.println("-----审核活动申请------");
                                                System.out.println("1 查看所有等待审核的活动");
                                                System.out.println("2 活动审核");
                                                System.out.println("3 返回上一级界面");
                                                System.out.println("请输入您的操作指令：");

                                                Scanner sc13 = new Scanner(System.in);
                                                String line13 = sc13.nextLine();
                                                switch (line13){
                                                    case "1":
                                                        administratorService.CheckApplication();
                                                        break;
                                                    case "2":
                                                        System.out.println("请输入您想要审核通过的活动名称：");
                                                        Scanner sc131 = new Scanner(System.in);
                                                        String actName = sc131.nextLine();
                                                        Activity activity = new ActivityDao().findByActName(actName);
                                                        if(activity == null){
                                                            System.out.println("-----该活动不存在-----");
                                                        }else {
                                                            administratorService.ApplicationService(actName, activity);
                                                            System.out.println("-----活动审核通过-----");
                                                        }
                                                        break;
                                                    case "3":
                                                        i13 = false;
                                                        break;
                                                }
                                            }
                                            break;
                                        case "3":
                                            i11 = false;
                                            break;
                                    }
                                }
                                break;
                            case "3":
                                i1 = false;
                                break;
                        }
                    }
                    break;

                case "2"://活动主办方操作界面
                    boolean i2 = true;
                    while(i2) {
                        Organizer organizer = new Organizer();
                        OrganizerDao organizerDao = new OrganizerDao();
                        OrganizerService organizerService = new OrganizerService();
                        System.out.println("-----活动主办方您好-----");
                        System.out.println("1 注册");
                        System.out.println("2 登录");
                        System.out.println("3 返回主界面");
                        System.out.println("请输入您的操作指令：");

                        Scanner sc2 = new Scanner(System.in);
                        String line2 = sc2.nextLine();
                        switch (line2) {
                            case "1":
                                organizerService.OrganizerRegister();
                                break;
                            case "2":
                                System.out.println("-----登录-----");
                                organizerService.OrganizerLogin();
                                boolean i21 = true;
                                while(i21){
                                    System.out.println("-----活动主办方操作页面-----");
                                    System.out.println("1 提交活动申请");
                                    System.out.println("2 撤销活动申请");
                                    System.out.println("3 审核报名参加活动的学生");
                                    System.out.println("4 给参加活动的学生发放时长");
                                    System.out.println("5 修改社团相关信息");
                                    System.out.println("6 返回上一级界面");
                                    System.out.println("请输入您的操作指令：");

                                    Scanner sc21 = new Scanner(System.in);
                                    String line21 = sc21.nextLine();
                                    switch (line21){
                                        case "1":
                                            organizerService.ApplyActivity();
                                            break;
                                        case "2":
                                            organizerService.RepealActivity();
                                            break;
                                        case "3":
                                            boolean i22 = true;
                                            while (i22){
                                                System.out.println("-----审核注册信息-----");
                                                System.out.println("1 查看所有等待审核的报名信息");
                                                System.out.println("2 报名信息审核");
                                                System.out.println("3 返回上一级界面");
                                                System.out.println("请输入您的操作指令：");

                                                Scanner sc22 = new Scanner(System.in);
                                                String line22 = sc22.nextLine();
                                                switch (line22){
                                                    case "1":
                                                        organizerService.CheckApplication();
                                                        break;
                                                    case "2":
                                                        Scanner sc221 = new Scanner(System.in);
                                                        System.out.println("请输入您想通过报名审核的活动名称：");
                                                        String actName = sc221.nextLine();
                                                        Student student = new Activity_StudentDao().findByActName(actName);
                                                        if(student == null){
                                                            System.out.println("-----不存在该活动-----");
                                                        }else {
                                                            organizerService.ApplicationService(actName,student);
                                                            System.out.println("-----报名审核通过-----");
                                                        }
                                                        break;
                                                    case "3":
                                                        i22 = false;
                                                        break;
                                                }
                                            }
                                            break;
                                        case "4":
                                            Scanner sc222 = new Scanner(System.in);
                                            System.out.println("请输入您想发放时长的活动名：");
                                            String actName = sc222.nextLine();
                                            Student student = new Activity_StudentDao().findByActName(actName);
                                            if(student == null){
                                                System.out.println("-----不存在该活动-----");
                                            }else {
                                                organizerService.GrantTime(student,actName);
                                                System.out.println("-----时长发放成功-----");
                                            }
                                            break;
                                        case "5":
                                            organizerService.ChangeMessage();
                                            break;
                                        case "6":
                                            i21 = false;
                                            break;
                                    }
                                }
                                break;
                            case "3":
                                i2 = false;
                                break;
                        }
                    }
                    break;

                case "3"://学生操作界面
                    boolean i3 = true;
                    while(i3) {
                        Student student = new Student();
                        StudentDao studentDao = new StudentDao();
                        StudentService studentService = new StudentService();
                        System.out.println("-----同学你好-----");
                        System.out.println("1 注册");
                        System.out.println("2 登录");
                        System.out.println("3 返回主界面");
                        System.out.println("请输入您的操作指令：");

                        Scanner sc3 = new Scanner(System.in);
                        String line3 = sc3.nextLine();
                        switch (line3) {
                            case "1":
                                studentService.StudentRegister();
                                break;
                            case "2":
                                System.out.println("-----登录-----");
                                studentService.StudentLogin();
                                boolean i31 = true;
                                while(i31){
                                    System.out.println("-----学生操作页面-----");
                                    System.out.println("1 查询已有时长");
                                    System.out.println("2 查看/报名参加活动");
                                    System.out.println("3 修改个人信息");
                                    System.out.println("4 返回上一级界面");
                                    System.out.println("请输入您的操作指令：");

                                    Scanner sc31 = new Scanner(System.in);
                                    String line31 = sc31.nextLine();
                                    switch (line31){
                                        case "1":
                                            studentService.InquireTime();
                                            break;
                                        case "2":
                                            boolean i32 = true;
                                            while(i32){
                                                System.out.println("-----活动主页-----");
                                                System.out.println("1 查看所有活动");
                                                System.out.println("2 查看某一具体活动");
                                                System.out.println("3 报名参加活动");
                                                System.out.println("4 返回上一级界面");
                                                System.out.println("请输入您的操作指令：");

                                                Scanner sc32 = new Scanner(System.in);
                                                String line32 = sc32.nextLine();
                                                switch (line32){
                                                    case "1":
                                                        studentService.CheckActivity1();
                                                        break;
                                                    case "2":
                                                        studentService.CheckActivity2();
                                                        break;
                                                    case "3":
                                                        studentService.ApplyActivity();
                                                        break;
                                                    case "4":
                                                        i32 = false;
                                                        break;
                                                }
                                            }
                                            break;
                                        case "3":
                                            studentService.ChangeMessage();
                                            break;
                                        case "4":
                                            i31 = false;
                                            break;
                                    }
                                }
                                break;
                            case "3":
                                i3 = false;
                                break;
                        }
                    }
                    break;

                case "4":
                    System.out.println("-----感谢使用趣柘校园-----");
                    i = false;//跳出循环
                    break;
            }
        }
    }
}
