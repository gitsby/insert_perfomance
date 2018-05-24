package com.github.gitsby.insert_perfomance.controller;

import com.github.gitsby.insert_perfomance.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TestController {

    @Autowired
    private DatabaseService databaseService;


    @GetMapping("/separate")
    @ResponseStatus(HttpStatus.OK)
    public void separateInsert() throws SQLException {
        databaseService.separateInsertLoop();
    }

    @GetMapping("/united")
    @ResponseStatus(HttpStatus.OK)
    public void unitedInsert() throws SQLException {
        databaseService.unitedInsertLoop();
    }




}
