package com.rabobank.services;

import com.rabobank.domain.Record;

/**
 * @author vinesh
 *
 */
public interface ValidationService {

	void validateDuplicate(Record record);

	void validateEndBalance(Record record);

}
