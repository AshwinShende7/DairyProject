package com.dairy.model.machineEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MachineJobCard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String machineId;
	private String machineName;
	private String MachineCode;
	private String perHourCapacity;
	private String vendorName;
	private String mobileNo;
	private String workingCondition;
	private String dateOfPurchase;
	private String machineUse;
	private String jobType;
	private String jobDetails;
	private String date;
	private String jobDoneBy;
	private String warehouse;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getMachineCode() {
		return MachineCode;
	}
	public void setMachineCode(String machineCode) {
		MachineCode = machineCode;
	}
	public String getPerHourCapacity() {
		return perHourCapacity;
	}
	public void setPerHourCapacity(String perHourCapacity) {
		this.perHourCapacity = perHourCapacity;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getWorkingCondition() {
		return workingCondition;
	}
	public void setWorkingCondition(String workingCondition) {
		this.workingCondition = workingCondition;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String getMachineUse() {
		return machineUse;
	}
	public void setMachineUse(String machineUse) {
		this.machineUse = machineUse;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobDetails() {
		return jobDetails;
	}
	public void setJobDetails(String jobDetails) {
		this.jobDetails = jobDetails;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getJobDoneBy() {
		return jobDoneBy;
	}
	public void setJobDoneBy(String jobDoneBy) {
		this.jobDoneBy = jobDoneBy;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	
	
	
	
	
	
	

}
