package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.*;

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
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.TaskInfo;
import com.tinhvan.model.User;

import org.hamcrest.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/dispatcher-servlet.xml" })
@WebAppConfiguration
public class ProjectControllerTest {
	private MockMvc mockMvc;
	@Mock
	private ProjectDao projectDao;
	@Mock
	private TypeDao typeDao;
	@Mock
	private StatusDao statusDao;
	@Mock
	private MemberProjectDao memberProjectDao;
	@Mock
	private CategoryDao categoryDao;
	@Mock
	private ScopeDao scopeDao;
	@Mock
	private EffortDao effortDao;
	@Mock
	private TaskInfoDao taskInfoDao;
	@Mock
	private PermissionDao per;
	@Mock 
	private UserDao userDao;
	
	@InjectMocks
	private ProjectController projectController;
	
	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
	}
	

	@Test
	public void detailProject() throws Exception {
		List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
		RequestBuilder request1 = get("/detalproject/1").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		this.mockMvc.perform(request1).andExpect(view().name("403Page")).andExpect(status().isOk());
		when(per.checker("pro_detail")).thenReturn(true);
		when(projectDao.getProjectById1(1)).thenReturn(new ProjectInfo());
		TaskInfo t = new TaskInfo();
		TaskInfo t1 = new TaskInfo();
		TaskInfo t2 = new TaskInfo();
		TaskInfo t3 = new TaskInfo();
		t.setTask_from("2017-03-22");
		t.setTask_to("2018-09-31");
		t.setTask_done(70);
		List<TaskInfo> lt = new ArrayList<TaskInfo>();
		lt.add(t);
		t1.setTask_from("2017-03-22");
		t1.setTask_to("2018-03-28");
		t1.setTask_done(0);
		lt.add(t1);
		t2.setTask_from("2017-03-22");
		t2.setTask_to("2018-02-28");
		t2.setTask_done(100);
		lt.add(t2);
		
		t3.setTask_from("2017-03-22");
		t3.setTask_to("2017-03-21");
		t3.setTask_done(100);
		lt.add(t3);
		
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt);
		
		RequestBuilder request = get("/detalproject/1").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		this.mockMvc.perform(request).andExpect(view().name("detailProject"))
				.andExpect(model().attribute("command", notNullValue())).andExpect(status().isOk());
		when(per.checker("pro_detail")).thenReturn(false);

		
	}


	
}
