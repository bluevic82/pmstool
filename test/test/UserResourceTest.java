package test;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.Mockito.when;

import com.tinhvan.controller.UserResourceController;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserResourceTest {
	private MockMvc mockmvc;
	@Mock
	RoleDao roleDao;
	@Mock
	UserDao userDao;
	@Mock
	MemberProjectDao memberProjectDao;
	
	@Mock ProjectDao projectDao;
	@Mock
	PermissionDao per;
	@Spy
	@InjectMocks
	UserResourceController userResource;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setPrefix("/WEB-INF/jsp/view/");
	     viewResolver.setSuffix(".jsp");
		
		this.mockmvc = MockMvcBuilders.standaloneSetup(userResource).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void resourceMember_Test() throws Exception{
		 /*List<User> users = Arrays.asList(
		            new User(1, "Daenerys Targaryen"),
		            new User(2, "John Snow"));
		    when(userService.getAll()).thenReturn(users);*/
		List<MemberProject> mem = new ArrayList<MemberProject>();
		mem.add(new MemberProject(1, 1, "chu quang dai", 2, 5, 3));
		mem.add(new MemberProject(2, 2, "to thanh luong", 2, 5, 3));
		mem.add(new MemberProject(3, 3, "ng van manh", 2, 5, 3));
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(3);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		
		Permission permission = new Permission();
		permission.setEff_mana(true);
		permission.setEff_can(true);
		permission.setSet_res(true);
		
		when(per.checker("set_res")).thenReturn(true);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/resource/{id}",3); 
		System.out.println(contentType);
			//when(check).thenReturn(true);
			when(memberProjectDao.getMemberProjectByProjectId1(3)).thenReturn(mem);
			when(projectDao.getProjectById(3)).thenReturn(projectInfo);
			System.out.println("aaaaaaa "+projectInfo.getProject_name());
			System.out.println("bbbbbbb "+mem.size());
			System.out.println("ccccccc "+mem.get(1).getMember_project_name());
		    MvcResult r =  mockmvc.perform(contentType)
		    .andExpect(MockMvcResultMatchers.status().isOk())
		    .andExpect(MockMvcResultMatchers.view().name("resourceMember"))
		    .andReturn();
		    Assert.assertNotNull(r.getModelAndView());
		    
		            
	}
	
	@Test
	public void resourceMember_Test_false() throws Exception{
		 /*List<User> users = Arrays.asList(
		            new User(1, "Daenerys Targaryen"),
		            new User(2, "John Snow"));
		    when(userService.getAll()).thenReturn(users);*/
		List<MemberProject> mem = new ArrayList<MemberProject>();
		mem.add(new MemberProject(1, 1, "chu quang dai", 2, 5, 3));
		mem.add(new MemberProject(2, 2, "to thanh luong", 2, 5, 3));
		mem.add(new MemberProject(3, 3, "ng van manh", 2, 5, 3));
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(3);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		
		boolean check = false;
		when(per.checker("set_res")).thenReturn(false);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/resource/{id}",3); 
			
			//when(check).thenReturn(true);
			
			 MvcResult r  = mockmvc.perform(contentType)
		    .andExpect(MockMvcResultMatchers.view().name("403Page"))
		    .andReturn();
		    
			 Assert.assertNotNull(r.getModelAndView());      
	}
	
	/*@Test
	public void registerTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/resource",1); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("/1/resource"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
		
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].username", is("John Snow")));
verify(userService, times(1)).getAll();
verifyNoMoreInteractions(userService);
		
	}*/
}
