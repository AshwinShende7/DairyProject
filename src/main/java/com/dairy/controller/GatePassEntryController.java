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
import com.dairy.model.gatePassEntry.EmployeeEntryInMaster;
import com.dairy.model.gatePassEntry.EmployeeOutEntryMaster;
import com.dairy.model.gatePassEntry.GuestInEntryMaster;
import com.dairy.model.gatePassEntry.GuestOutEntryMaster;
import com.dairy.model.gatePassEntry.Material1stEntryMaster;
import com.dairy.model.gatePassEntry.Material2ndEntryMaster;
import com.dairy.model.gatePassEntry.MilkVehicleInMaster;
import com.dairy.model.gatePassEntry.MilkVehicleOutMaster;
import com.dairy.model.gatePassEntry.OtherVehicleInMaster;
import com.dairy.model.gatePassEntry.OtherVehicleOutMaster;
import com.dairy.repository.gatePassEntry.EmployeeEntryInRepo;
import com.dairy.repository.gatePassEntry.EmployeeOutEntryRepo;
import com.dairy.repository.gatePassEntry.GuestEntryInRepo;
import com.dairy.repository.gatePassEntry.GuestOutEntryRepo;
import com.dairy.repository.gatePassEntry.Material1stEntryRepo;
import com.dairy.repository.gatePassEntry.Material2ndEntryRepo;
import com.dairy.repository.gatePassEntry.MilkVehicleInRepo;
import com.dairy.repository.gatePassEntry.MilkVehicleOutRepo;
import com.dairy.repository.gatePassEntry.OtherVehicleInRepo;
import com.dairy.repository.gatePassEntry.OtherVehicleOutRepo;
import com.dairy.service.GatePassEntryService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class GatePassEntryController {

	@Autowired
	private GatePassEntryService gatePassEntryService;

	@Autowired
	private EmployeeEntryInRepo employeeEntryInRepo;

	@Autowired
	private EmployeeOutEntryRepo employeeOutEntryRepo;

	@Autowired
	private GuestEntryInRepo guestEntryInRepo;

	@Autowired
	private GuestOutEntryRepo guestOutEntryRepo;

	@Autowired
	private OtherVehicleInRepo otherVehicleInRepo;

	@Autowired
	private OtherVehicleOutRepo otherVehicleOutRepo;

	@Autowired
	private MilkVehicleInRepo milkVehicleInRepo;

	@Autowired
	private MilkVehicleOutRepo milkVehicleOutRepo;

	@Autowired
	private Material1stEntryRepo material1stEntryRepo;

	@Autowired
	private Material2ndEntryRepo material2ndEntryRepo;

	// Employee Entry In
	// save
	@PostMapping("/saveEmployeeEntryIn")
	@ResponseBody

	public Response saveEmployeeEntryIn(@RequestBody EmployeeEntryInMaster employeeEntryInMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");
		EmployeeEntryInMaster emp = gatePassEntryService.saveEmployeeEntryIn(employeeEntryInMaster);

		if (emp != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(emp);
		}
		return resp;

	}

	// find all data
	@GetMapping("/findALLEmployeeEntryInData")
	@ResponseBody
	public List<EmployeeEntryInMaster> findALLEmployeeEntryInData() {

		return gatePassEntryService.findALLEmployeeEntryInData();
	}

	// delete by id
	@PostMapping("/deleteByidEmployeeEntryDataIn")
	@ResponseBody
	public ResponseEntity<String> deleteByidVehicleQcEntry(@RequestBody EmployeeEntryInMaster employeeEntryInMaster) {

		int i = employeeEntryInRepo.deleteByid(employeeEntryInMaster.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// Employee Out Entry
	// save
	@PostMapping("/saveEmployeeOutEntry")
	@ResponseBody
	public Response saveEmployeeOutEntry(@RequestBody EmployeeOutEntryMaster employeeOutEntryMaster) {
		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved...!!");

		EmployeeOutEntryMaster empout = gatePassEntryService.saveEmployeeOutEntry(employeeOutEntryMaster);

		if (empout != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(empout);
		}
		return resp;
	}

	// find all data
	@GetMapping("/findAllEmployeeOutEntry")
	@ResponseBody
	public List<EmployeeOutEntryMaster> findAllEmployeeOutEntry() {

		return gatePassEntryService.findAllEmployeeOutEntry();
	}

	// delete By id
	@PostMapping("/deleteByidEmployeeOutEntry")
	@ResponseBody
	public ResponseEntity<String> deleteByidEmployeeOutEntry(
			@RequestBody EmployeeOutEntryMaster employeeOutEntryMaster) {
		int i = employeeOutEntryRepo.deleteByid(employeeOutEntryMaster.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}

	}

	// Guest Entry In
	// save
	@PostMapping("/saveGuestEntryIn")
	@ResponseBody

	public Response saveGuestEntryIn(@RequestBody GuestInEntryMaster guestInEntryMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");
		GuestInEntryMaster emp = gatePassEntryService.saveGuestEntryIn(guestInEntryMaster);

		if (emp != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(emp);
		}
		return resp;

	}

	// find all data
	@GetMapping("/findALLGuestEntryInData")
	@ResponseBody

	public List<GuestInEntryMaster> findALLGuestEntryInData() {

		return gatePassEntryService.findALLGuestEntryInData();
	}

	// delete by id
	@PostMapping("/deleteByidGuestEntryDataIn")
	@ResponseBody

	public ResponseEntity<String> deleteByidGuestEntryDataIn(@RequestBody GuestInEntryMaster guestInEntryMaster) {

		int i = guestEntryInRepo.deleteByid(guestInEntryMaster.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// Guest Out Entry
	// save
	@PostMapping("/saveGuestOutEntry")
	@ResponseBody

	public Response saveGuestOutEntry(@RequestBody GuestOutEntryMaster guestOutEntryMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");
		GuestOutEntryMaster empout = gatePassEntryService.saveGuestOutEntry(guestOutEntryMaster);

		if (empout != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(empout);
		}
		return resp;

	}

	// find all data
	@GetMapping("/findALLGuestOutEntry")
	@ResponseBody
	public List<GuestOutEntryMaster> findALLGuestOutEntryData() {

		return gatePassEntryService.findALLGuestOutEntryData();
	}

	// delete by id
	@PostMapping("/deleteByidGuestOutEntry")
	@ResponseBody
	public ResponseEntity<String> deleteByidGuestOutEntry(@RequestBody GuestOutEntryMaster guestOutEntryMaster) {

		int i = guestOutEntryRepo.deleteByid(guestOutEntryMaster.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully....");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// Other Vehicle In
	// save
	@PostMapping("/saveotherVehicleIn")
	@ResponseBody
	public Response saveotherVehicleIn(@RequestBody OtherVehicleInMaster otherVehicleInMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		OtherVehicleInMaster ovinm = gatePassEntryService.saveotherVehicleIn(otherVehicleInMaster);

		if (ovinm != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(ovinm);
		}
		return resp;
	}

	// find all
	@GetMapping("/findAllOtherVehicleInData")
	@ResponseBody
	public List<OtherVehicleInMaster> findAllOtherVehicleInData() {
		return gatePassEntryService.findAllOtherVehicleInData();
	}

	// delete by id
	@PostMapping("/deleteByIdOtherVhicleInData")
	@ResponseBody
	public ResponseEntity<String> deleteByIdOtherVhicleInData(@RequestBody OtherVehicleInMaster otherVehicleInMaster) {

		int i = otherVehicleInRepo.deleteByid(otherVehicleInMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// export to excel
	@GetMapping("/exportOtherVehicleInData")
	public void exportOtherVehicleInData(HttpServletResponse resp) throws IOException {
		resp.setContentType("application/octet-stream");
		String headerkey = "Content-Disposition";
		String headervalue = "attachment;filename=OtherVehicleIn.xls";
		resp.setHeader(headerkey, headervalue);
		gatePassEntryService.genrateOtherVehicleInDataexcelsheet(resp);
	}
	
	// Other Vehicle Out
	// save
	@PostMapping("/saveOtherVehicleOut")
	@ResponseBody
	public Response saveOtherVehicleOut(@RequestBody OtherVehicleOutMaster otherVehicleOutMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		OtherVehicleOutMaster ovom = gatePassEntryService.saveOtherVehicleOut(otherVehicleOutMaster);

		if (ovom != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(ovom);
		}
		return resp;
	}

	// find all data
	@GetMapping("/findAllOtherVehicleOutData")
	@ResponseBody
	public List<OtherVehicleOutMaster> findAllOtherVehicleOutData() {
		return gatePassEntryService.findAllOtherVehicleOutData();
	}

	// delete by id
	@PostMapping("/deleteByIdOtherVehicleOutData")
	@ResponseBody
	public ResponseEntity<String> deleteByIdOtherVehicleOutData(
			@RequestBody OtherVehicleOutMaster otherVehicleOutMaster) {

		int i = otherVehicleOutRepo.deleteByid(otherVehicleOutMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}
	
	// Milk Vehicle In
	// save
	@PostMapping("/saveMilkVehicleInData")
	@ResponseBody
	public Response saveMilkVehicleInData(@RequestBody MilkVehicleInMaster milkVehicleInMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		MilkVehicleInMaster mvinm = gatePassEntryService.saveMilkVehicleInData(milkVehicleInMaster);
		if (mvinm != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(mvinm);
		}
		return resp;
	}

	// find all
	@GetMapping("/findAllMilkVehicleInData")
	@ResponseBody

	public List<MilkVehicleInMaster> findAllMilkVehicleInData() {
		return gatePassEntryService.findAllMilkVehicleInData();
	}

	// delete by id
	@PostMapping("/deleteByIdMilkVehicleInData")
	@ResponseBody

	public ResponseEntity<String> deleteByIdOtherVehicleOutData(@RequestBody MilkVehicleInMaster milkVehicleInMaster) {

		int i = milkVehicleInRepo.deleteByid(milkVehicleInMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}

	// Milk Vehicle Out
	// save
	@PostMapping("/saveMilkVehicleOutData")
	@ResponseBody
	public Response saveMilkVehicleOutData(@RequestBody MilkVehicleOutMaster milkVehicleOutMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		MilkVehicleOutMaster mvom = gatePassEntryService.saveMilkVehicleOutData(milkVehicleOutMaster);
		if (mvom != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(mvom);
		}
		return resp;
	}

	// find all
	@GetMapping("/findAllMilkVehicleOutData")
	@ResponseBody
	public List<MilkVehicleOutMaster> findAllMilkVehicleOutData() {
		return gatePassEntryService.findAllMilkVehicleOutData();
	}

	// delete by id
	@PostMapping("/deleteByIdMilkVehicleOutData")
	@ResponseBody
	public ResponseEntity<String> deleteByIdMilkVehicleOutData(@RequestBody MilkVehicleOutMaster milkVehicleOutMaster) {

		int i = milkVehicleOutRepo.deleteByid(milkVehicleOutMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}
	
	// Material 1st Entry
	// save
	@PostMapping("/saveMaterial1stEntry")
	@ResponseBody
	public Response saveMaterial1stEntry(@RequestBody Material1stEntryMaster material1stEntryMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		Material1stEntryMaster mvom = gatePassEntryService.saveMaterial1stEntry(material1stEntryMaster);
		if (mvom != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(mvom);
		}
		return resp;
	}

	// find all
	@GetMapping("/findAllMaterial1stEntry")
	@ResponseBody
	public List<Material1stEntryMaster> findAllMaterial1stEntry() {
		return gatePassEntryService.findAllMaterial1stEntry();
	}

	// delete by id
	@PostMapping("/deleteByIdMaterial1stEntryData")
	@ResponseBody
	public ResponseEntity<String> deleteByIdMaterial1stEntryData(
			@RequestBody Material1stEntryMaster material1stEntryMaster) {

		int i = material1stEntryRepo.deleteByid(material1stEntryMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}
	
	// Material 2nd Entry
	// save
	@PostMapping("/saveMaterial2ndEntry")
	@ResponseBody
	public Response saveMaterial2ndEntry(@RequestBody Material2ndEntryMaster material2ndEntryMaster) {

		Response resp = new Response();
		resp.setStatus("Not Success..");
		resp.setMessage("Data Not Saved..!!");

		Material2ndEntryMaster msecentry = gatePassEntryService.saveMaterial2ndEntry(material2ndEntryMaster);
		if (msecentry != null) {
			resp.setStatus("Success..");
			resp.setMessage("Data Saved..!!");
			resp.setData(msecentry);
		}
		return resp;
	}

	// find all
	@GetMapping("/findAllMaterial2ndEntry")
	@ResponseBody
	public List<Material2ndEntryMaster> findAllMaterial2ndEntry() {
		return gatePassEntryService.findAllMaterial2ndEntry();
	}

	// delete by id
	@PostMapping("/deleteByIdMaterial2ndEntryData")
	@ResponseBody
	public ResponseEntity<String> deleteByIdMaterial2ndEntryData(
			@RequestBody Material2ndEntryMaster material2ndEntryMaster) {

		int i = material2ndEntryRepo.deleteByid(material2ndEntryMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data deleted successfully...");
		} else {
			return ResponseEntity.badRequest().body("Failed to delete data....");
		}
	}
	
}
