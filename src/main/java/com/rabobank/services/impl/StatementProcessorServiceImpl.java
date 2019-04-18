package com.rabobank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementController;
import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Records;
import com.rabobank.factory.StatementFactory;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.services.ValidationService;
import com.rabobank.services.StatementProcessorService;

@Service
public class StatementProcessorServiceImpl implements StatementProcessorService {

	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	StatementFactory statementFactory;

	@Autowired
	ValidationService validationService;

	@Autowired
	CustomerStatementsRepository customerStatementsRepository;

	@Override
	public void process(MultipartFile file) {
		
		try {
			Records statements = (Records) statementFactory.getFileReader(file).readStatement(file);
			statements.getRecords().parallelStream().forEach(record -> {

				validationService.validateEndBalance(record);

				validationService.validateDuplicate(record);

				CustomerStatements statments = new CustomerStatements();

				BeanUtils.copyProperties(record, statments);

				customerStatementsRepository.save(statments);

			});
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
