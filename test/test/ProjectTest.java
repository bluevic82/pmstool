package test;

import static org.mockito.Mockito.when;

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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhvan.controller.ProjectController;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectAndScope;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.ScopeProject;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class ProjectTest {
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
	ScopeDao scopeDao;
	@Mock
	EffortDao effortDao;
	@Mock
	TaskInfoDao taskInfoDao;
	@Mock
	PermissionDao per;
	@Mock 
	UserDao userDao;
	@InjectMocks
	private ProjectController projectController;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setPrefix("/WEB-INF/jsp/view/");
	     viewResolver.setSuffix(".jsp");
	     
	     User u = new User();
	     u.setUser_id(1);
	     u.setRole_id(1);
	     u.setUser_fullName("abc");
	     u.setUser_mail("abc@tinhvan.com");
	     
		this.mockmvc = MockMvcBuilders.standaloneSetup(projectController).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void addProjectTest() throws Exception{
		 User u = new User();
	     u.setUser_id(1);
	     u.setRole_id(1);
	     u.setUser_fullName("abc");
	     u.setUser_mail("abc@tinhvan.com");
	     u.setUser_passWord("123456Aa@");
	     
	     Permission p = new Permission();
		//Boolean checker = per.checker("add_pro");
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/addProject");
	
		when(per.checker("add_pro")).thenReturn(true);
		mockmvc.perform(contentType).andExpect(MockMvcResultMatchers.view().name("addProject"));
		when(per.checker("add_pro")).thenReturn(false);
		mockmvc.perform(contentType).andExpect(MockMvcResultMatchers.view().name("403Page2"));
		
	}
	//List<ScopeProject> scope_For_Update = new ArrayList<ScopeProject>();
	@Test
	public void editProjectTrue() throws Exception{
		
		ScopeProject sp = new ScopeProject();
		sp.setProject_id(1);
		sp.setScope_id(1);
		sp.setScope_project_id(1);
		
		ScopeProject sp1 = new ScopeProject();
		sp1.setProject_id(1);
		sp1.setScope_id(2);
		sp1.setScope_project_id(2);
		
		List<ScopeProject> spl = new ArrayList<>();
		spl.add(sp);
		spl.add(sp1);
		ProjectInfo p =new ProjectInfo();
		p.setProject_id(1);
		p.setProject_name("abac");
		p.setType_id(1);
		p.setProject_charge_cost(3);
		p.setProject_description("afdg");
		p.setProject_from("22-02-2018");
		p.setProject_to("25-02-2018");
		p.setStatus_id(3);
		
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/editproject/{id}",p.getProject_id()); 
		when(per.checker("set_upd")).thenReturn(true);
		System.out.println("size start: " + spl.size());
		//List<ScopeProject> s = projectDao.getScope(sp.getScope_id());
		when(projectDao.getProjectById(1)).thenReturn(p);
		System.out.println(p.getProject_id());
		Mockito.when(projectDao.getScope(sp.getScope_id())).thenReturn(spl);
		//ArrayList<ScopeProject> s1 = new ArrayList<>(s);
		System.out.println("size mid:  " + spl.size());
		
		//ProjectInfo projectInfo = projectDao.getProjectById(1);
		List<ScopeProject> scope_For_Update = (ArrayList<ScopeProject>)(spl) ;
		
		//scope_For_Update = (ArrayList<ScopeProject>) spl;
		System.out.println("size after:  " + spl.size());
//		MvcResult result = mockmvc.perform(contentType)
//				.andExpect(view().name("updateProject"));
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.view().name("updateProject"))
				.andReturn();
	}
	
	@Test
	public void editProjectfalse() throws Exception{
		//ProjectAndScope ps = new ProjectAndScope();
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/editproject/{id}",1); 
		when(per.checker("set_upd")).thenReturn(false);
		mockmvc.perform(contentType).andExpect(MockMvcResultMatchers.view().name("403Page"));
	}
	@Test
	public void addProject() throws Exception{
		ProjectAndScope projectAndScope = new ProjectAndScope();
		projectAndScope.setProject_id(1);
		projectAndScope.setProject_name("abac");
		projectAndScope.setType_id(1);
		projectAndScope.setProject_charge_cost(3);
		projectAndScope.setProject_description("afdg");
		projectAndScope.setProject_from("22-02-2018");
		projectAndScope.setProject_to("25-02-2018");
		projectAndScope.setStatus_id(3);
		ArrayList<Integer> scope_id = new ArrayList<>();
		scope_id.add(1);
		scope_id.add(2);
		//List<Integer> scope_id = null;
		projectAndScope.setScope_id(scope_id);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(projectAndScope);
		
		RequestBuilder contentType = MockMvcRequestBuilders.post("/actionAdd").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(requestJson);
		final List<Integer> sp = new ArrayList<>();
		sp.addAll(projectAndScope.getScope_id());
		
		ProjectInfo p = new ProjectInfo(); 
		p.setProject_id(projectAndScope.getProject_id());
		p.setProject_name(projectAndScope.getProject_name());
		p.setType_id(projectAndScope.getType_id()); 
		p.setProject_from(projectAndScope.getProject_from());
		p.setProject_to(projectAndScope.getProject_to());
		p.setProject_technical(projectAndScope.getProject_technical());
		p.setProject_charge_cost(projectAndScope.getProject_charge_cost());
		p.setStatus_id(projectAndScope.getStatus_id());
		p.setProject_description(projectAndScope.getProject_description());
		
		projectDao.addProject(p);
		int id = projectDao.findProjectIdMax();
		projectDao.addScopeProject(id, sp);
		
		projectDao.updateProject(p);
		MvcResult result = mockmvc.perform(contentType)
				.andReturn();
		
		Assert.assertNotNull(result.getResponse());
		
	}
	@Test
	public void updateP() throws Exception{
		ProjectAndScope projectAndScope = new ProjectAndScope();
		projectAndScope.setProject_id(1);
		projectAndScope.setProject_name("abac");
		projectAndScope.setType_id(1);
		projectAndScope.setProject_charge_cost(3);
		projectAndScope.setProject_description("afdg");
		projectAndScope.setProject_from("22-02-2018");
		projectAndScope.setProject_to("25-02-2018");
		projectAndScope.setStatus_id(3);
		ArrayList<Integer> scope_id = new ArrayList<>();
		scope_id.add(1);
		scope_id.add(2);
		//List<Integer> scope_id = null;
		projectAndScope.setScope_id(scope_id);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(projectAndScope);
		
		
		RequestBuilder contentType = MockMvcRequestBuilders.post("/{id}/actionUpdateP",1).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(requestJson); 
		ProjectInfo p = new ProjectInfo(); 
		p.setProject_id(projectAndScope.getProject_id());
		p.setProject_name(projectAndScope.getProject_name());
		p.setType_id(projectAndScope.getType_id()); 
		p.setProject_from(projectAndScope.getProject_from());
		p.setProject_to(projectAndScope.getProject_to());
		p.setProject_technical(projectAndScope.getProject_technical());
		p.setProject_charge_cost(projectAndScope.getProject_charge_cost());
		p.setStatus_id(projectAndScope.getStatus_id());
		p.setProject_description(projectAndScope.getProject_description());
		
		projectDao.updateProject(p);
		final List<Integer> sp = new ArrayList<>();
		sp.addAll(projectAndScope.getScope_id());
		scopeDao.deleteListScopeProjectByProject_id(p.getProject_id());
		scopeDao.createListScopeProject(p.getProject_id(), sp);
		MvcResult result = mockmvc.perform(contentType)
				.andReturn();
		Assert.assertNotNull(result.getResponse());
		
	}
	@Test
	public void detailTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/detailProject/{id}",1); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.view().name("detailProject"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		
	}
}
