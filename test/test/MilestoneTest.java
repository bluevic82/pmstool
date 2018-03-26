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
import org.mockito.internal.stubbing.answers.CallsRealMethods;
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
import com.tinhvan.dao.MileStoneDao;
import com.tinhvan.dao.PermissionDao;
import com.tinhvan.dao.ProjectDao;
import com.tinhvan.dao.UserDao;
import com.tinhvan.model.MileStone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/dispatcher-servlet.xml"})
@WebAppConfiguration
public class MilestoneTest {
	private MockMvc mockmvc;
	@Mock
	MileStoneDao mileStoneDao;
	@Mock
	ProjectDao projectDao;
	@Mock UserDao userDao;
	@Mock
	PermissionDao per;
	@InjectMocks
	MileStoneController mileStoneController;
	
	//public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockmvc = MockMvcBuilders.standaloneSetup(mileStoneController).build();
	}
	@Test
	public void createMileTrue() throws Exception{
		when(per.checker("set_reg")).thenReturn(true);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/createMileStone",1); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("createMilestone"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	@Test
	public void createMileFalse() throws Exception{
		when(per.checker("set_reg")).thenReturn(false);
		MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.get("/{id}/createMileStone",1); 
		MvcResult result = mockmvc.perform(contentType)
				//.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("403Page"))
				//.andExpect(MockMvcResultMatchers.forwardedUrl("addProject"))
				.andReturn();
		
		//Assert.assertNotNull(result.getModelAndView());
	}
	
	@Test
	public void actionSave() throws Exception{
		MileStone m = new MileStone();
		m.setProject_id(1);
		m.setMilestone_date("22-11-2018");
		m.setMilestone_description("abcabsa");
		m.setMilestone_id(1);
		ArrayList<MileStone> mile = new ArrayList<>();
		mile.add(m);
		//MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",m.getProject_id()).contentType(APPLICATION_JSON_UTF8);
		//int mockR = MockHttpServletResponse.SC_OK;
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",1).accept(
				MediaType.APPLICATION_JSON);
		/*//mileStoneDao.updateMilestone(mile);
		*/
		Mockito.when(mileStoneDao.getMileStoneByProjectId(1)).thenReturn(mile);
		when(mileStoneController.update(1, mile)).thenReturn(mile);
		
		MvcResult result = mockmvc.perform(requestBuilder)
				//.andExpect(jsonPath("", matcher))
				.andReturn();
		System.out.println(result.getResponse());
		/*.andExpect(jsonPath("$.milestone", is( mile)))
		//.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
        .andExpect(jsonPath("id").value(1));*/
		//RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/{id}/actionSaveMileStone",m.getProject_id()).accept(MediaType.APPLICATION_JSON);
		//MvcResult result = mockmvc.perform(contentType)
		//		.andExpect(jsonPath("success",is(true)))
		//		.andReturn();
		//Assert.assertNotNull(result.getResponse().equals(mile));
	}
	@Test
	public void actionDelete() throws Exception{
		MileStone m = new MileStone();
		m.setProject_id(1);
		m.setMilestone_date("22-11-2018");
		m.setMilestone_description("abcabsa");
		m.setMilestone_id(1);
		ArrayList<MileStone> mile = new ArrayList<>();
		mile.add(m);
		//mileStoneDao.deleteMidelStone(m.getMilestone_id());
		//Mockito.when(mileStoneDao.deleteMidelStone(m.getMilestone_id()));
		//when(mileStoneDao.deleteMidelStone(m.getMilestone_id()));
		Mockito.when(mileStoneDao.getMileStoneByProjectId(1)).thenReturn(mile);
		when(mileStoneController.deleteMileStone(1, m.getMilestone_id())).thenReturn(mile);
		mockmvc.perform(post("/{id}/actionDeleteMileStone",1))
		.andReturn();
		
		
		/*.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(jsonPath("success", is(true)));*/
		//MockHttpServletRequestBuilder contentType = MockMvcRequestBuilders.post("/{id}/actionDeleteMileStone",1); 
		//mockmvc.perform(contentType);
		
	}
	
}
