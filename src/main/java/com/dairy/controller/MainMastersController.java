package com.dairy.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.Response;
import com.dairy.excelclass.AccountMasterExcel;
import com.dairy.model.mainMaster.AccountMaster;
import com.dairy.model.mainMaster.CustomerMaster;
import com.dairy.model.mainMaster.EmployeeMaster;
import com.dairy.model.mainMaster.HSNCodeMaster;
import com.dairy.model.mainMaster.ItemMaster;
import com.dairy.model.mainMaster.OpeningStock;
import com.dairy.model.mainMaster.SupplierMaster;
import com.dairy.model.mainMaster.TCSCodeMaster;
import com.dairy.model.mainMaster.TDSCodeMaster;
import com.dairy.repository.mainMaster.AccountMasterRepo;
import com.dairy.repository.mainMaster.CustomerMasterRepo;
import com.dairy.repository.mainMaster.EmployeeMasterRepo;
import com.dairy.repository.mainMaster.HSNCodeMasterRepo;
import com.dairy.repository.mainMaster.ItemMasterRepo;
import com.dairy.repository.mainMaster.OpeningStockRepo;
import com.dairy.repository.mainMaster.SupplierMasterRepo;
import com.dairy.repository.mainMaster.TCSCodeMasterRepo;
import com.dairy.repository.mainMaster.TDSCodeMasterRepo;
import com.dairy.service.MainMastersService;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MainMastersController {

	@Autowired
	private MainMastersService mainMastersService;

	@Autowired
	private AccountMasterRepo accountMasterRepo;

	@Autowired
	private CustomerMasterRepo customerMasterRepo;

	@Autowired
	private SupplierMasterRepo supplierMasterRepo;

	@Autowired
	private EmployeeMasterRepo employeeMasterRepo;

	@Autowired
	private ItemMasterRepo itemMasterRepo;

	@Autowired
	private OpeningStockRepo openingStockRepo;

	@Autowired
	private HSNCodeMasterRepo hsnCodeMasterRepo;

	@Autowired
	private TDSCodeMasterRepo tdsCodeMasterRepo;

	@Autowired
	private TCSCodeMasterRepo tcsCodeMasterRepo;

//ACCOUNT MASTER	
	// save Account Master
	@PostMapping("/saveAccountMaster")
	@ResponseBody
	public Response saveAccountMaster(@RequestBody AccountMaster accountMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		AccountMaster accountMaster2 = mainMastersService.saveAccountMaster(accountMaster);
		if (accountMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data saved..!!");
			response.setData(accountMaster2);
		}
		return response;
	}

	// Retrieve All Data
	@GetMapping("/getAllAccountMasterData")
	@ResponseBody
	public List<AccountMaster> getAllAccountMasterData() {
		return mainMastersService.findAllAccountMaster();
	}

	// Delete Account Master Data
	@PostMapping("/deleteAccountMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteAccountMasterById(@RequestBody AccountMaster accountMaster) {

		int i = accountMasterRepo.deleteByid(accountMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}
	
	@GetMapping("/accountExcel")
    public void accountExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<AccountMaster> listUsers = mainMastersService.listAll();
         
        AccountMasterExcel accountMasterExcel = new AccountMasterExcel(listUsers);
         
        accountMasterExcel.export(response);    
    }  
	
	
	@GetMapping(value = "/print", produces = MediaType.APPLICATION_PDF_VALUE)
    public void printPdf(HttpServletResponse response) {
        try {
            Document document = new Document(PageSize.A4);
            
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setHeadersInEvent(true);

            // Add table headers
            addTableHeader(table);

            // Fetch data from the database
            List<AccountMaster> data = mainMastersService.fetchData();

            // Add data rows to the table
            addTableData(table, data);

            // Add the table to the document
            document.add(table);

            // Close the document
            document.close();

            // Set the response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=print.pdf");

            // Flush the response
            response.flushBuffer();
        } catch (Exception e) {
            // Handle any exceptions that occur during PDF generation
            e.printStackTrace();
            // You may return an appropriate error message or throw an exception here
        }
    }

    private void addTableHeader(PdfPTable table) {
        // Add table header cells
        table.addCell("Accout Name");
        table.addCell("Status");
        table.addCell("GST");
        table.addCell("PAN");
        table.addCell("AADHAR");
    }

    private void addTableData(PdfPTable table, List<AccountMaster> data) {
        // Add data rows to the table
        for (AccountMaster record : data) {
            table.addCell(record.getAccountName());
            table.addCell(record.getStatus());
            table.addCell(record.getGstNo());
            table.addCell(record.getPanCardNo());
            table.addCell(record.getAadharcardNo());
        }
    }

//Customer Master
	// save Customer Master
	@PostMapping("/saveCustomerMaster")
	@ResponseBody
	public Response saveCustomerMaster(@RequestBody CustomerMaster customerMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		CustomerMaster customerMaster2 = mainMastersService.saveCustomerMaster(customerMaster);
		if (customerMaster2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved..!!");
		response.setData(customerMaster2);

		return response;
	}

	// Retrieve All Data from Customer Master
	@GetMapping("/getAllCustomerMasterData")
	@ResponseBody
	public List<CustomerMaster> getAllCustomerMasterData() {
		return mainMastersService.findAllCustomerMaster();
	}

	// Delete Customer Master Data
	@PostMapping("/deleteCustomerDataById")
	@ResponseBody
	public ResponseEntity<String> deleteCustomerDataById(@RequestBody CustomerMaster customerMaster) {

		int i = customerMasterRepo.deleteByid(customerMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

//SUPPLIER MASTER
	// Save Supplier Master
	@PostMapping("/saveSupplierMaster")
	@ResponseBody
	public Response saveSupplierMaster(@RequestBody SupplierMaster supplierMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		SupplierMaster supplierMaster2 = mainMastersService.saveSupplierMaster(supplierMaster);
		if (supplierMaster2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved..!!");
		response.setData(supplierMaster2);

		return response;
	}

	// retrieve all Data from Supplier master
	@GetMapping("/getAllSupplierData")
	@ResponseBody
	public List<SupplierMaster> getAllSupplierData() {
		return mainMastersService.findAllSupplierMaster();
	}

	// Delete Supplier Data by Id

	@PostMapping("/deleteSupplierDataById")
	@ResponseBody
	public ResponseEntity<String> deleteSupplierDataById(@RequestBody SupplierMaster supplierMaster) {

		int i = supplierMasterRepo.deleteByid(supplierMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

//	EMPLOYEE MASTER
	// Save Employee Master
	@PostMapping("/saveEmployeeMaster")
	@ResponseBody
	public Response saveEmployeeMaster(@RequestBody EmployeeMaster employeeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		EmployeeMaster employeeMaster2 = mainMastersService.saveEmployeeMaster(employeeMaster);
		if (employeeMaster2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved..!!");
		response.setData(employeeMaster2);

		return response;
	}

	// retrieve all Data from Employee master
	@GetMapping("/getAllEmployeeData")
	@ResponseBody
	public List<EmployeeMaster> getAllEmployeeData() {
		return mainMastersService.findAllEmployeeMaster();
	}

	// Delete Employee DAta BY ID
	@PostMapping("/deleteEmployeeDataById")
	@ResponseBody
	public ResponseEntity<String> deleteEmployeeDataById(@RequestBody EmployeeMaster employeeMaster) {

		int i = employeeMasterRepo.deleteByid(employeeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

//ITEM MASTER 

	// Save Item Master
	@PostMapping("/saveItemMaster")
	@ResponseBody
	public Response saveItemMaster(@RequestBody ItemMaster itemMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!!");

		ItemMaster itemMaster2 = mainMastersService.saveItemMaster(itemMaster);
		if (itemMaster2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved..!!");
		response.setData(itemMaster2);

		return response;
	}

	// Retrieve All Data From Item Master
	@GetMapping("/getItemMasterAllData")
	@ResponseBody
	public List<ItemMaster> getItemMasterAllData() {
		return mainMastersService.findAllItemMaster();
	}

	// Delete Item Master Data By Id
	@PostMapping("/deleteItemMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteItemMasterById(@RequestBody ItemMaster itemMaster) {

		int i = itemMasterRepo.deleteByid(itemMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

//OPENING STOCK
	// Save Opening Stock
	@PostMapping("/saveOpeningStock")
	@ResponseBody
	public Response<String> saveOpeningStock(@RequestBody OpeningStock openingStock) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not saved..!!");

		OpeningStock openingStock2 = mainMastersService.saveOpeningStock(openingStock);
		if (openingStock2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved Successfully..!!");
		response.setData(openingStock2);

		return response;
	}

	// Retrieve All Data from Opening Stock
	@GetMapping("/getAllOpeingStockData")
	@ResponseBody
	public List<OpeningStock> getAllOpeingStockData() {
		return mainMastersService.findAllOpeningStock();
	}

	// Delete Opening Stock by Id
	@PostMapping("/deleteOpeningStockById")
	@ResponseBody
	public ResponseEntity<String> deleteOpeningStockById(@RequestBody OpeningStock openingStock) {

		int i = openingStockRepo.deleteByid(openingStock.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

//HSN Code Master
	// Save HSN Code Master
	@PostMapping("/saveHSNCodeMaster")
	@ResponseBody
	public Response<String> saveHSNCodeMaster(@RequestBody HSNCodeMaster hsnCodeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not saved..!!");

		HSNCodeMaster hsnCodeMaster2 = mainMastersService.saveHSNCodeMaster(hsnCodeMaster);
		if (hsnCodeMaster2 != null)
			response.setStatus("Success..");
		response.setMessage("Data Saved Successfully..!!");
		response.setData(hsnCodeMaster2);

		return response;
	}

	// Retrieve All Data from HSN Code Master
	@GetMapping("/getAllHSNCodeMasterData")
	@ResponseBody
	public List<HSNCodeMaster> getAllHSNCodeMasterData() {
		return mainMastersService.findAllHSN();
	}

	// Delete HSN Code Master by Id
	@PostMapping("/deleteHSNCodeMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteHSNCodeMasterById(@RequestBody HSNCodeMaster hsnCodeMaster) {

		int i = hsnCodeMasterRepo.deleteByid(hsnCodeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

// TDS CODE MASTER
	// save TDS Code Master
	@PostMapping("/saveTDSCodeMaster")
	@ResponseBody
	public Response saveTDSCodeMaster(@RequestBody TDSCodeMaster tdsCodeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		TDSCodeMaster tdsCodeMaster2 = mainMastersService.saveTDSCodeMaster(tdsCodeMaster);
		if (tdsCodeMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data saved..!!");
			response.setData(tdsCodeMaster2);
		}
		return response;
	}

	// Retrieve All Data from TDS Code Master
	@GetMapping("/getAllTDSCodeMasterData")
	@ResponseBody
	public List<TDSCodeMaster> getAllTDSCodeMasterData() {
		return mainMastersService.findAllTDS();
	}

	// Delete TDS Code Master by Id
	@PostMapping("/deleteTDSCodeMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteTDSCodeMasterById(@RequestBody TDSCodeMaster tdsCodeMaster) {

		int i = tdsCodeMasterRepo.deleteByid(tdsCodeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

// TCS CODE MASTER
	// save TDS Code Master
	@PostMapping("/saveTCSCodeMaster")
	@ResponseBody
	public Response saveTCSCodeMaster(@RequestBody TCSCodeMaster tcsCodeMaster) {

		Response response = new Response();
		response.setStatus("Not Success..");
		response.setMessage("Data Not Saved..!!");

		TCSCodeMaster tcsCodeMaster2 = mainMastersService.saveTCSCodeMaster(tcsCodeMaster);
		if (tcsCodeMaster2 != null) {
			response.setStatus("Success..");
			response.setMessage("Data saved..!!");
			response.setData(tcsCodeMaster2);
		}
		return response;
	}

	// Retrieve All Data from TDS Code Master
	@GetMapping("/getAllTCSCodeMasterData")
	@ResponseBody
	public List<TCSCodeMaster> getAllTCSCodeMasterData() {
		return mainMastersService.findAllTCS();
	}

	// Delete TDS Code Master by Id
	@PostMapping("/deleteTCSCodeMasterById")
	@ResponseBody
	public ResponseEntity<String> deleteTCSCodeMasterById(@RequestBody TCSCodeMaster tcsCodeMaster) {

		int i = tcsCodeMasterRepo.deleteByid(tcsCodeMaster.getId());
		if (i > 0) {
			return ResponseEntity.ok("Data Deleted Successfully..!!");
		} else {
			return ResponseEntity.badRequest().body("Data Not Deleted..!!");
		}
	}

}
