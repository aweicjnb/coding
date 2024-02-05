package org.coding.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory factory;

    static {
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static SqlSession getSqlSession() {
        return factory.openSession();
    }
}
