package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
	@InjectMocks
	private LoginController loginController;
	
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
	public void overView() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/welcome"); 
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
		RequestBuilder request = post("/updatePer").contentType(APPLICATION_JSON_UTF8).content(requestJson);
		mockmvc.perform(request)
				.andExpect(status().isOk());
	}
	@Test
	public void updatePermissionTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/permissionManager"); 
		List<Permission> allPer = per.getAllPer();
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.view().name("permissionManager"))
				.andReturn();
		//Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void loinTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/login"); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("loginPage"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void logoutTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/logout"); 
		//HttpServletRequest request;
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		HttpSession session= request.getSession(false);
		when(request.getSession(false)).thenReturn(session);
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("logoutSuccessfulPage"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
}
