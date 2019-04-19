package com.rabobank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rabobank.domain.CustomerStatements;

/**
 * @author vinesh
 *
 */
@Repository
public interface CustomerStatementsRepository extends JpaRepository<CustomerStatements, Long> {

	List<CustomerStatements> findByReference(Long referenceId);

}