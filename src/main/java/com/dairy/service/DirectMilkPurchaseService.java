package com.dairy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.model.directMilkPurchase.PurchesInvoiceMaster;
import com.dairy.repository.directMilkPurchase.PurchesInvoiceRepo;

@Service
public class DirectMilkPurchaseService {
	
	@Autowired
	private PurchesInvoiceRepo purchesInvoiceRepo;

	public PurchesInvoiceMaster savePurchesInvoice(PurchesInvoiceMaster purchesInvoiceMaster) {
		return purchesInvoiceRepo.save(purchesInvoiceMaster);
	}

	public List<PurchesInvoiceMaster> findAllPurchesInvoice() {
		return purchesInvoiceRepo.findAll();
	}

	public List<PurchesInvoiceMaster> findByDateBetween(String getfDate, String gettDate) {
		return purchesInvoiceRepo.findByDateBetween(getfDate,gettDate);
	}
	
	public PurchesInvoiceMaster findPurchesInvoiceById(int id) {
		 return purchesInvoiceRepo.findById(id);
	}

}
