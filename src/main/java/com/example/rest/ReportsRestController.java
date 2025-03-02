package com.example.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.request.SearchRequest;
import com.example.response.SearchResponse;
import com.example.service.ReportsService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class ReportsRestController {
	@Autowired
	private ReportsService reportsService;

	@GetMapping("/planNames")
	public ResponseEntity<List<String>> getPlanNames(){
		List<String> uniquePlanNames = reportsService.getUniquePlanNames();
		return new ResponseEntity<>(uniquePlanNames,HttpStatus.OK);
	}
	@GetMapping("/planStatuses")
	public ResponseEntity<List<String>> getPlanStatuses(){
		List<String> uniquePlanNames = reportsService.getUniquePlanStatus();
		return new ResponseEntity<>(uniquePlanNames,HttpStatus.OK);
	}
	@PostMapping("/search")
	public ResponseEntity<List<SearchResponse>> search(SearchRequest request){
		List<SearchResponse> search = reportsService.search(request);
		return new ResponseEntity<>(search,HttpStatus.OK);
	}
	@GetMapping("/excel")
	public void excelReport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment;fileName=data.xls";
		response.setHeader(headerKey, headerValue);
		reportsService.generateExcel(response);
	}
	@GetMapping("/pdf")
	public void pdfReport(HttpServletResponse response)  throws Exception{
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment;fileName=data.pdf";
		response.setHeader(headerKey, headerValue);
		reportsService.generatePdf(response);
	}
	
	
	
	
}
