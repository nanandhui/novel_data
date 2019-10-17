package com.novel.dao;

import com.novel.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserDao {
    public User findModel(@Param("user") User user);
    public int insertModel(@Param("user") User user);
}
