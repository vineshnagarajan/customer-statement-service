package com.rabobank.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.exception.FileFormatException;
import com.rabobank.factory.StatementFactory;
import com.rabobank.repository.CustomerStatementsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatementProcessorServiceTest {

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
			wrongFile = new MockMultipartFile("dummy.txt",wrongTestFile.getName(),"text/plain" ,IOUtils.toByteArray(new FileInputStream(wrongTestFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test(expected = FileFormatException.class)
	public void processFailureTest() {

		statementProcessorService.process(wrongFile);

	}

}
