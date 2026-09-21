package com.demo.demoappwithdocker.repository;

import com.demo.demoappwithdocker.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer> {
}
