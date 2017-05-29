package com.example.demo.service;


import com.example.demo.model.Appeal;
import com.example.demo.repository.AppealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AppealServiceImpl implements AppealService {

    @Autowired
    private AppealRepository appealRepository;

    @Override
    public void saveAppeal(Appeal appeal) {
        appealRepository.save(appeal);
    }

    @Override
    public List<Appeal> getAll() {
        List<Appeal> appeals = new ArrayList<>();
        appealRepository.findAll().forEach(appeal -> appeals.add(appeal));

        return appeals;
    }

    @Override
    public Appeal findById(long id) {
        return appealRepository.findOne(id);
    }

}
