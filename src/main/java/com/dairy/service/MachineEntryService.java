package com.dairy.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.model.machineEntry.MachineJobCard;
import com.dairy.model.machineEntry.MachineMaster;
import com.dairy.model.machineEntry.MachineScheduler;
import com.dairy.model.machineEntry.ManualJobWorkCard;

import com.dairy.repository.machineEntry.MachineEntryRepo;
import com.dairy.repository.machineEntry.MachineJobCardRepo;
import com.dairy.repository.machineEntry.MachineScheduleRepo;
import com.dairy.repository.machineEntry.ManualJobWorkCardRepo;



@Service
public class MachineEntryService 
{
	@Autowired 
	private MachineEntryRepo machineEntryRepo;
	
	@Autowired
	private MachineScheduleRepo machineScheduleRepo ;
	
	@Autowired
	private ManualJobWorkCardRepo manualJobWorkCardRepo;
	
	@Autowired
	private MachineJobCardRepo machineJobCardRepo;
	
	

	//Save Machine Master
	public MachineMaster saveMachineMaster(MachineMaster machineMaster) 
	{
		return machineEntryRepo.save(machineMaster);
		
	}

	//Get Machine Master
	public List<MachineMaster> getAllMachineMasterdata() {
		return machineEntryRepo.findAll();
		
	}
	
	//Machine Sheduler
	//Save Machine Shedular
	public MachineScheduler saveMachineSchedule(MachineScheduler machineScheduler) {
		return machineScheduleRepo.save(machineScheduler);
	}

	//Get Machine Shedular
	public List<MachineScheduler> getAllMachineSchedulers() {
		return machineScheduleRepo.findAll();
	}

	//Export to Excke Machine Shedular
	  public void machineScheduleGetExcelFile(HttpServletResponse response) throws
	  IOException {
	  
	  List<MachineScheduler> listMachineSchedular= machineScheduleRepo.findAll();
	  
	  
	  HSSFWorkbook workbook =new HSSFWorkbook(); HSSFSheet sheet
	  =workbook.createSheet("Machine_Shedule"); HSSFRow row= sheet.createRow(0);
	  
	  row.createCell(0).setCellValue("Id");
	  row.createCell(1).setCellValue("Machine ID");
	  row.createCell(2).setCellValue("Machine Name");
	  row.createCell(3).setCellValue("Machine Code");
	  row.createCell(4).setCellValue("Make ");
	  row.createCell(5).setCellValue("Department");
	  row.createCell(6).setCellValue("Per Hour Capacity");
	  row.createCell(7).setCellValue("Shedule Details");
	  row.createCell(8).setCellValue("Start Date");
	  row.createCell(9).setCellValue("End Date");
	  row.createCell(10).setCellValue("Repeat");
	  
	  
	  int dataRowIndex =1; for(MachineScheduler machineSchedule :
	  listMachineSchedular) { HSSFRow dataRow = sheet.createRow(dataRowIndex);
	  dataRow.createCell(0).setCellValue(machineSchedule.getId());
	  dataRow.createCell(1).setCellValue(machineSchedule.getMachineId());
	  dataRow.createCell(2).setCellValue(machineSchedule.getMachineName());
	  dataRow.createCell(3).setCellValue(machineSchedule.getMachineCode());
	  dataRow.createCell(4).setCellValue(machineSchedule.getMake());
	  dataRow.createCell(5).setCellValue(machineSchedule.getDepartment());
	  dataRow.createCell(6).setCellValue(machineSchedule.getPerHourCapacity());
	  dataRow.createCell(7).setCellValue(machineSchedule.getScheduleDetails());
	  dataRow.createCell(8).setCellValue(machineSchedule.getStartDate());
	  dataRow.createCell(9).setCellValue(machineSchedule.getEndDate());
	  dataRow.createCell(10).setCellValue(machineSchedule.getRepeat1());
	  
	  dataRowIndex ++;
	  
	  
	  } ServletOutputStream outputStream =response.getOutputStream();
	  workbook.write(outputStream); workbook.close(); outputStream.close();
	  
	  
	  }
	


	public ManualJobWorkCard saveManualJobWorkCard(ManualJobWorkCard manualJobWorkCard) {
		return manualJobWorkCardRepo.save(manualJobWorkCard);
	}

	public List<ManualJobWorkCard> getallManualJobWorkCard() {
		return manualJobWorkCardRepo.findAll();
	}

	public MachineJobCard saveMachineJobCard(MachineJobCard machineJobCard) {
		return machineJobCardRepo.save(machineJobCard);
	}

	public List<MachineJobCard> getallMachineJobCard() {
		return machineJobCardRepo.findAll();
	}

	public void machineJobCardGetExcelFile(HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	List<MachineJobCard> listMachineJobCard= machineJobCardRepo.findAll();
		
			
			HSSFWorkbook workbook =new HSSFWorkbook();
			HSSFSheet sheet =workbook.createSheet("Vehicel Master");
			HSSFRow row= sheet.createRow(0);
			
			row.createCell(0).setCellValue("Id");
			row.createCell(1).setCellValue("Machine ID");
			row.createCell(2).setCellValue("Machine Name");
			row.createCell(3).setCellValue("Machine Code");
			row.createCell(4).setCellValue("Machine use ");
			row.createCell(5).setCellValue("Job Type");
			row.createCell(6).setCellValue("Job Details");
			row.createCell(7).setCellValue("Date");
			row.createCell(8).setCellValue("Job Done By");
			
			
			
			int  dataRowIndex =1;
			for(MachineJobCard machineJobCard : listMachineJobCard)
			{
				HSSFRow dataRow = sheet.createRow(dataRowIndex);
				dataRow.createCell(0).setCellValue(machineJobCard.getId());
				dataRow.createCell(1).setCellValue(machineJobCard.getMachineId());
				dataRow.createCell(2).setCellValue(machineJobCard.getMachineName());
				dataRow.createCell(3).setCellValue(machineJobCard.getMachineCode());
				dataRow.createCell(4).setCellValue(machineJobCard.getMachineUse());
				dataRow.createCell(5).setCellValue(machineJobCard.getJobType());
				dataRow.createCell(6).setCellValue(machineJobCard.getJobDetails());
				dataRow.createCell(7).setCellValue(machineJobCard.getDate());
				dataRow.createCell(8).setCellValue(machineJobCard.getJobDoneBy());
				
				
				dataRowIndex ++;
				
				
			}
			ServletOutputStream outputStream =response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			
		}

//	public List<MachineScheduler> deleteschedule(int id, MachineScheduler machineScheduler) {
//		List<MachineScheduler> list= msceRepo.deleteById(machineScheduler.getId());
//		return null;
//	}

//	public List<MachineScheduler> deleteschedule(int i,MachineScheduler machineScheduler) {
//		// TODO Auto-generated method stub
//		List<MachineScheduler> list = msceRepo.deleteById(machineScheduler.getId());
//		return list;
//	}
	
	
	
	
	
	

}
