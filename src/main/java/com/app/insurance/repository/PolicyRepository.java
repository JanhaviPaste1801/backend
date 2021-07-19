
package com.app.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.insurance.model.Policy;
/**
 * @author jpaste This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link Policy} that
 *         is being managed.
 */
@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {

	Policy findByPolicyId(int policyId);
	
	@Query("SELECT p FROM Policy p WHERE p.deleted!=true")
    public List<Policy> findAll();

}
