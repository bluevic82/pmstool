package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.tinhvan.controller.BugController;
import com.tinhvan.controller.ProjectController;
import com.tinhvan.dao.BugInfoDao;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.BugInfo;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class Bugtest {
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
	BugInfoDao bugInfoDao;
	@Mock
	UserDao userDao;
	@Mock
	PermissionDao per;
	@Mock
	Principal principal;
	@InjectMocks
	private BugController bugController;
	
	@Before
	public void initTest() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(bugController).build();
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
		when(bugController.getUserCurrentLogin(principal)).thenReturn(user);
	}*/
	
	/**
	 * @purpose: Function test for function Mapping view page createBug case if
	 */
	@Test
	public void createBugTrue() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/createBug",3); 
		when(per.checker("cre_iss")).thenReturn(true);
		ProjectInfo p = projectDao.getProjectById(3);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Infor", p))
				  .andExpect(view().name("createBug"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping view page createBug case else
	 */
	@Test
	public void createBugFalse() throws Exception{
		when(per.checker("cre_iss")).thenReturn(false);
		mockmvc.perform(get("/{id}/createBug",1))
				.andExpect(view().name("403Page"))
				.andReturn();
	}
	
	/**
	 * @purpose: Function test for function Mapping button click createBug
	 */
	@Test
	public void actionCreateBug() throws Exception {	
		BugInfo bugInfo = new BugInfo();
		bugInfo.setBug_id(1);
		bugInfo.setBug_subject("This is name's Bug");
		bugInfo.setBug_done(50);
		bugInfo.setBug_from("2018-03-22");
		bugInfo.setBug_to("2018-03-23");
		bugInfo.setBug_description("This is description");
		bugInfo.setBug_solution("This is Solution");
		bugInfo.setType_id(5);
		ArrayList<BugInfo> lstBug = new ArrayList<BugInfo>();
		lstBug.add(bugInfo);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionCreateBug",1)
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(bugInfoDao.getBugByIdPro(1)).thenReturn(lstBug);
		MockHttpServletRequest request = new MockHttpServletRequest();
		ModelMap model = null;
		when(bugController.actionCreate(bugInfo, 1, request, model)).thenReturn(lstBug);
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
				
		System.out.println(result.getResponse());
	}
	
	/**
	 * @purpose: Function test for function Mapping button saveBug
	 */
	@Test
	public void actionUpdateTask() throws Exception {
		BugInfo bugInfo = new BugInfo();
		bugInfo.setBug_id(1);
		bugInfo.setBug_subject("This is name's Bug");
		bugInfo.setBug_done(50);
		bugInfo.setBug_from("2018-03-22");
		bugInfo.setBug_to("2018-03-23");
		bugInfo.setBug_description("This is description");
		bugInfo.setBug_solution("This is Solution");
		bugInfo.setType_id(5);
		ArrayList<BugInfo> lstBug = new ArrayList<BugInfo>();
		lstBug.add(bugInfo);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/bugList/{id}/{idP}/actionUpdateBug",1,1)
				.accept(MediaType.APPLICATION_JSON);
		
		Mockito.when(bugInfoDao.getBugByIdPro(1)).thenReturn(lstBug);
		MockHttpServletRequest request = new MockHttpServletRequest();
		when(bugController.actionUpdate(1, bugInfo, request)).thenReturn(lstBug);
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
		System.out.println(result.getResponse());
	}
	
}
