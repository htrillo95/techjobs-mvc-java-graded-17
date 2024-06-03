package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search") //maps requests with "search" to methods in this class
public class SearchController {

    @GetMapping(value = "") //method displays initial search form
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    //TASK 3 BELOW
                //method for search form/submission/display results
    @PostMapping(value = "results") //handles post request
    public String displaySearchResults(Model model, @RequestParam String searchType, //param 1
                                                    @RequestParam String searchTerm) //param 2
    {
        ArrayList<Job> jobs; //list to store search results

        if (searchTerm.toLowerCase().equals("all") || searchTerm.trim().isEmpty()) { //lists all or empty depending on input
            jobs = JobData.findAll(); //jobs fetched based on search type/term
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm); //else list based on search type/term
        }
        model.addAttribute("jobs", jobs); //adds to model
        model.addAttribute("columns", columnChoices); //adds to model
        return "search"; //returns display search to be passed into view
    }

}

//
