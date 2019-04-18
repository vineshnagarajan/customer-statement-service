package com.rabobank.services;

import org.springframework.web.multipart.MultipartFile;


public interface StatementProcessorService {

	void process(MultipartFile file);

}
