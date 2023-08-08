package com.dairy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dairy.dto.LoginResponse;
import com.dairy.dto.Response;
import com.dairy.model.LogIn;
import com.dairy.model.LoginRequest;
import com.dairy.repository.LogInRepo;
import com.dairy.service.LogInService;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

@Controller
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:")
public class LogInController {

	@Autowired
	private LogInService logInService;
	
	@Autowired
	private LogInRepo logInRepo ;
//
//	@PostMapping("/login")
//	@ResponseBody
//	public ResponseEntity<?> login(@RequestBody LogIn logIn,HttpSession session) {
//		LogIn user = logInService.findByUsername(logIn.getUsername());
//		if (user != null && user.getPassword().equals(logIn.getPassword())) {
//			return ResponseEntity.ok(user);
//		}
//		session.setAttribute("ID", user.getId());
//		System.out.println("" + user.getId());
//		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//	}
//}


//	@PostMapping("/login")
//	public LoginResponse login(@RequestBody LoginRequest loginRequest) {
//		String username = loginRequest.getUsername();
//		String password = loginRequest.getPassword();
//
//		LogIn logIn = logInRepo.findByusername(username);
//
//		LoginResponse response = new LoginResponse();
//
//		if (logIn != null && logIn.getPassword().equals(password)) {
//			response.setMessage("Login successful");
//			response.setSuccess(true);
//		} else {
//			response.setMessage("Invalid username or password");
//			response.setSuccess(false);
//		}
//
//		return response;
//	}

//	@PostMapping("/LogIn")
//	@ResponseBody
//	public Response retrieveLogInDetails(@RequestBody LogIn login) {
//		Response response = new Response();
//		response.setStatus("Fail");
//		response.setMessage("No data found");
//
//		LogIn logIn2 = logInService.findByUserNameAndPassword(login.getUsername(), login.getPassword());
//		if (logIn2 != null) {
//			response.setStatus("Success");
//			response.setMessage("Login Data");
//			response.setData(logIn2);
//		}
//		return response;
//	}
//	
	
}


