package com.zhongxiaokang.www.dao;

import com.zhongxiaokang.www.po.Activity;
import com.zhongxiaokang.www.po.Student;
import com.zhongxiaokang.www.util.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class Activity_StudentDao {
    //添加一个报名申请到审核表
    public void insert(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());

        String sql = "insert into `Activity_Student` (actName,studentName,studentNo)values(?,?,?)";

        queryRunner.update(sql,student.getActName(),student.getStudentName(),student.getStudentNo());
    }

    //添加一个报名到通过申请的表
    public void insert1(Student student) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());

        String sql = "insert into `Activity_Student1` (actName,studentName,studentNo)values(?,?,?)";

        queryRunner.update(sql,student.getActName(),student.getStudentName(),student.getStudentNo());
    }

    public void delete(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行删除活动的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "delete from activity_student where actName = ?";
        //执行sql命令，通过活动名从数据库中删除活动
        queryRunner.update(sql,actName);

    }


    public List<Student> list() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from activity_student";
        //执行sql命令
        List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        return list;
    }


    public Student findByActName(String actName) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个活动信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from activity_student where actName = ?";
        //执行sql命令，返回该具体活动的相关信息
        List<Student> query = queryRunner.query(sql, new BeanListHandler<>(Student.class), actName);
        Student student ;
        if (query.size() == 0){
            student = null ;
        }
        else{
            student = query.get(0);
        }
        return student;
    }
}
