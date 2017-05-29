package com.example.demo.service;

import com.example.demo.model.Appeal;

import java.util.List;


public interface AppealService {

    List<Appeal> getAll();

    void saveAppeal(Appeal appeal);

    Appeal findById(long id);

}
