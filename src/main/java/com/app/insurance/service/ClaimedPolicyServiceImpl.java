package com.app.insurance.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.app.insurance.model.ClaimedPolicy;
import com.app.insurance.model.Policy;
import com.app.insurance.model.ResponseTemplate;
import com.app.insurance.model.User;
import com.app.insurance.repository.ClaimedPolicyRepository;

import javassist.NotFoundException;

/**
 * @author jpaste This class contains all the implemented methods and its
 *         business logic of its interface
 *
 */
@Service
@Transactional
public class ClaimedPolicyServiceImpl implements ClaimedPolicyService {

	@Autowired
	ClaimedPolicyRepository claimedPolicyRepository;

	@Autowired
	RestTemplate restTemplate;

	private static final String USER_URL = "http://localhost:9098/users/viewUser/";

	private static final String POLICY_URL = "http://localhost:9098/policy/viewPolicy/";

	Logger logger = LoggerFactory.getLogger(ClaimedPolicyServiceImpl.class);

	/**
	 * This method return {@link List} of {@link ClaimedPolicy} with respective
	 * policies
	 * 
	 * @return {@link List} of {@link ClaimedPolicy}
	 */
	@Override
	public List<ClaimedPolicy> getAllClaimedPolicies() {
		return claimedPolicyRepository.findAll();
	}

	/**
	 * This method return {@link ClaimedPolicy} that is added
	 * 
	 * @param claimedPolicy
	 * @return {@link ClaimedPolicy}
	 * @throws NotFoundException
	 */
	@Override
	@Transactional
	public ClaimedPolicy addClaimedPolicy(ClaimedPolicy claimedPolicy) throws NotFoundException {
		ClaimedPolicy claimedPolicies = claimedPolicyRepository.findByPolicyIdAndUserId(claimedPolicy.getPolicyId(),
				claimedPolicy.getUserId());
		if (claimedPolicies == null) {
			claimedPolicy.setClaimedDate(LocalDate.now().toString());
			logger.info("Claimed Policy Added Successfully");
			return claimedPolicyRepository.save(claimedPolicy);
		} else {
			logger.info("Error while adding because user can take the plan only once");
			throw new NotFoundException("Already taken");
		}
	}

	/**
	 * This method return {@link ClaimedPolicy} that is updated
	 * 
	 * @param claimedPolicy
	 * @return {@link ClaimedPolicy}
	 */
	@Override
	@Transactional
	public ClaimedPolicy updateClaimedPolicy(ClaimedPolicy claimedPolicy) {
		logger.info("Claimed Policy Updated Successfully");
		return claimedPolicyRepository.save(claimedPolicy);
	}

	/**
	 * This method deletes a ClaimedPolicy based on the id passed
	 * 
	 * @param planId
	 */
	@Override
	@Transactional
	public void deleteClaimedPolicy(int planId) {
		logger.info("Claimed Policy Deleted Successfully");
		claimedPolicyRepository.deleteById(planId);

	}

	/**
	 * This method return {@link Optional} of {@link ClaimedPolicy} based on the id
	 * 
	 * @param planId
	 * @return {@link Optional} of {@link ClaimedPolicy}
	 */
	@Override
	public Optional<ClaimedPolicy> viewClaimedPolicy(int planId) {
		return claimedPolicyRepository.findById(planId);
	}

	/**
	 * This method return {@link List} polices holed by particular user based on the
	 * accepted userId
	 * 
	 * @param userId
	 * @return {@link List} of {@link ResponseTemplate}
	 * @throws NotFoundException
	 */
	@Override
	public List<ResponseTemplate> getClaimPolicyByUser(int userId) throws NotFoundException {
		List<ResponseTemplate> responseTemplates = new ArrayList<>();
		ResponseTemplate responseTemplate = null;
		List<ClaimedPolicy> policyList = claimedPolicyRepository.findAll();
		if (policyList.isEmpty()) {
			throw new NotFoundException("Claimed Policy list is empty");
		}
		for (ClaimedPolicy claimPolicyDetails : policyList) {
			User user = restTemplate.getForObject(USER_URL + userId, User.class);
			Policy policy = restTemplate.getForObject(POLICY_URL + claimPolicyDetails.getPolicyId(), Policy.class);
			responseTemplate = new ResponseTemplate(user, policy, claimPolicyDetails);
			responseTemplates.add(responseTemplate);
		}
		logger.info("List of User with their claimed plans is retreived");
		return responseTemplates;
	}

