package com.rabobank.writer.impl;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.rabobank.domain.CustomerStatements;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.writer.StatementWriter;

@Component(value = "csvwriter")
public class CSVStatementWriterImpl implements StatementWriter {
	/**
	 * @author vinesh
	 *
	 */
	private static final Logger logger = LoggerFactory.getLogger(CSVStatementWriterImpl.class);

	@Autowired
	CustomerStatementsRepository customerStatementsRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rabobank.writer.StatementWriter#writeOutputReport(javax.servlet.http.
	 * HttpServletResponse)
	 * 
	 * This method get records from DB and convert to csv , then write to HTTP
	 * response
	 */
	@Override
	public void writeOutputReport(HttpServletResponse response) {
		String filename = "OutputReport.csv";

		response.setContentType("text/csv");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

		try {
			StatefulBeanToCsv<CustomerStatements> writer = new StatefulBeanToCsvBuilder<CustomerStatements>(
					response.getWriter()).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
							.withSeparator(CSVWriter.DEFAULT_SEPARATOR).withOrderedResults(false).build();
			writer.write(customerStatementsRepository.findAll());

		} catch (Exception e) {
			logger.error("Excveption on Writing Output", e);
		}
	}
}
