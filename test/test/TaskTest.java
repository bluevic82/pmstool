package test;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.TaskController;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;





import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class TaskTest {
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
	TaskInfoDao taskInfoDao;
	@Mock 
	UserDao userDao;
	@Mock
	PermissionDao per;
	@InjectMocks
	TaskController taskController;
	/*@Autowired
	TaskInfo task;*/
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(taskController).build();
	}
	
	@Test
	public void createTast() throws Exception{
		TaskInfo t = new TaskInfo();
		/*MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("{id}/createTask",1);*/ 
		mockmvc.perform(get("{id}/createTask",1))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("tongPer"))
				.andExpect(view().name("1/createTask"))
				.andReturn();
		//Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void info() throws Exception{
		User u = new User(1,"Dai","Dai@gmail.com","123456Aa@",1);
		MockMvcResultMatchers.model().attribute("UserInformation", "1");

	}
}
