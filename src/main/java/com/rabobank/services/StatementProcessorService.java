package com.rabobank.services;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author vinesh
 *
 */

public interface StatementProcessorService {

	void process(MultipartFile file);

}
