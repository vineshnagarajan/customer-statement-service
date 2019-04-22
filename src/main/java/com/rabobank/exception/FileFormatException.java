package com.rabobank.exception;

import com.rabobank.enums.StatementFileType;

/**
 * @author vinesh
 *
 */

public class FileFormatException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * @param fileType
	 */
	public FileFormatException(StatementFileType fileType) {
		super("UnSupported File Format !!! : " + fileType);
	}

}
