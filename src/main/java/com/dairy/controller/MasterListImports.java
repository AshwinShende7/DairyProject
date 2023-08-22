package com.dairy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dairy.service.MasterListImportsService;


@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class MasterListImports  {
	
	@Autowired
	private MasterListImportsService masterListImportsService ;
	
	@GetMapping("/getAllModels")
    public ResponseEntity<List<Object>> getAllModels(@RequestParam("type") String type) {
        List<Object> models = new ArrayList<>();

        if ("CustomerMaster".equals(type)) {
            models.addAll(masterListImportsService.getAllCustomerMaster());
        } else if ("SupplierMaster".equals(type)) {
            models.addAll(masterListImportsService.getAllSupplierMaster());
        }  else if("EmployeeMaster" .equals(type)) {
        	models.addAll(masterListImportsService.getAllEmployeeMaster());
        }else if("AccountMaster" .equals(type)) {
        	models.addAll(masterListImportsService.getAllAccountMaster());
        }else if("ItemMaster" .equals(type)) {
        	models.addAll(masterListImportsService.getAllItemMaster());
        }
        else {
            // Handle invalid or missing type parameter
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(models);
    }
}
