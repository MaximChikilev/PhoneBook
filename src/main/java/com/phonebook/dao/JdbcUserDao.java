package com.phonebook.dao;

import com.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("jdbcUserDao")
public class JdbcUserDao implements Dao<User> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (userlogin, userpassword, FIO) VALUES (?,?,?)";
        jdbcTemplate.update(sql, user.getUserLogin(), user.getUserPassword(), user.getFIO());
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE USERS SET userpassword = ? WHERE USERID = ?";
        jdbcTemplate.update(sql, user.getUserPassword(), user.getUserID());
    }

    @Override
    public void delete(long userID) {
        String sql = "DELETE FROM USERS WHERE USERID = ?";
        jdbcTemplate.update(sql, userID);
    }

    @Override
    public List<User> getAll(long userId) {
        String sql = "SELECT * FROM USERS WHERE USERID = ?";
        List<User> listUser = jdbcTemplate.query(sql, new Object[]{userId}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserID(resultSet.getLong("USERID"));
                user.setFIO(resultSet.getString("FIO"));
                user.setUserLogin(resultSet.getString("USERLOGIN"));
                user.setUserPassword(resultSet.getString("USERPASSWORD"));
                return user;
            }
        });
        return listUser;
    }

    @Override
    public List<User> getAll(User object) {
        return null;
    }

    public User getUserByLogin(String userLogin) {
        String sql = "SELECT * FROM USERS WHERE USERLOGIN = ? ";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{userLogin}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setUserID(resultSet.getLong("USERID"));
                user.setFIO(resultSet.getString("FIO"));
                user.setUserLogin(resultSet.getString("USERLOGIN"));
                user.setUserPassword(resultSet.getString("USERPASSWORD"));
                return user;
            }
        });
        return user;
    }


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
