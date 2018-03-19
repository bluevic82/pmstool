package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.portlet.MockActionRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.EffortController;
import com.tinhvan.dao.EffortDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class EfffortTest {
	private MockMvc mockmvc;
	@Mock
	ProjectDao projectDao;
	@Mock
	EffortDao effortDao;
	@Mock
	UserDao userDao;
	@Mock
	PermissionDao per;
	@InjectMocks
	private EffortController effortController;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(effortController).build();
	}
	
	@Test
	public void effManageTest() throws Exception{
		User u = new User(1,"Dai","Dai@gmail.com","123456Aa@",1);
		userDao.getUserInfoByUserMail("Dai@gmail.com");
		MockMvcResultMatchers.model().attribute("UserInformation", "1");
		
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effortManagement"); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("effortManagementPage"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
		
	}
	
	@Test
	public void effCanTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/effortCalculate/{id}",1); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("/effortCalculate/1"))
				.andReturn();
		
		Assert.assertNotNull(result.getModelAndView());
	}
}
