package com.tecsup.gestion.controller;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import javax.servlet.ServletContext;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml")
@WebAppConfiguration
public class EmployeeControllerIntegrationTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void VerificarDepartamentId() throws Exception {
		mockMvc.perform(get("/admin/emp/list"))
				.andExpect(status().isOk())
				.andExpect(view().name("admin/emp/list"))
				.andExpect(forwardedUrl("/WEB-INF/views/admin/emp/list.jsp"))
				.andExpect(model().attribute("employees",
						hasItem( 
										
										hasProperty("departamentId", is(1))
										//,
										//hasProperty("department",hasProperty("departmentId", is(12)))
								)));
	}
	
	
	@Test
	public void VerificarRegistros() throws Exception {
	
		mockMvc.perform(get("/admin/emp/editform/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("admin/emp/editform"))
        .andExpect(forwardedUrl("/WEB-INF/views/admin/emp/editform.jsp"))
        .andExpect(model().attribute("command", hasProperty("employeeId", is(1))))
        .andExpect(model().attribute("command", hasProperty("login", is("stef"))))
        .andExpect(model().attribute("command", hasProperty("password", is("123456"))))
        .andExpect(model().attribute("command", hasProperty("firstname", is("stefanny"))))
        .andExpect(model().attribute("command", hasProperty("lastname", is("Aquino"))))
        .andExpect(model().attribute("command", hasProperty("salary", is(1500))));

		mockMvc.perform(get("/admin/emp/editform/2"))
		.andExpect(status().isOk())
		.andExpect(view().name("admin/emp/editform"))
		.andExpect(forwardedUrl("/WEB-INF/views/admin/emp/editform.jsp"))
		.andExpect(model().attribute("command", hasProperty("employeeId", is(2))))
		.andExpect(model().attribute("command", hasProperty("login", is("maria"))))
		.andExpect(model().attribute("command", hasProperty("password", is("123456"))))
		.andExpect(model().attribute("command", hasProperty("firstname", is("maria"))))
		.andExpect(model().attribute("command", hasProperty("lastname", is("Gutierrez"))))
		.andExpect(model().attribute("command", hasProperty("salary", is(2500))));

	}

}
