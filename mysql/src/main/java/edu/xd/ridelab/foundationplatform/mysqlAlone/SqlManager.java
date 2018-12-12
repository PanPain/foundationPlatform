package edu.xd.ridelab.foundationplatform.mysqlAlone;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cwz
 * @date 2018/05/10
 */
public class SqlManager {
    private static Logger LOGGER = LoggerFactory.getLogger("SqlManager");
    private static volatile SqlManager manager;
    private SqlSessionFactory sessionFactory;

    private SqlManager(){
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            System.exit(1);
            LOGGER.error("read mybatis-config failed -> {}", e.getMessage());
        }
        sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlManager getInstance(){
        if(manager == null){
            synchronized (SqlManager.class){
                if(manager == null){
                    manager = new SqlManager();
                }
            }
        }
        return manager;
    }

    public static SqlSession getSession(){
        return getInstance().sessionFactory.openSession();
    }

}
