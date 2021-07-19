package com.app.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.insurance.model.ClaimedPolicy;
/**
 * @author jpaste This interface extends {@link JpaRepository} which provides
 *         JPA functionalities for the entity class {@link ClaimedPolicy} that
 *         is being managed.
 */
@Repository
public interface ClaimedPolicyRepository extends JpaRepository<ClaimedPolicy, Integer> {

	List<ClaimedPolicy> findAllByUserId(int userId);

	ClaimedPolicy findByPolicyId(int policyId);

	ClaimedPolicy findByPolicyIdAndUserId(int policyId, int userId);

	List<ClaimedPolicy> findAllByUserIdAndPlanId(int userId, int planId);

	List<ClaimedPolicy> findAllByPolicyId(int policyId);

	
}
