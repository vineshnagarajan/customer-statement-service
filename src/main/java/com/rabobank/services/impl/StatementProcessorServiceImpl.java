package com.rabobank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Records;
import com.rabobank.enums.StatementFileType;
import com.rabobank.exception.FileFormatException;
import com.rabobank.factory.StatementFactory;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.services.StatementProcessorService;
import com.rabobank.services.ValidationService;

@Service
public class StatementProcessorServiceImpl implements StatementProcessorService {

	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorServiceImpl.class);

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
		} catch (FileFormatException e) {
			logger.error(e.getMessage());

			throw new FileFormatException(StatementFileType.FILE_TYPE_NOT_SUPPORTED);

		}

	}

}
