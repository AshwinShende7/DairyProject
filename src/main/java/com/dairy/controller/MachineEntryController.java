package com.dairy.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.model.machineEntry.MachineJobCard;
import com.dairy.model.machineEntry.MachineMaster;
import com.dairy.model.machineEntry.MachineScheduler;
import com.dairy.model.machineEntry.ManualJobWorkCard;

import com.dairy.repository.machineEntry.MachineEntryRepo;
import com.dairy.repository.machineEntry.MachineJobCardRepo;
import com.dairy.repository.machineEntry.MachineScheduleRepo;
import com.dairy.repository.machineEntry.ManualJobWorkCardRepo;
import com.dairy.service.MachineEntryService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
public class MachineEntryController {
	@Autowired
	private MachineEntryService machineEntryService;

	@Autowired
	private MachineEntryRepo machineEntryRepo;
	
	@Autowired
	private MachineScheduleRepo machineScheduleRepo ;

	@Autowired
	private ManualJobWorkCardRepo manualJobWorkCardRepo;

	@Autowired
	private MachineJobCardRepo machineJobCardRepo;

	
    //Machine Master
	// Save Machine Master
	@PostMapping("/saveMachineMaster")
	@ResponseBody
	public Response saveMachineMaster(@RequestBody MachineMaster machineMaster,HttpSession session) {
		Response response = new Response();
		response.setStatus("not success..!");
		response.setMessage("Data not Save Successfully..!");
		String id=session.getAttribute("ID").toString();
		System.out.println("print value of login session id"+id);
	     machineMaster.setCreatedBy(id);
		MachineMaster machineMaster1 = machineEntryService.saveMachineMaster(machineMaster);

		if (machineMaster != null)
			response.setStatus("Success..!");
		response.setMessage("Data Save Sucessfully");
		response.setData(machineMaster1);
     
		return response;
	}
	
	
//	@PostMapping("/saveMachineMaster")
//	@ResponseBody
//	public Response saveMachineMaster(@RequestBody MachineMaster machineMaster, HttpSession session) {
//	    Response response = new Response();
//	    String loginId = (String) session.getAttribute("ID");
//        machineMaster.setCreatedBy(loginId);
//	    if (loginId == null) {
//	        response.setStatus("Error");
//	        response.setMessage("Login ID not found in the session.");
//	        return response;
//	    }
//
//	    machineMaster.setCreatedBy(loginId);
//
//	    try {
//	        MachineMaster savedMachineMaster = machineEntryService.saveMachineMaster(machineMaster);
//	        response.setStatus("Success");
//	        response.setMessage("Data saved successfully.");
//	        response.setData(savedMachineMaster);
//	    } catch (Exception e) {
//	        response.setStatus("Error");
//	        response.setMessage("Failed to save data.");
//	        // Optionally, you can log the exception for debugging purposes
//	        e.printStackTrace();
//	    }
//
//	    return response;
//	}


	// Get Machine master Data
	@GetMapping("/getMachineMasterdata")
	@ResponseBody
	public List<MachineMaster> getAllMachineMasterData() {
		return machineEntryService.getAllMachineMasterdata();
	}

	// Delete Master Data by ID
	@PostMapping("deleteMachineMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteMachineMasterById(@RequestBody MachineMaster machineMaster) {
		int i = machineEntryRepo.deleteByid(machineMaster.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Fail To Delete...!!");
		}
	}

	//MachineShedeule
	//save Machine Schedeular

	@PostMapping("/saveMachineSchedule")
	@ResponseBody
	public Response saveMachineSchedule(@RequestBody MachineScheduler machineScheduler) {
		Response response = new Response();
		response.setStatus("not success..!");
		response.setMessage("Data not Save Successfully..!");

		MachineScheduler machineScheduler1 = machineEntryService.saveMachineSchedule(machineScheduler);

		if (machineScheduler1 != null)
			response.setStatus("Success..!");
		response.setMessage("Data Save Sucessfully");
		response.setData(machineScheduler1);

		return response;
	}

