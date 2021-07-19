package com.app.insurance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.insurance.model.Policy;
import com.app.insurance.repository.PolicyRepository;
import com.app.insurance.util.JwtUtil;

import javassist.NotFoundException;

/**
 * @author jpaste
 * This class contains all the implemented methods and its business logic of its interface 
 *
 */
@Service
public class PolicyServiceImpl implements PolicyService {
	
	@Autowired
	PolicyRepository policyRepository;
	
	Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

	/**
	 * This method return {@link List} of {@link Policy}
	 * @return {@link List} of {@link Policy}
	 * @throws NotFoundException 
	 */
	@Override
	public List<Policy> getAllPolicies(String token) throws NotFoundException {
		 //int verifiedId = JwtUtil.tokenVerification(token);
		List<Policy> policies = policyRepository.findAll();
        if(policies.isEmpty()) {
            throw new NotFoundException("Policy list is empty");
        }
        logger.info("All  policies returned from Policy Service");
        return policies;
	}
	
	/**
	 *  This method return {@link Policy} that is added 
	 * @param policy
	 * @return {@link Policy}
	 */
	@Override
	@Transactional
	public Policy addPolicy(Policy policy, String token) {
		 //int verifiedId = JwtUtil.tokenVerification(token);
		logger.info("Policy Added Successfully");
		return policyRepository.save(policy);
	}

	/**
	 * This method return {@link Policy} that is updated 
	 * @param policy
	 * @return {@link Policy}
	 */
	@Override
	@Transactional
	public Policy updatePolicy(Policy policy) {
		logger.info("Policy Updated Successfully");
		return  policyRepository.save(policy);
	}

	/**
	 * This method return {@link Policy} that is deleted based on the passed policyId
	 * @param policyId
	 * @return {@link Policy}
	 */
	@Override
	@Transactional
	public Policy deletePolicy(int policyId) {
		Policy policy = policyRepository.findByPolicyId(policyId);
		policy.setDeleted(true);
		logger.info("Policy With the given policy id is soft deleted");
		return policyRepository.save(policy);
		
	}

	/**
     * This method return {@link Optional} of {@link Policy} based on the passed policyId
	 * @param policyId
     * @return {@link Optional} of {@link Policy}
	 */
	@Override
	public Optional<Policy> viewPolicy(int policyId) {
		logger.info("Policy With the given policy id is retrieved");
		return policyRepository.findById(policyId);
	}
	
	
}
