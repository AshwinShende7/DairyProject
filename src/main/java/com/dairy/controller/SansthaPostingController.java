package com.dairy.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import com.dairy.model.Sansthaposting.BatchCreationCompanyModel;
import com.dairy.model.Sansthaposting.BatchCreationmaster;
import com.dairy.model.Sansthaposting.OtherExpsJournalSanstha;
import com.dairy.model.Sansthaposting.SansthaBillRaiseMaster;
import com.dairy.model.Sansthaposting.SansthaReceipt;
import com.dairy.model.Sansthaposting.SansthaSaleReturn;
import com.dairy.model.Sansthaposting.Sansthabankpaymentmaster;
import com.dairy.repository.SansthaSalesReturnRepo;
import com.dairy.repository.sansthaPosting.BatchCreationCompanyRepo;
import com.dairy.repository.sansthaPosting.Batchcreationrepo;
import com.dairy.repository.sansthaPosting.OtherExpsJournalSansthaRepo;
import com.dairy.repository.sansthaPosting.SansthaBillRaiseRepo;
import com.dairy.repository.sansthaPosting.SansthaReceiptRepo;
import com.dairy.repository.sansthaPosting.Sansthabankpaymentrepo;
import com.dairy.service.SansthaPostingService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SansthaPostingController {

	@Autowired
	private SansthaPostingService sansthaPostingService;

	@Autowired
	private OtherExpsJournalSansthaRepo otherExpsJournalSansthaRepo;

	@Autowired
	private BatchCreationCompanyRepo batchCreationCompanyRepo;

	@Autowired
	private SansthaReceiptRepo sansthaReceiptRepo;

	@Autowired
	private SansthaBillRaiseRepo sansthaBillRaiseRepo;

	@Autowired
	private SansthaSalesReturnRepo sansthaSaleRepo;

	@Autowired
	private Batchcreationrepo batchcreationrepo;

	@Autowired
	private Sansthabankpaymentrepo sansthabankpaymentrepo;

	// other Exps Journal Sanstha
	// save other Exps Journal Sanstha
	@PostMapping("/saveOtherExpsJournalSanstha")
	@ResponseBody
	public Response saveOtherExpsJournalSanstha(@RequestBody OtherExpsJournalSanstha otherExpsJournalSanstha) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved");

		OtherExpsJournalSanstha otherExpsJournalSanstha2 = sansthaPostingService
				.saveOtherExpsJournalSanstha(otherExpsJournalSanstha);
		if (otherExpsJournalSanstha2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(otherExpsJournalSanstha2);
		}
		return response;
	}

	// get ALL Data of other Exps Journal Sanstha
	@GetMapping("/getAllOtherExpsJournalSanstha")
	@ResponseBody
	List<OtherExpsJournalSanstha> getAllOtherExpsJournalSanstha() {
		return sansthaPostingService.findAllOtherExpsJournalSanstha();
	}

	// Find other Exps Journal Sanstha By Date
	@GetMapping("/findOtherExpsJournalSansthaByDate")
	@ResponseBody
	public Response findOtherExpsJournalSansthaByDate(@RequestBody OtherExpsJournalSanstha otherExpsJournalSanstha) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved");

		List<OtherExpsJournalSanstha> otherJournalSansthas2 = sansthaPostingService
				.findBydateBetween(otherExpsJournalSanstha.getfDate(), otherExpsJournalSanstha.gettDate());
		if (!otherJournalSansthas2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(otherJournalSansthas2);
		}
		return response;

	}

	// delete other Exps Journal Sanstha by ID
	@PostMapping("/deleteOtherExpsJournalSansthaByid")
	@ResponseBody
	public ResponseEntity<String> deleteOtherExpsJournalSansthaByid(
			@RequestBody OtherExpsJournalSanstha otherExpsJournalSanstha) {
		int i = otherExpsJournalSansthaRepo.deleteByid(otherExpsJournalSanstha.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// other Exps Journal Sanstha Export to excel
	@GetMapping("/getOtherJournalSansthaExportToExcel")
	@ResponseBody
	public void generateExcelSheet(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-streram");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=suppliermaster.xls";
		res.setHeader(headerkey, headervalue);
		sansthaPostingService.generateExcel(res);
	}

	// Sanstha Bill Raise
	// save Sanstha Bill Raise
	@PostMapping("/saveSansthaBillRaise")
	@ResponseBody
	public Response saveSansthaBillRaise(@RequestBody SansthaBillRaiseMaster sansthaBillRaiseMaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved");

		SansthaBillRaiseMaster sansthaBillRaiseMaster2 = sansthaPostingService
				.saveSansthaBillRaise(sansthaBillRaiseMaster);
		if (sansthaBillRaiseMaster2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(sansthaBillRaiseMaster2);
		}
		return response;
	}

	// get ALL Data Sanstha Bill Raise
	@GetMapping("/getAllSansthaBillRaise")
	@ResponseBody
	List<SansthaBillRaiseMaster> getAllSansthaBillRaise() {
		return sansthaPostingService.getAllSansthaBillRaise();
	}

	// Find Data Sanstha Bill Raise By Date
	@GetMapping("/findSansthaBillRasieByDate")
	@ResponseBody
	public Response findSansthaBillRasieByDate(@RequestBody SansthaBillRaiseMaster sansthaBillRaiseMaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved");

		List<SansthaBillRaiseMaster> sansthaBillRaiseMaster2 = sansthaPostingService
				.findByDateBetween1(sansthaBillRaiseMaster.getfDate(), sansthaBillRaiseMaster.gettDate());
		if (!sansthaBillRaiseMaster2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(sansthaBillRaiseMaster2);
		}
		return response;

	}

	// Find Data Sanstha Bill Raise By Date in Chilling Center
	@GetMapping("/findSansthaBillRasieByDateChillingCenter")
	@ResponseBody
	public Response findSansthaBillRasieByDateChillingCenter(
			@RequestBody SansthaBillRaiseMaster sansthaBillRaiseMaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved");

		List<SansthaBillRaiseMaster> sansthaBillRaiseMaster2 = sansthaPostingService.findByDateBetweenChillingCenter(
				sansthaBillRaiseMaster.getCfDate(), sansthaBillRaiseMaster.getCtDate());
		if (!sansthaBillRaiseMaster2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(sansthaBillRaiseMaster2);
		}
		return response;

	}

	// delete Sanstha Bill Raise by ID
	@PostMapping("/deleteSansthaBillRaiseByid")
	@ResponseBody
	public ResponseEntity<String> deleteSansthaBillRaiseByid(
			@RequestBody SansthaBillRaiseMaster sansthaBillRaiseMaster) {
		int i = sansthaBillRaiseRepo.deleteByid(sansthaBillRaiseMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Sanstha Bill Raise Export to excel
	@GetMapping("/getSansthaBillRaiseExportToExcel")
	@ResponseBody
	public void getSansthaBillRaiseExportToExcel(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-streram");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=suppliermaster.xls";
		res.setHeader(headerkey, headervalue);
		sansthaPostingService.generateBillExcel(res);
	}

	// Sanstha Sale Return
	// Save Santah Sale Return
	@PostMapping("/saveSansthaSaleReturn")
	@ResponseBody
	public Response saveSansthaSaleReturn(@RequestBody SansthaSaleReturn santhaSaleReturn) {
		Response response = new Response();
		response.setStatus("Not Sucess..");
		response.setMessage("Data Not Sucessfully..!");

		SansthaSaleReturn sansthaSaleReturn = sansthaPostingService.saveSansthaSaleReturn(santhaSaleReturn);
		if (sansthaSaleReturn != null)

			response.setStatus("Sucess..!");
		response.setMessage("Data Save Sucessfully...!");
		response.setData(sansthaSaleReturn);

		return response;
	}

	// Retrive All Data
	@GetMapping("/getSansthaSaleReturn")
	@ResponseBody
	public List<SansthaSaleReturn> getAllCreamJournalData() {
		return sansthaPostingService.getAllSansthaSaleReturnService();
	}

	// DeleteCreamDataById
	@PostMapping("/deleteSansthaSaleReturn")
	@ResponseBody
	public ResponseEntity<String> deleteCreamJournalById(@RequestBody SansthaSaleReturn sansthaSaleReturn) {
		int i = sansthaSaleRepo.deleteCreamJournalById(sansthaSaleReturn.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..");
		} else {
			return ResponseEntity.badRequest().body("Fail To deleted The Data");
		}

	}

	// Export to excel
	@GetMapping("/SansthaSaleReturnExportToExcel")
	public void ExporttoExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date(0));

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename = purRateUpload" + currentDateTime + ".xls";
		response.setHeader(headerKey, headerValue);

		sansthaPostingService.generateExcle(response);

	}

	// Batch Creation Company module
	// save Batch Creation Company
	@PostMapping("/saveBatchCreationCompany")
	@ResponseBody

	public Response saveBatchCreationCompany(@RequestBody BatchCreationCompanyModel batchCreationCompanyModel) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		BatchCreationCompanyModel batchcrm = sansthaPostingService.saveBatchCreationCompany(batchCreationCompanyModel);

		if (batchcrm != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(batchcrm);
		}
		return resp;
	}

	// find all data
	@GetMapping("/findAllBatchCreationCompanyData")
	@ResponseBody

	public List<BatchCreationCompanyModel> findAllBatchCreationCompanyData() {

		return sansthaPostingService.findAllBatchCreationCompanyData();
	}

	// delete by id
	@PostMapping("/deleteByidBatchCreationCompany")
	@ResponseBody
	public ResponseEntity<String> deleteByidVehicleQcEntry(
			@RequestBody BatchCreationCompanyModel batchCreationCompanyModel) {

		int i = batchCreationCompanyRepo.deleteByid(batchCreationCompanyModel.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// find data by date in between
	@GetMapping("/findByDateInBetweenBatchCreation")
	@ResponseBody

	public Response findByDateBetween(@RequestBody BatchCreationCompanyModel batchCreationCompanyModel) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Found..!!");

		List<BatchCreationCompanyModel> batch = sansthaPostingService
				.finBydateBetween(batchCreationCompanyModel.getfDate(), batchCreationCompanyModel.gettDate());

		if (!batch.isEmpty()) {
			response.setStatus("Success..");
			response.setMessage("Data Found Successfully..!!");
			response.setData(batch);
		}
		return response;

	}

	// Sanstha Receipt
	// Save Sanstha Receipt
	@PostMapping("/saveSansthaReceipt")
	@ResponseBody
	public Response saveSansthaReceipt(@RequestBody SansthaReceipt sansthareceipt) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		SansthaReceipt sansthareceipt1 = sansthaPostingService.saveSansthaReceipt(sansthareceipt);

		if (sansthareceipt1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(sansthareceipt1);
		}
		return response;
	}

	// Retrieve Sanstha Receipt All Data
	@GetMapping("/getAllDataSansthaReceipt")
	@ResponseBody
	public List<SansthaReceipt> getAllDetailsSansthaReceipt() {
		return sansthaPostingService.getAllSansthaReceiptData();
	}

	// Retrieve Sanstha Receipt By Date
	@GetMapping("/getSansthaReceiptByDate")
	@ResponseBody
	public Response getSansthaReceiptByDate(@RequestBody SansthaReceipt sansthareceipt) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<SansthaReceipt> sansthareceipt1 = sansthaPostingService.findByDateBetween(sansthareceipt.getfDate(),
				sansthareceipt.gettDate());
		if (!sansthareceipt1.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(sansthareceipt1);
		}
		return response;
	}

	// Delete Sanstha Receipt By Id
	@PostMapping("/deleteSansthaReceiptById")
	@ResponseBody
	public ResponseEntity<String> deleteSansthaReceiptById(@RequestBody SansthaReceipt sansthareceipt) {
		int i = sansthaReceiptRepo.deleteByid(sansthareceipt.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Export Sanstha Receipt Data To Excel
//		@GetMapping("/sansthaReceiptExcel")
//		public void generateExcelReport(HttpServletResponse response) throws Exception {
//			response.setContentType("application/octet-stream");
//			
//			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:MM:SS");
//			String currentDate = dateFormat.format(new Date());
//			
//			String headerKey = "Content-Disposition";
//			String headerValue = "attachment;filename=SansthaReceipt" + currentDate + ".xls";
//			response.setHeader(headerKey, headerValue);
//
//			sansthaPostingService.generateSansthaReceiptExcel(response);
//		}

	// Batch Creation
//Save Batch Creation
	@PostMapping("/savebatchcreation")
	@ResponseBody
	public Response saveBatchcreation(@RequestBody BatchCreationmaster batchCreationMaster) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		BatchCreationmaster bcm = sansthaPostingService.savebatchCreation(batchCreationMaster);
		if (bcm != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(bcm);
		}
		return response;
	}

	@GetMapping("/getbatchcreationdata")
	@ResponseBody
	public List<BatchCreationmaster> getallbatchcreation() {
		return sansthaPostingService.findallbatchcreation();
	}

	@PostMapping("/deletebyidbatchcreation")
	@ResponseBody
	public ResponseEntity<String> deleteidbyBatchcreation(@RequestBody BatchCreationmaster batchcreationmaster) {
		int i = batchcreationrepo.deleteByid(batchcreationmaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}

	}

	@GetMapping("/getbatchcreationbydate")
	@ResponseBody
	public Response getbatchcreationbyDate(@RequestBody BatchCreationmaster batchCreationmaster) {
		Response resp = new Response();
		// response.setStatus("Not Success");
		// response.setMessage("Data Not Found");

		List<BatchCreationmaster> batchcreation = sansthaPostingService.findByDateBetween2(batchCreationmaster.getfDate(),
				batchCreationmaster.gettDate());
		if (batchcreation != null) {
			resp.setStatus("Success");
			resp.setMessage("Data Found..!!");
			resp.setData(batchcreation);
		} else {
			resp.setStatus("Not Success");
			resp.setMessage("Data Not Found");
		}
		return resp;
	}

	@PostMapping("/savesansthabankpayment")
	@ResponseBody
	public Response savesansthabankpayment(@RequestBody Sansthabankpaymentmaster santhabankpaymentmaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		Sansthabankpaymentmaster sbp = sansthaPostingService.savesansthabankpayment(santhabankpaymentmaster);
		if (sbp != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(sbp);
		}
		return response;
	}

	@GetMapping("/getallsansthabankpayments")
	@ResponseBody
	public List<Sansthabankpaymentmaster> getallsansthabankpayments() {
		return sansthaPostingService.findallsansthabankpayment();
	}

	@PostMapping("/deletebyidsansthabankpayment")
	@ResponseBody
	public ResponseEntity<String> deleteidbysansthabankpayment(
			@RequestBody Sansthabankpaymentmaster sansthabankpaymentmaster) {
		int i = sansthabankpaymentrepo.deleteByid(sansthabankpaymentmaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}

	}

	@GetMapping("/getsansthabankpaymentbydate")
	@ResponseBody

	public Response getsansthabankpaymentbydate(@RequestBody Sansthabankpaymentmaster sansthabankpaymentmaster) {
		Response resp = new Response();
		// response.setStatus("Not Success");
		// response.setMessage("Data Not Found");

		List<Sansthabankpaymentmaster> bankpayment = sansthaPostingService
				.findPaymentByDateBetween3(sansthabankpaymentmaster.getfDate(), sansthabankpaymentmaster.gettDate());
		if (bankpayment != null) {
			resp.setStatus("Success");
			resp.setMessage("Data Found..!!");
			resp.setData(bankpayment);
		} else {
			resp.setStatus("Not Success");
			resp.setMessage("Data Not Found");
		}
		return resp;
	}

	@GetMapping("/downloadCreamReportexcel")
	@ResponseBody
	public void generateExcelReport(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachement;filename=CreamReportExcel.xls";
		res.setHeader(headerkey, headervalue);
		sansthaPostingService.generateExcel1(res);

	}

}
