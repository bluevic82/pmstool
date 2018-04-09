package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

import com.tinhvan.controller.LoginController;
import com.tinhvan.dao.CategoryDao;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.ScopeDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.TaskInfoDao;
import com.tinhvan.dao.TypeDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MemberProject;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.TaskInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/dispatcher-servlet.xml" })
@WebAppConfiguration
public class LoginControllerTest {

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
	private TaskInfoDao taskInfoDao;
	@Mock
	private QuestionAnswerDao qaDao;
	@Mock
	private ScopeDao scopeDao;
	@Mock
	private PermissionDao per;
	@Mock
	private UserDao userDao;

	@InjectMocks
	private LoginController loginController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
	}

	@Test
	public void welcomePage_test() throws Exception {

		when(per.checker("over_view")).thenReturn(false);

		RequestBuilder request = get("/")

				.param("name", "").param("pm", "").param("from", "").param("to", "");
		this.mockMvc.perform(request).andExpect(view().name("403Page"));
	}

	@Test
	public void welcomePage_test_true() throws Exception {

		List<ProjectInfo> lp = new ArrayList<ProjectInfo>();
		ProjectInfo p = new ProjectInfo();
		lp.add(p);

		List<MemberProject> lmp = new ArrayList<MemberProject>();
		MemberProject mp = new MemberProject();
		lmp.add(mp);
		when(per.checker("over_view")).thenReturn(true);
		when(projectDao.getAllProject1("", "", "", "")).thenReturn(lp);
		when(projectDao.getPm(0)).thenReturn(lmp);
		List<TaskInfo> lt = new ArrayList<TaskInfo>();
		List<TaskInfo> lt1 = new ArrayList<TaskInfo>();
		List<TaskInfo> lt2 = new ArrayList<TaskInfo>();
		List<TaskInfo> lt3 = new ArrayList<TaskInfo>();
		TaskInfo t = new TaskInfo();
		TaskInfo t1 = new TaskInfo();
		TaskInfo t2 = new TaskInfo();
		TaskInfo t3 = new TaskInfo();
		t.setTask_from("2017-03-22");
		t.setTask_to("2018-09-31");
		t.setTask_done(100);
		
		lt.add(t);
		t1.setTask_from("2017-03-22");
		t1.setTask_to("2018-03-28");
		t1.setTask_done(0);
		lt1.add(t1);
		t2.setTask_from("2017-03-22");
		t2.setTask_to("2018-02-28");
		t2.setTask_done(100);
		lt2.add(t2);
		
		t3.setTask_from("2017-03-22");
		t3.setTask_to("2017-03-21");
		t3.setTask_done(100);
		lt3.add(t3);
		
		
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt);
		RequestBuilder request = get("/")

				.param("name", "").param("pm", "").param("from", "").param("to", "");
		this.mockMvc.perform(request).andExpect(view().name("welcomePage"));
		
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt1);
		RequestBuilder request1 = get("/")

				.param("name", "").param("pm", "").param("from", "").param("to", "");
		this.mockMvc.perform(request).andExpect(view().name("welcomePage"));
		
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt2);
		RequestBuilder request2 = get("/")

				.param("name", "").param("pm", "").param("from", "").param("to", "");
		this.mockMvc.perform(request).andExpect(view().name("welcomePage"));
		
		when(taskInfoDao.getTaskByIdPro(0)).thenReturn(lt3);
		RequestBuilder request3 = get("/")

				.param("name", "").param("pm", "").param("from", "").param("to", "");
		this.mockMvc.perform(request).andExpect(view().name("welcomePage"));
	}

}
