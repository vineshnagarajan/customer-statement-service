package com.rabobank.reader;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Record;
import com.rabobank.domain.Records;
import com.rabobank.factory.StatementFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementReaderTest {

	@Autowired
	StatementFactory statementFactory;

	@Autowired
	@Qualifier("csvreader")
	StatementReader<Records> csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader<Records> xmlReader;

	MultipartFile csvFile;

	MultipartFile xmlFile;

	MultipartFile wrongFile;

	private Record record;

	@Before
	public void init() {

		record = new Record();
		record.setAccountNumber("NL91RABO0315273637");
		record.setReference(Long.valueOf(194261));
		record.setDescription("Clothes from Jan Bakker");
		record.setStartBalance(BigDecimal.valueOf(21.6));
		record.setEndBalance(BigDecimal.valueOf(-20.23));
		record.setMutation(BigDecimal.valueOf(-41.83));
		record.setIsUniqueStatement(false);

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File csvTestFile = new File(classLoader.getResource("records.csv").getFile());
			csvFile = new MockMultipartFile("records.csv", csvTestFile.getName(), "text/csv",
					IOUtils.toByteArray(new FileInputStream(csvTestFile)));
			File xmlTestFile = new File(classLoader.getResource("records.xml").getFile());
			xmlFile = new MockMultipartFile("records.xml", xmlTestFile.getName(), "text/xml",
					IOUtils.toByteArray(new FileInputStream(xmlTestFile)));
			File wrongTestFile = new File(classLoader.getResource("dummy.txt").getFile());
			wrongFile = new MockMultipartFile("dummy.txt", wrongTestFile.getName(), "text/plain",
					IOUtils.toByteArray(new FileInputStream(wrongTestFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test()
	public void readStatementCsvTest() {

		assertEquals(Long.valueOf(194261), csvReader.readStatement(csvFile).getRecords().get(0).getReference());

	}
	
	@Test()
	public void readStatementXmlTest() {

		assertEquals(Long.valueOf(194261), xmlReader.readStatement(xmlFile).getRecords().get(0).getReference());

	}

}
