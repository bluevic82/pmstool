package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.condition.MediaTypeExpression;

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

import javax.servlet.http.HttpServletRequest;

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
	 * @purpose: Function test for function Mapping view page create Task/Spec/Issue case if
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
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTask_id(1);
		taskInfo.setTask_subject("This is name's Task");
		taskInfo.setTask_done(50);
		taskInfo.setTask_from("2018-03-22");
		taskInfo.setTask_to("2018-03-23");
		taskInfo.setTask_description("This is description");
		taskInfo.setTask_solution("This is Solution");
		taskInfo.setType_id(4);
		ArrayList<TaskInfo> lstTask = new ArrayList<TaskInfo>();
		lstTask.add(taskInfo);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionCreateTask",1)
				.accept(MediaType.APPLICATION_JSON);
		
		Mockito.when(taskInfoDao.getTaskByIdPro(1)).thenReturn(lstTask);
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelMap model = null;
//		when(taskController.actionCreate(taskInfo, 1, request, model)).thenReturn(lstTask);
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
				
		System.out.println(result.getResponse());
	}
	
	/**
	 * @purpose: Function test for function Mapping button save TaskSpecIssue
	 */
	@Test
	public void actionUpdateTask() throws Exception {
		TaskInfo taskInfo = new TaskInfo();
		taskInfo.setTask_id(1);
		taskInfo.setTask_subject("This is name's Task");
		taskInfo.setTask_done(50);
		taskInfo.setTask_from("2018-03-22");
		taskInfo.setTask_to("2018-03-23");
		taskInfo.setTask_description("This is description");
		taskInfo.setTask_solution("This is Solution");
		taskInfo.setType_id(4);
		ArrayList<TaskInfo> lstTask = new ArrayList<TaskInfo>();
		lstTask.add(taskInfo);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/taskList/{id}/{idP}/actionUpdateTask",1,1)
				.accept(MediaType.APPLICATION_JSON);
		
		Mockito.when(taskInfoDao.getTaskByIdPro(1)).thenReturn(lstTask);
		MockHttpServletRequest request = new MockHttpServletRequest();
		when(taskController.actionUpdate(1, taskInfo, request)).thenReturn(lstTask);
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
		System.out.println(result.getResponse());
	}
	
}
