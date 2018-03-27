package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public void detailProject_true() throws Exception {
		
		
		when(per.checker("pro_detail")).thenReturn(true);
		when(projectDao.getProjectById1(1)).thenReturn(new ProjectInfo());
		TaskInfo t= new TaskInfo();
		t.setTask_from("2017-03-22");
		t.setTask_to("2018-03-31");
		t.setTask_done(70);
		List<TaskInfo> lt= new ArrayList<TaskInfo>();
		lt.add(t);
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt);
		RequestBuilder request = get("/detalproject/1");
		this.mockMvc.perform(request).andExpect(view().name("detailProject")).andExpect(model().attribute("command", notNullValue())).andExpect(status().isOk());
	}
	
	@Test
	public void detailProject_false() throws Exception {
		
		
		when(per.checker("pro_detail")).thenReturn(false);
		
		RequestBuilder request = get("/detalproject/1");
		this.mockMvc.perform(request).andExpect(view().name("403Page")).andExpect(status().isOk());
	}

	
}
