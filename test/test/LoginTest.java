package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tinhvan.controller.LoginController;
import com.tinhvan.controller.ProjectController;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectAndScope;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class LoginTest {
	private MockMvc mockmvc;
	
	@Mock
	private UserDao userDao;
	@Mock
	private ProjectDao projectDao;
	@Mock
	PermissionDao per;
	@Mock
	TaskInfoDao taskInfoDao;
	@Mock
	Principal principal;
	@Mock
	HttpServletRequest request;
	@Mock 
	HttpServletResponse response;
	@InjectMocks
	private LoginController loginController;
	
	List<ProjectInfo> pi = new ArrayList<ProjectInfo>();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	
	@Before
	public void setup() {
		
		MockitoAnnotations.initMocks(this);
		 InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	     viewResolver.setPrefix("/WEB-INF/jsp/view/");
	     viewResolver.setSuffix(".jsp");
		this.mockmvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
	}
	
	@Test
	public void getUserCurrentLogin_Test() throws Exception{
		
		User user=new User();
		user.setUser_id(1);
		user.setUser_fullName("Chu Quang Dai");
		user.setUser_mail("daicq@tinhvan.com");
		user.setRole_id(1);
		
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
		when(loginController.getUserCurrentLogin(principal)).thenReturn(user);
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
		when(loginController.getListProject(principal)).thenReturn(pi);
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
		when(loginController.getListProject(principal)).thenReturn(pi);
		Assert.assertEquals(2, pi.size());
	}
	
	@Test
	public void overView() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi); 
		when(per.checker("over_view")).thenReturn(true);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("welcomePage"))
				.andReturn();
		
		when(per.checker("over_view")).thenReturn(false);
		MvcResult result1 = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		//Assert.assertNotNull(result.getModelAndView());
	}
	
	@Test
	public void permissionTest() throws Exception{
		Permission perm = new Permission();
		perm.setAdd_pro(true);
		perm.setCre_iss(false);

		List<Permission> perrr = new ArrayList<Permission>();
		perrr.add(perm);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(perrr);
		RequestBuilder request = post("/updatePer").contentType(APPLICATION_JSON_UTF8).content(requestJson).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		mockmvc.perform(request)
				.andExpect(status().isOk());
	}
	
	@Test
	public void updatePermissionTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/permissionManager").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi); 
		List<Permission> allPer = per.getAllPer();
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.view().name("permissionManager"))
				.andReturn();
		//Assert.assertNotNull(result.getModelAndView());
	}
	
/*	@Test
	public void logoutTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/logout").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi); 
		//HttpServletRequest request;
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session= request.getSession(false);
		when(request.getSession(false)).thenReturn(session);
		List<Cookie> cookies = Mockito.mock(List.class);
		Iterator<Cookie> mockIter = Mockito.mock(Iterator.class);
		 when(cookies.iterator()).thenReturn(mockIter); 
	        when(mockIter.hasNext()).thenReturn(true); 
	  //      when(mockIter.next()).thenReturn(); 
	        when(mockIter.hasNext()).thenReturn(false);
	        
	        for(Cookie cookie : cookies) {
	        //	Mockito.doNothing().when(cookie).setMaxAge(0);
	            cookie.setMaxAge(0);
	        }
	        
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("logoutSuccessfulPage"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}*/
	
	@Test
	public void adminTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/admin").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("adminPage"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	@Test
	public void accessDeniedTest() throws Exception{
		Model model = Mockito.mock(Model.class);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/403").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		when(principal.getName()).thenReturn("daicq@tinhvan.com");
	//	principal = null;
		String msg = "";
		
		MvcResult result = mockmvc.perform(contentType.principal(principal))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		
		
		
		Assert.assertNotNull(result.getModelAndView());
	}
	
	@Test
	public void accessDeniedTest2() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/403").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		
		
		when(principal.getName()).thenReturn(null);
		MvcResult result2 = mockmvc.perform(contentType.principal(principal))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				.andReturn();
		
		Assert.assertNotNull(result2.getModelAndView());
	}
	
	@Test
	public void userInfoTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/userInfo").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		MvcResult result2 = mockmvc.perform(contentType.principal(principal))
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("userInfoPage"))
				.andReturn();
		
		Assert.assertNotNull(result2.getModelAndView());
	}
}
