package org.coding.dao;

import org.apache.ibatis.annotations.Mapper;
import org.coding.entity.User;

public interface UserDao {
    void insert(User user);
}
