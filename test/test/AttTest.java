package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.GetAttributeController;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class AttTest {
	private MockMvc mockmvc;
	@Mock ProjectDao projectDao;
	@Mock UserDao userDao;
	@InjectMocks
	GetAttributeController getAttributeC;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(getAttributeC).build();
	}
	
	@Test
	public void createTest() throws Exception{
		//MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("UserInformation");
		MockMvcResultMatchers.model().attribute("UserInformation", "1");
		MockMvcResultMatchers.model().attribute("UserInformation", "2");
		
		
		//Assert.assertNotNull(result.getModelAndView());
	}
}
