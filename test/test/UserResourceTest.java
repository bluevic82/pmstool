package test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.bytebuddy.implementation.StubMethod;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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
import org.springframework.ui.Model;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhvan.controller.MileStoneController;
import com.tinhvan.controller.UserResourceController;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.MileStone;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.Role;
import com.tinhvan.model.User;

import org.mockito.internal.verification.Times;
import org.mockito.stubbing.Answer;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserResourceTest {
	private MockMvc mockmvc;
	@Mock
	MemberProjectDao memberProjectDao;
	@Mock
	ProjectDao projectDao;
	@Mock UserDao userDao;
	@Mock RoleDao roleDao;
	@Mock
	private Principal principal;
	@Mock
	private HttpServletRequest request;
	  
	@Mock
	private HttpServletResponse response;
	@Mock
	PermissionDao per;
	@InjectMocks
	UserResourceController userResourceController;
	List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(userResourceController).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(userResourceController.getUserCurrentLogin(principal)).thenReturn(user);
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
		when(userResourceController.getListProject(principal)).thenReturn(pi);
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
		when(userResourceController.getListProject(principal)).thenReturn(pi);
	}
	
	
	@Test
	public void resourceMemberTest_true() throws Exception{
		MemberProject memberProject = new MemberProject();
		memberProject.setMember_project_id(1);
		memberProject.setMember_project_name("chu quang dai");
		memberProject.setUser_id(5);
		memberProject.setRole_id(1);
		memberProject.setMember_project_effort(20);
		memberProject.setProject_id(2);
		List<MemberProject> memList = new ArrayList<MemberProject>();
		memList.add(memberProject);
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProject_id(2);
		projectInfo.setProject_name("aaa");
		projectInfo.setProject_from("2018-02-02");
		projectInfo.setProject_to("2018-04-02");
		projectInfo.setProject_technical("code");
		projectInfo.setProject_charge_cost(5);
		projectInfo.setProject_description("gggggggggggg");
		List<ProjectInfo> prList = new ArrayList<ProjectInfo>();
		prList.add(projectInfo);
		
		when(per.checker("set_res")).thenReturn(true);
		System.out.println(per.checker("set_res"));
		when(memberProjectDao.getMemberProjectByProjectId1(2)).thenReturn(memList);
		when(projectDao.getProjectById(2)).thenReturn(projectInfo);
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/resource",2); 
		mockmvc.perform(contentType.flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi).flashAttr("roleUser", new ArrayList<Role>()).flashAttr("getAllUser", new ArrayList<User>()))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attribute("listMemberOfProject", memList))
				.andExpect(MockMvcResultMatchers.model().attribute("projectInfo", projectInfo))
				.andExpect(MockMvcResultMatchers.view().name("resourceMember"))
				
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void resourceMemberTest_false() throws Exception{
		MemberProject memberProject = new MemberProject();
		memberProject.setMember_project_id(1);
		memberProject.setMember_project_name("chu quang dai");
		memberProject.setUser_id(5);
		memberProject.setRole_id(1);
		memberProject.setMember_project_effort(20);
		memberProject.setProject_id(2);
		Model model;
		int id =2;
		when(per.checker("set_res")).thenReturn(false);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/resource",id); 
		
		mockmvc.perform(contentType.flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi).flashAttr("roleUser", new ArrayList<Role>()).flashAttr("getAllUser", new ArrayList<User>()))
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	
	@Test
	public void updateTest() throws Exception{
		MemberProject memberProject1 = new MemberProject();
		memberProject1.setMember_project_id(1);
		memberProject1.setMember_project_name("chu quang dai");
		memberProject1.setUser_id(5);
		memberProject1.setRole_id(1);
		memberProject1.setMember_project_effort(20);
		memberProject1.setProject_id(2);
		memberProject1.setRole_name("Developer");
		ArrayList<MemberProject> memList = new ArrayList<MemberProject>();
		memList.add(memberProject1);
		
		int id=2;
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(memList);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveMemberToDB", id).accept(
				MediaType.APPLICATION_JSON).content(requestJson).contentType(MediaType.APPLICATION_JSON).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi).flashAttr("roleUser", new ArrayList<Role>()).flashAttr("getAllUser", new ArrayList<User>());
		
		doNothing().when(memberProjectDao).updateMemberProjectBy_PrjId(memList, id);
		
		memberProjectDao.updateMemberProjectBy_PrjId(memList, id);
		Mockito.verify(memberProjectDao, times(1)).updateMemberProjectBy_PrjId(memList, id);
		Mockito.verify(memberProjectDao).updateMemberProjectBy_PrjId(memList, id);
		//Mockito.when(mileStoneDao.getMileStoneByProjectId(Mockito.anyInt())).thenReturn(mile);
		when(memberProjectDao.getMemberProjectByProjectId1(memberProject1.getProject_id())).thenReturn(memList);
		when(userResourceController.update(id, memList)).thenReturn(memList);
		//Mockito.verify(memberProjectDao).updateMemberProjectBy_PrjId(memList, id);
		
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
				System.out.println("content = "+result.getResponse().getContentAsString());
			String expected = "[{\"member_project_id\":1,\"user_id\":5,\"member_project_name\":\"chu quang dai\",\"role_id\":1,\"member_project_effort\":20,\"project_id\":2,\"role_name\":\"Developer\"}]";
				Assert.assertEquals(expected, result.getResponse().getContentAsString());
				
	}
	
	@Test
	public void deleteTest() throws Exception{
		MemberProject memberProject1 = new MemberProject();
		memberProject1.setMember_project_id(1);
		memberProject1.setMember_project_name("chu quang dai");
		memberProject1.setUser_id(5);
		memberProject1.setRole_id(1);
		memberProject1.setMember_project_effort(20);
		memberProject1.setProject_id(2);
		memberProject1.setRole_name("Developer");
		
		MemberProject memberProject2 = new MemberProject();
		memberProject2.setMember_project_id(2);
		memberProject2.setMember_project_name("le thi thao");
		memberProject2.setUser_id(6);
		memberProject2.setRole_id(1);
		memberProject2.setMember_project_effort(20);
		memberProject2.setProject_id(2);
		memberProject2.setRole_name("Developer");
		
		ArrayList<MemberProject> memList1 = new ArrayList<MemberProject>();
		memList1.add(memberProject1); memList1.add(memberProject1);
		ArrayList<MemberProject> memList2 = new ArrayList<MemberProject>();
		memList2.add(memberProject2);
		
		ObjectMapper mapper = new ObjectMapper(); 
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson1 = ow.writeValueAsString(memList1.get(0).getMember_project_id());
		//String requestJson2 = ow.writeValueAsString(memList2);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/deleteOneMemberProject", memberProject1.getProject_id()).accept(
				MediaType.APPLICATION_JSON).content(requestJson1).contentType(MediaType.APPLICATION_JSON).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi).flashAttr("roleUser", new ArrayList<Role>()).flashAttr("getAllUser", new ArrayList<User>());
		
		
		//when(memberProjectDao.getMemberProjectByProjectId1(memberProject1.getProject_id())).thenReturn(memList1);
		//memberProjectDao.deleteOneMemberProject(memberProject1.getMember_project_id());
		/*do(memberProjectDao.deleteOneMemberProject(memberProject1.getMember_project_id()));*/
		doNothing().when(memberProjectDao).deleteOneMemberProject(memList1.get(0).getMember_project_id());
		
		memberProjectDao.deleteOneMemberProject(memList1.get(0).getMember_project_id());
		
		Mockito.verify(memberProjectDao, times(1)).deleteOneMemberProject(memList1.get(0).getMember_project_id());
		Mockito.verify(memberProjectDao).deleteOneMemberProject(memList1.get(0).getMember_project_id());
		when(memberProjectDao.getMemberProjectByProjectId1(memList1.get(0).getMember_project_id())).thenReturn(memList2);
		//userResourceController.deleteOneMemberProject(2,memList1.get(0).getMember_project_id() );
		when(userResourceController.deleteOneMemberProject(2,memList1.get(0).getMember_project_id())).thenReturn(memList2);
		//when(userResourceController.delete(memberProject1.getProject_id(), memberProject1.getMember_project_id())).thenReturn(memList2);
		MvcResult result = mockmvc.perform(requestBuilder)
				.andReturn();
		//Mockito.verify(memberProjectDao, times(1)).deleteOneMemberProject(memberProject1.getMember_project_id());
				System.out.println("content11111 = "+result.getResponse().getContentAsString());
			String expected = "[{\"member_project_id\":2,\"user_id\":6,\"member_project_name\":\"le thi thao\",\"role_id\":1,\"member_project_effort\":20,\"project_id\":2,\"role_name\":\"Developer\"}]";
			Assert.assertEquals(expected, result.getResponse().getContentAsString());
	}
	
	@Test
	public void getRole_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		
		Role role1 = new Role();
		role1.setRole_id(1);
		role1.setRole_name("Manager");
		Role role2 = new Role();
		role2.setRole_id(1);
		role2.setRole_name("Manager");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		
		
		when(userDao.getUserInfoByUserMail(principal.getName())).thenReturn(user);
		when(roleDao.getListRoleExceptManagerIfUserLoginIsManager(user.getRole_id())).thenReturn(roles);
		when(userResourceController.getRole(principal)).thenReturn(roles);
		//user = userDao.getUserInfoByUserMail(principal.getName());
	}
	
	@Test
	public void getAllUserTest() throws Exception{
		User user1=new User();
		user1.setUser_id(1);
		user1.setUser_fullName("Chu Quang Dai");
		user1.setUser_mail("daicq@tinhvan.com");
		user1.setRole_id(1);
		user1.setUser_passWord("123456");
		
		ArrayList<User> list_user = new ArrayList<User>();
		list_user.add(user1);
		when(userDao.getAllUser_Except_Admin()).thenReturn(list_user);
		when(userResourceController.getAllUser()).thenReturn(list_user);
		
	}
}
