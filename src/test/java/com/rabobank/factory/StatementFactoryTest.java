package com.rabobank.factory;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileFormatException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementFactoryTest {

	@Autowired
	StatementFactory statementFactory;

	MultipartFile csvFile;

	MultipartFile xmlFile;

	MultipartFile wrongFile;

	@Before
	public void init() {

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
	public void getFileReaderCsvTest() {

		assertEquals(true, !(statementFactory.getFileReader(csvFile).readStatement(csvFile).getRecords().isEmpty()));

	}

	@Test()
	public void getFileReaderXmlTest() {

		assertEquals(true, !(statementFactory.getFileReader(xmlFile).readStatement(xmlFile).getRecords().isEmpty()));

	}

	@Test(expected = FileFormatException.class)

	public void getFileReaderUnSupportedTest() {

		statementFactory.getFileReader(wrongFile).readStatement(wrongFile).getRecords().isEmpty();

	}

}
