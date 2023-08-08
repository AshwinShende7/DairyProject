package com.dairy.repository.directMilkPurchase;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dairy.model.directMilkPurchase.PurchesInvoiceMaster;


public interface PurchesInvoiceRepo extends JpaRepository<PurchesInvoiceMaster,Integer>{
@Transactional
int deleteByid(int id);

List<PurchesInvoiceMaster> findByDateBetween(String getfDate, String gettDate);

List<PurchesInvoiceMaster> findByid(int id);
}