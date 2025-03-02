package com.example.service;

import java.io.IOException;
import java.util.List;

import com.example.request.SearchRequest;
import com.example.response.SearchResponse;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportsService {
	
	public List<String> getUniquePlanNames();

	public List<String> getUniquePlanStatus();
	public List<SearchResponse> search(SearchRequest request);
	public void generateExcel(HttpServletResponse response) throws RuntimeException, IOException;
	//any thing is fine(both the methods -15 &18);
//	public HttpServletResponse generateExcel();
	public void generatePdf(HttpServletResponse response) throws Exception;
}
