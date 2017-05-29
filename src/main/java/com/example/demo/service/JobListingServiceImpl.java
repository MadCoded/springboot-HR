package com.example.demo.service;

import com.example.demo.model.JobListing;
import com.example.demo.repository.JobListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class JobListingServiceImpl implements JobListingService {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Override
    public void saveJobListing(JobListing jobListing) {
        jobListingRepository.save(jobListing);
    }

    @Override
    public JobListing findById(long id) {
        return jobListingRepository.findOne(id);
    }

    @Override
    public List<JobListing> getAll() {
        List<JobListing> jobListings = new ArrayList<>();
        jobListingRepository.findAll().forEach(jobListing ->
                {
                    jobListing.setDescription(jobListing.getDescription().substring(0, 5) + "..");
                    jobListings.add(jobListing);
                }
        );
        return jobListings;
    }

    @Override
    public void delete(long id) {
        jobListingRepository.delete(id);
    }
}
