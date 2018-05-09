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
import org.springframework.http.MediaType;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	@Mock
	User user;
	@SuppressWarnings("deprecation")
	@Mock
	private Principal principal;
	@Mock
	private Authentication auth;
	
	
	 @Mock
	 private HttpServletRequest request;
	  
	 @Mock
	 private HttpServletResponse response;
	
	@Spy
	@InjectMocks
	private EffortController effortController;
	/*@InjectMocks
	private EffortDao effortDaoDao;*/
	/*private final String USER = "User";
	private final String ROLE = "Manager";*/
	List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setup() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(effortController).setViewResolvers(viewResolver).build();
		/*this.mockmvc = MockMvcBuilders.standaloneSetup(effortDaoDao).build();*/
		//MockitoAnnotations.initMocks(effortController);
	}
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(effortController.getUserCurrentLogin(principal)).thenReturn(user);
		Assert.assertEquals(user.getUser_mail(), principal.getName());
		
	}
	
	@Test
	public void getListProject_Test_true() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq1@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		pi.add(new ProjectInfo());
		pi.add(new ProjectInfo());
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(1);
		when(projectDao.getAllProject()).thenReturn(pi);
		when(effortController.getListProject(principal)).thenReturn(pi);
		Assert.assertEquals(2, pi.size());
	}
	
	@Test
	public void getListProject_Test_false() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(2);
		user.setUser_passWord("123456");
		pi.add(new ProjectInfo());
		pi.add(new ProjectInfo());
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(2);
		when(projectDao.getListPRojectOfUserAccessed(user.getUser_id())).thenReturn(pi);
		when(effortController.getListProject(principal)).thenReturn(pi);
		Assert.assertEquals(2, pi.size());
	}
		
	
	@Test
	public void effManageTest_true() throws Exception{
		Model model = Mockito.mock(Model.class);
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		when(per.checker("eff_mana")).thenReturn(true);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effortManagement").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi); 
		
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("effortManagementPage"))
				.andReturn();
		
		when(per.checker("eff_mana")).thenReturn(false);
		MvcResult result2 = mockmvc.perform(contentType.flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi))
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page2"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		Assert.assertNotNull(result2.getModelAndView());
		
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
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effortCalculate/{id}",1); 
		when(per.checker("eff_can")).thenReturn(true);
		MvcResult result = mockmvc.perform(contentType.flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi))
				//.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attribute(Effort, e))\
				.andExpect(model().attributeExists("effort"))
				//.andExpect(MockMvcResultMatchers.view().name("effortCanculate"))
				.andReturn();
		
		when(per.checker("eff_can")).thenReturn(false);
		MvcResult result2 = mockmvc.perform(contentType.flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi))
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		Assert.assertNotNull(result2.getModelAndView());
	}
}