	// Retrive Machine Schedeular

	@GetMapping("/getMachineScheduledata")
	@ResponseBody
	public List<MachineScheduler> getAllMachineSchedulers() {
		return machineEntryService.getAllMachineSchedulers();
	}

//	// Delete Machine Schedule Data by ID
//	@PostMapping("deleteMachineScheduledataById")
//	@ResponseBody
//	public ResponseEntity<String> deleteMachineScheduledataById(@RequestBody MachineScheduler machineScheduler) {
//		int i = msceRepo.deleteByid(machineScheduler.getId());
//
//		if (i > 0) {
//			return ResponseEntity.ok("Data Deleted Sucessfully..!");
//		} else {
//			return ResponseEntity.badRequest().body("Fail To Delete...!!");
//		}
//	}

	// Export to Excel
	@GetMapping("/machineScheduleExportToExcel")
	public void exportToExcle(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = Machine_Schedule" + currentDateTime + ".xls";
		response.setHeader(headerKey, headerValue);
		machineEntryService.machineScheduleGetExcelFile(response);
	}

	// Manual Job Work Card
	// save Manual job Work card

	@PostMapping("/saveManualJobWorkCard")
	@ResponseBody
	public Response saveManualJobWorkCard(@RequestBody ManualJobWorkCard manualJobWorkCard) {
		Response response = new Response();
		response.setStatus("not success..!");
		response.setMessage("Data not Save Successfully..!");

		ManualJobWorkCard manualJobWorkCard1 = machineEntryService.saveManualJobWorkCard(manualJobWorkCard);

		if (manualJobWorkCard1 != null)
			response.setStatus("Success..!");
		response.setMessage("Data Save Sucessfully");
		response.setData(manualJobWorkCard1);

		return response;
	}

	// Retrive Manual job Work card
	@GetMapping("/getallManualJobWorkCard")
	@ResponseBody
	public List<ManualJobWorkCard> getallManualJobWorkCard() {
		return machineEntryService.getallManualJobWorkCard();
	}

	// Delete Manual job Work card
	@PostMapping("deleteManualJobWorkCardById")
	@ResponseBody
	public ResponseEntity<String> deleteManualJobWorkCardById(@RequestBody ManualJobWorkCard manualJobWorkCard) {
		int i = manualJobWorkCardRepo.deleteByid(manualJobWorkCard.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Fail To Delete...!!");
		}
	}

//--Machine Job Card

	// save Machine Job Card

	@PostMapping("/saveMachineJobCard")
	@ResponseBody
	public Response saveMachineJobCard(@RequestBody MachineJobCard machineJobCard) {
		Response response = new Response();
		response.setStatus("not success..!");
		response.setMessage("Data not Save Successfully..!");

		MachineJobCard machineJobCard2 = machineEntryService.saveMachineJobCard(machineJobCard);

		if (machineJobCard2 != null)
			response.setStatus("Success..!");
		response.setMessage("Data Save Sucessfully");
		response.setData(machineJobCard2);

		return response;
	}

	// Retrive Machine Job card
	@GetMapping("/getallMachineJobCard")
	@ResponseBody
	public List<MachineJobCard> getallMachineJobCard() {
		return machineEntryService.getallMachineJobCard();
	}

	// Delete Machine job card
	@PostMapping("deleteMachineJobCardById")
	@ResponseBody
	public ResponseEntity<String> deleteMachineJobCardById(@RequestBody MachineJobCard machineJobCard) {
		int i = machineJobCardRepo.deleteByid(machineJobCard.getId());

		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Sucessfully..!");
		} else {
			return ResponseEntity.badRequest().body("Fail To Delete...!!");
		}
	}

	// Export to Excel Machine Job Card
	@GetMapping("/machineJobCardExportToExcel")
	public void machineJobCardExportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename = Machine_job_Card" + currentDateTime + ".xls";
		response.setHeader(headerKey, headerValue);
		machineEntryService.machineJobCardGetExcelFile(response);
	}

}
