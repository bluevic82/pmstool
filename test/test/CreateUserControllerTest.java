package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.FileInputStream;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.UserSystemController;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.RoleDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/dispatcher-servlet.xml" })
@WebAppConfiguration
public class CreateUserControllerTest {
	private MockMvc mockMvc;
	@Mock
	private UserDao userDao;
	@Mock
	private RoleDao roleDao;
	@Mock
	private ProjectDao projectDao;

	@Mock
	private PermissionDao per;

	@InjectMocks
	private UserSystemController userSystemController;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userSystemController).build();

	}
	List<ProjectInfo> pi= new ArrayList<ProjectInfo>();
	@Test
	public void addUserTest() throws Exception {
		FileInputStream inputFile = new FileInputStream("C:/Users/Otoke/Downloads/soncsv.csv");
		MockMultipartFile file = new MockMultipartFile("fileName", "soncsv.csv", "multipart/form-data", inputFile);

		User u = new User();
		u.setRole_id(2);
		u.setUser_fullName("hehhehehe");
		u.setUser_id(9);
		u.setUser_mail("sonace264@gmail.com");
		u.setUser_passWord("aHeee7%asd");
		RequestBuilder request = fileUpload("/actionCreateUser").file(file).flashAttr("userInfo", u).flashAttr("UserInformation", new User()).flashAttr("list_Project_For_menu", pi);
		List<User> gettAllUser = new ArrayList();
		gettAllUser.add(u);
		when(userDao.gettAllUser()).thenReturn(gettAllUser);
		this.mockMvc.perform(request).andExpect(view().name("userRegister"));
	}

}
