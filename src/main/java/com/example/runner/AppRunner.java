package com.example.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.entity.EligibilityDetails;
import com.example.repo.EligibilityDetailsRepo;
@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private EligibilityDetailsRepo repo;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		EligibilityDetails ed=new EligibilityDetails();
		ed.setEligId(1);
		ed.setEmail("kurma@gmail.com");
		ed.setGender('M');
		ed.setName("kurnma");
		ed.setNumber(647382L);
		repo.save(ed);
		
		EligibilityDetails ed1=new EligibilityDetails();
		ed1.setEligId(2);
		ed1.setEmail("vansmsi@gmail.com");
		ed1.setGender('M');
		ed1.setName("vamsi");
		ed1.setNumber(7798765L);
		repo.save(ed1);
		
		EligibilityDetails ed2=new EligibilityDetails();
		ed2.setEligId(3);
		ed2.setEmail("mahesh@gmail.com");
		ed2.setGender('F');
		ed2.setName("mahesh");
		ed2.setNumber(8971L);
		repo.save(ed2);
	}
	

}
