package com.dairy.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.dairy.model.mainMaster.AccountMaster;
import com.dairy.model.otherMasters.AccountGroup;
import com.dairy.model.otherMasters.AreaMaster;
import com.dairy.model.otherMasters.DepartmentMaster;
import com.dairy.model.otherMasters.PurchasePaymentTermsandCondition;
import com.dairy.model.otherMasters.RateContractMaster;
import com.dairy.model.otherMasters.ReorderMaster;
import com.dairy.model.otherMasters.RouteMaster;
import com.dairy.model.otherMasters.SchemeMaster;
import com.dairy.model.otherMasters.TransportMaster;
import com.dairy.model.otherMasters.UnitMaster;
import com.dairy.model.otherMasters.VehicleMaster;
import com.dairy.model.otherMasters.WarehouseMaster;
import com.dairy.repository.otherMaster.AccountGroupRepo;
import com.dairy.repository.otherMaster.AreaMasterRepo;
import com.dairy.repository.otherMaster.DepartmentMasterRepo;
import com.dairy.repository.otherMaster.PurchasePaymentRepo;
import com.dairy.repository.otherMaster.RateContractMasterRepo;
import com.dairy.repository.otherMaster.ReorderMasterRepo;
import com.dairy.repository.otherMaster.RouteMasterRepo;
import com.dairy.repository.otherMaster.SchemeMasterRepo;
import com.dairy.repository.otherMaster.TransportMasterRepo;
import com.dairy.repository.otherMaster.UnitMasterRepo;
import com.dairy.repository.otherMaster.VehicleMasterRepo;
import com.dairy.repository.otherMaster.WarehouseMasterRepo;
import com.dairy.service.OtherMasterService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class OtherMasterController {

	@Autowired
	private OtherMasterService otherMasterService;

	@Autowired
	private AccountGroupRepo accountGroupRepo;

	@Autowired
	private DepartmentMasterRepo departmentMasterRepo;

	@Autowired
	private RouteMasterRepo routeMasterRepo;

	@Autowired
	private UnitMasterRepo unitMasterRepo;

	@Autowired
	private ReorderMasterRepo reorderMasterRepo;

	@Autowired
	private PurchasePaymentRepo purchasePaymentRepo;

	@Autowired
	private SchemeMasterRepo schemeMasterRepo;

	@Autowired
	private AreaMasterRepo areamasterrepo;

	@Autowired
	private WarehouseMasterRepo warehouseMasterRepo;

	@Autowired
	private VehicleMasterRepo vehiclemasterrepo;

	@Autowired
	private RateContractMasterRepo rateContractMasterRepo;
	
	@Autowired
	private TransportMasterRepo  transportmasterrepo ;

	// ACCOUNT GROUP
	// saveAccountGroup
	@PostMapping("/saveAccountGroup")
	@ResponseBody
	public Response saveAccountGroup(@RequestBody AccountGroup accountGroup) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		AccountGroup accountGroup2 = otherMasterService.saveAccountGroup(accountGroup);
		if (accountGroup2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(accountGroup2);
		}
		return response;
	}

	// Retrieve Account Group All Data
	@GetMapping("/getAllAccountGroupData")
	@ResponseBody
	public List<AccountGroup> getAllAccountGroupData() {
		return otherMasterService.findAllAccountGroup();
	}

	// Delete Account Group By Id
	@PostMapping("/deleteAccountGroupById")
	@ResponseBody
	public ResponseEntity<String> deleteAccountGroupById(@RequestBody AccountGroup accountGroup) {
		int i = accountGroupRepo.deleteByid(accountGroup.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// SchemeMaster
	// saveSchemeMaster
	@PostMapping("/saveSchemeMaster")
	@ResponseBody
	public Response saveSchemeMaster(@RequestBody SchemeMaster schemeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		SchemeMaster schemeMaster2 = otherMasterService.saveSchemeMaster(schemeMaster);
		if (schemeMaster2 != null) {
			response.setStatus("Success");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(schemeMaster2);
		}
		return response;
	}

	// Retrieve SchemeMaster All Data
	@GetMapping("/getAllSchemeMasterData")
	@ResponseBody
	public List<SchemeMaster> getAllSchemeMasterData() {
		return otherMasterService.findAllSchemeMaster();
	}

	// Delete SchemeMaster by id
	@PostMapping("/deleteSchemeMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteSchemeMasterById(@RequestBody SchemeMaster schemeMaster) {
		int i = schemeMasterRepo.deleteByid(schemeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}

	}

	// getSchemeMasterbyDate
	@GetMapping("/getSchemeMasterbyDate")
	@ResponseBody
	public Response getSchememasterdataInList(@RequestBody SchemeMaster schememaster) {

		Response response = new Response();
		response.setStatus("Not Success");
		response.setMessage("Data Not Found");

		List<SchemeMaster> schememaster1 = schemeMasterRepo.findBydateBetween(schememaster.getfDate(),
				schememaster.gettDate());
		if (schememaster1 != null) {
			response.setStatus("Success");
			response.setMessage("Data Found..!!");
			response.setData(schememaster1);
		}
		return response;
	}

	// Scheme Master Data Export to excel
	@GetMapping("/getSchemeMasterExportToExcel")
	@ResponseBody
	public void getSchemeMasterExportToExcel(HttpServletResponse res) throws IOException {
		res.setContentType("application/octet-streram");
		String headerkey = "content-Dispotion";
		String headervalue = "attachment:filename=schememaster.xls";
		res.setHeader(headerkey, headervalue);
		otherMasterService.generateExcel(res);
	}

	// Department Master
	// save Department Master
	@PostMapping("/saveDepartmentMaster")
	@ResponseBody
	public Response saveDepartmentMaster(@RequestBody DepartmentMaster departmentMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		DepartmentMaster departmentMaster2 = otherMasterService.saveDepartmentMaster(departmentMaster);
		if (departmentMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(departmentMaster2);
		}
		return response;
	}

	// Retrieve Department Master All Data
	@GetMapping("/getAllDepartmentMasterData")
	@ResponseBody
	public List<DepartmentMaster> getAllDepartmentMasterData() {
		return otherMasterService.findAllDepartmentMaster();
	}

	// Delete Department Master By Id
	@PostMapping("/deleteDepartmentMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteDepartmentMasterById(@RequestBody DepartmentMaster departmentMaster) {
		int i = departmentMasterRepo.deleteByid(departmentMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Area Master
	// save Area Master
	@PostMapping("/saveAreaMaster")
	@ResponseBody
	public Response AreaMaster(@RequestBody AreaMaster areamaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		AreaMaster AreaMaster2 = otherMasterService.saveareaMaster(areamaster);
		if (AreaMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(AreaMaster2);
		}
		return response;
	}

	// Retrieve all data area master
	@GetMapping("/findallareadata")
	@ResponseBody
	public List<AreaMaster> findallData() {
		return otherMasterService.findAllData();
	}

	// Delete Data

	@PostMapping("/deletedata")
	@ResponseBody
	public ResponseEntity<String> deleteareamasterDataById(@RequestBody AreaMaster areaMaster) {
		int i = areamasterrepo.deleteByid(areaMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Delete Data Successfully");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");

		}

	}

	// Download Excel File//

	@GetMapping("/downloadAreamasterExcelfile")
	@ResponseBody
	public void generateAreaMasterExcelReport(HttpServletResponse res1) throws IOException {
		res1.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=areamaster.xls";
		res1.setHeader(headerkey, headervalue);
		otherMasterService.generateExcelFile(res1);
	}

	// Route Master
	// save Route Master
	@PostMapping("/saveRouteMaster")
	@ResponseBody
	public Response saveRouteMaster(@RequestBody RouteMaster routeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		RouteMaster routeMaster2 = otherMasterService.saveRouteMaster(routeMaster);
		if (routeMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(routeMaster2);
		}
		return response;
	}

	// Retrieve Route Master All Data
	@GetMapping("/getAllRouteMasterData")
	@ResponseBody
	public List<RouteMaster> getAllRouteMasterData() {
		return otherMasterService.findAllRouteMaster();
	}

	// Delete Route Master By Id
	@PostMapping("/deleteRouteMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteRouteMasterById(@RequestBody RouteMaster routeMaster) {
		int i = routeMasterRepo.deleteByid(routeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Unit Master
	// save Route Master
	@PostMapping("/saveUnitMaster")
	@ResponseBody
	public Response saveUnitMaster(@RequestBody UnitMaster unitMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		UnitMaster unitMaster2 = otherMasterService.saveUnitMaster(unitMaster);
		if (unitMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(unitMaster2);
		}
		return response;
	}

	// Retrieve Unit Master All Data
	@GetMapping("/getAllUnitMasterData")
	@ResponseBody
	public List<UnitMaster> getAllUnitMasterData() {
		return otherMasterService.findAllUnitMaster();
	}

	// Delete Unit Master By Id
	@PostMapping("/deleteUnitMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteUnitMasterById(@RequestBody UnitMaster unitMaster) {
		int i = unitMasterRepo.deleteByid(unitMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Save Warehouse Master Data
	@PostMapping("/savewarehousemaster")
	@ResponseBody
	public Response saveWarehouseMaster(@RequestBody WarehouseMaster warehousemaster) {
		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		WarehouseMaster warehousemaster2 = otherMasterService.saveWarehouseMaster(warehousemaster);
		if (warehousemaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data saved Successfully");
			response.setData(warehousemaster2);
		}
		return response;
	}

	// Retrieve All Warehouse Master Data
	@GetMapping("/getallwarehousemasterdetails")
	@ResponseBody
	public List<WarehouseMaster> getAllWarehouseMasterDetails() {
		return otherMasterService.findAllWarehouseMasterDetails();
	}

	// Delete Data By Id
	@PostMapping("/deletewarehousemasterbyid")
	@ResponseBody
	public ResponseEntity<String> deleteWarehouseMasterBywarehouseid(@RequestBody WarehouseMaster warehousemaster) {

		int i = warehouseMasterRepo.deleteByid(warehousemaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Export Warehouse Master Data To Excel
	@GetMapping("/warehouseMasterExcel")
	public void generateExcelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:MM:SS");
		String currentDate = dateFormat.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=WarehouseMaster" + currentDate + ".xls";
		response.setHeader(headerKey, headerValue);

		otherMasterService.generateExcelWarehouse(response);
	}

	// Reorder Master
	// save Reorder Master
	@PostMapping("/saveReorderMaster")
	@ResponseBody
	public Response saveReorderMaster(@RequestBody ReorderMaster reorderMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		ReorderMaster reorderMaster2 = otherMasterService.saveReorderMaster(reorderMaster);
		if (reorderMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(reorderMaster2);
		}
		return response;
	}

	// Retrieve Reorder Master All Data
	@GetMapping("/getAllReorderMasterData")
	@ResponseBody
	public List<ReorderMaster> getAllReorderMasterData() {
		return otherMasterService.findAllReorderMaster();
	}

	// Delete Reorder Master By Id
	@PostMapping("/deleteReorderMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteReorderMasterById(@RequestBody ReorderMaster reorderMaster) {
		int i = reorderMasterRepo.deleteByid(reorderMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Vehicle Master
	// Save Vehicle Master Data
	@PostMapping("/saveVehiclemaster")
	@ResponseBody
	public Response saveVehicleMaster(@RequestBody VehicleMaster vehiclemaster) {
		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!");

		VehicleMaster vehiclemaster2 = otherMasterService.saveVehicleMaster(vehiclemaster);
		if (vehiclemaster2 != null)

			response.setStatus("Success..");
		response.setMessage("Data Saved...!");
		response.setData(vehiclemaster2);
		return response;
	}

	// Retrieve Vehicle Master Data
	@GetMapping("/getAllVehicleMasterData")
	@ResponseBody
	public List<VehicleMaster> getAllVehicleMasterData() {
		return otherMasterService.getAllVehicledata();
	}

	// Delete Vehicle Master Data by Id
	@PostMapping("/deleteVehicleMasterbyId")
	@ResponseBody
	public ResponseEntity<String> deleteVehicleMasterById(@RequestBody VehicleMaster vehicleMaster) {
		int i = vehiclemasterrepo.deleteByid(vehicleMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Fail To Delete...!!");
		}
	}

	// Export to Excel
	@GetMapping("/vehicleMasterExportToExcel")
	public void exportToExcle(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = Vehicle_Master_" + currentDateTime + ".xls";
		response.setHeader(headerKey, headerValue);
		otherMasterService.vehicleMasterGetExcelFile(response);
	}

	// Update vehical Master
	@PostMapping("/updateVehicleMaster")
	@ResponseBody
	public VehicleMaster updateVehicleMaster(@RequestBody VehicleMaster vehicleMaster) {
		otherMasterService.updateVehicleMaster(vehicleMaster, vehicleMaster.getId());
		return vehicleMaster;

	}

	// Purchase Payment Terms and Condition
	// save Purchase Payment
	@PostMapping("/savePurchasePayment")
	@ResponseBody
	public Response savePurchasePayment(
		@RequestBody PurchasePaymentTermsandCondition purchasePaymentTermsandCondition) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		PurchasePaymentTermsandCondition pCondition = otherMasterService
				.savePurchasePayment(purchasePaymentTermsandCondition);
		if (pCondition != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(pCondition);
		}
		return response;
	}

	// Retrieve Purchase Payment Terms and Condition All Data
	@GetMapping("/getAllPurchasePaymentData")
	@ResponseBody
	public List<PurchasePaymentTermsandCondition> getAllPurchasePaymentData() {
		return otherMasterService.findAllPurchasePayment();
	}

	// Delete Purchase Payment Terms and Condition By Id
	@PostMapping("/deletePurchasePaymentById")
	@ResponseBody
	public ResponseEntity<String> deletePurchasePaymentById(
			@RequestBody PurchasePaymentTermsandCondition purchasePayment) {
		int i = purchasePaymentRepo.deleteByid(purchasePayment.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Rate Contract Master
	// saveRateContract
	@PostMapping("/saveRateContractMaster")
	@ResponseBody
	public Response saveRateContractMaster(@RequestBody RateContractMaster rateContractMaster){
		
		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");
		
		RateContractMaster rateContractMaster2 = otherMasterService.saveRateContractMaster(rateContractMaster);
		if(rateContractMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(rateContractMaster2);
		}
		return response;
	}

	// Retrieve Rate Contract All Data
	@GetMapping("/getAllRateContractData")
	@ResponseBody
	public List<RateContractMaster> getAllRateContractMasterData() {
		return otherMasterService.findAllRateContract();
	}

	// Delete Rate Contract Master By Id
	@PostMapping("/deleteRateContractById")
	@ResponseBody
	public ResponseEntity<String> deleteRateContractMasterById(@RequestBody RateContractMaster rateContractMaster) {
		int i = rateContractMasterRepo.deleteByid(rateContractMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Transport Master
	// Save Transport Master
	@PostMapping("/saveTransportMaster")
	@ResponseBody
	public Response saveTransportMaster(@RequestBody TransportMaster transportmaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		TransportMaster transportmaster1 = otherMasterService.saveTransportMaster(transportmaster);
		if (transportmaster1 != null) {
			response.setStatus("Success..");
			response.setMessage("Data Saved Successfully..!!");
			response.setData(transportmaster1);
		}
		return response;
	}

	// Retrieve Transport Master Data
	@GetMapping("/gettransportMasterData")
	@ResponseBody
	public List<TransportMaster> getTransportMasterData() {
		return otherMasterService.getAllTransportMasterData1();
	}

	// Delete Transport Master Data
	@PostMapping("/deleteTransportMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteTransportMasterById(@RequestBody TransportMaster transportmaster) {
		int i = transportmasterrepo.deleteByid(transportmaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

	// Export Transport Master Data To Excel
	@GetMapping("/TransportMasterExcel")
	public void generateExcelTransport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:MM:SS");
		String currentDate = dateFormat.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment;filename=TransportMaster" + currentDate + ".xls";
		response.setHeader(headerKey, headerValue);

		otherMasterService.generateExcel(response);
	}

}
