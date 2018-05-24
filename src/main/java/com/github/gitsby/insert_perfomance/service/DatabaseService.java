package com.github.gitsby.insert_perfomance.service;

import java.sql.SQLException;
import java.util.List;

public interface DatabaseService {
    void separateInsert() throws SQLException;

    void unitedInsert() throws SQLException;


    void unitedInsertLoop() throws SQLException;
    void separateInsertLoop() throws SQLException;
}
