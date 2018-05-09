package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Permission;
import com.tinhvan.model.PreDefinedTask;
import com.tinhvan.model.Process;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.Type;
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
	
	List<ProjectInfo> pi = new ArrayList<ProjectInfo>();
	List<TimeSheetDetail> listAllTimeSheetDetails = new ArrayList<TimeSheetDetail>();
	List<MemberProject> listMem = new ArrayList<MemberProject>();
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(sheetListController).setViewResolvers(viewResolver).build();
		
		
	}
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(sheetListController.getUserCurrentLogin(principal)).thenReturn(user);
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
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(1);
		when(projectDao.getAllProject()).thenReturn(pi);
		when(sheetListController.getListProject(principal)).thenReturn(pi);
	}
	
	@Test
	public void getListProject_Test_false() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(2);
		user.setUser_passWord("123456");
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(2);
		when(projectDao.getListPRojectOfUserAccessed(user.getUser_id())).thenReturn(pi);
		when(sheetListController.getListProject(principal)).thenReturn(pi);
	}
	
	
	@Test
	public void listTimeSheet_TestForRoleIs1() throws Exception{
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
		
		
		
	//	 principal = Mockito.mock(Principal.class);
		 
		 when(principal.getName()).thenReturn(user.getUser_mail());
		 when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
			
			/*when(principal.getName()).thenReturn("daicq@tinhvan.com");
			when(request.getUserPrincipal()).thenReturn(principal);*/
		 
		 when(userDao.getUserInfoByUserMail(user.getUser_mail())).thenReturn(user);
		 when(timeSheetDao.getAllTimeSheet()).thenReturn(listAllTimeSheetDetails);
		 when(memberProjectDao.get_All_MemberProjects_filter_duplicate()).thenReturn(listMem);
//		 when(sheetListController.listTimeSheet(principal, model)).thenReturn("timeSheetList");
		 
		 MvcResult result = mockmvc.perform(get("/timeSheetList")
					.principal(principal))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("timeSheetList"))
					.andReturn();
			
			Assert.assertNotNull(result.getModelAndView());
	//	 when(sheetListController.listTimeSheet(principal, model)).thenReturn("timeSheetList");
		 
	}
	
	@Test
	public void listTimeSheet_TestForRoleIs2() throws Exception{
		User user=new User();
		user.setUser_id(2);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(2);
		user.setUser_passWord("123456");
		
		 
		 when(principal.getName()).thenReturn(user.getUser_mail());
		 when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
			
			/*when(principal.getName()).thenReturn("daicq@tinhvan.com");
			when(request.getUserPrincipal()).thenReturn(principal);*/
		 
		 when(userDao.getUserInfoByUserMail(user.getUser_mail())).thenReturn(user);
		 when(memberProjectDao.getListMemberProject_By_Current_User_Is_PM_filter_duplicate(user.getUser_id())).thenReturn(listMem);
		 when(timeSheetDao.getTimeSheetDetailsNoConditionsOfPM(user.getUser_id())).thenReturn(listAllTimeSheetDetails);
		 when(projectDao.getListPRojectOfUserAccessed(user.getUser_id())).thenReturn(pi);
		 

		 MvcResult result = mockmvc.perform(get("/timeSheetList")
				 	/*.flashAttr("UserInformation", user)
					.flashAttr("list_Project_For_menu", pi)*/
					/*.flashAttr("projectName", new ArrayList<ProjectInfo>())
					.flashAttr("pic", new ArrayList<MemberProject>())
					.flashAttr("process", new ArrayList<Process>())
					.flashAttr("timeSheetStatus", new ArrayList<Status>())
					.flashAttr("pre_defined", new ArrayList<PreDefinedTask>())
					.flashAttr("timsheetTypes", new ArrayList<Type>())
					.flashAttr("picL", new ArrayList<MemberProject>())*/
					.principal(principal))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("timeSheetList"))
					.andReturn();
			
			Assert.assertNotNull(result.getModelAndView());
		 
	}
	
	@Test
	public void listTimeSheet_TestForNoRole() throws Exception{
		User user=new User();
		user.setUser_id(3);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq3@tinhvan.com");
		user.setRole_id(3);
		user.setUser_passWord("123456");
		 
		 when(principal.getName()).thenReturn(user.getUser_mail());
		 when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
		 
		 MvcResult result = mockmvc.perform(get("/timeSheetList")
				 	/*.flashAttr("UserInformation", user)
					.flashAttr("list_Project_For_menu", pi)*/
					/*.flashAttr("projectName", new ArrayList<ProjectInfo>())
					.flashAttr("pic", new ArrayList<MemberProject>())
					.flashAttr("process", new ArrayList<Process>())
					.flashAttr("timeSheetStatus", new ArrayList<Status>())
					.flashAttr("pre_defined", new ArrayList<PreDefinedTask>())
					.flashAttr("timsheetTypes", new ArrayList<Type>())
					.flashAttr("picL", new ArrayList<MemberProject>())*/
					.principal(principal))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andExpect(MockMvcResultMatchers.view().name("403Page"))
					.andReturn();
			
			Assert.assertNotNull(result.getModelAndView());
	}
	
	
	@Test
	public void saveTest() throws JsonProcessingException{
		ArrayList<TimeSheetDetail> list = new ArrayList<TimeSheetDetail>();
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson1 = ow.writeValueAsString(list);
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/actionUpdateStatusTypeOfListTimesheets");
		when(timeSheetDao.updateStatusOfListTimeSheetDetails(list)).thenReturn(true);
		when(sheetListController.save(list)).thenReturn(true);
		
	}
	
	@Test
	public void getAllProjectTest(){
		when(projectDao.getAllProject()).thenReturn(pi);
		when(sheetListController.getAllProject()).thenReturn(pi);
	}
	
	@Test
	public void getPICTest(){
		when(memberProjectDao.getAllMember()).thenReturn(listMem);
		when(sheetListController.getPIC()).thenReturn(listMem);
	}
	
	@Test
	public void getProcessTest(){
		List<Process> listProcesses = new ArrayList<Process>();
		when(processDao.getAll()).thenReturn(listProcesses);
		when(sheetListController.getProcess()).thenReturn(listProcesses);
	}
	
	@Test
	public void getStatusOfTimeSheetTest(){
		List<Status> listStatus = new ArrayList<Status>();
		when(statusDao.getStatusOfTS()).thenReturn(new ArrayList<Status>());
		when(sheetListController.getStatusOfTimeSheet()).thenReturn(listStatus);
	}
	
	@Test
	public void getPreDefinedTasksTest(){
		List<PreDefinedTask> definedTasks = new ArrayList<PreDefinedTask>();
		when(definedTaskDao.getAll()).thenReturn(definedTasks);
		when(sheetListController.getPreDefinedTasks()).thenReturn(definedTasks);
	}
	
	
	@Test
	public void getTypeOfTimeSheetTest(){
		List<Type> list = new ArrayList<Type>();
		when(typeDao.getAllTypeOfTimeSheet()).thenReturn(list);
		when(sheetListController.getTypeOfTimeSheet()).thenReturn(list);
	}
	
	@Test
	public void getPICLTest(){
		when(memberProjectDao.getMember()).thenReturn(listMem);
		when(sheetListController.getPICL()).thenReturn(listMem);
	}
}
