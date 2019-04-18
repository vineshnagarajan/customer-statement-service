package com.rabobank.exception;

import com.rabobank.enums.StatementFileType;

public class FileFormatException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileFormatException(StatementFileType fileType) {
        super("UnSupported File Format !!! : " + fileType);
    } 

}
