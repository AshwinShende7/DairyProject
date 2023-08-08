package com.dairy.service;


import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dairy.model.processManagement.RPTProcessMilkJournalReportMaster;
import com.dairy.model.processManagement.RptProcessByProductJournalReport;
import com.dairy.model.processManagement.RptProcessCreamReport;
import com.dairy.repository.processManagement.RPTProcessMilkJournalReportRepo;
import com.dairy.repository.processManagement.RptProcessByProductJournalReportRepo;
import com.dairy.repository.processManagement.RptProcessCreamReportRepo;



@Service
public class ProcessReportManagementService {

	@Autowired
	private RPTProcessMilkJournalReportRepo rRPTProcessMilkJournalReportRepo;
	
	@Autowired
	private RptProcessCreamReportRepo rptProcessCreamReportRepo ;
	
	@Autowired
	private RptProcessByProductJournalReportRepo rptProcessByProductJournalReportRepo ;

	public RPTProcessMilkJournalReportMaster save(RPTProcessMilkJournalReportMaster rPTProcessMilkJournalReportMaster) {
		return rRPTProcessMilkJournalReportRepo.save(rPTProcessMilkJournalReportMaster);
	}

	public List<RPTProcessMilkJournalReportMaster> getAllRPTProcessMilkJournalReport() {
		return rRPTProcessMilkJournalReportRepo.findAll();
	}

	public List<RPTProcessMilkJournalReportMaster> findByDateBetween(String getfDate, String gettDate) {
		return rRPTProcessMilkJournalReportRepo.findByDateBetween(getfDate, gettDate);
	}

	public void generateExcel(HttpServletResponse res) throws Exception {
		List<RPTProcessMilkJournalReportMaster> rPTProcessMilkJournalReportMaster2=rRPTProcessMilkJournalReportRepo.findAll();
		HSSFWorkbook workbook=new HSSFWorkbook();
		HSSFSheet sheet=workbook.createSheet("Milk GRN");
		HSSFRow row=sheet.createRow(0);
		
		
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Date");
		row.createCell(2).setCellValue("Type Of Process");
		row.createCell(3).setCellValue("Out Warehouse");
		row.createCell(4).setCellValue("Milk Item Name");
		row.createCell(5).setCellValue("Product Item Warehouse");
		row.createCell(6).setCellValue("Product Item Name");
		row.createCell(7).setCellValue("SMP Item Warehouse");
		row.createCell(8).setCellValue("Item Name 1");
		row.createCell(9).setCellValue("Skimmed Item Warehouse");
		row.createCell(10).setCellValue("Item Name 2");
		
		
		
		int dataRowIndex=1;
		
		for(RPTProcessMilkJournalReportMaster aa:rPTProcessMilkJournalReportMaster2) {
			HSSFRow dataRow=sheet.createRow(dataRowIndex);
			
			dataRow.createCell(0).setCellValue(aa.getId());
			dataRow.createCell(1).setCellValue(aa.getDate());
			dataRow.createCell(2).setCellValue(aa.getTypeOfProcess());
			dataRow.createCell(3).setCellValue(aa.getOutWarehouse());
			dataRow.createCell(4).setCellValue(aa.getMilkItemName());
			dataRow.createCell(5).setCellValue(aa.getProductItemWarehouse());
			dataRow.createCell(6).setCellValue(aa.getProductItemName());
			dataRow.createCell(7).setCellValue(aa.getSmpItemWarehouse());
			dataRow.createCell(8).setCellValue(aa.getItemName1());
			dataRow.createCell(9).setCellValue(aa.getSkimmedItemWarehouse());
			dataRow.createCell(10).setCellValue(aa.getItemName2());
			
			dataRowIndex ++;
		}
		ServletOutputStream ops=res.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();
	}
	
	//Rpt Process Cream Report
	public RptProcessCreamReport saveCreamReport(RptProcessCreamReport rptProcessCreamReport) {
		return rptProcessCreamReportRepo.save(rptProcessCreamReport);
	}

	public List<RptProcessCreamReport> getCreamReport() {
		return rptProcessCreamReportRepo.findAll();
	}

	public List<RptProcessCreamReport> findByDateBetween4(String getfDate, String gettDate) {
		return rptProcessCreamReportRepo.findByDateBetween(getfDate, gettDate);
	}
	
	//Rpt Process By Product Journal Report
	public RptProcessByProductJournalReport saveProductJournalReport(
			RptProcessByProductJournalReport rptProcessByProductJournalReport) {
		return rptProcessByProductJournalReportRepo.save(rptProcessByProductJournalReport);
	}

	public List<RptProcessByProductJournalReport> getprodctJournalReport() {
		return rptProcessByProductJournalReportRepo.findAll();
	}

	public List<RptProcessByProductJournalReport> findproductJournalReportByDate(String getfDate, String gettDate) {
		return rptProcessByProductJournalReportRepo.findByDateBetween(getfDate, gettDate);
	}
}
