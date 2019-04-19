package com.rabobank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Record;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.services.ValidationService;

/**
 * @author vinesh
 *
 */
@Component
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	CustomerStatementsRepository customerStatementsRepository;

	@Override
	public boolean validateDuplicate(Record record) {
		if (record != null) {
			List<CustomerStatements> statement = customerStatementsRepository.findByReference(record.getReference());
			record.setIsUniqueStatement(statement.isEmpty());
			return record.getIsUniqueStatement();
		}
		return false;
	}

	@Override
	public boolean validateEndBalance(Record record) {

		if (record != null
				&& record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) == 0) {
			record.setIsValidEndBalance(true);
			return record.getIsValidEndBalance();
		}
		return false;

	}

}
