package fr.iut.loan_approval;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.iut.LoanApprovalApplication;
import fr.iut.loan_approval.model.*;
import fr.iut.loan_approval.services.LoanApprovalService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = LoanApprovalApplication.class)
@AutoConfigureMockMvc
class LoanApprovalServiceTests {
	@MockBean
	private LoanApprovalService loanApprovalService;
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;

	LoanApproval loanApproval1 = new LoanApproval(1111,100000);
	LoanApproval loanApproval2 = new LoanApproval(2222,10);
	BankAccount bankAccount1 = new BankAccount(1111,"Pays", "Antoine", 15000.0, Risk.HIGH);
	BankAccount bankAccount2 = new BankAccount(2222,"Pays", "Antoine", 15000.0, Risk.LOW);
	LoanResponse loanResponse1 = new LoanResponse(bankAccount1, Response.REFUSED);
	LoanResponse loanResponse2 = new LoanResponse(bankAccount2, Response.ACCEPTED);

	@Test
	void postLoanApproval_sucess() throws Exception {
		Mockito.when(loanApprovalService.addLoanApproval(loanApproval1)).thenReturn(loanResponse1);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/loanapproval")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(loanApproval1));

		mockMvc.perform(mockRequest)
				.andExpect(status().isCreated());
	}

}
