package com.zhongxiaokang.www.service;

import com.zhongxiaokang.www.dao.ActivityDao;
import com.zhongxiaokang.www.dao.AdministratorDao;
import com.zhongxiaokang.www.dao.OrganizerDao;
import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.po.Administrator;
import com.zhongxiaokang.www.po.Organizer;
import com.zhongxiaokang.www.util.PropertiesUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *管理员的操作系统
 */

public class AdministratorService {
    Administrator administrator = new Administrator(); //创建一个管理员对象来执行下列操作
    AdministratorDao administratorDao = new AdministratorDao();//创建一个管理员方法类的对象执行和数据库相关的操作
    Activity activity = new Activity();
    ActivityDao activityDao = new ActivityDao();
    Organizer organizer = new Organizer();
    OrganizerDao organizerDao = new OrganizerDao();

    //注册账号
    public void AdministratorRegister() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----注册账号-----");
        System.out.println("请输入账号：");
        String account = sc.nextLine();
        System.out.println("请输入密码：");
        String password = sc.nextLine();
        administrator.setAccount(account);
        administrator.setPassword(password);
        administratorDao.insert(administrator);
        System.out.println("-----注册成功-----");
    }

    //登录
    public void AdministratorLogin() throws SQLException {
        boolean i = true;
        while (i) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入账号：");
            String account = sc.nextLine();
            Administrator administrator1 = administratorDao.findByAccount(account);
            if (administrator1 == null) {
                System.out.println("-----该账号不存在！！！请检查您输入的账号-----");
            } else {
                System.out.println("请输入密码：");
                String password = sc.nextLine();
                if (password.equals(administrator1.getPassword())) {
                    System.out.println("登录成功！！！");
                    i = false;
                } else {
                    System.out.println("-----密码错误！！！请检查您输入的账号密码是否有误-----");
                }
            }
        }
    }

    //查看需要审核的账号注册名单
    public void CheckRegister() throws SQLException {
        List<Organizer> list = organizerDao.list();
        if(list.size() == 0){
            System.out.println("-----现在没有注册信息需要审核-----");
        }else{
            for(Organizer i : list){
                System.out.println("-----需要审核的活动总览-----");
                System.out.println("社团名称\t"+"社团简介");
                System.out.println(i.getClubName()+i.getClubIntroduction());
            }
        }
    }

    //审核注册名单
    public  void RegisterService(String clubName,Organizer organizer){
        try {
            //开启事务
            PropertiesUtils.getConnection();
            //删除活动审核表中的活动
            organizerDao.delete(clubName);
            //向通过审核的活动表中添加该活动
            organizerDao.insert1(organizer);
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

    //查看需要审核的活动提交名单
    public void CheckApplication() throws SQLException {
        List<Activity> list = activityDao.list();//查询所有等待审核的活动
        if(list.size() == 0){
            System.out.println("-----现在没有活动需要审核-----");
        }else{
            for(Activity i : list){
                System.out.println("-----需要审核的活动总览-----");
                System.out.println("活动名称\t"+"活动状态\t"+"活动类型");
                System.out.println(i.getActName()+i.getActState()+i.getActType());
            }
        }
    }

    //审核活动名单
    public void ApplicationService(String actName,Activity activity){
        try {
            //开启事务
            PropertiesUtils.getConnection();
            //删除活动审核表中的活动
            activityDao.delete(actName);
            //向通过审核的活动表中添加该活动
            activityDao.insert1(activity);
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
}
