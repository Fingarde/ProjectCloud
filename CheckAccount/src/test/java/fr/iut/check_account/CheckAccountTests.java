package fr.iut.check_account;

import fr.iut.check_account.exception.CheckAccountError;
import fr.iut.check_account.model.BankAccount;
import fr.iut.check_account.model.Risk;
import fr.iut.check_account.service.CheckAccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CheckAccountTests {

	@MockBean
	private CheckAccountService checkAccountService;
	@Autowired
	private MockMvc mockMvc;

	BankAccount bankAccount1 = new BankAccount(1111,"Pays", "Antoine", 15000.0, Risk.HIGH);

	@Test
	void getAllBankAccount_sucess() throws Exception {
		Mockito.when(checkAccountService.getRisk(bankAccount1.getId()))
          .thenReturn(bankAccount1.getRisk());

		mockMvc.perform(MockMvcRequestBuilders
						.get("/checkaccount/"+bankAccount1.getId())
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());


	}

//	@Test
//	void getAllBankAccount_wrongId() throws Exception {
//		Mockito.when(checkAccountService.getRisk(0))
//				.thenThrow(new CheckAccountError("Error not account found",  HttpStatus.NOT_FOUND));
//
//		mockMvc.perform(MockMvcRequestBuilders
//						.get("/checkaccount/0")
//						.contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isNotFound());
//
//
//	}

}
