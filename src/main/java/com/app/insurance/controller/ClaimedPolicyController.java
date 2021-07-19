package com.app.insurance.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.insurance.model.ClaimedPolicy;
import com.app.insurance.model.ResponseTemplate;
import com.app.insurance.model.User;
import com.app.insurance.service.ClaimedPolicyService;
import javassist.NotFoundException;

@CrossOrigin
@RestController
@RequestMapping("/claimedPolicies")
public class ClaimedPolicyController {

	@Autowired
	ClaimedPolicyService claimedPolicyService;
	
	Logger logger = LoggerFactory.getLogger(ClaimedPolicyController.class);

	/**
	 * This method return {@link List} of {@link ClaimedPolicy} with respective
	 * policies
	 * 
	 * @return {@link List} of {@link ClaimedPolicy}
	 */
	@GetMapping("/getAllClaimedPolicies")
	public List<ClaimedPolicy> getAllClaimedPolicies() {
		logger.info("List of All Claimed Policies Retreived Sucessfully");
		return claimedPolicyService.getAllClaimedPolicies();
	}

	/**
	 * This method return {@link ClaimedPolicy} that is added
	 * 
	 * @param claimedPolicy
	 * @return {@link ClaimedPolicy}
	 * @throws NotFoundException
	 */
	@PostMapping("/addClaimedPolicy")
	public ClaimedPolicy addClaimedPolicy(@RequestBody ClaimedPolicy claimedPolicy) throws NotFoundException {
		logger.info("Claimed Policy Added Successfully");
		return claimedPolicyService.addClaimedPolicy(claimedPolicy);
	}

	/**
	 * This method return {@link ClaimedPolicy} that is updated
	 * 
	 * @param claimedPolicy
	 * @return {@link ClaimedPolicy}
	 */
	@PutMapping("/updateClaimedPolicy")
	public ClaimedPolicy updateClaimedPolicy(@RequestBody ClaimedPolicy claimedPolicy) {
		logger.info("Claimed Policy Updated Successfully");
		return claimedPolicyService.updateClaimedPolicy(claimedPolicy);
	}

	/**
	 * This method deletes a ClaimedPolicy based on the id passed
	 * 
	 * @param planId
	 */
	@DeleteMapping("/deleteClaimedPolicy/{id}")
	public void deleteClaimedPolicy(@PathVariable("id") int planId) {
		logger.info("Claimed Policy Deleted Successfully");
		claimedPolicyService.deleteClaimedPolicy(planId);
	}

	/**
	 * This method return {@link Optional} of {@link ClaimedPolicy} based on the id
	 * 
	 * @param planId
	 * @return {@link Optional} of {@link ClaimedPolicy}
	 */
	@GetMapping("/viewClaimedPolicy/{id}")
	public Optional<ClaimedPolicy> viewClaimedPolicy(@PathVariable("id") int planId) {
		logger.info("Plan With the given plan id is retrieved");
		return claimedPolicyService.viewClaimedPolicy(planId);
	}

	/**
	 * This method return {@link List} polices holed by particular user based on the
	 * accepted userId
	 * 
	 * @param userId
	 * @return {@link List} of {@link ResponseTemplate}
	 * @throws NotFoundException 
	 */
	@GetMapping("/getClaimPolicyByUser/{id}")
	public List<ResponseTemplate> getClaimPolicyByUser(@PathVariable("id") int userId) throws NotFoundException {
		logger.info("List of User with their claimed plans is retreived");
		return claimedPolicyService.getClaimPolicyByUser(userId);
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
	@PostMapping("/claimAPolicy/{policyId}/{userId}")
	public ClaimedPolicy claimAPolicy(@PathVariable("policyId") int policyId, @PathVariable("userId") int userId,
			@RequestBody ClaimedPolicy claimedPolicy) {
		logger.info("Claimed Ploicy with the given user id and policy id is retreived");
		return claimedPolicyService.claimAPolicy(policyId, userId, claimedPolicy);
	}

	/**
	 * This method return {@link List} of ClaimedPolicies with respective policies
	 * 
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@GetMapping("/listOfAllClaimedPolicies")
	public List<ResponseTemplate> listOfAllClaimedPolicies() {
		logger.info("List of User with their claimed plans along with the policy details is retreived ");
		return claimedPolicyService.listOfAllClaimedPolicies();
	}

	/**
	 * This method accepts userId and planId and return {@link List} of users and
	 * their plans
	 * 
	 * @param userId
	 * @param planId
	 * @return {@link List} of {@link ResponseTemplate}
	 */
	@GetMapping("/findByPlanIdAndUserId/{userId}/{planId}")
	public List<ResponseTemplate> findByPlanIdAndUserId(@PathVariable("userId") int userId,
			@PathVariable("planId") int planId) {
		logger.info("List of User with their user id and plan id is retreived");
		return claimedPolicyService.findByPlanIdAndUserId(userId, planId);
	}

	/**
	 * This method return {@link List} of {@link User} based on the PolicyId
	 * 
	 * @param policyId
	 * @return {@link List} of {@link User}
	 * @throws NotFoundException
	 */
	@GetMapping("/getPolicyHoldersByPolicy/{policyId}")
	public List<User> getPolicyHoldersByPolicy(@PathVariable("policyId") int policyId) throws NotFoundException {
		logger.info("List of Users who have taken that speific policy is retreived");
		return claimedPolicyService.getPolicyHoldersByPolicy(policyId);
	}

}
