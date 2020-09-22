package com.CovidTracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.CovidTracker.Services.CovidDataService;
import com.CovidTracker.models.LocationStats;

@Controller
public class HomeController {

	@Autowired
	CovidDataService covidDataService;

	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = covidDataService.getAllStats();

		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int differenceInCases = allStats.stream().mapToInt(stat -> stat.getDifferenceFromPreviousDay()).sum();

		model.addAttribute("locationStats", allStats);
		model.addAttribute("differenceInCases", differenceInCases);
		model.addAttribute("totalReportedCases", totalReportedCases);
		return "home";
	}

}
