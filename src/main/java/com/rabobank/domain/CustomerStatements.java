package com.rabobank.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMER_STATEMENTS")
public class CustomerStatements {

	@Id
	@Column(name = "REFERENCE")
	private Long reference;

	@Column(name = "ACCOUNT_NUMBER")
	private String accountNumber;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "START_BALANCE")
	private BigDecimal startBalance;

	@Column(name = "MUTATION")
	private BigDecimal mutation;

	@Column(name = "END_BALANCE")
	private BigDecimal endBalance;

	@Column(name = "IS_VALID_END_BALANCE")
	private Boolean isValidEndBalance;

	@Column(name = "IS_UNIQUE_STATEMENT")
	private Boolean isUniqueStatement;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getEndBalance() {
		return endBalance;
	}

	public void setEndBalance(BigDecimal endBalance) {
		this.endBalance = endBalance;
	}

	public Boolean getIsValidEndBalance() {
		return isValidEndBalance;
	}

	public void setIsValidEndBalance(Boolean isValidEndBalance) {
		this.isValidEndBalance = isValidEndBalance;
	}

	public Boolean getIsUniqueStatement() {
		return isUniqueStatement;
	}

	public void setIsUniqueStatement(Boolean isUniqueStatement) {
		this.isUniqueStatement = isUniqueStatement;
	}

}
