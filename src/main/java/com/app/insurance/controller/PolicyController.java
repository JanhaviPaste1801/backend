package com.app.insurance.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.insurance.model.Policy;

import com.app.insurance.service.PolicyService;

import javassist.NotFoundException;

/**
 * @author jpaste
 *
 */

@CrossOrigin
@RestController
@RequestMapping("/policy")
public class PolicyController {
	
	@Autowired
	PolicyService policyService;
	
	Logger logger = LoggerFactory.getLogger(PolicyController.class);
	
	
	/**
	 * This method return {@link List} of {@link Policy}
	 * @return {@link List} of {@link Policy}
	 * @throws NotFoundException 
	 */
	@GetMapping("/getAllPolicies")
	public List<Policy> getAllPolicies(HttpServletRequest request) throws NotFoundException{
		String token = request.getHeader("token");
		logger.info("List of All Policies Retreived Sucessfully");
		return policyService.getAllPolicies(token);
	}
	
	/**
	 *  This method return {@link Policy} that is added 
	 * @param policy
	 * @return {@link Policy}
	 */
	@PostMapping("/addPolicy")
	public Policy addPolicy(@RequestBody Policy policy,  HttpServletRequest request) {
		String token = request.getHeader("token");
		logger.info("Policy Added Successfully");
		return policyService.addPolicy(policy, token);
	}
	
	/**
	 * This method return {@link Policy} that is updated 
	 * @param policy
	 * @return {@link Policy}
	 */
	@PutMapping("/updatePolicy")
	public Policy updatePolicy(@RequestBody Policy policy) {
		logger.info("Policy Updated Successfully");
		return policyService.updatePolicy(policy);
	}

	/**
	 * This method return {@link Policy} that is deleted based on the passed policyId
	 * @param policyId
	 * @return {@link Policy}
	 */
	@PutMapping("/deletePolicy/{id}")
	public Policy deletePolicy(@PathVariable("id") int policyId) {
		logger.info("Policy Deleted Successfully");
		return policyService.deletePolicy(policyId);
	}
	
	/**
     * This method return {@link Optional} of {@link Policy} based on the passed policyId
	 * @param policyId
     * @return {@link Optional} of {@link Policy}
	 */
	@GetMapping("/viewPolicy/{id}")
	public Optional<Policy> viewPolicy(@PathVariable("id") int policyId) {
		logger.info("Policy With the given policy id is retrieved");
		return policyService.viewPolicy(policyId);
	}

	
}
