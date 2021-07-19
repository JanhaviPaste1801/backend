package com.app.insurance.service;

import java.util.List;
import java.util.Optional;

import com.app.insurance.model.Policy;

import javassist.NotFoundException;

/**
 * @author jpaste
 * This interface contains abstract methods of {@link Policy}
 *
 */
public interface PolicyService {

	List<Policy> getAllPolicies(String token) throws NotFoundException;

	Policy addPolicy(Policy policy, String token);

	Policy updatePolicy(Policy policy);

	Policy deletePolicy(int policyId);

	Optional<Policy> viewPolicy(int policyId);

}
