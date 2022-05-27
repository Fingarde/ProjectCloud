package fr.iut.check_account;

import fr.iut.check_account.exception.CheckAccountError;
import fr.iut.check_account.model.BankAccount;
import fr.iut.check_account.model.Risk;
import fr.iut.check_account.service.CheckAccountService;
import fr.iut.config.Config;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

}
