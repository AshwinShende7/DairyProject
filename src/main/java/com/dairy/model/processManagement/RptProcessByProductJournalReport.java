package com.dairy.model.processManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class RptProcessByProductJournalReport {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)

private int id;
private String date;
@Transient
private String fDate;
@Transient
private String tDate;
private String typeofProcess;
private String warehouse;
private String outItemName;
private String produceItemName;
private String byProductitem;
private String employeeName;
private String accountName;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
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
public String getTypeofProcess() {
	return typeofProcess;
}
public void setTypeofProcess(String typeofProcess) {
	this.typeofProcess = typeofProcess;
}
public String getWarehouse() {
	return warehouse;
}
public void setWarehouse(String warehouse) {
	this.warehouse = warehouse;
}
public String getOutItemName() {
	return outItemName;
}
public void setOutItemName(String outItemName) {
	this.outItemName = outItemName;
}
public String getProduceItemName() {
	return produceItemName;
}
public void setProduceItemName(String produceItemName) {
	this.produceItemName = produceItemName;
}
public String getByProductitem() {
	return byProductitem;
}
public void setByProductitem(String byProductitem) {
	this.byProductitem = byProductitem;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getAccountName() {
	return accountName;
}
public void setAccountName(String accountName) {
	this.accountName = accountName;
}


}
