package com.example.demo.model;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;


@Entity
@Table(name = "joblisting")
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "joblisting_id")
    private long id;

    @Column(name = "title")
    @Length(min = 10, message = "*Title must have at least 10 characters")
    @NotEmpty(message = "*Please provide a title")
    private String title;

    @Column(name = "description")
    @Size(max = 5000)
    private String description;

    @Column(name = "people_quota")
    @Min(1)
    @Max(value = 2000, message = "*Max 2000")
    private int peopleQuota;

    @Column(name = "last_date")
    private Date lastDate;

    // SoftDelete
    // @Column(name = "deleted")
    // private boolean deleted = false;

    @OneToMany(mappedBy = "jobListing", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Appeal> appeals;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPeopleQuota() {
        return peopleQuota;
    }

    public void setPeopleQuota(int peopleQuota) {
        this.peopleQuota = peopleQuota;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Set<Appeal> getAppeals() {
        return appeals;
    }

    public void setAppeals(Set<Appeal> appeals) {
        this.appeals = appeals;
    }
}



