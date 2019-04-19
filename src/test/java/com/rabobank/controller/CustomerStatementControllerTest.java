package com.rabobank.controller;

import java.io.File;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.rabobank.exception.RaboExceptionHandler;
import com.rabobank.factory.StatementFactory;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.services.StatementProcessorService;

/**
 * @author vinesh
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerStatementControllerTest {

	private InputStream statementStream;
	private MockMvc mockMvc;
	private static final String TEST_FILE_NAME = "dummy.txt";

	@Mock
	StatementProcessorService statementProcessor;

	@Spy
	StatementFactory statementFactory;

	@Spy
	@InjectMocks
	private CustomerStatementController controller = new CustomerStatementController();

	@Spy
	CustomerStatementsRepository customerStatementsRepository;

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void init() {

		mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new RaboExceptionHandler()).build();
		statementStream = controller.getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME);

	}

	@Test
	public void getValidatedStatementMediaTypeTest() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(TEST_FILE_NAME).getFile());
		file.getAbsolutePath();

		MockMultipartFile mockMultipartFile = new MockMultipartFile("files", TEST_FILE_NAME, "application/text",
				statementStream);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/rabo/getValidatedStatement")
				.file(mockMultipartFile).contentType(MediaType.TEXT_PLAIN)).andReturn();

		Assert.assertEquals(415, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString().contains("FILE_TYPE_NOT_SUPPORTED"));
	}
}
