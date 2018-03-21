package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;












import com.tinhvan.controller.EffortController;
import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Effort;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;
import com.tinhvan.model.Permission;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class EfffortTest {
	private MockMvc mockmvc;
	@Mock
	ProjectDao projectDao;
	@Mock
	EffortDao effortDao;
	@Mock
	UserDao userDao;
	@Mock
	PermissionDao per;
	@SuppressWarnings("deprecation")
	@Mock
	private Principal principal;
	@Spy
	@InjectMocks
	private EffortController effortController;
	/*@InjectMocks
	private EffortDao effortDaoDao;*/
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(effortController).build();
		/*this.mockmvc = MockMvcBuilders.standaloneSetup(effortDaoDao).build();*/
		//MockitoAnnotations.initMocks(effortController);
	}
	
	@Test
	public void a() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		/*Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);*/
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		//EffortController effortController;
		
		
		
		//ModelAttribute model = MockitoAnnotations();
		
//		Mockito.when( userDao.getUserInfoByUserMail(user.getUser_mail())).thenReturn(user);
//		userDao.getUserInfoByUserMail("daicq@tinhvan.com");
//		MockMvcResultMatchers.model().attribute("UserInformation", "daicq@tinhvan.com");
		//User modelP = effortController.getUserCurrentLogin((Principal) user);
		//Mockito.verify("", modelP);
		//mockmvc.perform((RequestBuilder) modelP).andExpect(MockMvcResultMatchers.model().attribute("UserInformation", modelP))
		//.andReturn();
		//List<ProjectInfo> modeU = effortController.getListProject((Principal) user);
		 when(principal.getName()).thenReturn(user.getUser_mail());
		 when(effortController.getUserCurrentLogin(principal)).thenReturn(user);
//		when(userDao.getUserInfoByUserMail("daicq@tinhvan.com")).thenReturn(user);
//		 ((MockHttpServletRequestBuilder) mockmvc.perform(get("/effort")))
//         .andExpect(view().name("addClient"))
//         .andExpect(forwardedUrl("/WEB-INF/pages/addClient.jsp"))
//         .andExpect(model().attributeExists("client"))
//		 .flashAttr("principal", new Principal());
//         .andExpect(model().attribute("UserInformation", user));
		 
		 
		 
		//MockHttpServletRequestBuilder contentType = Mock
		/*
		when(per.checker("eff_mana")).thenReturn(true);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("effortManagementPage"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());*/
		
	}
	
	@Test
	public void a1() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		/*Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);*/
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		List<ProjectInfo> p = new ArrayList<>();
		p.add(projectInfo);
		//EffortController effortController;
		User u = new User();
		
		// when(principal.getName()).thenReturn(user.getUser_mail());
		when(userDao.getUserInfoByUserMail("daicq@tinhvan.com")).thenReturn(u);
		//User u = userDao.getUserInfoByUserMail("daicq@tinhvan.com");
		when(u.getRole_id()).thenReturn(1);
		when(effortController.getListProject(principal)).thenReturn(p);
	}
	
	@Test
	public void effManageTest_true() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		/*Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);*/
//Principal principal;
//		Mockito.when( userDao.getUserInfoByUserMail(user.getUser_mail())).thenReturn(user);
//		userDao.getUserInfoByUserMail("daicq@tinhvan.com");
//		MockMvcResultMatchers.model().attribute("UserInformation", "daicq@tinhvan.com");
		/*User modelP = effortController.getUserCurrentLogin((Principal) user);
		List<ProjectInfo> modeU = effortController.getListProject((Principal) user);*/
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effort/effortManagement"); 
		
		when(per.checker("eff_mana")).thenReturn(true);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("effortManagementPage"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		
	}
	
	@Test
	public void effManageTest_false() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(3);
		user.setUser_passWord("123456");
		/*Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);*/
		
		userDao.getUserInfoByUserMail("daicq@tinhvan.com");
		MockMvcResultMatchers.model().attribute("UserInformation", "daicq@tinhvan.com");
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effort/effortManagement"); 
		when(per.checker("eff_mana")).thenReturn(false);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page2"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		
	}
	
	@Test
	public void effCanTest_true() throws Exception{
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(3);
		user.setUser_passWord("123456");
		//Permission permission = new Permission();
		/*permission.setEff_mana(true);
		permission.setEff_can(true);*/
		
		Effort e = new Effort();
		e.setProject_id(1);
		e.setChange_Request(2);
		e.setOver_head(4);
		e.setProject_actual_cost(4);
		e.setProject_charge_cost(2);
		e.setProject_name("aaa");
		e.setStatus_id(3);
		e.setStatus_type("sda");
		e.setWidth_project_actual_cost(10);
		e.setWidth_project_charge_cost(10);
		when(effortDao.getEffortById(e.getProject_id())).thenReturn(e);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effort/effortCalculate/{id}",1); 
		when(per.checker("eff_can")).thenReturn(true);
		
		
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attribute(Effort, e))\
				.andExpect(model().attributeExists("effort"))
				//.andExpect(MockMvcResultMatchers.view().name("effortCanculate"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void effCanTest_false() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effort/effortCalculate/{id}",1); 
		when(per.checker("eff_can")).thenReturn(false);
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
	}
}
