package com.example.demo.repository;

import com.example.demo.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {

    JobListing findByTitle(String str);

}
