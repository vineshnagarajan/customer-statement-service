package com.rabobank.reader;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author vinesh
 *
 */
public interface StatementReader<T> {

	T readStatement(MultipartFile file);

}
