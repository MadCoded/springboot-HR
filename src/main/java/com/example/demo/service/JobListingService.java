package com.example.demo.service;

import com.example.demo.model.JobListing;

import java.util.List;


public interface JobListingService {

    List<JobListing> getAll();

    void saveJobListing(JobListing jobListing);

    JobListing findById(long id);

    void delete(long id);

}
