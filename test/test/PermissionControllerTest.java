package test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;




import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
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
import com.tinhvan.model.Permission;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/dispatcher-servlet.xml" })
@WebAppConfiguration
public class PermissionControllerTest {
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
	List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setUp() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void listPerTest() throws Exception {

		List<Permission> allPer = new ArrayList<Permission>();
		Permission p = new Permission();
		allPer.add(p);
		RequestBuilder request = get("/permissionManager").flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);;
		this.mockMvc.perform(request).andExpect(view().name("permissionManager"))
				.andExpect(model().attribute("listPer", notNullValue())).andExpect(status().isOk());

	}

	@Test
	public void updatePerTest() throws Exception {

		Permission perm = new Permission();
		perm.setAdd_pro(true);
		perm.setCre_iss(false);

		List<Permission> perrr = new ArrayList<Permission>();
		perrr.add(perm);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(perrr);
		RequestBuilder request = post("/updatePer").contentType(APPLICATION_JSON_UTF8).content(requestJson).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);;
		mockMvc.perform(request)
				.andExpect(status().isOk());

	}

}
