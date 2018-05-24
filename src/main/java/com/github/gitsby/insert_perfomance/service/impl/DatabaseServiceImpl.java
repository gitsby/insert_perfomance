package com.github.gitsby.insert_perfomance.service.impl;

import com.github.gitsby.insert_perfomance.model.TestObject;
import com.github.gitsby.insert_perfomance.service.DatabaseService;
import com.github.gitsby.insert_perfomance.utils.RandomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class DatabaseServiceImpl implements DatabaseService {

    @Qualifier("dataSource")
    @Autowired
    private DataSource dataSource;


    @Autowired
    private RandomEntity randomEntity;


    @Override
    public void separateInsert() throws SQLException {
        TestObject obj = randomEntity.rndTestObject();
        insert("insert into test_object(id,column1,column2,column3,column4,column5,column6)" +
                "values(?,?,?,?,?,?,?)", Arrays.asList(obj.id, obj.column1, obj.column2, obj.column3, obj.column4, obj.column5, obj.column6));
        insert("insert into m_test_object_column1(id, column1) values(?,?)", Arrays.asList(obj.id, obj.column1));
        insert("insert into m_test_object_column2(id, column2) values(?,?)", Arrays.asList(obj.id, obj.column2));
        insert("insert into m_test_object_column3(id, column3) values(?,?)", Arrays.asList(obj.id, obj.column3));
        insert("insert into m_test_object_column4(id, column4) values(?,?)", Arrays.asList(obj.id, obj.column4));
        insert("insert into m_test_object_column5(id, column5) values(?,?)", Arrays.asList(obj.id, obj.column5));
        insert("insert into m_test_object_column6(id, column6) values(?,?)", Arrays.asList(obj.id, obj.column6));
    }

    @Override
    public void unitedInsert() throws SQLException {
        TestObject obj = randomEntity.rndTestObject();
        insert("with r as (select ? as id, ? as col1, ? as col2, ? as col3, ? as col4, ? as col5, ? as col6),\n" +
                "a as (insert into test_object(id,column1,column2,column3,column4,column5,column6) select r.id, r.col1,r.col2,r.col3,r.col4,r.col5,r.col6 from r),\n" +
                "b as (insert into m_test_object_column1(id, column1) select r.id, r.col1 from r),\n" +
                "c as (insert into m_test_object_column2(id, column2) select r.id, r.col2 from r),\n" +
                "d as (insert into m_test_object_column3(id, column3) select r.id, r.col3 from r),\n" +
                "e as (insert into m_test_object_column4(id, column4) select r.id, r.col4 from r),\n" +
                "f as (insert into m_test_object_column5(id, column5) select r.id, r.col5 from r)\n" +
                "insert into m_test_object_column6(id, column6) select r.id, r.col6 from r", Arrays.asList(obj.id, obj.column1, obj.column2, obj.column3, obj.column4, obj.column5, obj.column6));
    }


    @Override
    public void unitedInsertLoop() throws SQLException {
        for (int i = 0; i < 25; i++) {
            unitedInsert();
        }
    }

    @Override
    public void separateInsertLoop() throws SQLException {
        for (int i = 0; i < 25; i++) {
            separateInsert();
        }
    }

    public void insert(String query, List<Object> params) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            IntStream.rangeClosed(1, params.size())
                    .forEach(index -> setParam(index, params.get(index - 1), ps));
            ps.execute();
        }
    }

    private void setParam(int index, Object value, PreparedStatement ps) {
        try {
            ps.setObject(index, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
