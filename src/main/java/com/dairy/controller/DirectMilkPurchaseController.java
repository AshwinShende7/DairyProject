package com.dairy.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.model.directMilkPurchase.PurchesInvoiceMaster;
import com.dairy.repository.directMilkPurchase.PurchesInvoiceRepo;
import com.dairy.service.DirectMilkPurchaseService;


@Controller

public class DirectMilkPurchaseController {
	
	@Autowired
	private DirectMilkPurchaseService directMilkPurchaseService ;
	
	
	@Autowired
	private  PurchesInvoiceRepo purchesInvoiceRepo;
	
	//save
	@PostMapping("/savePurchesInvoice")
	public Response savePurchesInvoice(@RequestBody PurchesInvoiceMaster purchesInvoiceMaster)
	{
		Response response = new Response();
		response.setStatus("Not Success");
    	response.setMessage("Data Not Saved..!!");
    	
    	String clr = purchesInvoiceMaster.getClr();
		String fat1 = purchesInvoiceMaster.getFat();

		double clr1 = Double.parseDouble(clr);
		double fat2 = Double.parseDouble(fat1);

		double result = clr1 / 4 + 0.2 * fat2 + 0.66;

		purchesInvoiceMaster.setSnf(String.valueOf(result));
    	
    	PurchesInvoiceMaster purchesInvoiceMaster2=directMilkPurchaseService.savePurchesInvoice(purchesInvoiceMaster);
    	if(purchesInvoiceMaster2 != null)
    	{
    		response.setStatus("Success");
		    response.setMessage("Data Saved Successfully..!!");
		    response.setData(purchesInvoiceMaster2);
    	}
    	return response;
	}
	
	//find all
	@GetMapping("/findAllPurchesInvoice")
	public List<PurchesInvoiceMaster> findAllPurchesInvoice(){
		return directMilkPurchaseService.findAllPurchesInvoice();
	}
	
	//deleteById
	@PostMapping("/deletePurchesInvoiceById")
	public ResponseEntity<String>deletePurchesInvoiceById(@RequestBody PurchesInvoiceMaster purchesInvoiceMaster){
	int i=purchesInvoiceRepo.deleteByid(purchesInvoiceMaster.getId());
	if(i>0)
	{
		return ResponseEntity.ok("Data Deleted Successfully..!!");
	}
	else
	{
		return ResponseEntity.badRequest().body("Data Not Deleted...!");
	}
	}
	
	//findPurchesInvoiceByDateBetween
	@GetMapping("/findPurchesInvoiceByDateBetween")
	public Response findPurchesInvoiceByDateBetween(@RequestBody PurchesInvoiceMaster purchesInvoiceMaster) {
		Response response=new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");
		
		List<PurchesInvoiceMaster> purchesInvoiceMaster2=directMilkPurchaseService.findByDateBetween(purchesInvoiceMaster.getfDate(),purchesInvoiceMaster.gettDate());
		if(!purchesInvoiceMaster2.isEmpty())
		{
			 response.setStatus("Success"); 
			 response.setMessage("Data Found..!!");
			 response.setData(purchesInvoiceMaster2);
		}
		return response;
	}
	
	@GetMapping("/fetchbyID")
	@ResponseBody
	public List<PurchesInvoiceMaster> showfindbyid(@RequestBody PurchesInvoiceMaster purchesInvoiceMaster){
		return purchesInvoiceRepo.findByid(purchesInvoiceMaster.getId());
		
	}
	
	
	@PostMapping("/updateBySelectId")
	@ResponseBody
	public ResponseEntity<String> updateBySelectId(
			@RequestParam(name = "inwordId", required = false) String inwordId,
			@RequestParam(name = "date", required = false) String date,
			@RequestParam(name = "collectionType", required = false) String collectionType, 
			@RequestParam(name = "vendorName", required = false) String vendorName,
			@RequestParam(name = "address", required = false) String address, 
			@RequestParam(name = "mobileNo", required = false) String mobileNo,
			@RequestParam(name = "milkType", required = false) String milkType, 
			@RequestParam(name = "itemName", required = false) String itemName,
			@RequestParam(name = "unit", required = false) String unit, 
			@RequestParam(name = "batch", required = false) String batch,
			@RequestParam(name = "mbrt", required = false) String mbrt, 
			@RequestParam(name = "fat", required = false) String fat,
			@RequestParam(name = "clr", required = false) String clr, 
			@RequestParam(name = "snf", required = false) String snf,
			@RequestParam(name = "warehouse", required = false) String warehouse,
			@RequestParam(name = "temp", required = false) String temp,
			@RequestParam(name = "rate", required = false) String rate,
			@RequestParam(name = "transportRate", required = false) String transportRate,
			@RequestParam(name = "overCharge", required = false) String overCharge, 
			@RequestParam(name = "acidity", required = false) String acidity,
			@RequestParam(name = "weight", required = false) String weight, 
			@RequestParam(name = "inKg", required = false) String inKg,
			@RequestParam(name = "total", required = false) String total, 
			@RequestParam(name = "remark", required = false) String remark,
			@RequestParam(name = "supplierWeight", required = false) String supplierWeight, 
			@RequestParam(name = "vendorInvoiceDate", required = false) String vendorInvoiceDate,
			@RequestParam(name = "sFat", required = false) String sFat, 
			@RequestParam(name = "sSnf", required = false) String sSnf,
			@RequestParam(name = "sAcidity", required = false) String sAcidity, 
			@RequestParam(name = "sTemp", required = false) String sTemp,
			@RequestParam(name = "vehicalNo", required = false) String vehicalNo,
			@RequestParam(name = "driverName", required = false) String driverName,
			@RequestParam(name = "driverNo", required = false) String driverNo,
			@RequestParam(name = "dremark", required = false) String dremark,
			@RequestParam(name = "id",required = false) int id
			) {
		try {
			List<PurchesInvoiceMaster> add = purchesInvoiceRepo.findByid(id);
			add.forEach(s->{
				if(!(date==null) && !(collectionType==null)) {
					try {
						
						s.setDate(date);
						s.setCollectionType(collectionType);
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
				s.setInwordId(inwordId);
				s.setVendorName(vendorName);
				s.setAddress(address);
				s.setMobileNo(mobileNo);
				s.setMilkType(milkType);
				s.setItemName(itemName);
				s.setUnit(unit);
				s.setBatch(batch);
				s.setMbrt(mbrt);
				s.setFat(fat);
				s.setClr(clr);
				s.setSnf(snf);
				s.setWarehouse(warehouse);
				s.setTemp(temp);
				s.setRate(rate);
				s.setTransportRate(transportRate);
				s.setOverCharge(overCharge);
				s.setAcidity(acidity);
				s.setWeight(weight);
				s.setInKg(inKg);
				s.setTotal(total);
				s.setRemark(remark);
				s.setSupplierWeight(supplierWeight);
				s.setVendorInvoiceDate(vendorInvoiceDate);
				s.setsFat(sFat);
				s.setsSnf(sSnf);
				s.setsAcidity(sAcidity);
				s.setsTemp(sTemp);
				s.setVendorName(vendorName);
				s.setVehicalNo(vehicalNo);
				s.setDriverName(driverName);
				s.setDriverNo(driverNo);
				s.setDremark(dremark);
				
				purchesInvoiceRepo.save(s);
			});
			return new ResponseEntity<>("Data Updated  successfully!!!!", HttpStatus.OK);
		}catch (Exception ex) {
			System.out.println(ex);
			return new ResponseEntity<>("Data Updated Failed !!!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
}
