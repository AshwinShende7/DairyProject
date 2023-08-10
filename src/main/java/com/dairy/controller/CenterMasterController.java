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
import com.dairy.model.centerMaster.ApplyRateChartToAll;
import com.dairy.model.centerMaster.CollectionMaster;
import com.dairy.model.centerMaster.FarmerMaster;
import com.dairy.repository.centerMaster.ApplyRateChartToAllRepo;
import com.dairy.repository.centerMaster.CollectionMasterRepo;
import com.dairy.repository.centerMaster.FarmerMasterRepo;
import com.dairy.service.CenterMasterService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class CenterMasterController {

	@Autowired
	private CenterMasterService centerMasterService;

	@Autowired
	private CollectionMasterRepo collectionMasterRepo;

	@Autowired
	private FarmerMasterRepo farmerMasterRepo;
	
	@Autowired
	private ApplyRateChartToAllRepo applyRateChartToAllRepo ;

	// Collection Master
	// saveCollectionMaster
	@PostMapping("/saveCollectionCenterMaster")
	@ResponseBody
	public Response saveCollectionMaster(@RequestBody CollectionMaster collectionMaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		CollectionMaster collectionCenterMaste2 = centerMasterService.saveCollectionMaster(collectionMaster);
		if (collectionCenterMaste2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(collectionCenterMaste2);
		}
		return response;
	}

	// getAllCollectionMasterData
	@GetMapping("/getAllCollectionMaster")
	@ResponseBody
	public List<CollectionMaster> getAllCollectionMaster() {
		return centerMasterService.findAllCollectionMaster();

	}

	// deleteById
	@PostMapping("/deleteByid")
	@ResponseBody
	public ResponseEntity<String> deleteByid(@RequestBody CollectionMaster collectionMaster) {
		int i = collectionMasterRepo.deleteByid(collectionMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Farmer Master
	// Save FarmerMaster
	@PostMapping("/saveFarmerMaster")
	@ResponseBody
	public Response saveFarmerMaster(@RequestBody FarmerMaster farmermaster) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		FarmerMaster farmermaster1 = centerMasterService.savefarmerMaster(farmermaster);
		if (farmermaster1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(farmermaster1);
		}
		return response;
	}

	// Retrieve FarmerMaster Data
	@GetMapping("/getAllFarmerMasterData")
	@ResponseBody
	public List<FarmerMaster> getAllFarmerMasterData() {
		return centerMasterService.getAllFermerMasterDetails();
	}

	// Delete FarmerMaster Date By Id
	@PostMapping("/deleteFarmerMasterDataById")
	@ResponseBody
	public ResponseEntity<String> deleteFarmerMasterByid(@RequestBody FarmerMaster farmermaster) {

		int i = farmerMasterRepo.deleteByid(farmermaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Export FarmerMaster Data To Excel
	@GetMapping("/farmerMasterExcel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=FarmerMaster.xls";
		response.setHeader(headerKey, headerValue);

		centerMasterService.generateFarmerMasterExcel(response);
	}

	// Apply Rate Chart To All Center
	// Save Apply Rate Chart To All Center
	@PostMapping("/saveApplyRateChart")
	@ResponseBody
	public Response saveApplyRateChartToAllCenter(@RequestBody ApplyRateChartToAll applyRateChartToAll) {
		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Saved..!!");

		ApplyRateChartToAll applyRateChartToAll1 = centerMasterService
				.saveApplyRateChartToAllCenter(applyRateChartToAll);
		if (applyRateChartToAll1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved..!!");
			response.setData(applyRateChartToAll1);
		}
		return response;
	}

	// Retrieve Apply Rate Chart To All Center
	@GetMapping("/getAllApplyRateChartData")
	@ResponseBody
	public List<ApplyRateChartToAll> getAllApplyRateChartData() {
		return centerMasterService.getAllApplyRateChartData();
	}

	// Delete Apply Rate Chart To All Center By Id
	@PostMapping("/deleteApplyRateChart")
	@ResponseBody
	public ResponseEntity<String> deleteApplyRateChart(@RequestBody ApplyRateChartToAll applyRateChartToAll) {
		int i = applyRateChartToAllRepo.deleteByid(applyRateChartToAll.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

}
