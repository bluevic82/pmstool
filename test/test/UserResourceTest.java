package test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.rmi.CORBA.Stub;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.RequestResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.tinhvan.controller.MileStoneController;
import com.tinhvan.controller.UserResourceController;
import com.tinhvan.dao.MemberProjectDao;
import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MileStone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class UserResourceTest {
	private MockMvc mockmvc;
	@Mock
	MemberProjectDao memberProjectDao;
	@Mock
	ProjectDao projectDao;
	@Mock UserDao userDao;
	@Mock
	PermissionDao per;
	@InjectMocks
	UserResourceController userResourceController;
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(userResourceController).build();
	}
	@Test
	public void resourceMemberTest_true() throws Exception{
		when(per.checker("set_res")).thenReturn(true);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/resource/{id}",1); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("resourceMember"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void resourceMemberTest_false() throws Exception{
		when(per.checker("set_res")).thenReturn(false);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/resource/{id}",1); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	
	/*@Test
	public void registerTest() throws Exception{
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/resource",1); 
		MvcResult result = mockmvc.perform(contentType)
				.andExpect(MockMvcResultMatchers.status().isOk())
				//.andExpect(MockMvcResultMatchers.model().attributeExists("tongPer"))
				.andExpect(MockMvcResultMatchers.view().name("/1/resource"))
				.andReturn();
		Assert.assertNotNull(result.getModelAndView());
		
		.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[1].username", is("John Snow")));
verify(userService, times(1)).getAll();
verifyNoMoreInteractions(userService);
		
	}*/
}