	/**
	 * This method return {@link List} of {@link User} based on the PolicyId
	 * 
	 * @param policyId
	 * @return {@link List} of {@link User}
	 * @throws NotFoundException
	 */
	public List<User> getPolicyHoldersByPolicy(int policyId) throws NotFoundException {
		List<ClaimedPolicy> policyList = claimedPolicyRepository.findAllByPolicyId(policyId);
		if (policyList.isEmpty()) {
			throw new NotFoundException("You don't have policies");
		}
		ClaimedPolicy claimPolicyDetails = null;
		List<User> allUsers = new ArrayList<User>();

		for (int i = 0; i < policyList.size(); i++) {
			claimPolicyDetails = policyList.get(i);
			User user = restTemplate.getForObject(USER_URL + claimPolicyDetails.getUserId(), User.class);
			allUsers.add(i, user);
		}
		logger.info("List of Users who have taken that speific policy is retreived");
		return allUsers;
	}

	/**
	 * This method return {@link ClaimedPolicy} based on the policyId, userId and
	 * claimedPolicy
	 * 
	 * @param policyId
	 * @param userId
	 * @param claimedPolicy
	 * @return {@link ClaimedPolicy}
	 */
	@Override
	public ClaimedPolicy claimAPolicy(int policyId, int userId, ClaimedPolicy claimedPolicy) {
		// int claimedpolicyId = claimedPolicy.getPolicyId();
		claimedPolicy.setClaimedDate(LocalDate.now().toString());
		claimedPolicy.setPolicyId(policyId);
		claimedPolicy.setUserId(userId);
		logger.info("Claimed Ploicy with the given user id and policy id is retreived");
		return claimedPolicyRepository.save(claimedPolicy);
	}

	/**
	 * This method return {@link List} of ClaimedPolicies with respective policies
	 * 
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@Override
	public List<ResponseTemplate> listOfAllClaimedPolicies() {

		List<ResponseTemplate> responseTemplates = new ArrayList<ResponseTemplate>();
		ResponseTemplate responseTemplate = null;
		List<ClaimedPolicy> policyList = claimedPolicyRepository.findAll();
		ClaimedPolicy claimPolicyDetails = null;
		for (int i = 0; i < policyList.size(); i++) {
			claimPolicyDetails = policyList.get(i);
			User user = restTemplate.getForObject(USER_URL + claimPolicyDetails.getUserId(), User.class);
			Policy policy = restTemplate.getForObject(POLICY_URL + claimPolicyDetails.getPolicyId(), Policy.class);
			responseTemplate = new ResponseTemplate(user, policy, claimPolicyDetails);
			responseTemplates.add(i, responseTemplate);
		}
		logger.info("List of User with their claimed plans along with the policy details is retreived ");
		return responseTemplates;
	}

	/**
	 * This method accepts userId and planId and return {@link List} of users and
	 * their plans
	 * 
	 * @param userId
	 * @param planId
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@Override
	public List<ResponseTemplate> findByPlanIdAndUserId(int userId, int planId) {
		List<ResponseTemplate> responseTemplates = new ArrayList<ResponseTemplate>();
		ResponseTemplate responseTemplate = null;
		// List<ClaimedPolicy> claim =
		// claimedPolicyRepository.findAllByUserIdAndPlanId(userId, planId);
		List<ClaimedPolicy> policyList = claimedPolicyRepository.findAllByUserIdAndPlanId(userId, planId);
		ClaimedPolicy claimPolicyDetails = null;
		for (int i = 0; i < policyList.size(); i++) {
			claimPolicyDetails = policyList.get(i);
			User user = restTemplate.getForObject(USER_URL + claimPolicyDetails.getUserId(), User.class);
			Policy policy = restTemplate.getForObject(POLICY_URL + claimPolicyDetails.getPolicyId(), Policy.class);
			responseTemplate = new ResponseTemplate(user, policy, claimPolicyDetails);
			responseTemplates.add(i, responseTemplate);
		}
		logger.info("List of User with their user id and plan id is retreived");
		return responseTemplates;
	}

}
