package com.team.planopedia.repository;

import com.team.planopedia.dao.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The existence of this interface exposes the spring JPA's ORM functionality
 * for Plan object, The method declared is custom data access function,
 * Which is automatically defined by Spring
 * @author andrewkawabata
 */
public interface PlanRepository extends JpaRepository<Plan,String> {
    public Plan findByPlanId(Long id);
}
