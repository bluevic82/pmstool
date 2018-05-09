package test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Stub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhvan.controller.MileStoneController;
import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MileStone;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

/*import org.skyscreamer.jsonassert.JSONAssert;*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class MilestoneTest {
	private MockMvc mockmvc;
	@Mock
	MileStoneDao mileStoneDao;
	@Mock
	ProjectDao projectDao;
	@Mock UserDao userDao;
	@Mock
	Principal principal;
	@Mock
	PermissionDao per;
	@InjectMocks
	MileStoneController mileStoneController;
	List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(mileStoneController).setViewResolvers(viewResolver).build();
	}
	
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(mileStoneController.getUserCurrentLogin(principal)).thenReturn(user);
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
		when(mileStoneController.getListProject(principal)).thenReturn(pi);
		
		
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
		when(mileStoneController.getListProject(principal)).thenReturn(pi);
		Assert.assertEquals(2, pi.size());
	}
	
	
	@Test
	public void createMileTrue() throws Exception{
		int id=1;
		when(per.checker("set_reg")).thenReturn(true);
		when(projectDao.getProjectById(id)).thenReturn(new ProjectInfo());
		when(mileStoneDao.getMileStoneByProjectId(id)).thenReturn(new ArrayList<MileStone>());
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/createMileStone",1).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("createMilestone"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		when(per.checker("set_reg")).thenReturn(false);
		MvcResult result2 = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		Assert.assertNotNull(result2.getModelAndView());
	}
	/**/
	
	@Test
	public void actionSave() throws Exception{
		MileStone m = new MileStone();
		m.setProject_id(1);
		m.setMilestone_date("22-11-2018");
		m.setMilestone_description("abcabsa");
		m.setMilestone_id(1);
		/*
		MileStone m2 = new MileStone();
		m2.setProject_id(1);
		m2.setMilestone_date("23-11-2018");
		m2.setMilestone_description("fggggggg");
		m2.setMilestone_id(2);*/
		ArrayList<MileStone> mile = new ArrayList<>();
		mile.add(m);
		//mile.add(m2);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(mile);
		
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",1).accept(
				MediaType.APPLICATION_JSON).content(requestJson).contentType(MediaType.APPLICATION_JSON).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		/*//mileStoneDao.updateMilestone(mile);
		*/
		Mockito.when(mileStoneDao.getMileStoneByProjectId(Mockito.anyInt())).thenReturn(mile);
		
		MvcResult result = mockmvc.perform(requestBuilder)
				//.andExpect(jsonPath("", matcher))
				.andReturn();
		//System.out.println(result.getResponse().);
		//String expected = "[{milestone_id:1,milestone_date:22-11-2018,milestone_description:abcabsa,project_id:1}]";
		String expected = "[{\"milestone_id\":1,\"milestone_date\":\"22-11-2018\",\"milestone_description\":\"abcabsa\",\"project_id\":1}]";
		//<[{["milestone_id":1,"milestone_date":"22-11-2018","milestone_description":"abcabsa","project_id"]:1}]>
		
		Assert.assertEquals(expected, result.getResponse().getContentAsString());
		
		
		//Sonnv make junit test ajax json
				/*Permission perm = new Permission();
				perm.setAdd_pro(true);
				perm.setCre_iss(false);

				List<Permission> perrr = new ArrayList<Permission>();
				perrr.add(perm);
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
				ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
				String requestJson = ow.writeValueAsString(perrr);

				mockMvc.perform(post("/updatePer").contentType(APPLICATION_JSON_UTF8).content(requestJson))
						.andExpect(status().isOk());*/
				
				
				//MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",m.getProject_id()).contentType(APPLICATION_JSON_UTF8);
				//int mockR = MockHttpServletResponse.SC_OK;
				/*String listMileStoneJson = "{\"project_id\":\"1\",\"milestone_date\":\"22-11-2018\""
						+ ",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";*/
				/*String listMileStoneJson = "{\"project_id\":\"1\",\"milestone_date\":\"22-11-2018\",\"milestone_description\":\"abcabsa\",\"milestone_id\":\"1\"}";*/
				//String listMileStoneJson = "{[\"1\",\"22-11-2018\",\"abcabsa\",\"1\"]}";
		
		/*.andExpect(jsonPath("$.milestone", is( mile)))
		//.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("id").value(1));*/
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",m.getProject_id()).accept(MediaType.APPLICATION_JSON);
		//MvcResult result = mockmvc.perform(contentType)
		//		.andExpect(jsonPath("success",is(true)))
		//		.andReturn();
		//Assert.assertNotNull(result.getResponse().equals(mile));
	}
	@Test
	public void actionDelete() throws Exception{
		MileStone m = new MileStone();
		m.setProject_id(2);
		m.setMilestone_date("22-11-2018");
		m.setMilestone_description("abcabsa");
		m.setMilestone_id(1);
		
		MileStone m2 = new MileStone();
		m2.setProject_id(2);
		m2.setMilestone_date("23-11-2018");
		m2.setMilestone_description("dddddddddd");
		m2.setMilestone_id(2);
		ArrayList<MileStone> mile = new ArrayList<>();
		mile.add(m);
		mile.add(m2);
		
		ArrayList<MileStone> mile2 = new ArrayList<>();
		mile2.add(m2);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(mile.get(0).getMilestone_id());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionDeleteMileStone",1).accept(
				MediaType.APPLICATION_JSON).content(requestJson).contentType(MediaType.APPLICATION_JSON).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		//mileStoneDao.deleteMidelStone(m.getMilestone_id());
		//Mockito.when(mileStoneDao.deleteMidelStone(m.getMilestone_id()));
		//when(mileStoneDao.deleteMidelStone(m.getMilestone_id()));
		//Mockito.when(mileStoneDao.getMileStoneByProjectId(1)).thenReturn(mile2);
		//when(mileStoneController.deleteMileStone(m.getProject_id(), m.getMilestone_id())).thenReturn(mile2);
		
		doNothing().when(mileStoneDao).deleteMidelStone(mile.get(0).getMilestone_id());
		
		mileStoneDao.deleteMidelStone(mile.get(0).getMilestone_id());
		
		Mockito.verify(mileStoneDao, times(1)).deleteMidelStone(mile.get(0).getMilestone_id());
		Mockito.verify(mileStoneDao).deleteMidelStone(mile.get(0).getMilestone_id());
		when(mileStoneDao.getMileStoneByProjectId(mile.get(0).getMilestone_id())).thenReturn(mile2);
		//userResourceController.deleteOneMemberProject(2,memList1.get(0).getMember_project_id() );
		
		
		MvcResult result = mockmvc.perform(requestBuilder)
		.andReturn();
		System.out.println("content = "+result.getResponse().getContentAsString());
		String expected = "[{\"milestone_id\":2,\"milestone_date\":\"23-11-2018\",\"milestone_description\":\"dddddddddd\",\"project_id\":2}]";
		Assert.assertEquals(expected, result.getResponse().getContentAsString());
		/*JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);*/
		
		
		
		
		/*.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("success", is(true)));*/
		//MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/{id}/actionDeleteMileStone",1); 
		//mockmvc.perform(contentType);
		
	}
	
}
