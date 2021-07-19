package com.app.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author jpaste This class includes declaration of parameters of policy class,
 *         default constructor, parameterized constructors, getter and setter
 *         methods of parameters and toString method to display.
 */
@Entity
@Table(name = "policies")
public class Policy {

	@Id
	@GeneratedValue
	@Column(name = "policy_id")
	private int policyId;
	@Column(name = "policy_name")
	private String policyName;
	@Column(name = "duration")
	private int duration;
	@Column(name = "amount")
	private float premiumAmount;
	@Column(name = "policy_type")
	private String policyType;
	@Column(name = "policy_desc")
	private String description;
	@Column(name = "status")
	private boolean deleted;

	public Policy() {

	}

	public Policy(int policyId, String policyName, int duration, float premiumAmount, String policyType,
			String description) {
		super();
		this.policyId = policyId;
		this.policyName = policyName;
		this.duration = duration;
		this.premiumAmount = premiumAmount;
		this.policyType = policyType;
		this.description = description;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public float getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(float premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
