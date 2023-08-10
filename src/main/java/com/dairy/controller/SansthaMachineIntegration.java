package com.dairy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.model.sansthaMachineIntegration.MilkCollectionKg;
import com.dairy.model.sansthaMachineIntegration.MilkCollectionQCMachine;
import com.dairy.repository.sansthaMachineIntegration.MilkCollectionKgRepo;
import com.dairy.repository.sansthaMachineIntegration.MilkCollectionQCMachineRepo;
import com.dairy.service.SansthaMachineIntegrationService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class SansthaMachineIntegration {

	@Autowired
	private SansthaMachineIntegrationService sansthaMachineIntegrationService;

	@Autowired
	private MilkCollectionKgRepo milkCollectionKgRepo;
	
	@Autowired
	private MilkCollectionQCMachineRepo milkCollectionQCMachineRepo ;
	
	//MILK COLLECTION KG MACHINE
	// save Milk Collection Kg
	@PostMapping("/saveMilkCollectionKg")
	@ResponseBody

	public Response saveMilkCollectionKg(@RequestBody MilkCollectionKg milkCollectionKg) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");
		MilkCollectionKg mkckg = sansthaMachineIntegrationService.saveMilkCollectionKg(milkCollectionKg);

		if (mkckg != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(mkckg);
		}
		return resp;
	}

	// find all Milk Collection Kg

	@GetMapping("/findAllMilkCollectioKgnData")
	@ResponseBody

	public List<MilkCollectionKg> findAllMilkCollectionData() {
		return sansthaMachineIntegrationService.findAllMilkCollectionData();
	}

	// delete by id Milk Collection Kg

	@PostMapping("/deleteByIdMilkCollectionKgData")
	@ResponseBody

	public ResponseEntity<String> deleteByIdMilkCollectionKgData(@RequestBody MilkCollectionKg milkCollectionKg) {

		int i = milkCollectionKgRepo.deleteByid(milkCollectionKg.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Succefully...");
		} else {
			return ResponseEntity.badRequest().body("Data not Deleted...");
		}
	}

	// find data by date in between Milk Collection Kg
	@GetMapping("/findByDateBetweenMilkcollectionKg")
	@ResponseBody

	public Response findByDateBetweenMilkcollectionKg(@RequestBody MilkCollectionKg milkCollectionKg) {

		Response resp1 = new Response();
		resp1.setStatus("Not Success..");
		resp1.setMessage("Data Not Found..!!");
		List<MilkCollectionKg> mkckg1 = sansthaMachineIntegrationService.findByDateBetween(milkCollectionKg.getfDate(),
				milkCollectionKg.gettDate());

		if (!mkckg1.isEmpty()) {
			resp1.setStatus("Success..");
			resp1.setMessage("Data Found Successfully..!!");
			resp1.setData(mkckg1);
		}

		return resp1;
	}
	
	//MILK COLLECTION QC MACHINE
	//Save Milk Collection Qc Machine 
	@PostMapping("saveMilkCollectionQCMachine")
	@ResponseBody
	public Response saveMilkCollectionQCMachine (@RequestBody MilkCollectionQCMachine milkCollectionQCMachine) {
		
		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");
		
		MilkCollectionQCMachine milkQCMachine = sansthaMachineIntegrationService.savemilkQCMachine(milkCollectionQCMachine);
		
		if(milkQCMachine!=null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved");
			response.setStatus("milkQCMachine");
		}
		return response ;
	}

}
