package com.zhongxiaokang.www.dao;

import com.zhongxiaokang.www.po.Organizer;
import com.zhongxiaokang.www.util.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 *Organizer是数据库中用来存放正在审核中的注册信息表
 *Organizer1是数据库中用来存放通过审核的注册信息表
 */

public class OrganizerDao {
    //添加一个活动主办方的方法(添加到审核名单)
    public void insert(Organizer organizer) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添一个活动主办方的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "insert into Organizer(clubName,clubIntroduction,peopleName,phoneNumber,account,password)values(?,?,?,?,?,?)";
        //执行sql命令，将活动主办方的相关信息存入数据库
        queryRunner.update(sql,organizer.getClubName(),organizer.getClubIntroduction(),organizer.getPeopleName(),organizer.getPhoneNumber(),organizer.getAccount(),organizer.getPassword());
    }

    //添加一个活动主办方的方法(添加到审核名单)
    public void insert1(Organizer organizer) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添一个活动主办方的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "insert into Organizer1(clubName,clubIntroduction,peopleName,phoneNumber,account,password)values(?,?,?,?,?,?)";
        //执行sql命令，将活动主办方的相关信息存入数据库
        queryRunner.update(sql,organizer.getClubName(),organizer.getClubIntroduction(),organizer.getPeopleName(),organizer.getPhoneNumber(),organizer.getAccount(),organizer.getPassword());
    }

    //根据社团名称来删除活动主办方的相关信息内容(删除注册信息申请表中的内容)
    public void delete(String clubName) throws SQLException {
        //创建一个 QueryRunner 对象来执行删除一个活动主办方的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "delete from Organizer where clubName = ?";
        //执行sql命令，通过社团名来删除活动
        queryRunner.update(sql,clubName);
    }

    //修改活动主办方相关信息的方法(已经通过审核才能修改)
    public void update(Organizer organizer) throws SQLException {
        //创建一个 QueryRunner 对象来执行修改活动主办方信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令，账号不允许修改
        String sql = "update Organizer1 set clubName = ?,clubIntroduction = ?,peopleName = ?,phoneNumber = ?,password = ? where clubName = ?";
        //执行sql命令，根据原社团名找到该活动主办方，再将修改好的信息存入数据库
        queryRunner.update(sql,organizer.getClubName(),organizer.getClubIntroduction(),organizer.getPeopleName(),organizer.getPhoneNumber(),organizer.getPassword(),organizer.getClubName());
    }

    //查询审核列表中所有的活动主办方，以列表形式返回
    public List<Organizer> list() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Organizer";
        //执行sql命令
        List<Organizer> list = queryRunner.query(sql, new BeanListHandler<>(Organizer.class));
        return list;
    }

    //根据账号来查询具体的活动主办方(通过审核的主办方)
    public Organizer findByAccount(String account) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Organizer1 where account = ?";
        //执行sql命令，返回该活动主办方的相关信息
        List<Organizer> query = queryRunner.query(sql, new BeanListHandler<>(Organizer.class), account);
        Organizer organizer;
        if (query.size() == 0){
            organizer = null;
        }
        else{
            organizer = query.get(0);
        }
        return organizer;
    }

    //根据账号来查询具体的活动主办方(审核中的主办方)
    public Organizer findByClubName(String clubName) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Organizer where clubName = ?";
        //执行sql命令，返回该活动主办方的相关信息
        List<Organizer> query = queryRunner.query(sql, new BeanListHandler<>(Organizer.class), clubName);
        Organizer organizer;
        if (query.size() == 0){
            organizer = null;
        }
        else{
            organizer = query.get(0);
        }
        return organizer;
    }
}


