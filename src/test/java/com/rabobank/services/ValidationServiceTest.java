package com.rabobank.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Record;
import com.rabobank.repository.CustomerStatementsRepository;

/**
 * @author vinesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidationServiceTest {

	@Autowired
	ValidationService validationService;

	@MockBean
	CustomerStatementsRepository customerStatementsRepository;

	private Record record;

	private Record emptyRecord = null;

	List<CustomerStatements> resultMatch = new ArrayList<>();

	List<CustomerStatements> resultNotMatch = new ArrayList<>();

	@Before
	public void init() {

		record = new Record();
		record.setAccountNumber("NL91RABO0315273637");
		record.setReference(Long.valueOf(194261));
		record.setDescription("Clothes from Jan Bakker");
		record.setStartBalance(BigDecimal.valueOf(21.6));
		record.setEndBalance(BigDecimal.valueOf(-41.83));
		record.setMutation(BigDecimal.valueOf(-20.23));

		CustomerStatements statments = new CustomerStatements();
		BeanUtils.copyProperties(record, statments);
		resultMatch.add(statments);
	}

	@Test(expected = NullPointerException.class)
	public void validateDuplicateNull() {

		when(customerStatementsRepository.findByReference(any(Long.class))).thenReturn(resultMatch);
		validationService.validateDuplicate(emptyRecord);
		assertEquals(false, emptyRecord.getIsUniqueStatement());

	}

	@Test
	public void validateDuplicateAccurate() {

		when(customerStatementsRepository.findByReference(any(Long.class))).thenReturn(resultMatch);
		validationService.validateDuplicate(record);
		assertEquals(false, record.getIsUniqueStatement());
	}

	@Test
	public void validateDuplicateInAccurate() {

		when(customerStatementsRepository.findByReference(any(Long.class))).thenReturn(resultNotMatch);
		validationService.validateDuplicate(record);
		assertEquals(true, record.getIsUniqueStatement());

	}

	@Test(expected = NullPointerException.class)
	public void validateEndBalanceNull() {
		validationService.validateEndBalance(emptyRecord);
		assertEquals(false, emptyRecord.getIsValidEndBalance());

	}

	@Test
	public void validateEndBalanceInAccurate() {
		validationService.validateEndBalance(record);
		assertEquals(false, record.getIsValidEndBalance());

	}

	@Test
	public void validateEndBalanceAccurate() {
		record.setStartBalance(BigDecimal.valueOf(15));
		record.setMutation(BigDecimal.valueOf(-5));
		record.setEndBalance(BigDecimal.valueOf(10));
		validationService.validateEndBalance(record);
		assertEquals(true, record.getIsValidEndBalance());

	}

}
