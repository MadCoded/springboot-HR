package com.example.demo.controller;

import com.example.demo.model.JobListing;
import com.example.demo.service.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class JobListingController {

    @Autowired
    private JobListingService jobListingService;


    @RequestMapping(value={"/", "/joblistings"}, method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("jobListings",jobListingService.getAll());
        mv.setViewName("jobListing/index");

        return  mv;
    }

    @RequestMapping(value="/admin/joblistings/create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView();
        JobListing jobListing = new JobListing();
        mv.addObject("jobListing", jobListing);
        mv.setViewName("jobListing/create");
        return mv;
    }

    @RequestMapping(value="/joblistings", method = RequestMethod.POST)
    public ModelAndView store(@Valid JobListing jobListing, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasErrors()) {
            mv.setViewName("jobListing/create");
        } else {
            jobListingService.saveJobListing(jobListing);
            mv.addObject("successMessage", "JobListing has been registered successfully");

            mv.addObject("jobListing", jobListing);
            mv.setViewName("jobListing/create");
        }
        return mv;
    }

    @RequestMapping(value = "/joblistings/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable long id){
        JobListing jobListing = jobListingService.findById(id);
        if(jobListing==null){
            return new ModelAndView("redirect:/joblistings");
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("jobListing",jobListing);
        mv.setViewName("jobListing/detail");
        return  mv;
    }

    @RequestMapping(value = "/admin/joblistings/{id}/delete",method = RequestMethod.POST)
    public String delete(@PathVariable long id){
        jobListingService.delete(id);
        return "redirect:/joblistings";
    }

}
