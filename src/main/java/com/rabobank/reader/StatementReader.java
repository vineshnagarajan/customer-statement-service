package com.rabobank.reader;

import org.springframework.web.multipart.MultipartFile;

public interface StatementReader<T> {

	T readStatement(MultipartFile file);

}
