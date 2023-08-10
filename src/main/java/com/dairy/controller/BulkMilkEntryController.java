package com.dairy.controller;

import java.io.IOException;
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
import com.dairy.model.BulkMilkEntry.MilkGRN;
import com.dairy.model.BulkMilkEntry.MilkGrnToPurchase;
import com.dairy.model.BulkMilkEntry.VehicleEntry;
import com.dairy.model.BulkMilkEntry.VehicleQCApproval;
import com.dairy.model.BulkMilkEntry.VehicleQcEntry;
import com.dairy.repository.BulkMilkEntry.MilkGRNRepo;
import com.dairy.repository.BulkMilkEntry.MilkGrnToPurchaseRepo;
import com.dairy.repository.BulkMilkEntry.VehicleEntryRepo;
import com.dairy.repository.BulkMilkEntry.VehicleQCApprovalRepo;
import com.dairy.repository.BulkMilkEntry.VehicleQcEntryRepo;
import com.dairy.service.BulkMilkEntryService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class BulkMilkEntryController {

	@Autowired
	private BulkMilkEntryService bulkMilkEntryService;

	@Autowired
	private VehicleEntryRepo vehicleEntryRepo;

	@Autowired
	private VehicleQCApprovalRepo vehicleQCApprovalRepo;

	@Autowired
	private VehicleQcEntryRepo vehicleQcEntryRepo;

	@Autowired
	private MilkGrnToPurchaseRepo milkGrnToPurchaseRepo;
	
	@Autowired
	private MilkGRNRepo milkGRNRepo ;

	// ***VEHICLE ENTRY**
	// Save Vehicle Entry Data...
	@PostMapping("/saveVehicleEntry")
	@ResponseBody
	public Response saveVehicleEntry(@RequestBody VehicleEntry vehicleEntry) {
		Response response = new Response();
		response.setStatus("Not Sucess..");
		response.setMessage("Data Not Saved..");

		VehicleEntry vehicleEntry2 = bulkMilkEntryService.saveVehicleEntry(vehicleEntry);
		if (vehicleEntry2 != null)

			response.setStatus("Success..");
		response.setMessage("Data Saved...!");
		response.setData(vehicleEntry2);
		return response;
	}

	// Get Vehicle Entry Data
	@GetMapping("/getallVehicleEntry")
	@ResponseBody
	public List<VehicleEntry> getAllVehicleEntry() {
		return bulkMilkEntryService.getAllVehicleEntry();
	}

	// Delete VEhicle Entry Data
	@PostMapping("/deleteVehicleEntryById")
	public ResponseEntity<String> deleteVehicleEntryById(@RequestBody VehicleEntry vehicleEntry) {
		int i = vehicleEntryRepo.deleteVehicleEntryById(vehicleEntry.getId());

		if (i > 0) {
			return ResponseEntity.ok().body(vehicleEntry.getId() + " This Data Deleted Sucessfully");
		} else {
			return ResponseEntity.badRequest().body("Fail To Delete Data..!");
		}
	}

	// Get Vehicle Entry Data From Date To Date
	@GetMapping("/getdataByDate")
	@ResponseBody
	public Response getVehicleEntryDataByDate(@RequestBody VehicleEntry vehicleEntry) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");
		List<VehicleEntry> vehicleEntrys = bulkMilkEntryService.findBydateBetween(vehicleEntry.getFromDate(),
				vehicleEntry.getToDate());
		System.out.println("From Date : " + vehicleEntry.getFromDate());
		System.out.println("To Date : " + vehicleEntry.getToDate());

		if (!vehicleEntrys.isEmpty()) {
			response.setStatus("Sucess..");
			response.setMessage("Data Found Sucessfully...!");
			response.setData(vehicleEntrys);
		}

		return response;

	}

	// Vehicle QC Approval
	// Save VehicleQCApproval
	@PostMapping("/saveVehicleQCApproval")
	@ResponseBody
	public Response saveVehicleQCApproval(@RequestBody VehicleQCApproval vehicleQCApproval) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		VehicleQCApproval vehicleapproval = bulkMilkEntryService.saveBulkMilkEntry(vehicleQCApproval);

		if (vehicleapproval != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(vehicleapproval);
		}

		return response;
	}

	// Retrieve VehicleQCApproval Data
	@GetMapping("/getAllVehicleQCApprovalData")
	@ResponseBody
	public List<VehicleQCApproval> getAllVehicleQCApprovalData() {
		return bulkMilkEntryService.getAllVehicleQCApprovalDetails();
	}

	// Retrieve VehicleQCApproval Data By Date
	@GetMapping("/getVehicleQCApprovalByDate")
	@ResponseBody
	public Response getVehicleQCApprovalByDate(@RequestBody VehicleQCApproval vehicleQCApproval) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<VehicleQCApproval> vehicleQcApproval = bulkMilkEntryService
				.getVehicleQCApprovalByDate(vehicleQCApproval.getfDate(), vehicleQCApproval.gettDate());

		if (!vehicleQcApproval.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(vehicleQcApproval);
		}

		return response;
	}

	// Delate VehicleQCAprroval By Id
	@PostMapping("/deleteVehicleQCApprovalById")
	@ResponseBody
	public ResponseEntity<String> deleteVehicleQCApprovalById(@RequestBody VehicleQCApproval vehicleQCApproval) {
		int i = vehicleQCApprovalRepo.deleteByid(vehicleQCApproval.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// vehivle qc entry
	// save data

	@PostMapping("/saveVehicleQcEntry")
	@ResponseBody

	public Response saveVehicleQcEntry(@RequestBody VehicleQcEntry vehicleQcEntry) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");
		VehicleQcEntry vqcentry = bulkMilkEntryService.saveVehicleQcEntry(vehicleQcEntry);

		if (vqcentry != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(vqcentry);
		}
		return resp;

	}

	// find all data
	@GetMapping("/findALLVehicleQcEntryData")
	@ResponseBody

	public List<VehicleQcEntry> findALLVehicleQcEntryData() {

		return bulkMilkEntryService.findALLVehicleQcEntryData();
	}

	// delete by id

	@PostMapping("/deleteByidVehicleQcEntry")
	@ResponseBody

	public ResponseEntity<String> deleteByidVehicleQcEntry(@RequestBody VehicleQcEntry vehicleQcEntry) {

		int i = vehicleQcEntryRepo.deleteByid(vehicleQcEntry.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// find data by date in between

	@GetMapping("/findByDateInBetweenVEhicleQcEntry")
	@ResponseBody

	public Response findByDateInBetweenVEhicleQcEntry(@RequestBody VehicleQcEntry vehicleQcEntry) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Found..!!");

		List<VehicleQcEntry> vehiqc = bulkMilkEntryService.findByDateBetween1(vehicleQcEntry.getfDate(),
				vehicleQcEntry.gettDate());

		if (!vehiqc.isEmpty()) {
			response.setStatus("Success..");
			response.setMessage("Data Found Successfully..!!");
			response.setData(vehiqc);
		}
		return response;

	}

	// Vehicle Qc Entry data Export to Excel

	@GetMapping("/getVehicleQcEntryExportToExcel")
	@ResponseBody

	public void getVehicleQcEntryExportToExcel(HttpServletResponse resp) throws IOException {

		resp.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=vehicleqcentry.xls";
		resp.setHeader(headerkey, headervalue);
		bulkMilkEntryService.generateVehicleQcEntryExcelSheet(resp);
	}

	// Save Milk GRN To Purchase
	@PostMapping("/saveMilkCollectionGrntoPurchase")
	@ResponseBody

	public Response saveMilkCollectionGrntoPurchase(@RequestBody MilkGrnToPurchase milkGrnToPurchase)

	{
		Response response = new Response();
		response.setStatus("Not Saved");
		response.setMessage("Data Not Saved");

		MilkGrnToPurchase mgtp = bulkMilkEntryService.saveMilkGrnToPurchase(milkGrnToPurchase);
		if (mgtp != null) {
			response.setStatus("Data Saved");
			response.setMessage("Data Saved Successfully");
			response.setData(mgtp);
		}
		return response;
	}

	// Milk Grn To Purchase Find All Data

	@GetMapping("/findAllMilkGrntopurcase")
	@ResponseBody
	public List<MilkGrnToPurchase> fildAllMilkGrnData() {
		return bulkMilkEntryService.findAllMilkGrnData();
	}

	// Delete Milk Grn To Purchase By Id
	@PostMapping("/deleteDatabyid")
	@ResponseBody
	public ResponseEntity<String> deleteMilkgrptopurchaseDataById(@RequestBody MilkGrnToPurchase milkGrnTopurchase) {
		int i = milkGrnToPurchaseRepo.deleteByid(milkGrnTopurchase.getId());
		if (i > 0) {
			return ResponseEntity.ok("Delete Data Successfully");
		} else {
			return ResponseEntity.badRequest().body("Data Not found..!!");

		}
	}

	// Find Milk Grn To Purchase Data By Date
	@GetMapping("/FindBYDate")
	@ResponseBody
	public Response GetMilkGrpPurchaseByDate(@RequestBody MilkGrnToPurchase milkGrntopurchase) {
		Response res = new Response();
		res.setStatus("Data Not Found");
		res.setMessage("Data Not Available");

		List<MilkGrnToPurchase> mktp = bulkMilkEntryService.findByDateBetween(milkGrntopurchase.getfDate(),
				milkGrntopurchase.gettDate());

		if (!mktp.isEmpty()) {
			res.setStatus("Success");
			res.setMessage("Data Found..!!");
			res.setData(mktp);
		} else {
			res.setStatus("Not Success");
			res.setMessage("Data Not Found");
		}
		return res;
	}

	// Download Milk Grn To Purchase Excel File
	@GetMapping("/downloadmilkGrppurchaseexcel")
	@ResponseBody
	public void generateExcelReport(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachement;filename=MilkGRnTopurchase.xls";
		res.setHeader(headerkey, headervalue);
		bulkMilkEntryService.generateMilkGRnTopurchaseExcel(res);

	}
	
	//Milk GRN
	//saveMilk GRN
		@PostMapping("/saveMilkGRN")
		@ResponseBody
		public Response saveMilkGRN(@RequestBody MilkGRN milkGRN)
		{
			Response response=new Response();
			response.setStatus("Not Success");
			response.setMessage("Data Not Saved..!!");
			
			MilkGRN milkGRN2=bulkMilkEntryService.save(milkGRN);
			if(milkGRN2 != null)
			{
				response.setStatus("Success");
				response.setMessage("Data Saved Successfully..!!");
				response.setData(milkGRN2);
			}
			return response;	
		}
		
		
		// find all
		@GetMapping("/findAllMilkGRN")
		@ResponseBody
		public List<MilkGRN> findAllMilkGRN(){
			return bulkMilkEntryService.findAllMilkGRN();
		}
		
		//Retrieve Milk GRN By From date and To date
		  
		  @GetMapping("/getMilkGRNByDate")
		  @ResponseBody 
		  public Response getMilkGRNByDate(@RequestBody MilkGRN milkGRN) {	
			  
		  Response response = new Response(); 
		  response.setStatus("Not Success");
		  response.setMessage("Data Not Found");
		  
		  List<MilkGRN> milkGRN2 =bulkMilkEntryService.findByDateBetween2(milkGRN.getfDate(),milkGRN.gettDate()); 
		  if(!milkGRN2.isEmpty()) {
		  response.setStatus("Success"); 
		  response.setMessage("Data Found..!!");
		  response.setData(milkGRN2); 
		  } 
		  return response ;
		  }
		  
		//deleteById
			 @PostMapping("/MilkGRNdeleteByid")
			 @ResponseBody
			 public ResponseEntity<String> deleteByid(@RequestBody MilkGRN milkGRN){
				 int i=milkGRNRepo.deleteByid(milkGRN.getId());
				 if(i>0) {
					 return ResponseEntity.ok("Data Deleted Successfully..!!");
				 }
				 else{
					 return ResponseEntity.badRequest().body("Data Not Deleted..!!");
				 }
			 }
		
		  //Milk GRN Data Export to excel 
			 @GetMapping("/getMilkGRNExportToExcel")
			 @ResponseBody
			 public void getMilkGRNExportToExcel(HttpServletResponse res) throws IOException
				{
					res.setContentType("application/octet-streram");
					String headerkey="Content-Disposition";
					String headervalue="attachment;filename=MilkGRN.xls";
					res.setHeader(headerkey, headervalue);
					bulkMilkEntryService.generateExcel(res);
				}

}
