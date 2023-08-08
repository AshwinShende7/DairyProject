package com.dairy.model.machineEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MachineScheduler 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private String machineId;
	private String machineName;
	private String machineCode;
	private String make;
	private String department;
	private String perHourCapacity;
	private String scheduleDetails;
	private String startDate;
	private String endDate;
	private String priority;
	private String repeat1;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public String getScheduleDetails() {
		return scheduleDetails;
	}
	public void setScheduleDetails(String scheduleDetails) {
		this.scheduleDetails = scheduleDetails;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getRepeat1() {
		return repeat1;
	}
	public void setRepeat1(String repeat1) {
		this.repeat1 = repeat1;
	}
	
	
	

}
