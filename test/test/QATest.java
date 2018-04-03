package test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.Principal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.tinhvan.controller.QAController;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.QuestionAnswerDao;
import com.tinhvan.dao.StatusDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.ProjectInfo;
import com.tinhvan.model.QuestionAnwer;
import com.tinhvan.model.User;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




//import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class QATest {
	private MockMvc mockMvc;
	@Mock
	ProjectDao projectDao;
	@Mock
	QuestionAnswerDao answerDao;
	@Mock
	StatusDao statusDao;
	@Mock
	MemberProjectDao memberProjectDao;
	@Mock 
	UserDao userDao;
	@Mock
	PermissionDao per;
	@Mock Model model;
	@Mock
	Principal principal;
	@InjectMocks
	QAController qaController;
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	@Before
	public void initTest() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/view/");
	    viewResolver.setSuffix(".jsp");
	    
	    
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(qaController).setViewResolvers(viewResolver).build();
	}
	
	/**
	 * @purpose: Function test for function set User info
	 */
/*	@Test
	public void getUserLogin() {
		User user = new User();
		user.setUser_id(1);
		user.setUser_fullName("Admin");
		user.setUser_mail("Manhnv@gmail.com");
		user.setRole_id(1);
		user.setUser_passWord("123456");
		when(principal.getName()).thenReturn(user.getUser_mail());
		when(qaController.getUserCurrentLogin(principal)).thenReturn(user);
	}*/
	
	/**
	 * @purpose: Function test for function Mapping view List QA
	 */
	@Test
	public void listQA() throws Exception{
		 MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/qaList")
		.param("projectName", "0")
		.param("status", "0")
		.param("member_project_id", "0");

		MvcResult result = mockMvc.perform(builder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("qaList"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping view page registerQA case if
	 */
	@Test
	public void registerQATrue() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/registerQA",1); 
		when(per.checker("qva_upd")).thenReturn(true);
		ProjectInfo p = projectDao.getProjectById(2);
		MvcResult result = mockMvc.perform(contentType)
				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Infor", p))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
	
	/**
	 * @purpose: Function test for function Mapping view page registerQA case e
	 */
	@Test
	public void registerQAFalse() throws Exception{
		when(per.checker("qva_upd")).thenReturn(false);
		mockMvc.perform(get("/{id}/registerQA",1))
				.andExpect(view().name("403Page"))
				.andReturn();
	}
	
	/**
	 * @purpose: Function test button click registerQA case if
	 */
	@Test
	public void actionRegisterQACaseIf() throws Exception {
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/actionRegisterQA");
		MockMultipartFile file = new MockMultipartFile("i", "file.txt", MediaType.TEXT_PLAIN_VALUE, "some xml".getBytes());
		
		
		QuestionAnwer questionAnwer = Mockito.mock(QuestionAnwer.class, Mockito.RETURNS_DEEP_STUBS);
		File f = new File("D:/temp/file.txt");
		
		MultipartFile file2 = Mockito.mock(MultipartFile.class);
		Mockito.when( file2.isEmpty()).thenReturn(false);
		
			file2.transferTo(f);
			questionAnwer.setReferencepoint("D:/temp/file.txt");
		
		
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/actionRegisterQA")
				.file(file).sessionAttr("qa", questionAnwer).param("i", "file.txt"))
				// .andExpect(status().isOk())
				.andExpect(view().name("redirect:/qaList")).andDo(print()).andReturn();
		
	}
	
	/**
	 * @purpose: Function test button click registerQA case else
	 */
	
	@Test
	public void actionRegisterQACaseElse() throws Exception {
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/actionRegisterQA");
		MockMultipartFile firstFile = new MockMultipartFile("i", "filename.txt", "text/plain", "some xml".getBytes()){
			@Override
			public boolean isEmpty() {
				return true;
			}
		};
		MockMultipartFile secondfile = new MockMultipartFile("i", "other-file-name.data", "text/plain",
				"some other type".getBytes());

		QuestionAnwer questionAnwer = Mockito.mock(QuestionAnwer.class, Mockito.RETURNS_DEEP_STUBS);
		File file = new File("file.txt");
		questionAnwer.setReferencepoint("");
		Mockito.doCallRealMethod().when(questionAnwer).setReferencepoint("");
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/actionRegisterQA")
				.file(firstFile).file(secondfile).sessionAttr("qa", questionAnwer).param("i", "file.txt"))
				
				// .andExpect(status().isOk())
				.andExpect(view().name("redirect:/qaList")).andReturn();
	}
	
	/**
	 * @purpose: Function test button click actionUpdateQA case if
	 */
	@Test
	public void actionUpdateQACaseIf() throws Exception {
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/actionUpdateQA");
		MockMultipartFile firstFile = new MockMultipartFile("i", "l.txt", "text/plain", "some xml".getBytes());
		MockMultipartFile secondfile = new MockMultipartFile("i", "other-file-name.data", "text/plain",
				"some other type".getBytes());
		QuestionAnwer questionAnwer = Mockito.mock(QuestionAnwer.class, Mockito.RETURNS_DEEP_STUBS);
		File f = new File("D:/temp/l.txt");
		firstFile.transferTo(f);
		questionAnwer.setReferencepoint("D:/temp/l.txt");
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/actionUpdateQA")
				.file(firstFile).file(secondfile).sessionAttr("qa", questionAnwer).param("i", "file.txt"))
				// .andExpect(status().isOk())
				.andExpect(view().name("redirect:/qaList")).andReturn();
	}
	
	/**
	 * @purpose: Function test button click actionUpdateQA case else
	 */
	
	@Test
	public void actionUpdateQACaseElse() throws Exception {
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/actionUpdateQA");
		MockMultipartFile firstFile = new MockMultipartFile("i", "filename.txt", "text/plain", "some xml".getBytes());
		MockMultipartFile secondfile = new MockMultipartFile("i", "other-file-name.data", "text/plain",
				"some other type".getBytes());

		QuestionAnwer questionAnwer = Mockito.mock(QuestionAnwer.class, Mockito.RETURNS_DEEP_STUBS);
		File file = new File("file.txt");
		questionAnwer.setReferencepoint("");
		Mockito.doCallRealMethod().when(questionAnwer).setReferencepoint("");
		mockMvc.perform(MockMvcRequestBuilders.fileUpload("/actionRegisterQA")
				.file(firstFile).file(secondfile).sessionAttr("qa", questionAnwer).param("i", "file.txt"))
				// .andExpect(status().isOk())
				.andExpect(view().name("redirect:/qaList")).andReturn();
	}
	/**
	 * @purpose: Function test for function Mapping get dataById for updateQA
	 */
	@Test
	public void editQA() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/qaList/{q_a_id}/editQA/{pr_id}",1,1); 
		ProjectInfo p = projectDao.getProjectById(2);
		MvcResult result = mockMvc.perform(contentType)
				.andExpect(status().isOk())
				.andExpect(model().attribute("project_Infor", p))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
	}
}
