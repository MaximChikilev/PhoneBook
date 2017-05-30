package com.phonebook.service;

import com.phonebook.dao.JdbcUserDao;
import com.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private JdbcUserDao userDao;

    @Override
    public User getUser(String login) {
        User user = userDao.getUserByLogin(login);
        return userDao.getUserByLogin(login);
    }

    @Autowired
    @Qualifier("jdbcUserDao")
    public void setUserDao(JdbcUserDao userDao) {
        this.userDao = userDao;
    }
}
