package fr.iut.acc_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import fr.iut.app_manager.exceptions.ApprovalError;
import fr.iut.app_manager.model.Approval;
import fr.iut.app_manager.repository.ApprovalRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApprovalRepositoryTests {
	private static LocalDatastoreHelper helper = LocalDatastoreHelper.create(1.0);
	private Closeable session;

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	@MockBean
	ApprovalRepository approvalRepository;

	Approval approval1 = new Approval(11111, Approval.Response.ACCEPTED);
	Approval approval2 = new Approval(22222, Approval.Response.REFUSED);

	@BeforeAll
	public static void beforeClass() throws Exception {
		ObjectifyService.init(new ObjectifyFactory(helper.getOptions().getService()));
		ObjectifyService.register(Approval.class);
		helper.start();
	}

	@AfterAll
	public static void afterClass() throws Exception {
		helper.stop();
	}

	@BeforeEach
	public void before() throws Exception {
		helper.reset();
		this.session = ObjectifyService.begin();
	}

	@AfterEach
	public void after() throws Exception {
		this.session.close();
	}

	@Test
	void getAllApproval_sucess() throws Exception {
		List<Approval> approvals = new ArrayList<>(Arrays.asList(approval1,approval2));

		Mockito.when(approvalRepository.getAllApproval()).thenReturn(approvals);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/approval")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getOneApproval_sucess() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(approval1.getIdAccount())).thenReturn(approval1);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/approval/"+approval1.getIdAccount())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getOneApproval_wrongId() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(0)).thenThrow(new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND));

		mockMvc.perform(MockMvcRequestBuilders
						.get("/approval/0")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	void postApproval_sucess() throws Exception {
		Mockito.when(approvalRepository.addApproval(approval1)).thenReturn(approval1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/approval")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(approval1));

		mockMvc.perform(mockRequest)
				.andExpect(status().isCreated());
	}

	@Test
	void postApproval_entityNull() throws Exception {
		Mockito.when(approvalRepository.addApproval(null)).thenThrow(new ApprovalError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY));

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/approval")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(null));

		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest());
	}

	@Test
	void putApproval_sucess() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(approval1.getIdAccount())).thenReturn(approval1);
		Mockito.when(approvalRepository.modifyApproval(approval2)).thenReturn(approval2);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/approval/"+approval1.getIdAccount())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(approval2));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	void putApproval_wrongId() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(0)).thenThrow(new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND));
		Mockito.when(approvalRepository.modifyApproval(approval2)).thenReturn(approval2);


		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/approval/0")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(approval2));

		mockMvc.perform(mockRequest)
				.andExpect(status().isNotFound());
	}

	@Test
	void putApproval_entityNull() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(approval1.getIdAccount())).thenReturn(approval1);
		Mockito.when(approvalRepository.modifyApproval(null)).thenThrow(new ApprovalError("Unable to Modify", HttpStatus.UNPROCESSABLE_ENTITY));

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/approval/"+approval1.getIdAccount())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(null));

		mockMvc.perform(mockRequest)
				.andExpect(status().isBadRequest());
	}

	@Test
	void deleteBankAccount_sucess() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(approval1.getIdAccount())).thenReturn(approval1);

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/approval/"+approval1.getIdAccount())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void deleteBankAccount_wrongId() throws Exception {
		Mockito.when(approvalRepository.getApprovalPerIdAccount(0)).thenThrow(new ApprovalError("Unable to delete", HttpStatus.NOT_FOUND));

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/approval/0")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

}
