package com.dairy.model.milkCollection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MilkCollectionManualKG {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String collectionDate;
	private String dock;
	private String milkType;
	private String collectionType;
	private String sansthaName;
	private String routeName;
	private String rateType;
	private String address;
	private String sampleNo;
	private String sansthaCode;
	private String canQty;
	private String weightKg;
	private String weightLtr;
	private String sourcanQty;
	private String sourWeight;
	private String mobileNo;
	@Transient
	private String fdate;
	@Transient
	private String tdate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCollectionDate() {
		return collectionDate;
	}
	public void setCollectionDate(String collectionDate) {
		this.collectionDate = collectionDate;
	}
	public String getDock() {
		return dock;
	}
	public void setDock(String dock) {
		this.dock = dock;
	}
	public String getMilkType() {
		return milkType;
	}
	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getSansthaName() {
		return sansthaName;
	}
	public void setSansthaName(String sansthaName) {
		this.sansthaName = sansthaName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getRateType() {
		return rateType;
	}
	public void setRateType(String rateType) {
		this.rateType = rateType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSampleNo() {
		return sampleNo;
	}
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
	public String getSansthaCode() {
		return sansthaCode;
	}
	public void setSansthaCode(String sansthaCode) {
		this.sansthaCode = sansthaCode;
	}
	public String getCanQty() {
		return canQty;
	}
	public void setCanQty(String canQty) {
		this.canQty = canQty;
	}
	public String getWeightKg() {
		return weightKg;
	}
	public void setWeightKg(String weightKg) {
		this.weightKg = weightKg;
	}
	public String getWeightLtr() {
		return weightLtr;
	}
	public void setWeightLtr(String weightLtr) {
		this.weightLtr = weightLtr;
	}
	public String getSourcanQty() {
		return sourcanQty;
	}
	public void setSourcanQty(String sourcanQty) {
		this.sourcanQty = sourcanQty;
	}
	public String getSourWeight() {
		return sourWeight;
	}
	public void setSourWeight(String sourWeight) {
		this.sourWeight = sourWeight;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getFdate() {
		return fdate;
	}
	public void setFdate(String fdate) {
		this.fdate = fdate;
	}
	public String getTdate() {
		return tdate;
	}
	public void setTdate(String tdate) {
		this.tdate = tdate;
	}
	
	
}
