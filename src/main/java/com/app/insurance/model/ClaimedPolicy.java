package com.app.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jpaste This class includes declaration of parameters of claimed
 *         policy class, default constructor, parameterized constructors, getter
 *         and setter methods of parameters and toString method to display.
 */

@Entity
@Table(name = "claimed_policies")
public class ClaimedPolicy {

	@Id
	@GeneratedValue
	@Column(name = "plan_id")
	private int planId;
	@Column(name = "user_id")
	private int userId;
	@Column(name = "policy_id")
	private int policyId;
	@Column(name = "claimed_date")
	private String claimedDate;
	@Column(name = "status")
	private boolean status;

	public ClaimedPolicy() {

	}

	public ClaimedPolicy(int planId, int userId, int policyId, String claimedDate, boolean status) {
		super();
		this.planId = planId;
		this.userId = userId;
		this.policyId = policyId;
		this.claimedDate = claimedDate;
		this.status = status;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getClaimedDate() {
		return claimedDate;
	}

	public void setClaimedDate(String claimedDate) {
		this.claimedDate = claimedDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
