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

@Component
@Qualifier("csvreader")
public class CSVStatementReaderImpl implements StatementReader<Records> {

	private static final Logger logger = LoggerFactory.getLogger(CSVStatementReaderImpl.class);

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

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * CSVParser parser; try { parser =
		 * CSVFormat.EXCEL.withHeader().withDelimiter(',') .parse(new
		 * InputStreamReader(file.getInputStream()));
		 * 
		 * parser.forEach(record -> { Record customerRecords = new Record(new
		 * Long(record.get("Reference")), record.get("AccountNumber"),
		 * record.get("Description"), new
		 * BigDecimal(record.get("Start Balance")), new
		 * BigDecimal(record.get("Mutation")), new
		 * BigDecimal(record.get("End Balance")));
		 * 
		 * customerStatements.add(customerRecords); });
		 * 
		 * records.setRecords(customerStatements); }
		 * 
		 * catch (IOException e) { logger.error("Exception on Reading CSV file "
		 * , e); }
		 */

		return records;

	}

}
