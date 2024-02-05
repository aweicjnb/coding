package org.coding.service;

import org.apache.ibatis.session.SqlSession;
import org.coding.dao.UserDao;
import org.coding.entity.User;
import org.coding.util.MybatisUtils;
import org.junit.Test;

public class MyService {
    SqlSession sqlSession;
    @Test
    public void test() {
        sqlSession = MybatisUtils.getSqlSession();
        UserDao mapper = sqlSession.getMapper(UserDao.class);
        User user = new User();
//        user.setId(1);
        user.setAge(32);
        user.setName("阿斯顿");
        mapper.insert(user);
        sqlSession.commit();
        sqlSession.close();
    }
}



