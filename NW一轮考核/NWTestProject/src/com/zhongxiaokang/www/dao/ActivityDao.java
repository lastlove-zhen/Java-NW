package com.zhongxiaokang.www.dao;

import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.util.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Activity是数据库中用来存放等待审核的活动表
 * Activity1是数据库中通过审核正在进行中的活动表
 * Activity2是数据库中已经结束了的活动表
 */

public class ActivityDao {
    //添加一个活动的方法(添加至活动申请列表中)
    public void insert(Activity activity) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添活动的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令，编写该活动的相关信息
        String sql = "insert into Activity(actName,actContent,actType,actPlace,actDate,actDuration,peopleNumber,clubName)values(?,?,?,?,?,?,?,?)";
        //执行sql命令，将该活动添加至数据库
        queryRunner.update(sql,activity.getActName(),activity.getActContent(),activity.getActType(),activity.getActPlace(),activity.getActDate(),activity.getActDuration(),activity.getPeopleNumber(),activity.getClubName());
    }

    //添加一个活动至通过审核的活动的表
    public void insert1(Activity activity) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添活动的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令，编写该活动的相关信息
        String sql = "insert into Activity1(actName,actContent,actType,actPlace,actDate,actDuration,peopleNumber,clubName)values(?,?,?,?,?,?,?,?)";
        //执行sql命令，将该活动添加至数据库
        queryRunner.update(sql,activity.getActName(),activity.getActContent(),activity.getActType(),activity.getActPlace(),activity.getActDate(),activity.getActDuration(),activity.getPeopleNumber(),activity.getClubName());
    }

    //根据活动名来删除活动(删除活动申请列表中的活动)
    public void delete(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行删除活动的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "delete from Activity where actName = ?";
        //执行sql命令，通过活动名从数据库中删除活动
        queryRunner.update(sql,actName);

    }

    //修改活动的方法
    public void update(Activity activity) throws SQLException {
        //创建一个 QueryRunner 对象来执行修改活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "update Activity set actName = ?,actContent = ?,actType = ?,actPlace = ?,actDate = ?,actDuration = ?,peopleNumber = ?,clubName = ? where actName = ?";
        //执行sql命令，根据原活动名找到相关信息，再将修改后的活动相关信息存入数据库
        queryRunner.update(sql,activity.getActName(),activity.getActContent(),activity.getActType(),activity.getActPlace(),activity.getActDate(),activity.getActDuration(),activity.getPeopleNumber(),activity.getClubName(),activity.getActName());
    }

    //查询所有审核中的活动，以列表形式返回
    public List<Activity> list() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Activity";
        //执行sql命令
        List<Activity> list = queryRunner.query(sql, new BeanListHandler<>(Activity.class));
        return list;
    }


    //查询所有通过审核的活动，以列表形式返回
    public List<Activity> list1() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Activity1";
        //执行sql命令
        List<Activity> list = queryRunner.query(sql, new BeanListHandler<>(Activity.class));
        return list;
    }


    //查询所有已完结的活动，以列表形式返回
    public List<Activity> list2() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Activity2";
        //执行sql命令
        List<Activity> list = queryRunner.query(sql, new BeanListHandler<>(Activity.class));
        return list;
    }

    //根据活动名来查询具体的活动（查询审核中的活动）
    public Activity findByActName(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Activity where actName = ?";
        //执行sql命令，返回该具体活动的相关信息
        List<Activity> query = queryRunner.query(sql, new BeanListHandler<>(Activity.class), actName);
        Activity activity ;
        if (query.size() == 0){
            activity = null ;
        }
        else{
            activity = query.get(0);
        }
        return activity;
    }

    //根据活动名来查询具体的活动（查询审核通过的活动）
    public Activity findByActName1(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from `Activity1` where actName = ?";
        //执行sql命令，返回该具体活动的相关信息
        List<Activity> query = queryRunner.query(sql, new BeanListHandler<>(Activity.class), actName);
        Activity activity ;
        if (query.size() == 0){
            activity = null ;
        }
        else{
            activity = query.get(0);
        }
        return activity;
    }

    //根据活动名来查询具体的活动（查询已完结的活动）
    public Activity findByActName2(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Activity2 where actName = ?";
        //执行sql命令，返回该具体活动的相关信息
        List<Activity> query = queryRunner.query(sql, new BeanListHandler<>(Activity.class), actName);
        Activity activity ;
        if (query.size() == 0){
            activity = null ;
        }
        else{
            activity = query.get(0);
        }
        return activity;
    }
}
