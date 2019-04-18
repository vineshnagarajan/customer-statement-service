package com.rabobank.controller;

import java.io.File;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rabobank.factory.StatementFactory;
import com.rabobank.services.StatementProcessorService;

@RunWith(MockitoJUnitRunner.class)
public class StatementProcessorControllerTest {

	private InputStream statementStream;
	private MockMvc mockMvc;
	private static final String TEST_FILE_NAME = "records.csv";

	@Mock
	StatementProcessorService statementProcessor;

	@Spy
	StatementFactory statementFactory;

	@Spy
	@InjectMocks
	private StatementController controller = new StatementController();

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		statementStream = controller.getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void getValidatedStatementTest() throws Exception {

		ClassLoader classLoader = getClass().getClassLoader();
		HttpServletResponse response ;
		File file = new File(classLoader.getResource("records.csv").getFile());
		file.getAbsolutePath();
		System.out.println("checkingfile if exist" + file.getAbsolutePath());

		MockMultipartFile mockMultipartFile = new MockMultipartFile("files", TEST_FILE_NAME, "application/csv",
				statementStream);
	

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.multipart("/rabo/getValidatedStatement").file(mockMultipartFile)
						.contentType(MediaType.MULTIPART_FORM_DATA))
				.andExpect(MockMvcResultMatchers.status().is(200)).andReturn();
		Assert.assertEquals(200, result.getResponse().getStatus());
		Assert.assertNotNull(result.getResponse().getContentAsString());
		Assert.assertEquals(TEST_FILE_NAME, result.getResponse().getContentAsString());
	}
}
