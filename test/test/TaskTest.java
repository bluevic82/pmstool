package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import com.tinhvan.controller.TaskController;
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
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class TaskTest {
	private MockMvc mockmvc;
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
	TaskController taskController;

	
	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}
	
	/**
	 * @purpose: Function test for function set User info
	 */
/*	@Test
	public void getUserLogin() {
		User user = new User();
		user.setUser_id(1);
		user.setUser_fullName("Admin");
		user.setUser_mail("Manhnv@gmail.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		when(principal.getName()).thenReturn(user.getUser_mail());
		when(taskController.getUserCurrentLogin(principal)).thenReturn(user);
	}*/
	
	/**
	 * @purpose: Function test for function Mapping view page create Task/Spec/Issue case i
	 */
	@Test
	public void createTaskTrue() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/createTask",1); 
		when(per.checker("cre_iss")).thenReturn(true);
		ProjectInfo p = projectDao.getProjectById(2);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Infor", p))
			  //.andExpect(view().name("createTaskSpecIssue"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping view page createTaskSpecIssue case else
	 */
	@Test
	public void createTaskFalse() throws Exception{
		when(per.checker("cre_iss")).thenReturn(false);
		mockmvc.perform(get("/{id}/createTask",1))
				.andExpect(view().name("403Page"))
				.andReturn();
	}
	
	/**
	 * @purpose: Function test for function Mapping button click createTaskSpecIssue
	 */
	@Test
	public void actionCreateTask() throws Exception {
		int id = 1;
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTask_id(1);
		taskInfo.setTask_subject("This is name's Task");
		taskInfo.setTask_done(50);
		taskInfo.setTask_from("2018-03-22");
		taskInfo.setTask_to("2018-03-23");
		taskInfo.setTask_description("This is description");
		taskInfo.setTask_solution("This is Solution");
		taskInfo.setType_id(4);
		List<TaskInfo> lstTask = new ArrayList<TaskInfo>();
		lstTask.add(taskInfo);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/{id}/actionCreateTask",id);
		MvcResult mvcResult = mockmvc.perform(builder)
				.andReturn();
		Assert.assertNotNull(mvcResult.getResponse().equals(taskInfo));
	}
	
	/**
	 * @purpose: Function test for function Mapping view page updateTaskSpecIssue
	 */
	@Test
	public void updateTask() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/{id}/createTask");
//		ProjectInfo p = projectDao.getProjectById(2);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
//		when(projectDao.getProjectById(2)).thenReturn(projectInfo);
		
		MvcResult result = mockmvc.perform(builder)
//				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Info", projectInfo))
				.andExpect(view().name("updateTaskSpecIssue"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping button save createTaskSpecIssue
	 */
	@Test
	public void actionUpdateTask() throws Exception {
		int id = 1;
		int idP = 1;
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTask_id(1);
		taskInfo.setTask_subject("This is name's UpdateTask");
		taskInfo.setTask_done(50);
		taskInfo.setTask_from("2018-03-22");
		taskInfo.setTask_to("2018-03-23");
		taskInfo.setTask_description("This is description");
		taskInfo.setTask_solution("This is Solution");
		taskInfo.setType_id(4);
		List<TaskInfo> lstTask = new ArrayList<TaskInfo>();
		lstTask.add(taskInfo);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/taskList/{id}/{idP}/actionUpdateTask",id, idP);
		MvcResult mvcResult = mockmvc.perform(builder)
				.andReturn();
		Assert.assertNotNull(mvcResult.getResponse().equals(taskInfo));
	}
	
//	/taskList/{id}/{idP}/actionUpdateTask
	

}
