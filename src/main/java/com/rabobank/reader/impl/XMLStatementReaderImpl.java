package com.rabobank.reader.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementController;
import com.rabobank.domain.Records;
import com.rabobank.reader.StatementReader;

@Component
@Qualifier("xmlreader")
public class XMLStatementReaderImpl implements StatementReader<Records> {
	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Override
	public Records readStatement(MultipartFile file) {
		Records statments = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Records.class);
			Unmarshaller unmarshallerObj = jaxbContext.createUnmarshaller();
			statments = (Records) unmarshallerObj.unmarshal(file.getInputStream());
		} catch (JAXBException e) {
			logger.error("JAXBException While reading the statement from XML", e);
		} catch (Exception e) {
			logger.error("Exception While reading the statement from XML", e);
		}
		return statments;

	}

}
