package com.rabobank.enums;

import org.apache.commons.io.FilenameUtils;

/**
 * @author vinesh
 * 
 *         this file used to validate the input and output statement file type.
 *         the file will not be processed if file type not matching
 *
 */
public enum StatementFileType {
	CSV("csv"), XML("xml"), FILE_TYPE_NOT_SUPPORTED("unsupported File Format !!!");

	String fileExtension;

	private StatementFileType(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	String getFileExtension() {
		return fileExtension;
	}

	public static StatementFileType getFileType(String inputFilePath) {
		if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), CSV.getFileExtension())) {
			return CSV;
		} else if (FilenameUtils.isExtension(inputFilePath.toLowerCase(), XML.getFileExtension())) {
			return XML;
		}
		return FILE_TYPE_NOT_SUPPORTED;
	}

}
