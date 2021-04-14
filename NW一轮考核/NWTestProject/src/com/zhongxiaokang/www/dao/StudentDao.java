package com.zhongxiaokang.www.dao;

import com.zhongxiaokang.www.po.Student;
import com.zhongxiaokang.www.util.PropertiesUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class StudentDao {
    //添加一个学生的方法
    public void insert(Student student) throws SQLException {
        //创建一个 QueryRunner 对象来执行增添一个学生的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "insert into Student(studentName,studentNo,account,password,gradeName,className)values(?,?,?,?,?,?)";
        //执行sql命令，将学生的相关信息存入数据库
        queryRunner.update(sql,student.getStudentName(),student.getStudentNo(),student.getAccount(),student.getPassword(),student.getGradeName(),student.getClassName());
    }
    //根据学生学号来删除该学生的相关信息内容
    public void delete(String studentNo) throws SQLException {
        //创建一个 QueryRunner 对象来执行删除一个学生信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "delete from Student where studentNo = ?";
        //执行sql命令，通过学生学号从数据库中删除该学生的相关信息
        queryRunner.update(sql,studentNo);

    }

    //修改学生相关信息的方法
    public void update(Student student) throws SQLException {
        //创建一个 QueryRunner 对象来执行修改活动主办方信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令，学生学号和账户不允许修改
        String sql = "update Student set studentName = ?,password = ?,gradeName = ?,className = ? where studentNo = ?";
        //执行sql命令，根据原学生学号名找到该学生，再将修改好的信息存入数据库
        queryRunner.update(sql,student.getStudentName(),student.getPassword(),student.getGradeName(),student.getClassName(),student.getStudentNo());
    }

    //发放时长给学生
    public void updateTime(Student student) throws SQLException {
        //创建一个 QueryRunner 对象来执行修改活动主办方信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令，学生学号和账户不允许修改
        String sql = "update Student set activityTime = ? where studentNo = ?";
        //执行sql命令，根据原学生学号名找到该学生，再将修改好的信息存入数据库
        queryRunner.update(sql,student.getActivityTime(),student.getStudentNo());
    }

    //查询数据库中所有的学生信息，以列表形式返回
    public List<Student> list() throws SQLException {
        //创建一个 QueryRunner 对象来执行查询的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Student";
        //执行sql命令
        List<Student> list = queryRunner.query(sql, new BeanListHandler<>(Student.class));
        return list;
    }

    //根据学生账号来查询具体的学生(用于学生登录时进行检验)
    public Student findByAccount(String account) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个学生信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Student where account = ?";
        //执行sql命令，返回该学生的相关信息
        List<Student> query = queryRunner.query(sql, new BeanListHandler<>(Student.class), account);
        Student student;
        if(query.size() == 0){
            student = null ;
        }
        else{
            student = query.get(0);
        }
        return student;
    }

    //根据学生学号来查询该学生的信息
    public Student findByStudentNo(String studentNo) throws SQLException {
        //创建一个 QueryRunner 对象来执行查询具体的一个学生信息的功能
        QueryRunner queryRunner = new QueryRunner(PropertiesUtils.getDateSource());
        //预编译sql命令
        String sql = "select * from Student where studentNo = ?";
        //执行sql命令，返回该学生的相关信息
        List<Student> query = queryRunner.query(sql, new BeanListHandler<>(Student.class), studentNo);
        Student student;
        if(query.size() == 0){
            student = null ;
        }
        else{
            student = query.get(0);
        }
        return student;
    }
}
