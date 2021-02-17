package com.luv2code.springboot.thymeleafdemo.controller;


import java.util.List;



import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService){
		employeeService = theEmployeeService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		//get data from db
		List<Employee> theEmployees = employeeService.findAll();
		
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);
		
		return "employees/list-employees";
	}
	@GetMapping("/showFormForAdd")
	public String showformForAdd(Model theModel){

		//create model attribute
		Employee theEmployee = new Employee();

		theModel.addAttribute("employee", theEmployee);

		return "employees/employee_form";
	}

	@GetMapping("/showFormForUpdate")
	public String ShowformForUpdate(@RequestParam("employeeId") int theId, Model theModel){

		Employee thEmployee = employeeService.findById(theId);

		theModel.addAttribute("employee", thEmployee);

		return "employees/employee_form";
	}


	@PostMapping("/save")
	public String SaveDEmployee(@ModelAttribute("employee") Employee theEmployee){

		employeeService.save(theEmployee);

		return "redirect:/employees/list";

	}
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId" ) int theId){
		employeeService.deleteById(theId);

		 return "redirect:/employees/list";
	}

}









