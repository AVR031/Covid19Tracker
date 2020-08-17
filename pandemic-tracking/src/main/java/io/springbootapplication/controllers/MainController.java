package io.springbootapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.springbootapplication.models.LocationBean;
import io.springbootapplication.service.DataService;

@Controller
public class MainController {
	
	@Autowired
	DataService dataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		List<LocationBean> allCases = dataService.getAlldata();
		//Converting object to stream and doing a sum and assigning it to integer
		int totalCases = allCases.stream().mapToInt(stat -> stat.getTotalCases()).sum();
		
		model.addAttribute("locationBeans", allCases);
		//Set Attribute total cases
		model.addAttribute("totalCases", totalCases);
		
		return "home";
	}
}