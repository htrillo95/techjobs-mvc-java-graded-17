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
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    //TASK 3 BELOW
    @PostMapping(value = "results") //handles post request
    public String displaySearchResults(Model model, @RequestParam String searchType, //param 1
                                                    @RequestParam String searchTerm) //param 2
    {
        ArrayList<Job> jobs;
        if (searchType.equals("all") || searchTerm.isEmpty()) { //lists all or left empty depending on input
            jobs = JobData.findAll(); //based on search type/term
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm); //else list based on search type/term
        }
        model.addAttribute("jobs", jobs); //adds to model
        model.addAttribute("columns", columnChoices); //adds to model
        return "search"; //returns display search to be passed into view
    }

}

