package com.example.demo;

import com.example.demo.model.JobListing;
import com.example.demo.service.JobListingService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests2 {

    @Autowired
    private JobListingService jobListingService;

    private long lastCreatedID;

    @After
    public void tearDown() {
        jobListingService.delete(lastCreatedID);
    }

    @Test
    public void testFindAll() {
        Collection<JobListing> list = jobListingService.getAll();

        Assert.assertNotNull("failure - expected not null", list);
        Assert.assertEquals("failure - expected list size", 1, list.size());
    }


    @Test
    public void testFindOneNotFound() {
        Long id = Long.MAX_VALUE;

        JobListing entity = jobListingService.findById(id);
        Assert.assertNull("failure - expected null", entity);
    }

    @Test
    public void testCreate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            parsed = format.parse("2017-07-13");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JobListing entity = new JobListing();
        entity.setPeopleQuota(10);
        entity.setTitle("JobListing Başlık");
        entity.setDescription("desc");
        entity.setLastDate(new java.sql.Date(parsed.getTime()));

        jobListingService.saveJobListing(entity);
        JobListing createdEntity = jobListingService.findById(entity.getId());
        this.lastCreatedID = createdEntity.getId();

        Assert.assertNotNull("failure - expected not null", createdEntity);
        Assert.assertNotNull("failure - expected id attribute not null", createdEntity.getId());
        Assert.assertEquals("failure - expected text attribute match", entity.getTitle(), createdEntity.getTitle());
    }
}
