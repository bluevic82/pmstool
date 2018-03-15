package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.LoginController;
import com.tinhvan.controller.ProjectController;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectAndScope;
import com.tinhvan.model.ProjectInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class overviewTest {
private MockMvc mockmvc;
	
	@Mock
	private UserDao userDao;
	@Mock
	private ProjectDao projectDao;
	
	@InjectMocks
	private LoginController loginController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}
	
	@Test
	public void overView() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/welcome"); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("welcomePage"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
		
	}
}
