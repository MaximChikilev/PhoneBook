package com.phonebook.dao;

import com.phonebook.entity.PhoneBookRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("jdbcPhoneBookDao")
public class JdbcPhoneBookDao implements Dao<PhoneBookRecord> {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void create(PhoneBookRecord phoneBookRecord) {
        String SQL = "INSERT INTO phonebook (userid, surname, name, patronymic, mobilephone, homephone, adress, email) VALUES (?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                SQL,
                phoneBookRecord.getUserid(),
                phoneBookRecord.getSurname(),
                phoneBookRecord.getName(),
                phoneBookRecord.getPatronymic(),
                phoneBookRecord.getMobilePhone(),
                phoneBookRecord.getHomePhone(),
                phoneBookRecord.getAdress(),
                phoneBookRecord.geteMail());
    }

    @Override
    public void update(PhoneBookRecord phoneBookRecord) {
        String SQL = "UPDATE phonebook SET USERID = ?, surname = ?, name = ?, patronymic = ?, mobilephone = ?, homephone = ?, adress = ?, email = ? WHERE ID = ?";
        jdbcTemplate.update(SQL,
                phoneBookRecord.getUserid(),
                phoneBookRecord.getSurname(),
                phoneBookRecord.getName(),
                phoneBookRecord.getPatronymic(),
                phoneBookRecord.getMobilePhone(),
                phoneBookRecord.getHomePhone(),
                phoneBookRecord.getAdress(),
                phoneBookRecord.geteMail(),
                phoneBookRecord.getId());

    }

    @Override
    public void delete(long id) {
        String SQL = "DELETE FROM PHONEBOOK WHERE ID = ?";
        jdbcTemplate.update(SQL, id);
    }

    @Override
    public List<PhoneBookRecord> getAll(long id) {
        String SQL = "SELECT * FROM PHONEBOOK WHERE USERID = ?";
        List<PhoneBookRecord> listUser = jdbcTemplate.query(SQL, new Object[]{id}, new RowMapper<PhoneBookRecord>() {
            @Override
            public PhoneBookRecord mapRow(ResultSet resultSet, int i) throws SQLException {
                PhoneBookRecord record = new PhoneBookRecord();
                record.setId(resultSet.getLong("ID"));
                record.setUserid(resultSet.getLong("USERID"));
                record.setSurname(resultSet.getString("SURNAME"));
                record.setName(resultSet.getString("NAME"));
                record.setPatronymic(resultSet.getString("PATRONYMIC"));
                record.setMobilePhone(resultSet.getString("MOBILEPHONE"));
                record.setHomePhone(resultSet.getString("HOMEPHONE"));
                record.setAdress(resultSet.getString("ADRESS"));
                record.seteMail(resultSet.getString("EMAIL"));
                return record;
            }
        });
        return listUser;
    }

    private Object[] getObjectForSQL(PhoneBookRecord phoneBookRecord) {
        Object[] objects = new Object[8];
        objects[0] = "%" + phoneBookRecord.getSurname() + "%";
        objects[1] = "%" + phoneBookRecord.getName() + "%";
        objects[2] = "%" + phoneBookRecord.getPatronymic() + "%";
        objects[3] = "%" + phoneBookRecord.getMobilePhone() + "%";
        objects[4] = "%" + phoneBookRecord.getHomePhone() + "%";
        objects[5] = "%" + phoneBookRecord.getAdress() + "%";
        objects[6] = "%" + phoneBookRecord.geteMail() + "%";
        objects[7] = phoneBookRecord.getUserid();
        return objects;
    }

    public List<PhoneBookRecord> getAll(PhoneBookRecord phoneBookRecord) {
        String SQL = "SELECT * FROM PHONEBOOK WHERE (SURNAME LIKE ?)and(NAME LIKE ?)and(PATRONYMIC LIKE ?)and(MOBILEPHONE LIKE ?)and(HOMEPHONE LIKE ?)and(ADRESS LIKE ?)and(EMAIL LIKE ?)and(USERID = ?)";

        List<PhoneBookRecord> listUser = jdbcTemplate.query(SQL, getObjectForSQL(phoneBookRecord), new RowMapper<PhoneBookRecord>() {
            @Override
            public PhoneBookRecord mapRow(ResultSet resultSet, int i) throws SQLException {
                PhoneBookRecord record = new PhoneBookRecord();
                record.setId(resultSet.getLong("ID"));
                record.setUserid(resultSet.getLong("USERID"));
                record.setSurname(resultSet.getString("SURNAME"));
                record.setName(resultSet.getString("NAME"));
                record.setPatronymic(resultSet.getString("PATRONYMIC"));
                record.setMobilePhone(resultSet.getString("MOBILEPHONE"));
                record.setHomePhone(resultSet.getString("HOMEPHONE"));
                record.setAdress(resultSet.getString("ADRESS"));
                record.seteMail(resultSet.getString("EMAIL"));
                return record;
            }
        });
        return listUser;
    }

    public PhoneBookRecord getRecord(long id) {
        String SQL = "SELECT * FROM PHONEBOOK WHERE ID=?";
        PhoneBookRecord phoneBookRecord = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new RowMapper<PhoneBookRecord>() {
            @Override
            public PhoneBookRecord mapRow(ResultSet resultSet, int i) throws SQLException {
                PhoneBookRecord record = new PhoneBookRecord();
                record.setId(resultSet.getLong("ID"));
                record.setUserid(resultSet.getLong("USERID"));
                record.setSurname(resultSet.getString("SURNAME"));
                record.setName(resultSet.getString("NAME"));
                record.setPatronymic(resultSet.getString("PATRONYMIC"));
                record.setMobilePhone(resultSet.getString("MOBILEPHONE"));
                record.setHomePhone(resultSet.getString("HOMEPHONE"));
                record.setAdress(resultSet.getString("ADRESS"));
                record.seteMail(resultSet.getString("EMAIL"));
                return record;
            }
        });
        return phoneBookRecord;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
