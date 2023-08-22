package com.dairy.model.machineEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ManualJobWorkCard 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String machineId;
	private String machineName;
	private String machineCode;
	private String make;
	private String department;
	private String perHourCapacity;
	private String jobCardId;
	private String raiseBy;
	private String warehouse;
	private String machineIssue;
	private String machineStatus;
	private String dateAndTime;
	private String remark;
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
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPerHourCapacity() {
		return perHourCapacity;
	}
	public void setPerHourCapacity(String perHourCapacity) {
		this.perHourCapacity = perHourCapacity;
	}
	public String getJobCardId() {
		return jobCardId;
	}
	public void setJobCardId(String jobCardId) {
		this.jobCardId = jobCardId;
	}
	public String getRaiseBy() {
		return raiseBy;
	}
	public void setRaiseBy(String raiseBy) {
		this.raiseBy = raiseBy;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getMachineIssue() {
		return machineIssue;
	}
	public void setMachineIssue(String machineIssue) {
		this.machineIssue = machineIssue;
	}
	public String getMachineStatus() {
		return machineStatus;
	}
	public void setMachineStatus(String machineStatus) {
		this.machineStatus = machineStatus;
	}
	public String getDateAndTime() {
		return dateAndTime;
	}
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
