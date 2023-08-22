package com.dairy.model.directMilkPurchase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class PurchesInvoiceMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String inwordId;
	private String date;
	private String collectionType;
	private String vendorName;
	private String address;
	private String mobileNo;
	private String milkType;
	private String itemName;
	private String unit;
	private String batch;
	private String mbrt;
	private String fat;
	private String clr;
	private String snf;
	private String warehouse;
	private String temp;
	private String rate;
	private String transportRate;
	private String overCharge;
	private String acidity;
	private String weight;
	private String inKg;
	private String total;
	private String remark;
	private String supplierWeight;
	private String vendorInvoiceDate;
	private String sFat;
	private String sSnf;
	private String sAcidity;
	private String sTemp;
	private String vehicalName;
	private String vehicalNo;
	private String driverName;
	private String driverNo;
	private String dremark;
	@Transient
	private String fDate;
	@Transient
	private String tDate;
	public String getClr() {
		return clr;
	}
	public void setClr(String clr) {
		this.clr = clr;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInwordId() {
		return inwordId;
	}
	public void setInwordId(String inwordId) {
		this.inwordId = inwordId;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getMilkType() {
		return milkType;
	}
	public void setMilkType(String milkType) {
		this.milkType = milkType;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBatch() {
		return batch;
	}
	public void setBatch(String batch) {
		this.batch = batch;
	}
	public String getMbrt() {
		return mbrt;
	}
	public void setMbrt(String mbrt) {
		this.mbrt = mbrt;
	}
	public String getFat() {
		return fat;
	}
	public void setFat(String fat) {
		this.fat = fat;
	}
	public String getSnf() {
		return snf;
	}
	public void setSnf(String snf) {
		this.snf = snf;
	}
	public String getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}
	public String getTemp() {
		return temp;
	}
	public void setTemp(String temp) {
		this.temp = temp;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTransportRate() {
		return transportRate;
	}
	public void setTransportRate(String transportRate) {
		this.transportRate = transportRate;
	}
	public String getOverCharge() {
		return overCharge;
	}
	public void setOverCharge(String overCharge) {
		this.overCharge = overCharge;
	}
	public String getAcidity() {
		return acidity;
	}
	public void setAcidity(String acidity) {
		this.acidity = acidity;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getInKg() {
		return inKg;
	}
	public void setInKg(String inKg) {
		this.inKg = inKg;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSupplierWeight() {
		return supplierWeight;
	}
	public void setSupplierWeight(String supplierWeight) {
		this.supplierWeight = supplierWeight;
	}
	public String getVendorInvoiceDate() {
		return vendorInvoiceDate;
	}
	public void setVendorInvoiceDate(String vendorInvoiceDate) {
		this.vendorInvoiceDate = vendorInvoiceDate;
	}
	public String getsFat() {
		return sFat;
	}
	public void setsFat(String sFat) {
		this.sFat = sFat;
	}
	public String getsSnf() {
		return sSnf;
	}
	public void setsSnf(String sSnf) {
		this.sSnf = sSnf;
	}
	public String getsAcidity() {
		return sAcidity;
	}
	public void setsAcidity(String sAcidity) {
		this.sAcidity = sAcidity;
	}
	public String getsTemp() {
		return sTemp;
	}
	public void setsTemp(String sTemp) {
		this.sTemp = sTemp;
	}
	public String getVehicalName() {
		return vehicalName;
	}
	public void setVehicalName(String vehicalName) {
		this.vehicalName = vehicalName;
	}
	public String getVehicalNo() {
		return vehicalNo;
	}
	public void setVehicalNo(String vehicalNo) {
		this.vehicalNo = vehicalNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverNo() {
		return driverNo;
	}
	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}
	public String getDremark() {
		return dremark;
	}
	public void setDremark(String dremark) {
		this.dremark = dremark;
	}
	public String getfDate() {
		return fDate;
	}
	public void setfDate(String fDate) {
		this.fDate = fDate;
	}
	public String gettDate() {
		return tDate;
	}
	public void settDate(String tDate) {
		this.tDate = tDate;
	}
	
}
