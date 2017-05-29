package com.example.demo.controller;

import com.example.demo.helper.PreviousPage;
import com.example.demo.model.Appeal;
import com.example.demo.model.JobListing;
import com.example.demo.service.AppealService;
import com.example.demo.service.JobListingService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;


@Controller
public class AppealController {

    @Autowired
    private AppealService appealService;
    @Autowired
    private JobListingService jobListingService;

    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "F://";


    @RequestMapping(value = "/admin/applications", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("appeals", appealService.getAll());
        mv.setViewName("appeal/index");

        return mv;
    }


    @RequestMapping(value = "/applications/{id}", method = RequestMethod.GET)
    public ModelAndView applicationDetail(@PathVariable long id) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("appeal", appealService.findById(id));
        mv.setViewName("appeal/detail");

        return mv;
    }


    @RequestMapping(value = "/apply/{id}", method = RequestMethod.GET)
    public ModelAndView get(@PathVariable long id) {
        JobListing jobListing = jobListingService.findById(id);
        if (jobListing == null) {
            return new ModelAndView("redirect:/joblistings");
        }
        ModelAndView mv = new ModelAndView();

        Appeal appeal = new Appeal();
        appeal.setJobListing(jobListing);

        mv.addObject("jobListing", jobListing);
        mv.addObject("appeal", appeal);

        mv.setViewName("appeal/form");

        return mv;
    }


    @RequestMapping(value = "/apply/form", method = RequestMethod.POST)
    public ModelAndView post(@RequestParam("cv") MultipartFile file,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest request,
                             @Valid Appeal appeal,
                             BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView();
        mv.setViewName(PreviousPage.getPreviousPageByRequest(request).orElse("/"));

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select your resume to upload");
            return mv;
        } else {
            String realFileName = file.getOriginalFilename();
            String fileExtension = FilenameUtils.getExtension(realFileName);

            String fileExtentions = "doc|docx|pdf";
            if (!fileExtentions.contains(fileExtension)) {
                redirectAttributes.addFlashAttribute("message", "Only doc,docx and pdf files");
                return mv;
            }
        }
        if (bindingResult.hasErrors()) {
            return  mv;
        } else {

            String storeFileName = UUID.randomUUID().toString().replaceAll("-", "");
            try {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + storeFileName);
                Files.write(path, bytes);

                appeal.setCvURI(path.toString());
                appealService.saveAppeal(appeal);

                redirectAttributes.addFlashAttribute("message", "Your apply successfully sent.");

                mv.setViewName("redirect:/apply/result");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mv;

    }

    @GetMapping("/apply/result")
    public String appealResult() {
        return "appeal/result";
    }


}
