package com.dairy.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.model.processManagement.ByProductProcessJournal;
import com.dairy.model.processManagement.CreamJournal;
import com.dairy.model.processManagement.ProcessJournalMilk;
import com.dairy.model.processManagement.RPTProcessMilkJournalReportMaster;
import com.dairy.model.processManagement.RptProcessByProductJournalReport;
import com.dairy.model.processManagement.RptProcessCreamReport;
import com.dairy.repository.processManagement.ByProductProcessJournalRepo;
import com.dairy.repository.processManagement.CreamJournalRepo;
import com.dairy.repository.processManagement.ProcessJournalMilkRepo;
import com.dairy.repository.processManagement.RPTProcessMilkJournalReportRepo;
import com.dairy.repository.processManagement.RptProcessByProductJournalReportRepo;
import com.dairy.repository.processManagement.RptProcessCreamReportRepo;
import com.dairy.service.ProcessManagementService;
import com.dairy.service.ProcessReportManagementService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class ProcessManagementController {

	@Autowired
	private ProcessManagementService processManagementService;

	@Autowired
	private ProcessReportManagementService processReportManagementService;

	@Autowired
	private CreamJournalRepo creamJournalRepo;

	@Autowired
	private ProcessJournalMilkRepo processJournalMilkRepo;

	@Autowired
	private ByProductProcessJournalRepo byProductProcessJournalRepo;

	@Autowired
	private RPTProcessMilkJournalReportRepo rPTProcessMilkJournalReportRepo;
	
	@Autowired
	private RptProcessCreamReportRepo rptProcessCreamReportRepo ;
	
	@Autowired
	private RptProcessByProductJournalReportRepo rptProcessByProductJournalReportRepo ;


	// Cream Journal
	// Save Cream Journal Data
	@PostMapping("/saveCreamJournal")
	@ResponseBody
	public Response saveCreamJournal(@RequestBody CreamJournal creamJournal) {
		Response response = new Response();
		response.setStatus("Not Sucess..");
		response.setMessage("Data Not Sucessfully..!");

		CreamJournal creamJournal2 = processManagementService.saveCreamJournal(creamJournal);
		if (creamJournal != null)

			response.setStatus("Sucess..!");
		response.setMessage("Data Save Sucessfully...!");
		response.setData(creamJournal2);

		return response;
	}

	// Retrive All Data
	@GetMapping("/getAllCreamJournalData")
	@ResponseBody
	public List<CreamJournal> getAllCreamJournalData() {
		return processManagementService.getAllCreamJournalData();
	}

	// DeleteCreamDataById
	@PostMapping("/deleteCreamJournalById")
	@ResponseBody
	public ResponseEntity<String> deleteCreamJournalById(@RequestBody CreamJournal creamJournal) {
		int i = creamJournalRepo.deleteCreamJournalById(creamJournal.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..");
		} else {
			return ResponseEntity.badRequest().body("Fail To deleted The Data");
		}
	}

	// Get CreamJournal By Date
	@GetMapping("/getcreamJournalByDate")
	@ResponseBody
	public Response getCreamJournalTodateFromdate(@RequestBody CreamJournal creamjournal) {
		Response response = new Response();
		response.setStatus("Not Sucess..");
		response.setMessage("Data Not Found");

		List<CreamJournal> creamJournal1 = processManagementService.findBydateBetween3(creamjournal.getFromDate(),
				creamjournal.getToDate());

		if (!creamJournal1.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found Sucessfully..!");
			response.setData(creamJournal1);
		}
		return response;
	}

	// Milk Journal Process
	// Save Process journal Milk
	@PostMapping("/saveProcessjournalMilk")
	@ResponseBody
	public Response saveProcessjournalMilk(@RequestBody ProcessJournalMilk processJournalMilk) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		ProcessJournalMilk processmilk = processManagementService.saveProcessjournalMilk(processJournalMilk);
		if (processmilk != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(processmilk);
		}
		return response;
	}

	// Retrieve All Process Journal Milk Data
	@GetMapping("/getAllProcessJournalMilkData")
	@ResponseBody
	public List<ProcessJournalMilk> getAllProcessJournalMilkData() {
		return processManagementService.getProcessJournalMilkDetails();
	}

	// Retrieve Process Journal Milk Data By Date
	@GetMapping("/getProcessJournalMilkByDate")
	@ResponseBody
	public Response getProcessJournalMilkByDate(@RequestBody ProcessJournalMilk processJournalMilk) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<ProcessJournalMilk> journalmilk = processManagementService
				.findByDateBetween(processJournalMilk.getFromDate(), processJournalMilk.getToDate());
		if (!journalmilk.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(journalmilk);
		}
		return response;
	}

	// Delete Process Journal Milk Data
	@PostMapping("/deleteProcessJournalMilkData")
	@ResponseBody
	public ResponseEntity<String> deleteProcessJournalMilkData(@RequestBody ProcessJournalMilk processJournalMilk) {
		int i = processJournalMilkRepo.deleteByid(processJournalMilk.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// By Product Process Journal
	// save by product process
	@PostMapping("/saveByProductProcessJournal")
	@ResponseBody
	public Response saveByProductProcessJournal(@RequestBody ByProductProcessJournal byProductProcessJournal) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		ByProductProcessJournal bproj = processManagementService.saveByProductProcessJournal(byProductProcessJournal);

		if (bproj != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(bproj);
		}
		return resp;
	}

	// find all data
	@GetMapping("/findALLByProductProcessJournal")
	@ResponseBody
	public List<ByProductProcessJournal> findALLByProductProcessJournal() {

		return processManagementService.findALLByProductProcessJournal();
	}

	// delete by id
	@PostMapping("/deleteByidByProductProcJournal")
	@ResponseBody
	public ResponseEntity<String> deleteByidByProductProcJoirnal(
			@RequestBody ByProductProcessJournal byProductProcessJournal) {

		int i = byProductProcessJournalRepo.deleteByid(byProductProcessJournal.getId());

		if (i > 0) {

			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}

	}

	// find data by date between
	@GetMapping("/findByProductProcessJournalDataByDate")
	@ResponseBody

	public Response findByDateBetween(@RequestBody ByProductProcessJournal byProductProcessJournal) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Found..!!");

		List<ByProductProcessJournal> bprodj = processManagementService
				.findByDateBetween1(byProductProcessJournal.getfDate(), byProductProcessJournal.gettDate());

		if (!bprodj.isEmpty()) {
			response.setStatus("Success..");
			response.setMessage("Data Found Successfully..!!");
			response.setData(bprodj);
		}
		return response;
	}

	// RPTProcessMilkJournalReport

	// saveRPTProcessMilkJournalReport
	@PostMapping("/saveRPTProcessMilkJournalReport")
	@ResponseBody
	public Response saveRPTProcessMilkJournalReport(
			@RequestBody RPTProcessMilkJournalReportMaster rPTProcessMilkJournalReportMaster) {
		Response response = new Response();
		response.setStatus("Not Sucess..!!!");
		response.setMessage("Data Not saved..!!");

		RPTProcessMilkJournalReportMaster rPTProcessMilkJournalReportMaster2 = processReportManagementService
				.save(rPTProcessMilkJournalReportMaster);
		if (rPTProcessMilkJournalReportMaster2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(rPTProcessMilkJournalReportMaster2);
		}
		return response;
	}

	// find all RPTProcessMilkJournalReport data
	@GetMapping("/getAllRPTProcessMilkJournalReport")
	@ResponseBody
	public List<RPTProcessMilkJournalReportMaster> getAllRPTProcessMilkJournalReport() {
		return processReportManagementService.getAllRPTProcessMilkJournalReport();
	}

	// RPTProcessMilkJournalReport get by date
	@GetMapping("/getRPTProcessMilkJournalReportByDate")
	@ResponseBody
	public Response getRPTProcessMilkJournalReportByDate(
			@RequestBody RPTProcessMilkJournalReportMaster rPTProcessMilkJournalReportMaster) {
		Response response = new Response();
		response.setStatus("Not Success..!!!");
		response.setMessage("Data Not Found.!!");

		List<RPTProcessMilkJournalReportMaster> rPTProcessMilkJournalReportMaster2 = processReportManagementService
				.findByDateBetween(rPTProcessMilkJournalReportMaster.getfDate(),
						rPTProcessMilkJournalReportMaster.gettDate());
		if (!rPTProcessMilkJournalReportMaster2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(rPTProcessMilkJournalReportMaster2);
		}
		return response;
	}

	// deleteById
	@PostMapping("/RPTProcessMilkJournalReportdeleteByid")
	@ResponseBody
	public ResponseEntity<String> deleteByid(
			@RequestBody RPTProcessMilkJournalReportMaster rPTProcessMilkJournalReportMaster) {
		int i = rPTProcessMilkJournalReportRepo.deleteByid(rPTProcessMilkJournalReportMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// RPTProcessMilkJournalReport Export to excel
	@GetMapping("/getRPTProcessMilkJournalReportToExcel")
	@ResponseBody
	public void getRPTProcessMilkJournalReportToExcel(HttpServletResponse res) throws Exception {
		res.setContentType("application/octet-streram");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=RPTProcessMilkJournalReport.xls";
		res.setHeader(headerkey, headervalue);
		processReportManagementService.generateExcel(res);
	}

	// Save Cream Report
	@PostMapping("/SaveCreamReport")
	@ResponseBody
	public Response SaveCreamReport(@RequestBody RptProcessCreamReport rptProcessCreamReport) {
		Response resp = new Response();
		resp.setStatus("Data Not Saved");
		resp.setMessage("Data Not Saved Successfully");

		RptProcessCreamReport rpcr = processReportManagementService.saveCreamReport(rptProcessCreamReport);
		if (rpcr != null) {
			resp.setStatus("Data Saved");
			resp.setMessage("Data Saved Successfully");
			resp.setData(rpcr);
		}
		return resp;
	}

	// Get All Cream Report Dataa
	@GetMapping("/getCreamProcessReport")
	@ResponseBody

	public List<RptProcessCreamReport> getCreamReport() {
		return processReportManagementService.getCreamReport();

	}

	// Delete data By Id of Cream Report
	@PostMapping("/DeleteById")
	@ResponseBody
	public ResponseEntity<String> deleteCreamReportById(@RequestBody RptProcessCreamReport rptProcessCreamreport) {
		int i = rptProcessCreamReportRepo.deleteByid(rptProcessCreamreport.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully");

		} else {
			return ResponseEntity.badRequest().body("Data Not Found");
		}
	}

	// get Cream Report process By Date
	@GetMapping("/getDataByDate")
	@ResponseBody
	public Response getSansthaadvanceMasterdata(@RequestBody RptProcessCreamReport rptprocessCreamreport) {
		Response response = new Response();
		List<RptProcessCreamReport> sam2 = processReportManagementService
				.findByDateBetween4(rptprocessCreamreport.getfDate(), rptprocessCreamreport.gettDate());
		if (!sam2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(sam2);
		} else {
			response.setStatus("Not Success");
			response.setMessage("Data Not Found");
		}
		return response;
	}
	
	
	//RptProcessByProductJournalReport Method
	  //Save RptProcessByProductJournalReport
	  	@PostMapping("/SaveProductoJournalReport")
	  	@ResponseBody
	  	public Response SaveProductReport(@RequestBody  RptProcessByProductJournalReport rptProcessByProductJournalReport)
	  	{
	  		Response resp=new Response();
	  		resp.setStatus("Data Not Saved");
	  		resp.setMessage("Data Not Saved Successfully");
	  		
	  		RptProcessByProductJournalReport rpcr=processReportManagementService.saveProductJournalReport(rptProcessByProductJournalReport);
	  		if(rpcr!=null)
	  		{
	  			resp.setStatus("Data Saved");
	  			resp.setMessage("Data Saved Successfully");
	  			resp.setData(rpcr);
	  		}
	  		return resp;
	  	}
	  	
	  	//Get All RptProcessByProductJournalReport
	  	@GetMapping("/getProductJournalReport")
	  	@ResponseBody
	  	public List<RptProcessByProductJournalReport> getProductJournalReport()
	  	{
	  		return processReportManagementService.getprodctJournalReport();
	  		
	  	}
	  	
	  	
	  	//Delete data By Id of RptProcessByProductJournalReport
	  	@PostMapping("/DeleteProductJouralById")
	  	@ResponseBody
	  	public ResponseEntity<String> deleteProductJournalById(@RequestBody RptProcessByProductJournalReport rptprocessByProductJournalReport)
	  	{
	  		int i=rptProcessByProductJournalReportRepo.deleteByid(rptprocessByProductJournalReport.getId());
	  		if(i>0)
	  		{
	  			 return ResponseEntity.ok("Data Deleted Successfully");
	  			
	  		}
	  		else
	  		{
	  			return ResponseEntity.badRequest().body("Data Not Found");
	  		}
	  	}
	  
	  	
	  	//get RptProcessByProductJournalReport By Date
	      @GetMapping("/getproductJournalDataByDate")
	      @ResponseBody
	      public Response getrptProcessByProductJournalReportdata(@RequestBody RptProcessByProductJournalReport rptProcessbyproductJournalReport)
	      {
	     	 Response resp = new Response(); 
	    	 // response.setStatus("Not Success");
	    	//  response.setMessage("Data Not Found");
	    	  
	    	  List<RptProcessByProductJournalReport> processjournalreport=processReportManagementService.findproductJournalReportByDate(rptProcessbyproductJournalReport.getfDate(), rptProcessbyproductJournalReport.gettDate());
	       if(!processjournalreport.isEmpty())
	       {
	     	  resp.setStatus("Success"); 
	     	  resp.setMessage("Data Found..!!");
	     	  resp.setData(processjournalreport); 
	       }else
	       {
	     	  resp.setStatus("Not Success");
	        	  resp.setMessage("Data Not Found");
	       }
	       return resp;
	      }
}
