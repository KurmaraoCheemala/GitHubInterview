package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.entity.EligibilityDetails;

@Repository
public interface EligibilityDetailsRepo extends JpaRepository<EligibilityDetails, Integer> {

	@Query("select distint PlanName from EligibilityDetails")
	public List<String> getUniquePlanNames();
	@Query("select distint PlanStatus from EligibilityDetails")
	public List<String> getUniquePlanStatus();
}
