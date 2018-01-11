package com.workplace.employee.controller;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.workplace.employee.controller.EmployeeController;
import com.workplace.employee.model.Employee;
import com.workplace.employee.service.EmployeeService;


@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest{
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
    private EmployeeService employeeService;
	
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void testEmployee() throws Exception {
		List<Employee> allEmployees = Arrays.asList(new Employee(1,"sushmi","SE",30000));
		when(employeeService.getAllEmployees()).thenReturn(allEmployees);
		mockMvc.perform(get("/getAllEmployees")).andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.[0].id", Matchers.is(1))).andExpect(jsonPath("$.[0].designation",Matchers.is("SE")))
				.andExpect(jsonPath("$.[0].name", Matchers.is("sushmi"))).andExpect(jsonPath("$.[0].salary", Matchers.is(30000.0)));
		verify(this.employeeService).getAllEmployees();

	}

}
