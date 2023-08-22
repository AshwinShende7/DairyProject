package com.dairy.model.machineEntry;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MachineMaster 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String machineId;
	private String vendorName;
	private String machineType;
	private String capacity;
	private String perHourCapacity;
	private String date;
	private String machineCode;
	private String mobileNo;
	private String location;
	private String dateOfPurchess;
	private String workingCondition;
	private String status;
	private String make;
	private String address;
	private String type;
	private String department;
	private String yearOfManufacturing;
	private String othertype;
	private String machineCost;
	private String machineName;
	private String CreatedBy;
	public String getCreatedBy() {
		return CreatedBy;
	}
	public void setCreatedBy(String createdBy) {
		CreatedBy = createdBy;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
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
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getMachineType() {
		return machineType;
	}
	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	public String getPerHourCapacity() {
		return perHourCapacity;
	}
	public void setPerHourCapacity(String perHourCapacity) {
		this.perHourCapacity = perHourCapacity;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMachineCode() {
		return machineCode;
	}
	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDateOfPurchess() {
		return dateOfPurchess;
	}
	public void setDateOfPurchess(String dateOfPurchess) {
		this.dateOfPurchess = dateOfPurchess;
	}
	public String getWorkingCondition() {
		return workingCondition;
	}
	public void setWorkingCondition(String workingCondition) {
		this.workingCondition = workingCondition;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getYearOfManufacturing() {
		return yearOfManufacturing;
	}
	public void setYearOfManufacturing(String yearOfManufacturing) {
		this.yearOfManufacturing = yearOfManufacturing;
	}
	public String getOthertype() {
		return othertype;
	}
	public void setOthertype(String othertype) {
		this.othertype = othertype;
	}
	public String getMachineCost() {
		return machineCost;
	}
	public void setMachineCost(String machineCost) {
		this.machineCost = machineCost;
	}
	
	
	
	
	
	
	

}
