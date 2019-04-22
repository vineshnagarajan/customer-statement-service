package com.rabobank.reader.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rabobank.domain.Record;
import com.rabobank.domain.Records;
import com.rabobank.reader.StatementReader;

/**
 * @author vinesh
 *
 */
@Component
@Qualifier("csvreader")
public class CSVStatementReaderImpl implements StatementReader<Records> {

	private static final Logger logger = LoggerFactory.getLogger(CSVStatementReaderImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.reader.StatementReader#readStatement(org.springframework.web
	 * .multipart.MultipartFile)
	 * 
	 * this take csv file as input and parse it .
	 */
	@Override
	public Records readStatement(MultipartFile file) {
		List<Record> customerStatements = new ArrayList<>();
		Records records = new Records();
		CSVReader csvReader;
		try {
			csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
			CsvToBean<Record> csvToBean = new CsvToBeanBuilder<Record>(csvReader).withType(Record.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			csvToBean.forEach(record -> {

				Record customerRecords = new Record(record.getReference(), record.getAccountNumber(),
						record.getDescription(), record.getStartBalance(), record.getMutation(),
						record.getEndBalance());

				customerStatements.add(customerRecords);
			});
			records.setRecords(customerStatements);

		} catch (IOException e) {
			logger.error("IO Exception While reading the statement from CSV", e);
		}

		return records;

	}

}
