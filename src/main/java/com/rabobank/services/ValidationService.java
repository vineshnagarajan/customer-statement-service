package com.rabobank.services;

import com.rabobank.domain.Record;

/**
 * @author vinesh
 *
 */
public interface ValidationService {

	boolean validateDuplicate(Record record);

	boolean validateEndBalance(Record record);

}
