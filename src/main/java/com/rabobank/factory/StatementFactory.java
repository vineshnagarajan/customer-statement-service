package com.rabobank.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.domain.Records;
import com.rabobank.enums.StatementFileType;
import com.rabobank.exception.FileFormatException;
import com.rabobank.reader.StatementReader;
import com.rabobank.writer.StatementWriter;

/**
 * @author vinesh
 * 
 *         Factory class to invoke concrete implementation
 *
 */

@Component
public class StatementFactory {

	@Autowired
	@Qualifier("csvreader")
	StatementReader<Records> csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader<Records> xmlReader;

	@Autowired
	@Qualifier("csvwriter")
	StatementWriter csvwriter;

	@Autowired
	@Qualifier("xmlwriter")
	StatementWriter xmlwriter;

	/**
	 * @param inputFile
	 * @return concrete impl
	 * 
	 *         This method return xmlReaderImpl or csvReaderImpl based on file
	 *         type else throw exception
	 */
	public StatementReader<Records> getFileReader(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		switch (fileType) {
		case CSV:
			return csvReader;
		case XML:
			return xmlReader;
		default:
			throw new FileFormatException(fileType);
		}
	}

	/**
	 * @param inputFile
	 * @return concrete impl
	 * 
	 *         This method return xmlWriterImpl or csvWriterImpl based on file
	 *         type else throw exception
	 */
	public StatementWriter getFileWriter(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		switch (fileType) {
		case CSV:
			return csvwriter;
		case XML:
			return xmlwriter;
		default:
			throw new FileFormatException(fileType);
		}
	}

}
