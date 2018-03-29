package test;

import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tinhvan.controller.TimeSheetListController;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.PreDefinedTaskDao;
import com.tinhvan.dao.ProcessDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TimeSheetDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class TimesheetListTest {
	private MockMvc mockmvc;
	@Mock
	TimeSheetDao timeSheetDao;
	@Mock
	ProjectDao projectDao;
	@Mock
	TypeDao typeDao;
	@Mock
	StatusDao statusDao;
	@Mock
	MemberProjectDao memberProjectDao;
	@Mock
	ProcessDao processDao;
	@Mock Principal principal;
	@Mock HttpServletRequest request;
	@Mock HttpServletResponse reponse;
	@Mock Model model;
	@Mock
	PreDefinedTaskDao definedTaskDao;
	@Mock
	TaskInfoDao TaskInfoDao;
	@Mock PermissionDao per;
	@Mock
	UserDao userDao;
	@InjectMocks
	TimeSheetListController sheetListController;
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(sheetListController).setViewResolvers(viewResolver).build();
	}
	
	
	@Test
	public void listTimeSheet_Test() throws Exception{
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(request.getUserPrincipal()).thenReturn(principal);
		when(request.isUserInRole("Manager")).thenReturn(true);
		//when(model.)
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/timeSheetList")
					.param("projectName", "0")
					.param("user_id", "0")
					.param("process_id", "0")
					.param("status_name", "");
		 
		 when(userDao.getUserInfoByUserMail(user.getUser_mail())).thenReturn(user);
		 //when(sheetListController.listTimeSheet(0, 0, 0, "", principal, model)).the
		// when(projectDao.getAllProject()).thenReturn(arg0)
		 //when(per.checker("cre_iss")).thenReturn(true);

			MvcResult result = mockmvc.perform(builder)
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("timeSheetList"))
					.andReturn();
			Assert.assertNotNull(result.getModelAndView());
	}
	/*@Test
	public void listTimesheet() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/timeSheetList"); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("timeSheetList"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}*/
}
