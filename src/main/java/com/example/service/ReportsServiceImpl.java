package com.example.service;

import java.awt.Color;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.entity.EligibilityDetails;
import com.example.repo.EligibilityDetailsRepo;
import com.example.request.SearchRequest;
import com.example.response.SearchResponse;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPTableEvent;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private EligibilityDetailsRepo eligRepo;
	@Override
	public List<String> getUniquePlanNames() {
		// TODO Auto-generated method stub
		return eligRepo.getUniquePlanNames();
	}

	@Override
	public List<String> getUniquePlanStatus() {
		// TODO Auto-generated method stub
		return eligRepo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		// TODO Auto-generated method stub
		List<EligibilityDetails> entities = eligRepo.findAll();
		List<SearchResponse> response=new ArrayList<>();
		
		EligibilityDetails queryBuilder=new EligibilityDetails();
		String planName = request.getPlanName();
		if(planName!=null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals("")) {
			queryBuilder.setPlanStatus(planStatus);
		}
		LocalDate planStartDate = request.getPlanStartDate();
		if(planStartDate!=null && !planStartDate.equals("")) {
			queryBuilder.setPlanStartDate(planStartDate);
		}
		LocalDate planEndDate = request.getPlanEndDate();
		if(planEndDate!=null && !planEndDate.equals("")) {
			queryBuilder.setPlanEndDate(planEndDate);
		}
		Example<EligibilityDetails> of = Example.of(queryBuilder);
		for(EligibilityDetails entity: entities) {
			SearchResponse sr=new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}
		return response;
		
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws IOException{
		// TODO Auto-generated method stub


		
		List<EligibilityDetails> entities = eligRepo.findAll();
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("SI.no");
		row.createCell(1).setCellValue("NAME");
		row.createCell(2).setCellValue("MOBILE");
		row.createCell(5).setCellValue("GENDER");
		row.createCell(4).setCellValue("SSN");
		int i=0;
		for(EligibilityDetails entity :entities){
			
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getNumber());
			dataRow.createCell(2).setCellValue(String.valueOf(entity.getGender()));
			dataRow.createCell(3).setCellValue(entity.getSsn());
			i++;
		}
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
	}
	

	@Override
	public void generatePdf(HttpServletResponse response) throws RuntimeException, IOException   {
		// TODO Auto-generated method stub
//		https://www.codejava.net/frameworks/spring-boot/pdf-export-example
		
		List<EligibilityDetails> entities = eligRepo.findAll();
		Document doc=new Document(PageSize.A4);
		
		PdfWriter.getInstance(doc, response.getOutputStream());
		doc.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);
		
		Paragraph p=new Paragraph("Search Report",font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		
		doc.add(p);
		
		PdfPTable table=new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] {1.5f, 3.0f});
		table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("NAME", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("PHNO", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("GENDER", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("SSN", font));
        table.addCell(cell);  
        
        for(EligibilityDetails entity:entities) {
        	table.addCell(entity.getName());
        	table.addCell(entity.getEmail());
        	table.addCell(String.valueOf(entity.getNumber()));
        	table.addCell(String.valueOf(entity.getGender()));
        	table.addCell(String.valueOf(entity.getSsn()));
        	
        	
        }
        doc.add(table);
        doc.close();
		
		
		
	}


}
