package com.team.planopedia.repository;

import com.team.planopedia.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByGoogleEmail(String email);
    public User findByUserId(Long id);



}
