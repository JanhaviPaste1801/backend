package com.app.insurance.service;

import java.util.List;
import java.util.Optional;

import com.app.insurance.model.ClaimedPolicy;
import com.app.insurance.model.ResponseTemplate;
import com.app.insurance.model.User;

import javassist.NotFoundException;
/**
 * @author jpaste
 * This interface contains abstract methods of {@link ClaimedPolicyService}
 *
 */
public interface ClaimedPolicyService {

	List<ClaimedPolicy> getAllClaimedPolicies();

	ClaimedPolicy addClaimedPolicy(ClaimedPolicy claimedPolicy) throws NotFoundException;

	ClaimedPolicy updateClaimedPolicy(ClaimedPolicy claimedPolicy);

	void deleteClaimedPolicy(int planId);

	Optional<ClaimedPolicy> viewClaimedPolicy(int planId);

	List<ResponseTemplate> getClaimPolicyByUser(int userId) throws NotFoundException;

	ClaimedPolicy claimAPolicy(int policyId, int userId, ClaimedPolicy claimedPolicy);

	List<ResponseTemplate> listOfAllClaimedPolicies();

	List<ResponseTemplate> findByPlanIdAndUserId(int userId, int planId);

	List<User> getPolicyHoldersByPolicy(int policyId) throws NotFoundException;

}
