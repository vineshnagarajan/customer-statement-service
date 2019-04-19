package com.rabobank.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileFormatException;
import com.rabobank.factory.StatementFactory;
import com.rabobank.factory.StatementFactoryTest;
import com.rabobank.repository.CustomerStatementsRepository;

/**
 * @author vinesh
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementProcessorServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(StatementFactoryTest.class);

	@Autowired
	StatementFactory statementFactory;

	@Autowired
	ValidationService validationService;

	@Autowired
	StatementProcessorService statementProcessorService;

	@MockBean
	CustomerStatementsRepository customerStatementsRepository;

	MultipartFile wrongFile;

	@Before
	public void init() {

		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File wrongTestFile = new File(classLoader.getResource("dummy.txt").getFile());
			wrongFile = new MockMultipartFile("dummy.txt", wrongTestFile.getName(), "text/plain",
					IOUtils.toByteArray(new FileInputStream(wrongTestFile)));
		} catch (IOException e) {
			logger.info(e.getMessage());
		}

	}

	@Test(expected = FileFormatException.class)
	public void processFailureTest() {

		statementProcessorService.process(wrongFile);

	}

}
