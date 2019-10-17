package com.novel.serviceImp;

import com.novel.dao.UserDao;
import com.novel.model.User;
import com.novel.service.UserService;
import com.novel.utils.NovelTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program novel-core 
 * @description:
 * @author: wang
 * @create: 2019/08/21 16:37 
 */
@Service("userService")
public class UserServiceImp implements UserService {
    @Resource
    public UserDao userDao;

    @Override
    public void test() {
        User user = new User();
        user.setId(NovelTool.getPrivateKey());
        user.setUsername("test");
        user.setPassword("test");
        userDao.insertModel(user);
        User model = userDao.findModel(user);
        System.out.println("service 已经在运行!"+model.toString());
    }
}
