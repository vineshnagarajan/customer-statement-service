package com.rabobank.domain;

/**
 * @author vinesh
 *
 */
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)

public class Records {

	@XmlElement(name = "record")
	private List<Record> record;

	public Records(List<Record> record) {
		super();
		this.record = record;
	}

	public Records() {
	}

	public List<Record> getRecords() {
		return record;
	}

	public void setRecords(List<Record> record) {
		this.record = record;
	}

}
