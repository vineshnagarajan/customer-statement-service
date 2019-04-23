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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.services.ValidationService#validateDuplicate(com.rabobank.
	 * domain.Record)
	 * 
	 * This method validate duplicate reference field in inputfile
	 */
	@Override
	public void validateDuplicate(Record record) {
		if (record != null) {
			List<CustomerStatements> statement = customerStatementsRepository.findByReference(record.getReference());
			record.setIsUniqueStatement(statement.isEmpty());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.services.ValidationService#validateEndBalance(com.rabobank.
	 * domain.Record)
	 * 
	 * this method validates endBalance field in input file
	 */
	@Override
	public void validateEndBalance(Record record) {

		if (record != null
				&& record.getStartBalance().add(record.getMutation()).compareTo(record.getEndBalance()) == 0) {
			record.setIsValidEndBalance(true);
		}

	}

}
