package test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhvan.controller.TimeSheetRegisterController;
import com.tinhvan.dao.MemberProjectDao;
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
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Role;
import com.tinhvan.model.Status;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.TimeSheetDetail;
import com.tinhvan.model.Type;
import com.tinhvan.model.User;
import com.tinhvan.model.UserInfo;
import com.tinhvan.model.Process;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class TimesheetTest {
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
	@Mock
	PreDefinedTaskDao definedTaskDao;
	@Mock
	TaskInfoDao TaskInfoDao;
	@Mock
	UserDao userDao;
	@Mock
	PreDefinedTaskDao preDefinedTaskDao;
	@Mock
	Principal principal;
	@Mock
	Model model;
	@InjectMocks
	TimeSheetRegisterController timesheetController;
	List<ProjectInfo> pi = new ArrayList<ProjectInfo>();
	List<MemberProject> list_memMemberProjects = new ArrayList<MemberProject>();
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(timesheetController).setViewResolvers(viewResolver).build();
	}
	
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(timesheetController.getUserCurrentLogin(principal)).thenReturn(user);
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
		Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);
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
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(1);
		when(projectDao.getAllProject()).thenReturn(p);
		when(timesheetController.getListProject(principal)).thenReturn(p);
		Assert.assertEquals(1, p.size());
	}
	
	@Test
	public void getListProject_Test_false() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(2);
		user.setUser_passWord("123456");
		Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		List<ProjectInfo> p = new ArrayList<ProjectInfo>();
		p.add(projectInfo);
		//EffortController effortController;
		User u = new User();
		
		
	//	Principal principal = Mockito.mock(Principal.class);
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
	//	when(user.getRole_id()).thenReturn(2);
		when(projectDao.getListPRojectOfUserAccessed(user.getUser_id())).thenReturn(p);
		when(timesheetController.getListProject(principal)).thenReturn(p);
		Assert.assertEquals(1, p.size());
	}
	
	@Test
	public void registerTimesheetTest() throws Exception{
		ProjectInfo projectInfo = new ProjectInfo();
		MemberProject memberProject = new MemberProject();
		
		User user = new User();
		user.setUser_id(2);
		when(projectDao.getProjectById(1)).thenReturn(projectInfo);
		
		String message = "Access denied for "+principal.getName()+"!";
		int id=1;
		when(timesheetController.getUserCurrentLogin(principal)).thenReturn(user);
	//	when(timesheetController.regisTimeSheet(id, model, principal)).thenReturn("403Page");
		
	//	when(user.getUser_id()).thenReturn(2);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/registerTimeSheet",id); 
		
		when(memberProjectDao.getMemberProjectByProject_Id_And_UserCurrentLogged(1, 2)).thenReturn(null);
		MvcResult result = mockmvc.perform(contentType
				
				.principal(principal))
			//	.flashAttr("message", message))
				
				.andExpect(MockMvcResultMatchers.model().attribute("message", message))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		
		when(memberProjectDao.getMemberProjectByProject_Id_And_UserCurrentLogged(1, 2)).thenReturn(memberProject);
		MvcResult result2 = mockmvc.perform(contentType
				
				.principal(principal))
			//	.flashAttr("message", message))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("timesheetRegister"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		Assert.assertNotNull(result2.getModelAndView());
	}
	
	
	
	@Test
	public void saveTest() throws Exception{
		
		TimeSheetDetail timeSheetDetail1 = new TimeSheetDetail();
		timeSheetDetail1.setDetail_timesheet_id(0);
		TimeSheetDetail timeSheetDetail2 = new TimeSheetDetail();
		timeSheetDetail2.setDetail_timesheet_id(2);
		ArrayList<TimeSheetDetail> liTimeSheetDetails = new ArrayList<TimeSheetDetail>();
		liTimeSheetDetails.add(timeSheetDetail1);
		liTimeSheetDetails.add(timeSheetDetail2);
		ArrayList<TimeSheetDetail> list_TimeSheetDetails_To_Insert = new ArrayList<TimeSheetDetail>();
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson1 = ow.writeValueAsString(liTimeSheetDetails);
		int id=1;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveTimeSheet", id).accept(
				MediaType.APPLICATION_JSON).content(requestJson1).contentType(MediaType.APPLICATION_JSON);
		
		for(int i=0;i<liTimeSheetDetails.size();i++){
			list_TimeSheetDetails_To_Insert.add(liTimeSheetDetails.get(i));
			
		}
		when(timeSheetDao.createListTimeSheet(list_TimeSheetDetails_To_Insert)).thenReturn(true);
		when(timeSheetDao.updateListTimeSheetToDB(list_TimeSheetDetails_To_Insert)).thenReturn(true);
		when(timesheetController.save(id, liTimeSheetDetails, principal)).thenReturn(true);
	}
	
	@Test
	public void deleteListTimeSheetTest() throws Exception{
		TimeSheetDetail timeSheetDetail1 = new TimeSheetDetail();
		//TimeSheetDetail timeSheetDetail2 = new TimeSheetDetail();
		timeSheetDetail1.setTs_id(1);
		ArrayList<TimeSheetDetail> list_TimeSheetDetails_DeleteBefor = new ArrayList<TimeSheetDetail>();
		list_TimeSheetDetails_DeleteBefor.add(timeSheetDetail1);
		
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson1 = ow.writeValueAsString(list_TimeSheetDetails_DeleteBefor);
		
		int id=1;
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionDeleteListTimeSheet", id).accept(
				MediaType.APPLICATION_JSON).content(requestJson1).contentType(MediaType.APPLICATION_JSON);
		
				when(timeSheetDao.deleteListTimeSheet(list_TimeSheetDetails_DeleteBefor)).thenReturn(true);
				when(timesheetController.deleteListTimeSheet(list_TimeSheetDetails_DeleteBefor)).thenReturn(true);
		
	}
	
	@Test
	public void getAllProjectTest(){
		when(projectDao.getAllProject()).thenReturn(pi);
		when(timesheetController.getAllProject()).thenReturn(pi);
	}
	
	@Test
	public void getPICTest(){
		when(memberProjectDao.getAllMember()).thenReturn(list_memMemberProjects);
		when(timesheetController.getPIC()).thenReturn(list_memMemberProjects);
	}
	
	@Test
	public void getProcessTest(){
		List<Process> listProcesses = new ArrayList<Process>();
		when(processDao.getAll()).thenReturn(listProcesses);
		when(timesheetController.getProcess()).thenReturn(listProcesses);
	}
	
	@Test
	public void getStatusOfTimeSheetTest(){
		List<Status> listStatus = new ArrayList<Status>();
		when(statusDao.getStatusOfTS()).thenReturn(new ArrayList<Status>());
		when(timesheetController.getStatusOfTimeSheet()).thenReturn(listStatus);
	}
	
	@Test
	public void getPreDefinedTasksTest(){
		List<PreDefinedTask> definedTasks = new ArrayList<PreDefinedTask>();
		when(preDefinedTaskDao.getAll()).thenReturn(definedTasks);
		when(timesheetController.getPreDefinedTasks()).thenReturn(definedTasks);
	}
	
	
	@Test
	public void getTypeOfTimeSheetTest(){
		List<Type> list = new ArrayList<Type>();
		when(typeDao.getAllTypeOfTimeSheet()).thenReturn(list);
		when(timesheetController.getTypeOfTimeSheet()).thenReturn(list);
	}
	
	
	@Test
	public void getTaskInfosTest(){
		ProjectInfo pj = new ProjectInfo();
		pj.setProject_id(1);
		List<TaskInfo> listTaskInfos = new ArrayList<TaskInfo>();
		when(projectDao.getProjectById(1)).thenReturn(pj);
		when(TaskInfoDao.getTaskInfo_By_Status_Open_And_OnGoing(pj.getProject_id())).thenReturn(listTaskInfos);
		when(timesheetController.getTaskInfos(pj.getProject_id(), model)).thenReturn(listTaskInfos);
	}
	
	@Test
	public void getPICLTest(){
		when(memberProjectDao.getMember()).thenReturn(list_memMemberProjects);
		when(timesheetController.getPICL()).thenReturn(list_memMemberProjects);
	}
	
	
	
}
