package com.dairy.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dairy.dto.Response;
import com.dairy.model.sansthaMaster.PerRateContractMaster;
import com.dairy.model.sansthaMaster.PurRateExcleImport;
import com.dairy.model.sansthaMaster.SansthaMaster;
import com.dairy.repository.sansthaMaster.PerRateContractMasterRepo;
import com.dairy.repository.sansthaMaster.PurRateExcelImportRepo;
import com.dairy.repository.sansthaMaster.SansthaMasterRepo;
import com.dairy.service.SansthaMasterService;






@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SansthaMasterController {

	@Autowired
//	private SansthaMasterService sansthaMasterservice;
	private SansthaMasterService sansthaMasterService ;

	@Autowired
	private SansthaMasterRepo sansthaMasterrepo;

	@Autowired
	private PerRateContractMasterRepo contractMasterRepo;
	
	@Autowired
	private PurRateExcelImportRepo purRateExcelImportRepo;

	
	// Pur Rate Excel Import
	// Save Pur Rate Excel Import
	    @PostMapping("/saveExcelImport")
		@ResponseBody
		public ResponseEntity<String> saveExcelImport(
				@RequestParam(name = "milkType", required = false) String milkType,
				@RequestParam(name = "rateChartName", required = false) String rateChartName,
				@RequestParam(name = "standerdFat", required = false) String standerdFat,
				@RequestParam(name = "standerdSnf", required = false) String standerdSnf,
				@RequestParam(name = "rate", required = false) String rate,
				@RequestParam(name = "file", required = false) MultipartFile file) {
	    	    
	    	try {
	    		byte[] fileb = file.getBytes();
	    		if (!file.isEmpty()) {
					
	    			
	    			
	    			PurRateExcleImport import1=new PurRateExcleImport();
	    			import1.setMilkType(milkType);
	    			import1.setRateChartName(rateChartName);
	    			import1.setStanderdFat(standerdFat);
	    			import1.setStanderdSnf(standerdSnf);
	    			import1.setRate(rate);
	    			import1.setFilename(fileb);
	    			
					purRateExcelImportRepo.save(import1);
				} 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return ResponseEntity.ok("Data Updated Successfully..!!");
		}

	// Retrive All data
	@GetMapping("/getallPurRateData")
	@ResponseBody
	public List<PurRateExcleImport> getallpurRatedata() {
		return sansthaMasterService.getallpurRatedata();
	}

	//delete Pur Rate Data By Id
	@PostMapping("/deletePurRateDataById")
	@ResponseBody
	public ResponseEntity<String>deletePurRateDataById(@RequestBody PurRateExcleImport purReateExcleImport)
	{
		int i=purRateExcelImportRepo.deleteByid(purReateExcleImport.getId());
		
		if(i > 0)
		{
			return ResponseEntity.ok("Data Deleted Sucessfully");
		}
		else
		{
			return ResponseEntity.badRequest().body("Data Not Deleted");
		}
		
	}

	
	//Save Sanstha master
	@PostMapping("/saveSanstha")
	@ResponseBody
	public Response<T> savesansthaMaster(@RequestBody SansthaMaster sansthamaster) {
		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		SansthaMaster sm2 = sansthaMasterService.saveSansthaMasterNew(sansthamaster);
		if (sm2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(sm2);
		}
		return response;
	}

	// Retrieve all data sanstha master
	@GetMapping("/findallmasterdata")
	@ResponseBody
	public List<SansthaMaster> findallData() {
		return sansthaMasterService.findAllData();
	}

	// Delete Data

	@PostMapping("/deletedatabyid")
	@ResponseBody
	public ResponseEntity<String> deleteareamasterDataById(@RequestBody SansthaMaster sansthaMaster) {
		int i = sansthaMasterrepo.deleteByid(sansthaMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Delete Data Successfully");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");

		}

	}

	@GetMapping("/downloadexcel")
	@ResponseBody
	public void generateExcelReport1(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachement;filename=sansthamaster.xls";
		res.setHeader(headerkey, headervalue);
		sansthaMasterService.generateExcel1(res);

	}

	// Per Rate Contract Master
	// save Per Rate Contract Master
	@PostMapping("/savePerRateContraactMaster")
	@ResponseBody
	public Response savePerRateContractMaster(@RequestBody PerRateContractMaster perratecontractmaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		PerRateContractMaster perRateContactMaster = sansthaMasterService
				.saveperratecontractMaster(perratecontractmaster);
		if (perRateContactMaster != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(perRateContactMaster);
		}
		return response;
	}

	// Delete Per Rate Contract Master Data
	@PostMapping("/deletePerrateContractMasterdata")
	@ResponseBody
	public ResponseEntity<String> deleteratecontractmasterDataById(
			@RequestBody PerRateContractMaster perrateContractMaster) {
		int i = contractMasterRepo.deleteByid(perrateContractMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Delete Data Successfully");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");

		}

	}

	// Retrieve Per Rate Contract Master Data
	@GetMapping("/findallPerRatecontractMasterData")
	@ResponseBody
	public List<PerRateContractMaster> findPerRateContractMasterData() {
		return sansthaMasterService.findAlldata();

	}

	// Download Excel File
	@GetMapping("/excel")
	@ResponseBody
	public void generateExcelReport(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachement;filename=perratecontractmaster.xls";
		res.setHeader(headerkey, headervalue);
		sansthaMasterService.generateExcel(res);

	}

}
