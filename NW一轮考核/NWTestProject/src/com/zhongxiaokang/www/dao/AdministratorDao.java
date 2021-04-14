package com.zhongxiaokang.www.dao;

import com.zhongxiaokang.www.po.Administrator;
import com.zhongxiaokang.www.util.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class AdministratorDao {
    //添加一个管理员的方法
    public void insert(Administrator administrator) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添一个管理员的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "insert into Administrator(account,password) values (?,?)";
        //执行sql命令，将管理员的相关信息存入数据库
        queryRunner.update(sql,administrator.getAccount(),administrator.getPassword());
    }

    //根据管理员的账号来删除该管理员的相关信息内容
    public void delete(String account) throws SQLException {
        //创建一个 QueryRunner 对象来执行删除一个管理员信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "delete from Administrator where account = ?";
        //执行sql命令，通过管理员账号从数据库中删除该管理员的相关信息
        queryRunner.update(sql,account);

    }

    //修改管理员相关信息的方法
    public void update(Administrator administrator) throws SQLException {
        //创建一个 QueryRunner 对象来执行修改管理员信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令,账号不允许修改
        String sql = "update Administrator set password = ? where account = ?";
        //执行sql命令，根据原管理员账号名找到该管理员，再将修改好的信息存入数据库
        queryRunner.update(sql,administrator.getPassword(),administrator.getAccount());
    }

    //根据管理员的账号来查询其账号密码(用于登录验证)
    public Administrator findByAccount(String account) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Administrator where account = ?";
        //执行sql命令，返回该活动主办方的相关信息
        List<Administrator> query = queryRunner.query(sql, new BeanListHandler<>(Administrator.class), account);
        Administrator administrator;
        if (query.size() == 0) {
            administrator = null;
        } else {
            administrator = query.get(0);
        }
        return administrator;
    }
}


