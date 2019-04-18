package com.rabobank.domain;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vinesh
 *
 */
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class Record {
	/**
	 * 
	 */
	@XmlAttribute
	private Long reference;
	private String accountNumber;
	private BigDecimal startBalance;
	private BigDecimal mutation;
	private String description;
	private BigDecimal endBalance;
	private boolean isValidEndBalance;
	private Boolean isUniqueStatement;

	public Record(Long reference, String accountNumber, String description, BigDecimal startBalance,
			BigDecimal mutation, BigDecimal endBalance) {
		super();
		this.reference = reference;
		this.accountNumber = accountNumber;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.description = description;
		this.endBalance = endBalance;
		this.isValidEndBalance = false;
		this.isUniqueStatement = false;

	}

	public Record() {
	}

	public Long getReference() {
		return reference;
	}

	public void setReference(Long reference) {
		this.reference = reference;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getStartBalance() {
		return startBalance;
	}

	public void setStartBalance(BigDecimal startBalance) {
		this.startBalance = startBalance;
	}

	public BigDecimal getMutation() {
		return mutation;
	}

	public void setMutation(BigDecimal mutation) {
		this.mutation = mutation;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public boolean getIsValidEndBalance() {
		return isValidEndBalance;
	}

	public void setIsValidEndBalance(boolean isValidEndBalance) {
		this.isValidEndBalance = isValidEndBalance;
	}

	public Boolean getIsUniqueStatement() {
		return isUniqueStatement;
	}

	public void setIsUniqueStatement(Boolean isUniqueStatement) {
		this.isUniqueStatement = isUniqueStatement;
	}

	@Override
	public String toString() {
		return "CustomerStatements [reference=" + reference + ", accountNumber=" + accountNumber + ", start_Balance="
				+ startBalance + ", mutation=" + mutation + ", description=" + description + ", end_Balance="
				+ endBalance + ", isValidEndBalance=" + isValidEndBalance + ", isUniqueStatement=" + isUniqueStatement
				+ "]";
	}

}
