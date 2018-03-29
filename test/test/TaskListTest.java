package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
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
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tinhvan.controller.TaskController;
import com.tinhvan.controller.TaskListController;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class TaskListTest {
	private MockMvc mockMvc;
	@Mock
	ProjectDao projectDao;
	@Mock
	TypeDao typeDao;
	@Mock
	StatusDao statusDao;
	@Mock
	MemberProjectDao memberProjectDao;
	@Mock
	CategoryDao categoryDao;
	@Mock
	TaskInfoDao taskInfoDao;
	@Mock 
	UserDao userDao;
	@Mock
	PermissionDao per;
	@Mock
	Principal principal;
	@InjectMocks
	TaskListController taskListController;
	
	@Before
	public void setUp() {
		
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
	    
	    mockMvc = MockMvcBuilders.standaloneSetup(new TaskListTest())
                .setViewResolvers(viewResolver)
                .build();
	    
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(taskListController).build();
	}
	
	/**
	 * @purpose: Function test for function set User info
	 */
//	@Test
//	public void getUserLogin() {
//		User user = new User();
//		user.setUser_id(1);
//		user.setUser_fullName("Admin");
//		user.setUser_mail("Manhnv@gmail.com");
//		user.setRole_id(1);
//		user.setUser_passWord("123456");
//		when(principal.getName()).thenReturn(user.getUser_mail());
//		when(taskListController.getUserCurrentLogin(principal)).thenReturn(user);
//	}
	
	/**
	 * @purpose: Function test for function Mapping get dataById for updateTaskSpecIssue case true
	 */
	@Test
	public void editTaskTrue() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/taskList/{id}/editTask/{idP}",1,1); 
		when(per.checker("upd_iss")).thenReturn(true);
		ProjectInfo p = projectDao.getProjectById(2);
		MvcResult result = mockMvc.perform(contentType)
				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Infor", p))
			  //.andExpect(view().name("createTaskSpecIssue"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping get dataById for updateTaskSpecIssue case false
	 */
	@Test
	public void editTaskFalse() throws Exception{
		when(per.checker("upd_iss")).thenReturn(false);
		mockMvc.perform(get("/taskList/{id}/editTask/{idP}",1,1))
				.andExpect(view().name("403Page"))
				.andReturn();
	}
	
	/**
	 * @purpose: Function test for function Mapping view ListTask case true
	 */
	@Test
	public void listTaskTrue() throws Exception{
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/taskList")
		.param("projectName", "0")
		.param("type_id", "0")
		.param("status_id", "0")
		.param("member_project_id", "0")
		.param("task_priority", "0");
		
		when(per.checker("cre_iss")).thenReturn(true);

		MvcResult result = mockMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("taskList"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping view ListTask case false
	 */
	@Test
	public void listTaskFalse() throws Exception{
		when(per.checker("cre_iss")).thenReturn(false);
		mockMvc.perform(get("/taskList"))
				.andExpect(view().name("403Page"))
				.andReturn();
	}

}
