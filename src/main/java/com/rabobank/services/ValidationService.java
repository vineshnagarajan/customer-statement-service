package com.rabobank.services;

import com.rabobank.domain.Record;

public interface ValidationService {

	boolean validateDuplicate(Record record);

	boolean validateEndBalance(Record record);

}
