package com.example.demo.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "appeal")
public class Appeal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "appeal_id")
    private long id;

    @Column(name = "email")
    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    @Column(name = "name")
    @NotEmpty(message = "*Please provide your name")
    private String name;

    @Column(name = "phone")
    @NotEmpty(message = "*Please provide your phone number")
    private String phone;

    @Column(name = "address")
    private String address;


    @Column(name = "thoughts")
    @Size(max = 2000)
    private String thoughts;

    @Column(name = "cv")
    //@NotEmpty(message = "*Please provide your resume") // This validation will take place in contoller
    private String cvURI;


    @ManyToOne(targetEntity = JobListing.class)
    @JoinColumn(name = "joblisting_id")
    private JobListing jobListing;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getThoughts() {
        return thoughts;
    }

    public void setThoughts(String thoughts) {
        this.thoughts = thoughts;
    }

    public String getCvURI() {
        return cvURI;
    }

    public void setCvURI(String cvURI) {
        this.cvURI = cvURI;
    }

    public JobListing getJobListing() {
        return jobListing;
    }

    public void setJobListing(JobListing jobListing) {
        this.jobListing = jobListing;
    }
}



