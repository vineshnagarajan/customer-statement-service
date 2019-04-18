package com.rabobank.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Record;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.services.ValidationService;

@Component
public class ValidationServiceImpl implements ValidationService {

	@Autowired
	CustomerStatementsRepository customerStatementsRepository;

	@Override
	public boolean validateDuplicate(Record record) {
		if (record != null) {
			Optional<CustomerStatements> statement = customerStatementsRepository.findById(record.getReference());
			record.setIsUniqueStatement(!statement.isPresent());
			return statement.isPresent();
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
