package com.team.planopedia.repository;

import com.team.planopedia.dao.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan,String> {
    
}
