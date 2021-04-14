package com.zhongxiaokang.www.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
*这是一个连接数据库，返回DateSource数据源的工具类
*/

public class PropertiesUtils {
    private static Properties properties = new Properties();  //创建properties的对象

    static {
        try {
            //获取db.properties文件的资源
            InputStream inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("db.properties");

            properties.load(inputStream);  //用对象去加载该文件
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static DataSource getDateSource(){
        try {
            DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
            return dataSource;                     //获取数据源,并返回数据用DateSource
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;     //若获取不到数据源，则返回空
    }

    /**
     * 当需要用JDBC手动处理事务时，调用以下方法
     * @return
     */
    //获取连接
    public static Connection getConnection() throws Exception {
        DataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        return dataSource.getConnection();
    }

    //开启事务
    public static void startTransaction() throws Exception {
        getConnection().setAutoCommit(false); //设置事务为手动事务，相当于开启事务
    }

    //事务回滚
    public static void rollback() throws Exception {
        getConnection().rollback();
    }

    //事务提交
    public static void CommitAndReleased() throws Exception{
        getConnection().commit();//事务提交
        getConnection().close();//释放connection
    }
}
