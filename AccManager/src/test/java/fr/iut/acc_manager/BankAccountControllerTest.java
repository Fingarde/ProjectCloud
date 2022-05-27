package fr.iut.acc_manager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.datastore.testing.LocalDatastoreHelper;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;
import fr.iut.acc_manager.model.BankAccount;
import fr.iut.acc_manager.model.Risk;
import fr.iut.acc_manager.repository.BankAccountRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class BankAccountControllerTest {
	private static LocalDatastoreHelper helper = LocalDatastoreHelper.create(1.0);
	private Closeable session;
	@Autowired
	 MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	BankAccountRepository bankAccountRepository;

	BankAccount bankAccount1 = new BankAccount("Pays", "Antoine", 15000.0, Risk.HIGH);
	BankAccount bankAccount2 = new BankAccount("Bertrand", "Timothe", 1.0, Risk.LOW);

	@BeforeAll
	public static void beforeClass() throws Exception {
		ObjectifyService.init(new ObjectifyFactory(helper.getOptions().getService()));
		ObjectifyService.register(BankAccount.class);
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
	void getAllBankAccount_sucess() throws Exception {
		List<BankAccount> bankAccounts = new ArrayList<>(Arrays.asList(bankAccount1,bankAccount2));

		Mockito.when(bankAccountRepository.getAllBankAccount()).thenReturn(bankAccounts);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/bankaccount")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void getOneBankAccount_sucess() throws Exception {
		Mockito.when(bankAccountRepository.getOneBankAccountPerId(bankAccount1.getId())).thenReturn(bankAccount1);

		mockMvc.perform(MockMvcRequestBuilders
						.get("/bankaccount/"+bankAccount1.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void postBankAccount_sucess() throws Exception {
		Mockito.when(bankAccountRepository.addBankAccount(bankAccount1)).thenReturn(bankAccount1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/bankaccount")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(this.mapper.writeValueAsString(bankAccount1));

		mockMvc.perform(mockRequest)
				.andExpect(status().isCreated());
	}

	@Test
	void putBankAccount_sucess() throws Exception {
		Mockito.when(bankAccountRepository.getOneBankAccountPerId(bankAccount1.getId())).thenReturn(bankAccount1);
		Mockito.when(bankAccountRepository.modifyBankAccount(bankAccount2)).thenReturn(bankAccount2);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/bankaccount/"+bankAccount1.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(bankAccount2));

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	void deleteBankAccount_sucess() throws Exception {
		Mockito.when(bankAccountRepository.getOneBankAccountPerId(bankAccount1.getId())).thenReturn(bankAccount1);

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/bankaccount/"+bankAccount1.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
