package com.greedy.jpadata.menu.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.greedy.jpadata.common.Pagenation;
import com.greedy.jpadata.common.PagingButtonInfo;
import com.greedy.jpadata.menu.dto.EmployeeDTO;
import com.greedy.jpadata.menu.dto.JobDTO;
import com.greedy.jpadata.menu.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {
	
	private final EmployeeService employeeService;

	public MenuController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/{empId}")
	public String findOne(@PathVariable(name = "empId") String empId, Model model) {
	    EmployeeDTO emp = employeeService.findEmployeeByCode(empId);
	   model.addAttribute("emp", emp);
	    return "menu/one";
	}
	
	
	@GetMapping("/list")
	public String findMenuList(@PageableDefault Pageable pageable, Model model) {
		/*page -> number , size, sort 파라미터가 Pageable 객체에 담긴다.*/
//		log.info("pageable : {} " ,pageable);

		Page<EmployeeDTO> empList = employeeService.findEmployeeList(pageable);

		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(empList);

		model.addAttribute("paging",paging);
		model.addAttribute("empList",empList);

		return "menu/list";
	}
	
	//페이지 이동 메소드
	@GetMapping("/querymethod")
	public void queryMethodPage() {}

	@GetMapping("/test1")
	public String menuTest1(@RequestParam("salary") Integer salary, Model model) {
	    List<EmployeeDTO> empList = employeeService.menuTest1(salary);
	    model.addAttribute("empList", empList);
	    return "menu/list2";
	}

	
	
	//추가하기
	@GetMapping("/regist")
	public void registPage() {}
	
	@GetMapping(value = "/job",produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<JobDTO> findJobList() {
		
		return employeeService.findAllJob();
	}
	
	@PostMapping("/regist")
	public String registNewEmp(EmployeeDTO emp) {

	employeeService.registNewEmp(emp);

	return "redirect:/menu/list";
	}
	
	
	//삭제
	@GetMapping("/delete")
	public void deletePage() {

	}
	
	@PostMapping("/delete")
	public String deleteMenu(@RequestParam String empId) {

		employeeService.deleteMenu(empId);
		return "redirect:/menu/list";
	}

//	@PostMapping("/delete")
//	public String deleteMenu(@RequestParam Integer menuCode) {
//
//		employeeService.deleteMenu(menuCode);
//		return "redirect:/menu/list";
//	}
	

	
	

}
