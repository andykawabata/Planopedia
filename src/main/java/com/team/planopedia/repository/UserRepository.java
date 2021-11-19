package com.team.planopedia.repository;

import com.team.planopedia.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The existence of this interface exposes the spring JPA's ORM functionality
 * for User object, The methods declared are custom data access function,
 * which are automatically defined by Spring
 * @author andrewkawabata
 */
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByGoogleEmail(String email);
    public User findByUserId(Long id);



}
