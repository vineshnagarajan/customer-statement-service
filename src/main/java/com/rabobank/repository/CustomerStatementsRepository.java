package com.rabobank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabobank.domain.CustomerStatements;

@Repository
public interface CustomerStatementsRepository extends JpaRepository<CustomerStatements,Long>{

}