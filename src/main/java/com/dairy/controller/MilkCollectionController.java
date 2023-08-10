package com.dairy.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.model.milkCollection.CombinationColletionWithQc;
import com.dairy.model.milkCollection.MilkCollection;
import com.dairy.model.milkCollection.MilkCollectionManualKG;
import com.dairy.model.milkCollection.MilkCollectionQC;
import com.dairy.model.milkCollection.SansthRateReduce;
import com.dairy.model.sansthaMaster.PurRateExcleImport;
import com.dairy.repository.milkCollection.CombCollectionQCRepo;
import com.dairy.repository.milkCollection.MilkCollectionManualKGRepo;
import com.dairy.repository.milkCollection.MilkCollectionQCRepo;
import com.dairy.repository.milkCollection.MilkCollectionRepo;
import com.dairy.repository.milkCollection.SansthRateReduceRepo;
import com.dairy.repository.sansthaMaster.PurRateExcelImportRepo;
import com.dairy.service.MilkCollectionService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MilkCollectionController {

	@Autowired
	private MilkCollectionService milkCollectionService;

	@Autowired
	private MilkCollectionRepo milkCollectionRepo;

	@Autowired
	private MilkCollectionQCRepo milkCollectionQCRepo;

	@Autowired
	private CombCollectionQCRepo combCollectionQCRepo;

	@Autowired
	private PurRateExcelImportRepo purRateExcelImportRepo;

	@Autowired
	private SansthRateReduceRepo sansthaReducerepo;

	@Autowired
	private MilkCollectionManualKGRepo milkCollectionManualKGRepo;

//MILK COLLECTION	
	// Save Milk Collection Details

	@PostMapping("/saveMilkCollection")
	@ResponseBody
	public Response saveMilkCollection(@Valid @RequestBody MilkCollection milkCollection) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		MilkCollection mc2 = milkCollectionService.saveMilkCollection(milkCollection);
		System.out.println(mc2.toString());
		if (mc2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(mc2);
		}
		return response;
	}

	// Retrieve Milk Collection Details By From date and To date

	@GetMapping("/getMilkCollection")
	@ResponseBody
	public Response getMilkCollection(@RequestBody MilkCollection milkCollection) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<MilkCollection> milkCollection2 = milkCollectionService.findByinwardDateBetween(milkCollection.getfDate(),
				milkCollection.gettDate());
		if (!milkCollection2.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(milkCollection2);
		}
		return response;
	}

	// Retrieve All Data

	@GetMapping("/getAllMilkCollectionData")
	@ResponseBody
	public List<MilkCollection> getAllMilkCollectionData() {
		return milkCollectionService.findAll();
	}

	// Delete milk Collection By Id

	@PostMapping("/deleteMilkCollectionById")
	@ResponseBody
	public ResponseEntity<String> deleteMilkCollectionById(@RequestBody MilkCollection milkCollection) {

		int i = milkCollectionRepo.deleteByid(milkCollection.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
	}

//MILK COLLECTION QC	  
	// Save Milk collection QC DATA
	@PostMapping("/saveMilkCollectionQC")
	@ResponseBody
	public Response saveMilkCollectionQC(@RequestBody MilkCollectionQC milkcollectionQC) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!!");

		String degree1 = milkcollectionQC.getDegree();
		String fat1 = milkcollectionQC.getFat();

		double degree2 = Double.parseDouble(degree1);
		double fat2 = Double.parseDouble(fat1);

		double result = degree2 / 4 + 0.2 * fat2 + 0.66;

		milkcollectionQC.setSnf(String.valueOf(result));

		MilkCollectionQC milkCollectionQC2 = milkCollectionService.saveMilkCollection(milkcollectionQC);
		if (milkCollectionQC2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved...!!!");
			response.setData(milkCollectionQC2);
		}

		return response;
	}

	// Retrieve Milk Collection QC Details By From date and To date
	@GetMapping("/getMilkCollectionQC")
	@ResponseBody
	public Response getMilkCollectionQC(@RequestBody MilkCollectionQC milkCollectionQC) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not SAved..!!");

		List<MilkCollectionQC> milkCollectionQC2 = milkCollectionService.findBydateBetween(milkCollectionQC.getfDate(),
				milkCollectionQC.gettDate());
		if (!milkCollectionQC2.isEmpty()) {
			response.setStatus("Success..");
			response.setMessage("Data Found...!!");
			response.setData(milkCollectionQC2);
		} else {
			response.setStatus("not success");
		}
		return response;
	}

	public List<PurRateExcleImport> showgitmethod() {
		return purRateExcelImportRepo.findAll();
	}

	// Retrieve All Data

	@GetMapping("/getAllMilkCollectionQCData")
	@ResponseBody
	public List<MilkCollectionQC> getAllMilkCollectionQCData() {
		return milkCollectionService.findAllmilkQC();
	}

	// Delete milk Collection QC By Id

	@PostMapping("/deleteMilkCollectionQCById")
	@ResponseBody
	public ResponseEntity<String> deleteMilkCollectionQCById(@RequestBody MilkCollectionQC milkCollectionQC) {

		int i = milkCollectionQCRepo.deleteByid(milkCollectionQC.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
	}

	// Combination Collection With Qc
	// Save Combination Collection With Qc
	@PostMapping("/saveCombinationCollectionWithQC")
	@ResponseBody
	public Response saveCombCollectionQc(@RequestBody CombinationColletionWithQc combcollectionQC) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Fail To Saved Data..!");

		CombinationColletionWithQc combcollectionQC1 = milkCollectionService.saveCombCollectionQc(combcollectionQC);
		if (combcollectionQC1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved...!!!");
			response.setData(combcollectionQC1);
		}
		return response;
	}

	// Retrive All Data of Combination Collection With Qc
	@GetMapping("/getalldataCombCollectionQC")
	@ResponseBody
	public List<CombinationColletionWithQc> getalldataCombCollectionQC() {
		return milkCollectionService.getalldataCombCollectionQC();

	}

	// Delete data by ID
	@PostMapping("/deleteCombCollectionQCById")
	@ResponseBody
	public ResponseEntity<String> deleteCombCollectionQCById(@RequestBody CombinationColletionWithQc combcollectionQC) {
		int i = combCollectionQCRepo.deleteByid(combcollectionQC.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Data not Deleted");
		}

	}

	// Retrieve Milk Collection Details By From date and To date

	@GetMapping("/getdatabydate")
	@ResponseBody
	public Response getdatabydate(@RequestBody CombinationColletionWithQc combcollectionQC) {
		Response response = new Response();

		response.setStatus("Not Success..");
		response.setMessage("Data Not Found..!!");

		List<CombinationColletionWithQc> combcollectionQC2 = milkCollectionService
				.findByinwardDateBetween1(combcollectionQC.getfDate(), combcollectionQC.gettDate());

		if (!combcollectionQC2.isEmpty()) {
			response.setStatus("Success..");
			response.setMessage("Data Found...!!");
			response.setData(combcollectionQC2);
		}

		return response;
	}

	// Milk Collection Manual:KG
	// Save Milk Collection Manual-KG
	@PostMapping("/saveMilkCollectionManualKG")
	@ResponseBody
	public Response saveMilkCollectionManualKg(@RequestBody MilkCollectionManualKG milkcollectionmanualKG) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!!");

		MilkCollectionManualKG milkCollectionManualKG1 = milkCollectionService
				.saveMilkCollectionManualKG(milkcollectionmanualKG);
		if (milkCollectionManualKG1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved...!!!");
			response.setData(milkCollectionManualKG1);
		}

		return response;
	}

	// Retrieve Milk Collection Manual-KG Data By From Date and To Date
	@GetMapping("/getMilkCollectionManualKGByDate")
	@ResponseBody
	public Response getMilkCollectionManualKGBydate(@RequestBody MilkCollectionManualKG milkCollectionManualKG) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<MilkCollectionManualKG> milkCollectionManualKG1 = milkCollectionService
				.findBycollectionDateBetween(milkCollectionManualKG.getFdate(), milkCollectionManualKG.getTdate());
		if (!milkCollectionManualKG1.isEmpty()) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(milkCollectionManualKG1);
		}
		return response;
	}

	// Delete Milk Collection Manual-KG By Id
	@PostMapping("/deleteMilkCollectionManualKGById")
	@ResponseBody
	public ResponseEntity<String> deleteMilkCollectionManualKGById(@RequestBody MilkCollectionManualKG milkCollectionManualKG) {
		int i = milkCollectionManualKGRepo.deleteByid(milkCollectionManualKG.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Data not Deleted");
		}

	}
	
	// Retrieve Milk Collection Manual-kG All Data
		@GetMapping("/getAllDataMilkCollectionManualKG")
		@ResponseBody
		public List<MilkCollectionManualKG> getAllDataMilkCollectionManualKG() {
			return milkCollectionService.getAllMilkCollectionManualKGData();
		}



	// Save Sanstha Rate Reduce Data

	@PostMapping("/saveSansthaRateReduce")
	@ResponseBody
	public Response saveSansthRatereduce(@RequestBody SansthRateReduce sansthaRateReduce) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!!");

		SansthRateReduce sansthaRateReduce2 = milkCollectionService.savereduceRate(sansthaRateReduce);
		if (sansthaRateReduce2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved...!!!");
			response.setData(sansthaRateReduce2);
		}

		return response;
	}

	// Delete Sanstha Rate Reduce Data
	@PostMapping("/deletesanstharateReduce")
	@ResponseBody
	public ResponseEntity<String> deletereducerateDataById(@RequestBody SansthRateReduce sansthaRateReduce) {
		int i = sansthaReducerepo.deleteByid(sansthaRateReduce.getId());
		if (i > 0) {
			return ResponseEntity.ok("Delete Data Successfully");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Retreive Sanstha Rate Reduce Data
	@GetMapping("/getSanstharatereduceData")
	@ResponseBody
	List<SansthRateReduce> getSanstharatereduceData() {
		return milkCollectionService.findallSansthaRate();
	}

}
